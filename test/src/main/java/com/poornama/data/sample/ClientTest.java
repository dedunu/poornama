package com.poornama.data.sample;

import com.poornama.api.objects.Client;
import com.poornama.data.dao.ClientDAO;
import org.junit.Test;

/**
 * Created by ddhananjaya on 4/12/15.
 */
public class ClientTest {
    @Test
    public void runTest(){
        ClientDAO clientDAO = new ClientDAO();

        Client client1 = new Client();
        client1.setOrganizationName("MAS Active");
        client1.setTelephoneNumber("0112947874");
        client1.setAddress("22 Delgoda, Colombo.");
        clientDAO.create(client1);

        Client client2 = new Client();
        client2.setOrganizationName("Universal Freighters");
        client2.setTelephoneNumber("0112947874");
        client2.setAddress("22 Delgoda, Colombo.");
        clientDAO.create(client2);

    }
}
