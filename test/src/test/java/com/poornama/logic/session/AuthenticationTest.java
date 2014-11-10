package com.poornama.logic.session;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dedunu on 11/10/14.
 */
public class AuthenticationTest {

    @Test
    public void doSuccessfulAuthentication(){
        Authentication authentication = new Authentication();
        boolean isSuccessful = authentication.doAuthenticate("dedunumax","rootroot");
        Assert.assertTrue(isSuccessful);
    }

    @Test
    public void doUnsuccessfulAuthentication(){
        Authentication authentication = new Authentication();
        boolean isSuccessful = authentication.doAuthenticate("dedunumax","notroot");
        Assert.assertTrue(!isSuccessful);
    }
}
