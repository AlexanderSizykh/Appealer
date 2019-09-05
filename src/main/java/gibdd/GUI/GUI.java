package gibdd.GUI;

import gibdd.InitAppeal;
import gibdd.Screenshoter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.File;
import java.util.logging.Logger;

public class GUI extends Application {
    private static Logger log = Logger.getLogger(InitAppeal.class.getName());

    GridPane gridPane = new GridPane();

    VBox strings = new VBox();
    HBox buttonBox = new HBox();
    HBox captchaBox = new HBox();
    final private int WIDTH = 1000;
    final private int HEIGHT = 600;
    Button buttonGetCaptcha = new Button("Получить капчу");
    File spinner = new File("\\tmpCaptcha\\spinner.gif");
    ImageView spinnerImageView = new ImageView(spinner.toURI().toString());


    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene((Parent) gridPane, WIDTH, HEIGHT);
        primaryStage.setTitle("Подача обращения в ГИБДД РФ");
        primaryStage.setScene(scene);
        primaryStage.show();

        gridPane.add(strings, 1, 1);
        gridPane.add(captchaBox, 1,2);
        gridPane.add(buttonBox, 2, 3);

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("Получение капчи и принятие условий обращения"));
        strings.getChildren().add(buttonBox);

        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(buttonGetCaptcha);

        captchaBox.setPadding(new Insets(10, 30, 10, 30));

        buttonGetCaptcha.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                showSpinner();


                InitAppeal initiator = new InitAppeal();
                Screenshoter screenshoter = new Screenshoter();
                initiator.setDriverName("webdriver.chrome.driver");
                initiator.setDriverUrl("/chromedriver/chrome76driver.exe");
                initiator.setSiteUrl("https://гибдд.рф/request_main");
                initiator.setCaptchaSavingPath("\\tmpCaptcha\\captchaScreenshot.png");
                initiator.setHeadlessBrowserOption(true);
                log.info("Начинаем процесс получения капчи");
                initiator.init();
                File captchaFile = new File(initiator.getCaptchaSavingPath());
                log.info("Создаем временную папку");
                captchaFile.mkdirs();
                log.info("Делаем скриншот капчи");
                screenshoter.doCaptchaScreenshot(
                        initiator.getCaptchaSavingPath(),
                        initiator.driver,
                        initiator.captcha);
                hideSpinner();
                ImageView captchaImageView = new ImageView(captchaFile.toURI().toString());
                log.info("Показываем капчу");
                captchaBox.getChildren().add(captchaImageView);


                // TODO отладить отображение спиннера и вынести инициатор

            }
        });
    }

    private void showSpinner() {
        log.info("Показываем спиннер");
        captchaBox.getChildren().add(spinnerImageView);

    }
    private void hideSpinner() {
        log.info("Прячем спиннер");
        captchaBox.getChildren().remove(spinnerImageView);

    }

    public static void main(String[] args) {
        launch(args);
    }






}
