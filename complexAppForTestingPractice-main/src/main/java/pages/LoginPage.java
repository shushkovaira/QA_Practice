package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
  //  private HeaderElement headerElement = new HeaderElement(webDriver);


    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputUserPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    @Step
    public LoginPage enterUserNameIntoLoginInput(String userName) {

        enterTextIntoElement(inputUserNameHeader, userName);
        return new LoginPage(webDriver);
    }

    @Step
    public LoginPage enterPasswordIntoLoginInput(String password) {

        enterTextIntoElement(inputUserPasswordHeader, password);
        return new LoginPage(webDriver);
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSingIn);
    }

    public boolean isMessageInvalidCredsDisplayed() {
        try {
            WebElement messageInvalidCreds = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center' and contains(text(),'Invalid username / pasword')]"));
            return messageInvalidCreds.isDisplayed();

        } catch (Exception e) {
            return true;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
