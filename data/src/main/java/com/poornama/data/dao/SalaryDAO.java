package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Salary;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * @author dedunu
 */
public class SalaryDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = SalaryDAO.class.getName();

	public SalaryDAO() {
		log.debug("[" + className + "] SalaryDAO: constructor()");
	}

	public void create(Salary salary) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.save(salary);
		log.debug("[" + className + "] create()");
	}

	public void delete(Salary salary) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.delete(salary);
		log.debug("[" + className + "] delete()");
	}

	public void saveOrUpdate(Salary salary) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.saveOrUpdate(salary);
		log.debug("[" + className + "] saveOrUpdate()");
	}

	public void update(Salary salary) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.update(salary);
		log.debug("[" + className + "] update()");
	}

	public Salary getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		Salary salary = (Salary) databaseSession.getById(
				Salary.class, id);
		log.debug("[" + className + "] getById()");
		return salary;
	}

	public Salary getByEmployeeDate(Employee employee, Date date) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Criteria criteria = databaseSession.createCriteria(Salary.class);
		Criterion employeeIdCriterion = Restrictions.eq("employee", employee);
		Criterion dateCriterion = Restrictions.eq("date", date);
		LogicalExpression logicalExpression = Restrictions.and(employeeIdCriterion, dateCriterion);
		criteria.add(logicalExpression);
		Salary salary = (Salary) criteria.uniqueResult();
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getByEmployeeDate()");
		return salary;
	}

	@SuppressWarnings("unchecked")
	public List<Salary> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		List<Salary> salaryList = databaseSession
				.getAll(Salary.class);
		log.debug("[" + className + "] getAll()");
		return salaryList;
	}
}
