package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.BankAccountType;

public class BankAccountTypeDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = BankAccountTypeDAO.class.getName();

	public BankAccountTypeDAO() {
		log.debug("[" + className + "] BankAccountTypeDAO: constructor()");
	}

	public void create(BankAccountType bankAccountType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(bankAccountType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(BankAccountType bankAccountType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(bankAccountType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(BankAccountType bankAccountType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(bankAccountType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public BankAccountType getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		BankAccountType bankAccountType = (BankAccountType) databaseSession
				.getById(BankAccountType.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return bankAccountType;
	}

	@SuppressWarnings("unchecked")
	public List<BankAccountType> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<BankAccountType> bankAccountTypeList = databaseSession
				.getAll(BankAccountType.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return bankAccountTypeList;
	}
}
