package gibdd;

public class main {
    public static void main(String[] args) {
        InitAppeal initiator = new InitAppeal();
        Screenshoter screenshoter = new Screenshoter();
        initiator.setDriverName("webdriver.chrome.driver");
        initiator.setDriverUrl("/chromedriver/chrome76driver.exe");
        initiator.setSiteUrl("https://гибдд.рф/request_main");
        initiator.setCaptchaSavingPath("c:/tmp/captchaScreenshot.png");
        initiator.setHeadlessBrowserOption(true);
        initiator.init();
        screenshoter.doCaptchaScreenshot(
                initiator.getCaptchaSavingPath(),
                initiator.driver,
                initiator.captcha);

        // TODO
    }
}
