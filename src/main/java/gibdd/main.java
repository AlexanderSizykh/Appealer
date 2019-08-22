package gibdd;

public class main {
    public static void main(String[] args) {
        InitAppeal sender = new InitAppeal();
        Screenshoter screenshoter = new Screenshoter();

        sender.init();
        sender.setCaptchaSavingPath("c:/tmp/captchaScreenshot.png");
        screenshoter.doCaptchaScreenshot(
                sender.getCaptchaSavingPath(),
                sender.driver,
                sender.captcha);

    }
}
