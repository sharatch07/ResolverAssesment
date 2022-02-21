package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utilities.ActionEvents;
import utilities.Waits;


public class HomePage {

	WebDriver driver;
	Waits wait;
	ActionEvents action;
    private final String EMAIL = "inputEmail";
    private final String PASSWORD = "inputPassword";
    private final String SIGNIN = "//button[.='Sign in']";
    private final String LISTGRP = ".list-group li";
    private final String DROPDOWN = "dropdownMenuButton";
    private final String DROPDOWNGRP = "dropdown-item";
    private final String TEST4BTN1 = "#test-4-div .btn-primary";
    private final String TEST4BTN2 = "#test-4-div .btn-secondary";
    private final String TEST5PLACEHOLDER = "test5-placeholder";
    private final String TEST5BTN = "test5-button";
    private final String TEST5ALERT = "test5-alert";
 
    @FindBy(how = How.ID, using = EMAIL)
    @CacheLookup
    private WebElement emailElement;
    
    @FindBy(how = How.ID, using = PASSWORD)
    @CacheLookup
    private WebElement passwordElement;
    
    @FindBy(how = How.XPATH, using = SIGNIN)
    @CacheLookup
    private WebElement signinElement;
    
    @FindAll(@FindBy(how = How.CSS, using = LISTGRP))
    @CacheLookup
    private List<WebElement> listItemsLocator;
    
    @FindBy(how = How.ID, using = DROPDOWN)
    @CacheLookup
    private WebElement dropDownButtonElement;
    
    @FindAll(@FindBy(how = How.CLASS_NAME, using = DROPDOWNGRP))
    @CacheLookup
    private List<WebElement> dropdownGrpLocator;
    
    @FindBy(how = How.CSS, using = TEST4BTN1)
    @CacheLookup
    private WebElement test4Btn1Element;

    @FindBy(how = How.CSS, using = TEST4BTN2)
    @CacheLookup
    private WebElement test4Btn2Element;
    
    @FindBy(how = How.ID, using = TEST5BTN)
    @CacheLookup
    private WebElement test5BtnElement;
    
    @FindBy(how = How.ID, using = TEST5ALERT)
    @CacheLookup
    private WebElement test5AlertElement;
    
    @FindBy(how = How.ID, using = TEST5PLACEHOLDER)
    @CacheLookup
    private WebElement test5PlaceHolderElement;

    public HomePage(WebDriver driver) {
    	this.driver = driver;
    	this.wait = new Waits(driver, 10);
    	this.action = new ActionEvents(driver);
    	PageFactory.initElements(driver, this);
    }

    public void validateEmail() {
    	Assert.assertTrue(emailElement.isDisplayed());
    	System.out.println("Email is Displayed");
    }
    
    public void validatePassword() {
    	Assert.assertTrue(passwordElement.isDisplayed());
    	System.out.println("Password is Displayed");
    }
    
    public void validateLoginBtn() {
    	Assert.assertTrue(signinElement.isDisplayed());
    	System.out.println("LoginButton is Displayed");
    }

	public void enterEmailAndPassword(String email, String password) {
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
	}

	public void validateListSize(int size) {
		System.out.println("Validating ListItem Size");
		Assert.assertEquals(listItemsLocator.size(), size);
	}

	public void validateListItem(int index, String listItemStr) {
		System.out.println("Validating List Item");
		String actualItem = listItemsLocator.get(index-1).getText();
		Assert.assertTrue(actualItem.contains(listItemStr), "Expected :" + listItemStr + " ,but found :" + actualItem);
	}

	public void validateListItemBadgeValue(int index, String badgeValue) {
		System.out.println("Validating ListItem BadgeValue");
		String actualItemBadgeValue = listItemsLocator.get(index-1).findElement(By.cssSelector("span")).getText();
		Assert.assertEquals(actualItemBadgeValue, badgeValue);		
	}

	public void validateDefaultDropDownValue(String expectedDropDown) {
		System.out.println("Validating default dropdown value");
		String actualDropDownValue = dropDownButtonElement.getText();
		Assert.assertEquals(actualDropDownValue, expectedDropDown);
		
	}

	public void selectDropDownItem(String dropDownOption) {
		System.out.println("Selecting dropdown value :" + dropDownOption);
		dropDownButtonElement.click();
		for (int i=0; i<dropdownGrpLocator.size();i++){
			WebElement element = dropdownGrpLocator.get(i);
			if(element.getText().equals(dropDownOption)) {
				element.click();
				break;
			}
		}
	}

	public void validateButtons() {
		System.out.println("Validating first Button is Enabled");
		Assert.assertTrue(test4Btn1Element.isEnabled(), "Button is not Enabled");
		
		System.out.println("Validating second Button is Disabled");
		Assert.assertFalse(test4Btn2Element.isEnabled(), "Button is not Disabled");
	}

	public void waitAndClickButton() {
		System.out.println("Waiting for button to be displayed");
		
		//Scrolling to the element
		action.moveToElement(test5PlaceHolderElement).build().perform();
		//Using 10 Sec Explicit wait for element to be displayed 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(TEST5BTN)));
		
		System.out.println("Clicking on the button");
		test5BtnElement.click();
	}

	public void validateSuccessMsg() {
		System.out.println("Validating Success message is displayed");
		Assert.assertTrue(test5AlertElement.isDisplayed());
	}

	public void validateButtonIsDisabled() {
		System.out.println("Validating Test5 Button is disabled");
		Assert.assertFalse(test5BtnElement.isEnabled());		
	}

	public String getCellAt(int i, int j) {
		String path = "//tbody/tr["+ (i+1) +"]/td[" + (j+1) +"]";
		WebElement cellElement = driver.findElement(By.xpath(path));
		return cellElement.getText();
	}

}
