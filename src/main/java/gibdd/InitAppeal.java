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

    String SiteUrl = "https://гибдд.рф/request_main";
    ChromeDriver driver;
    WebDriverWait wait;
    String driverName = "webdriver.chrome.driver";
    String driverUrl = "/chromedriver/chrome76driver.exe";
    WebElement AgreementCheckbox;
    WebElement AgreementSubmitBtn;
    WebElement captcha;

    String CaptchaSavingPath;
    public String getCaptchaSavingPath() {        return CaptchaSavingPath;    }
    public void setCaptchaSavingPath(String captchaSavingPath) {        CaptchaSavingPath = captchaSavingPath;    }


    public void init() {


        log.info("Настраиваем драйвер");
        System.setProperty(driverName, driverUrl);
        ChromeOptions options = new ChromeOptions();
//        log.info("Браузер будет открыт в скрытом режиме");
//        options.addArguments("headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        log.info("Предварителная настройка драйвера завершена");

        log.info("Открываем сайт");
        driver.get(SiteUrl);
        driver.manage().deleteAllCookies();

        log.info("Принимаем соглашение");
        AgreementCheckbox = driver.findElement(By.cssSelector("[class=\"f-left checkError\"]"));
        AgreementSubmitBtn = driver.findElement(By.cssSelector("[class=\"u-form__sbt\"]"));
        AgreementCheckbox.click();
        AgreementSubmitBtn.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"captcha-img\"]")));
        captcha = driver.findElement(By.cssSelector("[class=\"captcha-img\"]"));

    }

}
