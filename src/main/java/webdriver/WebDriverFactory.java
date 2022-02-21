package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/*
 * Factory to instantiate a WebDriver object. It returns an instance of the driver (local invocation).
 */
public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";
    public static final String SAFARI = "safari";

    private WebDriverFactory(){}

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     *
     * @param browser : Browser representing the local browser to hit
     *
     * @return WebDriver instance
     */
    public static WebDriver getInstance(String browserName, String browserVersion) {

        WebDriver webDriver = null;

        if (CHROME.equals(browserName)) {
//            ChromeDriverManager.getInstance().setup();
        	WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
            webDriver = new ChromeDriver();
        }
        else if (FIREFOX.equals(browserName)) {
        	WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();

        }
        else if (EDGE.equals(browserName)) {
        	WebDriverManager.edgedriver().setup();
            webDriver = new InternetExplorerDriver();
        }
        else {
            throw new IllegalArgumentException("Blah! Unsupported browser!");
        }

        return webDriver;
    }
}
