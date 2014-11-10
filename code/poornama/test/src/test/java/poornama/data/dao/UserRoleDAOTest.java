package poornama.data.dao;

import com.poornama.api.objects.UserRole;
import com.poornama.data.dao.UserRoleDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by dedunu on 10/23/14.
 */
public class UserRoleDAOTest {

    private UserRoleDAO userRoleDAO = new UserRoleDAO();

    @Test
    public void createUserRole() {
        UserRole userRole = new UserRole();
        Random random = new Random();
        int value = random.nextInt(10000);
        userRole.setName("test" + value);
        userRole.setDisplayName("Temp" + value);
        userRoleDAO.create(userRole);
        Assert.assertTrue(true);
    }

    @Test
    public void deleteUser() {
        UserRole userRole = new UserRole();
        Random random = new Random();
        int value = random.nextInt(10000);
        userRole.setName("test" + value);
        userRole.setDisplayName("Temp" + value);
        userRoleDAO.create(userRole);
        userRoleDAO.delete(userRole);
        Assert.assertTrue(true);
    }

}
