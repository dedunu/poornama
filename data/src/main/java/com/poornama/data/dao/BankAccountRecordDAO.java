package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.BankAccountRecord;

public class BankAccountRecordDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = BankAccountRecordDAO.class.getName();

	public BankAccountRecordDAO() {
		log.debug("[" + className + "] BankAccountRecordDAO: constructor()");
	}

	public void create(BankAccountRecord bankAccountRecord) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(bankAccountRecord);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(BankAccountRecord bankAccountRecord) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(bankAccountRecord);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(BankAccountRecord bankAccountRecord) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(bankAccountRecord);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public BankAccountRecord getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		BankAccountRecord bankAccountRecord = (BankAccountRecord) databaseSession
				.getById(BankAccountRecord.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return bankAccountRecord;
	}

	@SuppressWarnings("unchecked")
	public List<BankAccountRecord> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<BankAccountRecord> bankAccountRecordList = databaseSession
				.getAll(BankAccountRecord.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return bankAccountRecordList;
	}
}
