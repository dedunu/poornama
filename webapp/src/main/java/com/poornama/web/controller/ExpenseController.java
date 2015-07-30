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
 * Created by dedunu on 7/29/15.
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

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("tagValueJS", tagLogic.getTagValueJS());
        model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
        log.debug("[" + className + "] createForm()");
        return "expense/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createExpense(Model model, HttpServletRequest request) {
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
    }

    @RequestMapping(value = "edit/{expenseId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("expenseId") String expenseId) {
        Expense expense;
        expense = expenseLogic.getExpense(expenseId);

        if (expense == null) {
            log.error("[" + className + "] editForm: retrieving Expense failed");
        }

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date expenseDate = expense.getDate();

        model.addAttribute("expenseId", expense.getId());
        model.addAttribute("expenseDate", dateFormat.format(expenseDate));
        model.addAttribute("tagValue", tagLogic.getTagValue(expense.getTags()));
        model.addAttribute("description", expense.getDescription());
        model.addAttribute("amount", expense.getAmount().toBigInteger().toString());
        model.addAttribute("tagValueJS", tagLogic.getTagValueJS());
        model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
        return "expense/edit";
    }

    @RequestMapping(value = "edit/{expenseId}", method = RequestMethod.POST)
    public String editExpense(Model model, @PathVariable("expenseId") String expenseId, HttpServletRequest request) {
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
    }

    @RequestMapping(value = "delete/{expenseId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("expenseId") String expenseId) {
        Expense expense;
        expense = expenseLogic.getExpense(expenseId);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date expenseDate = expense.getDate();
        model.addAttribute("expenseId", expense.getId());
        model.addAttribute("expenseDate", dateFormat.format(expenseDate));
        model.addAttribute("tagValue", tagLogic.getTagValue(expense.getTags()));
        model.addAttribute("description", expense.getDescription());
        model.addAttribute("amount", expense.getAmount().toString());
        model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
        return "expense/delete";
    }


    @RequestMapping(value = "delete/{expenseId}", method = RequestMethod.POST)
    public String deleteExpense(Model model, @PathVariable("expenseId") String expenseId) {
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
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        String table = expenseLogic.getExpenseTable("");
        model.addAttribute("table", table);
        model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
        log.debug("[" + className + "] searchForm()");
        return "expense/search";
    }

    @RequestMapping(value = "search/{expenseId}", method = RequestMethod.POST)
    public void searchAJAX(@PathVariable("expenseId") String expenseId, HttpServletResponse response) throws IOException {
        String table = expenseLogic.getExpenseTable(expenseId);
        response.getWriter().print(table);
        log.debug("[" + className + "] searchAJAX()");
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public void searchAJAXAll(HttpServletResponse response) throws IOException {
        searchAJAX("", response);
        log.debug("[" + className + "] searchAJAXAll()");
    }
}
