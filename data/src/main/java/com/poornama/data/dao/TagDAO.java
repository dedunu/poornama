package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Tag;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * Created by dedunu on 7/28/15.
 */
public class TagDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = TagDAO.class.getName();

    public TagDAO() {
        log.debug("[" + className + "] TagDAO: constructor()");
    }

    public void create(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(tag);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(tag);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void deleteById(int id) {
        Tag tag = getById(id);
        delete(tag);
        log.debug("[" + className + "] deleteById()");
    }

    public void update(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(tag);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public Tag getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Tag tag = (Tag) databaseSession.getById(Tag.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return tag;
    }

    public Tag getById(String id) {
        int tagId;
        Tag tag = null;
        try {
            tagId = Integer.parseInt(id);
            tag = getById(tagId);
        } catch (Exception e) {
            log.debug("[" + className + "] getById(String): failed retrieving tag");
        }
        return tag;
    }

    public Tag getByDisplayName(String displayName) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Tag.class);
        SimpleExpression simpleExpression = Restrictions.eq("displayName", displayName);
        Tag tag = (Tag) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByDisplayName()");
        return tag;
    }

    public List<Tag> searchByDisplayName(String displayName) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Tag.class);
        SimpleExpression simpleExpression = Restrictions.like("displayName", displayName + "%");
        criteria.addOrder(Order.asc("displayName"));
        List<Tag> tagList = criteria.add(simpleExpression).list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] searchByDisplayName()");
        return tagList;
    }

    public List<Tag> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Tag.class);
        criteria.addOrder(Order.asc("displayName"));
        List<Tag> tagList = criteria.list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return tagList;
    }
}
