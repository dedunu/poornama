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

    /**
     * Create tag
     *
     * @param tag Tag
     */
    public void create(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(tag);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete tag
     *
     * @param tag Tag
     */
    public void delete(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(tag);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update tag
     *
     * @param tag Tag
     */
    public void update(Tag tag) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(tag);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the tag from the id
     *
     * @param id int
     * @return Tag
     */
    public Tag getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Tag tag = (Tag) databaseSession.getById(Tag.class, id);
        log.debug("[" + className + "] getById()");
        return tag;
    }

    /**
     * Returns the tag from the id
     *
     * @param id String
     * @return Tag
     */
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

    /**
     * Returns the tag from the display name
     *
     * @param displayName String
     * @return Tag
     */
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

    /**
     * Return all the tags
     *
     * @return List&lt;Tag&gt;
     */
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
