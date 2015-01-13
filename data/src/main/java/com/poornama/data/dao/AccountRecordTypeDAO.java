package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.AccountRecordType;

public class AccountRecordTypeDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = AccountRecordTypeDAO.class.getName();

	public AccountRecordTypeDAO() {
		log.debug("[" + className + "] AccountRecordTypeDAO: constructor()");
	}

	public void create(AccountRecordType accountRecordType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(accountRecordType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(AccountRecordType accountRecordType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(accountRecordType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(AccountRecordType accountRecordType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(accountRecordType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public AccountRecordType getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		AccountRecordType accountRecordType = (AccountRecordType) databaseSession
				.getById(AccountRecordType.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return accountRecordType;
	}

	@SuppressWarnings("unchecked")
	public List<AccountRecordType> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<AccountRecordType> accountRecordTypeList = databaseSession
				.getAll(AccountRecordType.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return accountRecordTypeList;
	}
}