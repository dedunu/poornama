package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.BankAccount;

public class BankAccountDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = BankAccountDAO.class.getName();

	public BankAccountDAO() {
		log.debug("[" + className + "] BankAccountDAO: constructor()");
	}

	public void create(BankAccount bankAccount) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(bankAccount);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(BankAccount bankAccount) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(bankAccount);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(BankAccount bankAccount) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(bankAccount);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public BankAccount getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		BankAccount bankAccount = (BankAccount) databaseSession.getById(
				BankAccount.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return bankAccount;
	}

	@SuppressWarnings("unchecked")
	public List<BankAccount> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<BankAccount> bankAccountList = databaseSession
				.getAll(BankAccount.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return bankAccountList;
	}
}
