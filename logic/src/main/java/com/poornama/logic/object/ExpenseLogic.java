package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Expense;
import com.poornama.api.objects.Tag;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.ExpenseDAO;
import com.poornama.data.dao.TagDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author dedunu
 */
@Service
public class ExpenseLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ExpenseLogic.class.getName();

    /**
     * Creates the expense using HttpServletRequest and returns Notification object with message
     *
     * @param request HttpServletRequest
     * @return the notification with the message
     */
    public Notification createExpense(HttpServletRequest request) {
        Notification notification = new Notification();
        ExpenseDAO expenseDAO = new ExpenseDAO();
        TagDAO tagDAO = new TagDAO();
        Expense expense = new Expense();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date expenseDate = new Date();
        BigDecimal amount;

        try {
            expenseDate = simpleDateFormat.parse(request.getParameter("expenseDate"));
        } catch (ParseException e) {
            log.error("[" + className + "] createExpense: Error in parsing expenseDate");
        }

        HashSet<String> uniqueStringList = new HashSet<String>();
        HashSet<Tag> tagSet = new HashSet<Tag>();
        String tagStringList = request.getParameter("tagString");
        tagStringList = tagStringList.replace(" ", "");

        if (tagStringList != null) {
            String[] tagStringTokenizeList = tagStringList.split(",");

            for (String tagName : tagStringTokenizeList) {
                uniqueStringList.add(tagName);
            }

            for (String tagName : uniqueStringList) {
                Tag tag = tagDAO.getByDisplayName(tagName);

                if (tag == null) {
                    log.error("tag is null;");
                    Tag createTag = new Tag();
                    createTag.setName(tagName);
                    createTag.setDisplayName(tagName);
                    tagDAO.create(createTag);
                    tag = tagDAO.getByDisplayName(tagName);
                }

                tagSet.add(tag);
            }
        }

        amount = new BigDecimal(request.getParameter("amount"));

        expense.setDate(expenseDate);
        expense.setDescription(request.getParameter("description"));
        expense.setAmount(amount);
        expense.setTags(tagSet);

        try {
            expenseDAO.create(expense);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Expense created successfully.");
            log.info("[" + className + "] createExpense: created Expense");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with creating expense. Please try again.");
            log.error("[" + className + "] createExpense: failed creating expense");
        }

        return notification;
    }

    /**
     * Edit the expense using HttpServletRequest and returns Notification object with message
     *
     * @param request   HttpServletRequest
     * @param expenseId String
     * @return the notification with the message
     */
    public Notification editExpense(HttpServletRequest request, String expenseId) {
        ExpenseDAO expenseDAO = new ExpenseDAO();
        TagDAO tagDAO = new TagDAO();
        Expense expense;
        Notification notification = new Notification();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date expenseDate = new Date();
        BigDecimal amount;

        try {
            expenseDate = simpleDateFormat.parse(request.getParameter("expenseDate"));
        } catch (ParseException e) {
            log.error("[" + className + "] editEmployee: Error in parsing expenseDate");
        }

        HashSet<String> uniqueStringList = new HashSet<String>();
        HashSet<Tag> tagSet = new HashSet<Tag>();
        String tagStringList = request.getParameter("tagString");
        tagStringList = tagStringList.replace(" ", "");

        if (tagStringList != null) {
            String[] tagStringTokenizeList = tagStringList.split(",");

            for (String tagName : tagStringTokenizeList) {
                uniqueStringList.add(tagName);
            }

            for (String tagName : uniqueStringList) {
                Tag tag = tagDAO.getByDisplayName(tagName);

                if (tag == null) {
                    Tag createTag = new Tag();
                    createTag.setName(tagName);
                    createTag.setDisplayName(tagName);
                    tagDAO.create(createTag);
                    tag = tagDAO.getByDisplayName(tagName);
                }

                tagSet.add(tag);
            }
        }

        amount = new BigDecimal(request.getParameter("amount"));

        expense = expenseDAO.getById(expenseId);
        expense.setDate(expenseDate);
        expense.setDescription(request.getParameter("description"));
        expense.setAmount(amount);
        expense.setTags(tagSet);

        try {
            expenseDAO.update(expense);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Expense data updated successfully.");
            log.info("[" + className + "] editExpense: created Expense");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with updating expense. Please try again.");
            log.error("[" + className + "] editExpense: failed updating expense");
        }

        return notification;
    }

    /**
     * Deletes the expense using expenseId and returns the Notification object with message
     *
     * @param expenseId String
     * @return the notification with the message
     */
    public Notification deleteExpense(String expenseId) {
        Notification notification = new Notification();
        ExpenseDAO expenseDAO = new ExpenseDAO();

        try {
            Expense expense = expenseDAO.getById(expenseId);
            expenseDAO.delete(expense);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Deleted expense successfully");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Deleted expense failed. Please try again.");
        }

        return notification;
    }

    /**
     * Returns the expense object from the given expenseId
     *
     * @param expenseId String
     * @return Expense object
     */
    public Expense getExpense(String expenseId) {
        ExpenseDAO expenseDAO = new ExpenseDAO();
        Expense expense;
        try {
            expense = expenseDAO.getById(Integer.parseInt(expenseId));
        } catch (Exception e) {
            log.error("[" + className + "] getExpense: error in retrieving Expense by Id");
            return null;
        }
        return expense;
    }

    /**
     * Returns expense table for controller classes as a HTML table for given searchCriteria
     *
     * @param searchCriteria String
     * @return expense table as a HTML string
     */
    public String getExpenseTable(String searchCriteria) {
        List<Expense> expenseList = new ArrayList<Expense>();
        List<Expense> tempExpenseList;
        HashSet<Integer> uniqueIdList = new HashSet<Integer>();
        ExpenseDAO expenseDAO = new ExpenseDAO();
        TagLogic tagLogic = new TagLogic();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DataTableGenerator dataTableGenerator = new DataTableGenerator();
        String table;

        if (searchCriteria.equals("")) {
            tempExpenseList = expenseDAO.getAll();

            for (Expense expense : tempExpenseList) {
                uniqueIdList.add(expense.getId());
            }

            for (Integer expenseId : uniqueIdList) {
                Expense expense = expenseDAO.getById(expenseId);
                expenseList.add(expense);
            }

        } else {
            expenseList = new ArrayList<Expense>();
            if (expenseDAO.getById(searchCriteria) != null) {
                expenseList.add(expenseDAO.getById(searchCriteria));
            }
        }

        table = dataTableGenerator.getStartTable();
        String dataArray[] = new String[4];
        dataArray[0] = "Transaction ID";
        dataArray[1] = "Date";
        dataArray[2] = "Tags";
        dataArray[3] = "Amount";
        table = table + dataTableGenerator.getTableHeader(dataArray);
        table = table + dataTableGenerator.getStartTableBody();

        for (Expense expense : expenseList) {
            dataArray[0] = String.valueOf(expense.getId());
            dataArray[1] = dateFormat.format(expense.getDate());
            dataArray[2] = tagLogic.getTagValue(expense.getTags());
            dataArray[3] = expense.getAmount().toString();
            table = table + dataTableGenerator.getTableBodyRow(dataArray, "edit/" + expense.getId(), "delete/" + expense.getId());
        }

        table = table + dataTableGenerator.getEndTableBody();
        table = table + dataTableGenerator.getEndTable();
        return table;
    }
}
