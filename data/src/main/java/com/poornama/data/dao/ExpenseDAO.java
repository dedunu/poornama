package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Expense;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * @author dedunu
 */
public class ExpenseDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ExpenseDAO.class.getName();

    public ExpenseDAO() {
        log.debug("[" + className + "] ExpenseDAO: constructor()");
    }

    public void create(Expense expense) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(expense);
        log.debug("[" + className + "] create()");
    }

    public void delete(Expense expense) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(expense);
        log.debug("[" + className + "] delete()");
    }

    public void update(Expense expense) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(expense);
        log.debug("[" + className + "] update()");
    }

    public Expense getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Expense expense = (Expense) databaseSession.getById(Expense.class, id);
        log.debug("[" + className + "] getById()");
        return expense;
    }

    public Expense getById(String id) {
        int expenseId = 0;
        try {
            expenseId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(expenseId);
    }

    public List<Expense> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Expense.class);
        criteria.addOrder(Order.asc("id"));
        List<Expense> expenseList = criteria.list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return expenseList;
    }
}
