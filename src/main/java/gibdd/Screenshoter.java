package gibdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Screenshoter {
    private static Logger log = Logger.getLogger(Screenshoter.class.getName());

    public void doCaptchaScreenshot(String CaptchaSavingPath, WebDriver driver, WebElement element) {
        log.info("Начинаем сохранение капчи в " + CaptchaSavingPath);


        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(200)).takeScreenshot(driver, element);

        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(CaptchaSavingPath));
            log.info("Записываем капчу в файл " + CaptchaSavingPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Не удалось записать файл");
        }
    }
}
