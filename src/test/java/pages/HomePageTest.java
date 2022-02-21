package pages;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Browser;

public class HomePageTest extends TestBase{

    @Test
    public void test1() throws InterruptedException {
        HomePage home = new HomePage(Browser.Driver());
        home.validateEmail();
        home.validatePassword();
        home.validateLoginBtn();
        home.enterEmailAndPassword("abc@test.com", "password");
    }

    @Test
    public void test2() throws InterruptedException {
    	HomePage home = new HomePage(Browser.Driver());
    	home.validateListSize(3);
    	home.validateListItem(2, "List Item 2");
    	home.validateListItemBadgeValue(2, "6");
    }
    
    @Test
    public void test3() throws InterruptedException {
    	HomePage home = new HomePage(Browser.Driver());
    	home.validateDefaultDropDownValue("Option 1");
    	home.selectDropDownItem("Option 3");
    }
    
    @Test
    public void test4() throws InterruptedException {
    	HomePage home = new HomePage(Browser.Driver());
    	home.validateButtons();
    }
    
    @Test
    public void test5() throws InterruptedException {
    	HomePage home = new HomePage(Browser.Driver());
    	home.waitAndClickButton();
    	home.validateSuccessMsg();
    	home.validateButtonIsDisabled();
    }
    
    @Test
    public void test6() throws InterruptedException {
    	HomePage home = new HomePage(Browser.Driver());
    	String actualValue = home.getCellAt(2,2);
    	Assert.assertEquals(actualValue, "Ventosanzap");
    }
}
