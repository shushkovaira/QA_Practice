package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract class ParentPage extends CommonActionsWithElements{
    protected String baseUrl;


    /** запуск тестів на різних евнах:куа/стж і тд.**/

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl=configProperties.base_url()
                .replace("[env]",System.getProperty("env","qa"));

    }
    abstract String getRelativeUrl();

    protected void checkUrl(){

        Assert.assertEquals(
                "Invalid Page",
                baseUrl+getRelativeUrl(),webDriver.getCurrentUrl());
    }
    protected void checkUrlWithPattern(){
        Assert.assertThat("InvalidPage"
                ,webDriver.getCurrentUrl()
                ,containsString(baseUrl+getRelativeUrl()));
    }
    protected void waitChatToBeHide(){
        webDriverWaitLow.withMessage("Chat is not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}

