package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Tag;
import com.poornama.data.dao.TagDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by dedunu on 7/29/15.
 */
@Service
public class TagLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = TagLogic.class.getName();

    public String getTagValueJS() {
        String result = "";

        TagDAO tagDAO = new TagDAO();

        List<Tag> tagList = tagDAO.getAll();

        for (Tag tag : tagList) {
            result = result + " \'" + tag.getDisplayName() + "\',";
        }

        if (result.length() > 0 && result.charAt(result.length()-1)==',') {
            result = result.substring(0, result.length()-1);
        }

        return result;
    }

    public String getTagValue(Set<Tag> tagList) {
        String result = "";

        for (Tag tag : tagList) {
            result = result + tag.getDisplayName() + ",";
        }

        if (result.length() > 0 && result.charAt(result.length()-1)==',') {
            result = result.substring(0, result.length()-1);
        }

        return result;
    }

    public String getTagValue(List<Tag> tagList) {
        String result = "";

        for (Tag tag : tagList) {
            result = result + tag.getDisplayName() + ",";
        }

        if (result.length() > 0 && result.charAt(result.length()-1)==',') {
            result = result.substring(0, result.length()-1);
        }

        return result;
    }
}
