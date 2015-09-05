package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Expense;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.ExpenseLogic;
import com.poornama.logic.object.TagLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dedunu
 */
@Controller
@RequestMapping("/expense/")
public class ExpenseController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ExpenseController.class.getName();

    @Autowired
    TagLogic tagLogic;

    @Autowired
    ExpenseLogic expenseLogic;

    /**
     * Returns the create form for the expense entity
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        try {
            model.addAttribute("tagValueJS", tagLogic.getTagValueJS());
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            log.debug("[" + className + "] createForm()");
            return "expense/create";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Creates the expense and shows the notification
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createExpense(Model model, HttpServletRequest request) {
        try {
            Notification notification = expenseLogic.createExpense(request);
            model.addAttribute("message", notification.getMessage());
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            if (notification.getNotificationType() == NotificationType.DANGER) {
                log.error("[" + className + "] createExpense: failed");
                return "notify/danger";
            }
            if (notification.getNotificationType() == NotificationType.SUCCESS) {
                log.info("[" + className + "] createExpense: success");
                return "notify/success";
            }
            log.fatal("[" + className + "] createExpense: cannot reach this phrase");
            return "redirect:/";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Returns the edit form for expense entity
     *
     * @param model     Model
     * @param expenseId String
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{expenseId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("expenseId") String expenseId) {
        try {
            Expense expense;
            expense = expenseLogic.getExpense(expenseId);

            if (expense == null) {
                log.error("[" + className + "] editForm: retrieving Expense failed");
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date expenseDate = expense.getDate();

            model.addAttribute("expenseId", expense.getId());
            model.addAttribute("expenseDate", dateFormat.format(expenseDate));
            model.addAttribute("tagValue", tagLogic.getTagValue(expense.getTags()));
            model.addAttribute("description", expense.getDescription());
            model.addAttribute("amount", expense.getAmount().toBigInteger().toString());
            model.addAttribute("tagValueJS", tagLogic.getTagValueJS());
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            return "expense/edit";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Edits the expense and show the notification
     *
     * @param model     Model
     * @param expenseId String
     * @param request   HttpServletResponse
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{expenseId}", method = RequestMethod.POST)
    public String editExpense(Model model, @PathVariable("expenseId") String expenseId, HttpServletRequest request) {
        try {
            Notification notification = expenseLogic.editExpense(request, expenseId);
            log.debug("[" + className + "] editExpense()");
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            model.addAttribute("message", notification.getMessage());
            if (notification.getNotificationType() == NotificationType.DANGER) {
                log.error("[" + className + "] editExpense: failed");
                return "notify/danger";
            }
            if (notification.getNotificationType() == NotificationType.SUCCESS) {
                log.info("[" + className + "] editExpense: success");
                return "notify/success";
            }
            log.fatal("[" + className + "] editExpense: cannot reach this phrase");
            return "redirect:/";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Show the delete confirmation form for the expense entity
     *
     * @param model     Model
     * @param expenseId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{expenseId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("expenseId") String expenseId) {
        try {
            Expense expense;
            expense = expenseLogic.getExpense(expenseId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date expenseDate = expense.getDate();
            model.addAttribute("expenseId", expense.getId());
            model.addAttribute("expenseDate", dateFormat.format(expenseDate));
            model.addAttribute("tagValue", tagLogic.getTagValue(expense.getTags()));
            model.addAttribute("description", expense.getDescription());
            model.addAttribute("amount", expense.getAmount().toString());
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            return "expense/delete";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Deletes the employee and show the notification
     *
     * @param model     Model
     * @param expenseId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{expenseId}", method = RequestMethod.POST)
    public String deleteExpense(Model model, @PathVariable("expenseId") String expenseId) {
        try {
            Notification notification = expenseLogic.deleteExpense(expenseId);
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            switch (notification.getNotificationType()) {
                case DANGER:
                    model.addAttribute("message", notification.getMessage());
                    log.error("[" + className + "] deleteExpense: error in deleting Expense");
                    return "notify/danger";
                case SUCCESS:
                    model.addAttribute("message", notification.getMessage());
                    log.info("[" + className + "] deleteExpense: deleted Expense successfully");
                    return "notify/success";
                default:
                    model.addAttribute("message", "Something went wrong. Please contact developer.");
                    log.error("[" + className + "] deleteExpense: fatal error in deleting Expense");
                    return "notify/danger";
            }
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Shows the search form for the expense entity
     *
     * @param model Model
     * @return view path as a String
     * @throws IOException
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        try {
            String table = expenseLogic.getExpenseTable("");
            model.addAttribute("table", table);
            model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
            log.debug("[" + className + "] searchForm()");
            return "expense/search";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Returns HTML table for the search page depending the search query
     *
     * @param expenseId String
     * @param response  HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "search/{expenseId}", method = RequestMethod.POST)
    public void searchAJAX(@PathVariable("expenseId") String expenseId, HttpServletResponse response) throws IOException {
        try {
            String table = expenseLogic.getExpenseTable(expenseId);
            response.getWriter().print(table);
            log.debug("[" + className + "] searchAJAX()");
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
        }
    }

    /**
     * Returns HTML table for the search page
     *
     * @param response HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public void searchAJAXAll(HttpServletResponse response) throws IOException {
        try {
            searchAJAX("", response);
            log.debug("[" + className + "] searchAJAXAll()");
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
        }
    }
}
