package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.AccountRecord;

public class AccountRecordDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = AccountRecordDAO.class.getName();

	public AccountRecordDAO() {
		log.debug("[" + className + "] AccountRecordDAO: constructor()");
	}

	public void create(AccountRecord accountRecord) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(accountRecord);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(AccountRecord accountRecord) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(accountRecord);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(AccountRecord accountRecord) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(accountRecord);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public AccountRecord getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		AccountRecord accountRecord = (AccountRecord) databaseSession
				.getById(AccountRecord.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return accountRecord;
	}

	@SuppressWarnings("unchecked")
	public List<AccountRecord> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<AccountRecord> accountRecordList = databaseSession
				.getAll(AccountRecord.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return accountRecordList;
	}
}
