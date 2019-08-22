package gibdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SendAppeal  {
    String SiteUrl = "https://гибдд.рф/request_main";
    ChromeDriver driver;
    WebDriverWait wait;
    String driverName = "webdriver.chrome.driver";
    String driverUrl = "/chromedriver/chrome76driver.exe";
    WebElement AgreementCheckbox;
    WebElement AgreementSubmitBtn;
    WebElement Captcha;

    public void run() throws IOException {

        // Настроим драйвер
        System.setProperty(driverName, driverUrl);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless"); // открывает браузер в скрытом режиме
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);

        // Откроем сайт
        driver.get(SiteUrl);
        driver.manage().deleteAllCookies();

        // Принимаем соглашение
        AgreementCheckbox = driver.findElement(By.cssSelector("[class=\"f-left checkError\"]"));
        AgreementSubmitBtn = driver.findElement(By.cssSelector("[class=\"u-form__sbt\"]"));
        AgreementCheckbox.click();
        AgreementSubmitBtn.click();

        // Ищем и фотаем капчу, сохраняем

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"captcha-img\"]")));
        Captcha = driver.findElement(By.cssSelector("[class=\"captcha-img\"]"));

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(200)).takeScreenshot(driver, Captcha);

        ImageIO.write(screenshot.getImage(), "PNG", new File("c:\\tmp\\div_element.png"));

    }

}
