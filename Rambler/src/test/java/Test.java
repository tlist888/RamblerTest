import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by tanya on 12.08.2018.
 */
public class Test {
    private RamblerHomePage ramblerPage;
    private SectionData data;
    private WebDriver driver;

    @Before
    public void setUp(){
        ramblerPage = new RamblerHomePage();
        data = new SectionData();
        driver = ramblerPage.getDriver();
    }

    @After
    public void quitDriver() {
        ramblerPage.quitWebDriver();
    }


    /**
     * Тест заходит на каждый раздел и проверяет заход путем сверки заголовка вкладки с данными из класса SectionData.
     */
    @org.junit.Test
    public void checkTitle() {
        List<WebElement> list = ramblerPage.getListSections();
        List<String> titles = data.getData();

        try {
            for (int i = 0; i < list.size() - 1; ++i) {
                Thread.sleep(1000);
                if(list.get(i).isDisplayed()){
                    list.get(i).click();
                    Thread.sleep(3000);
                    assertEquals(driver.getTitle().contains(titles.get(i)), true);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Тест проверяет сторку поиска, вводом запроса "политика".
     */
    @org.junit.Test
    public void checkCorrectSearchQuery() {
        WebElement searchLine = ramblerPage.getSearchLine();
        WebElement submitBtn = ramblerPage.getSubmitBtn();
        String request = "политика";
        searchLine.sendKeys(request);
        submitBtn.click();

        swichToNextTab();
        WebElement title = driver.findElement(By.className("b-coolstream-mxn__count"));
        assertEquals(true, title.getText().contains("статей по вашему запросу"));
    }


    /**
     * Тест проверяет сторку поиска, вводом запроса "пАлитика".
     */
    @org.junit.Test
    public void checkUncorrectSearchQuery() {
        WebElement searchLine = ramblerPage.getSearchLine();
        WebElement submitBtn = ramblerPage.getSubmitBtn();
        String request = "пAлитика";
        searchLine.sendKeys(request);
        submitBtn.click();

        swichToNextTab();
        WebElement title = driver.findElement(By.className("b-spellcheck__content"));
        assertEquals(true, title.getText().contains("Возможно, вы искали:"));
    }


    /**
     * Тест проверяет сторку поиска, вводом запроса "+ХЖЭЪХЮ_№Й)ШК ЗЦЩУОЕЭ()№;ЬХСЗУЩОП УЦЗЩ".
     */
    @org.junit.Test
    public void checkGarbageSearchQuery() {
        WebElement searchLine = ramblerPage.getSearchLine();
        WebElement submitBtn = ramblerPage.getSubmitBtn();
        String request = "+ХЖЭЪХЮ_№Й)ШК ЗЦЩУОЕЭ()№;ЬХСЗУЩОП УЦЗЩ";
        searchLine.sendKeys(request);
        submitBtn.click();

        swichToNextTab();
        WebElement title = driver.findElement(By.className("b-error__text"));
        assertEquals(true, title.getText().contains("Искомая комбинация слов нигде не встречается."));
    }


    /**
     * Метод для переключения на другую вкладку
     */
    private void swichToNextTab(){
        Set<String> set = driver.getWindowHandles();
        String oldHandles = driver.getWindowHandle();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if(element.equals(oldHandles)) continue;
            else driver.switchTo().window(element);
        }
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
