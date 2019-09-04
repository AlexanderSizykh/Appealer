package gibdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;


public class InitAppeal {
    private static Logger log = Logger.getLogger(InitAppeal.class.getName());
    public ChromeDriver driver;
    WebDriverWait wait;

    String siteUrl;
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
    String driverName;
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    String driverUrl;
    public void setDriverUrl(String driverUrl) {
        this.driverUrl = driverUrl;
    }
    Boolean headlessBrowserOption;
    public void setHeadlessBrowserOption(Boolean headlessBrowserOption) {
        this.headlessBrowserOption = headlessBrowserOption;
    }
    String captchaSavingPath;
    public String getCaptchaSavingPath() {        return captchaSavingPath;    }
    public void setCaptchaSavingPath(String captchaSavingPath) {        this.captchaSavingPath = captchaSavingPath;    }

    WebElement agreementCheckbox;
    WebElement agreementSubmitBtn;
    public WebElement captcha;

    public void init() {


        log.info("Настраиваем драйвер");
        System.setProperty(driverName, driverUrl);
        ChromeOptions options = new ChromeOptions();

        if (headlessBrowserOption == true) {
            log.info("Браузер будет открыт в скрытом режиме");
            options.addArguments("headless");
        }

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        log.info("Предварителная настройка драйвера завершена");

        log.info("Открываем сайт");
        driver.get(siteUrl);
        driver.manage().deleteAllCookies();

        log.info("Принимаем соглашение");
        agreementCheckbox = driver.findElement(By.cssSelector("[class=\"f-left checkError\"]"));
        agreementSubmitBtn = driver.findElement(By.cssSelector("[class=\"u-form__sbt\"]"));
        agreementCheckbox.click();
        agreementSubmitBtn.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"captcha-img\"]")));
        captcha = driver.findElement(By.cssSelector("[class=\"captcha-img\"]"));

    }

}
