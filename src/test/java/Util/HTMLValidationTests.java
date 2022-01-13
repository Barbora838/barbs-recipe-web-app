package Util;

import org.junit.Test;

import java.util.ArrayList;

import static Dataset.CookiesDB.CookiesTable.returnName;
import static Server.HTTPConstants.*;
import static Util.HTMLExtra.*;
import static org.junit.Assert.assertEquals;

public class HTMLValidationTests {


    @Test
    public void testHTMLLandingPage(){
        String landingpage = HTMLExtra.getLandingPage();
        assertEquals(true, HTMLValidator.isValid(landingpage));
    }

    @Test
    public void testHTMLHomepage(){
        String homepage = getHomepage(returnName("Barbora"));
        System.out.println(homepage);
        assertEquals(true, HTMLValidator.isValid(homepage));
    }

    @Test
    public void testHTMLHomepage2(){
        String homepage = NOT_UTHORIZED;
        assertEquals(true, HTMLValidator.isValid(homepage));
    }

    @Test
    public void testHTMLFindGET(){
        String find = NOT_UTHORIZED;
        assertEquals(true, HTMLValidator.isValid(find));
    }

    @Test
    public void testHTMLFindGET2(){
        String find = FIND_HTML;
        assertEquals(true, HTMLValidator.isValid(find));
    }


    @Test
    public void testHTMLFindPOST2(){
        String find = notFoundRecipe("XXX");
        System.out.println(find);
        assertEquals(true, HTMLValidator.isValid(find));
    }


    @Test
    public void testHTMLSearch(){
        String search = SEARCH_HTML;
        assertEquals(true, HTMLValidator.isValid(search));
    }

    @Test
    public void testHTMLLogout(){
        String logout =  getLogoutPage(returnName("barbora"));
        assertEquals(true, HTMLValidator.isValid(logout));
    }

    @Test
    public void testHTMLCommingSoon(){
        String commingSoon = getCommingSoon("barb");
        assertEquals(true, HTMLValidator.isValid(commingSoon));

    }
}