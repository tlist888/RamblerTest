import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tanya on 12.08.2018.
 */
public class RamblerHomePage {
    private WebDriver driver;
    private List<WebElement> childs;
    private WebElement searchLine, submitBtn;
    private static String URL = "https://www.rambler.ru";


    public RamblerHomePage(){
        initDriver();
        driver.get(URL);

        searchLine = driver.findElement(By.className("rui-ComplexSearch-input"));
        submitBtn = driver.findElement(By.className("rui-ComplexSearch-searchButton"));

        WebElement parent = driver.findElement(By.className("_141U"));
        childs = parent.findElements(By.xpath(".//*"));

        //удаление дублей и мусора
        int i = 0;
        while(i < childs.size()){
            String el = childs.get(i).getText();
            if(el.equals("")) childs.remove(childs.get(i));
            else if(i + 1 < childs.size() && el.equals(childs.get(i + 1).getText())){
                childs.remove(childs.get(i));
            }
            else i ++;
        }
        WebElement el = childs.get(1);
        childs.set(1, childs.get(0));
        childs.set(0, el);
    }

    private void initDriver(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\tanya\\Documents\\Drivers\\geckodriver.exe");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("reader.parse-on-load.enabled", false);
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setCapability("marionette", true);
        cap.setBrowserName("firefox");
        driver = new FirefoxDriver(cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    public List<WebElement> getListSections(){
        return childs;
    }

    public WebElement getSearchLine(){
        return searchLine;
    }

    public void quitWebDriver(){
        driver.quit();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public WebElement getSubmitBtn(){
        return submitBtn;
    }
}
