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
 * @author dedunu
 */
public class TagDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = TagDAO.class.getName();

    public TagDAO() {
        log.debug("[" + className + "] TagDAO: constructor()");
    }

    public void create(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(tag);
        log.debug("[" + className + "] create()");
    }

    public void delete(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(tag);
        log.debug("[" + className + "] delete()");
    }

    public void update(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(tag);
        log.debug("[" + className + "] update()");
    }

    public Tag getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Tag tag = (Tag) databaseSession.getById(Tag.class, id);
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
