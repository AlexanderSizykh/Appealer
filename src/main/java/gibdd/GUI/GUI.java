package gibdd.GUI;

import gibdd.InitAppeal;
import gibdd.Screenshoter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.io.File;
import java.net.MalformedURLException;

public class GUI extends Application {
    Group root = new Group();
    VBox strings = new VBox();
    HBox buttonBox = new HBox();
    final private int WIDTH = 1000;
    final private int HEIGHT = 600;
    Button buttonGetCaptcha = new Button("Получить капчу");
    Text textGetCaptcha = new Text();


    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene((Parent) root, WIDTH, HEIGHT);
        primaryStage.setTitle("Подача обращения в ГИБДД РФ");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.getChildren().add(strings);

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("Получение капчи и принятие условий обращения"));
        strings.getChildren().add(buttonBox);

        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(buttonGetCaptcha);
        buttonBox.getChildren().add(textGetCaptcha);

        buttonGetCaptcha.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
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
                Image captchaImage = new Image(getClass().getResourceAsStream(initiator.getCaptchaSavingPath()));
                ImageView captchaImageView = new ImageView(captchaImage);

                buttonBox.getChildren().add(captchaImageView);

                // TODO отладить отображение капчи из файла

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }






}
