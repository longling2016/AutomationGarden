import UI.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MainClass extends Application {
    static Logger logger = LoggerFactory.getLogger(MainClass.class);
    Stage stage;
    Scene mainScene;
    public static ImageView viewNutrient;
    public static ImageView viewFire;
    public static ImageView viewFrozen;
    public static ImageView viewRain;
    public static ImageView viewPest;
    public static ImageView viewHeat;
    public static ImageView viewWater;

    public static void main(String[] args) throws InterruptedException {
        BackendMain.kickOutAsyncJobs();
        logger.info("Automated Garden starts to work!");
        launch(args);
        logger.info("GUI launched");

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 30 * 60 * 60 * 1000) {
            Thread.sleep(60 * 60 * 1000);
        }
        System.exit(0);
    }

    private void closeProgram() {
        Boolean close = OnOffSettingBox.display("Confirming", "Are you sure to close the program?", "Yes", "No");
        if (close) {
            stage.close();
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Secret Garden");

        stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // drop down menu for checking status
        Menu check = new Menu("Check Garden Status");
        MenuItem checkSetting = new MenuItem("Check Current Setting...");
        checkSetting.setOnAction(e -> CurrentSetting.display(BackendMain.currentStatus.getCurrentTemp(),
                BackendMain.currentStatus.getCurrentWater(), BackendMain.currentStatus.ifHaveNutrient(),
                BackendMain.tempController.getOnOff(), BackendMain.irrigation.getOnOff()));

        MenuItem checkCropList = new MenuItem("Check Crop List...");
        checkCropList.setOnAction(e -> CropTableView.display());

        MenuItem checkLog = new MenuItem("Check Log");
        checkLog.setOnAction(e -> {
            File log = new File(System.getProperty("user.home") + "/garden/logs/garden.log");
            if (log.exists()) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(log);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    AlertBox.display("Log format on user's Desktop is not supported.");
                }
            } else {
                AlertBox.display("Please make sure the file's path is correct for line#72 in MainClass :)");
            }
        });
        check.getItems().addAll(checkSetting, checkCropList, checkLog);

        //drop down menu for changing setting
        Menu changeSetting = new Menu("Change Setting");
        MenuItem setTemp = new MenuItem("Set Temperature...");
        setTemp.setOnAction(e -> {
            int[] temp = ChangingValueBox.display("Temperature in Garden",
                    BackendMain.tempController.getMaxTemp(), BackendMain.tempController.getMinTemp());
            BackendMain.tempController.setMaxTemp(temp[0]);
            BackendMain.tempController.setMinTemp(temp[1]);
        });

        MenuItem tempSwitch = new MenuItem("Turn on/off Air Conditioner...");
        tempSwitch.setOnAction(e -> {
            boolean result = OnOffSettingBox.display("Temperature Controller",
                    "Do you want to turn on/off air conditioner?", "ON", "OFF");
            BackendMain.tempController.setOnOff(result);
        });

        MenuItem setWater = new MenuItem("Set Irrigation...");
        setWater.setOnAction(e -> {
            int[] temp = ChangingValueBox.display("Water Content in Garden",
                    BackendMain.irrigation.getMaxWater(), BackendMain.irrigation.getMinWater());
            BackendMain.irrigation.setMaxWater(temp[0]);
            BackendMain.irrigation.setMinWater(temp[1]);
        });

        MenuItem waterSwitch = new MenuItem("Turn on/off Irrigation...");
        waterSwitch.setOnAction(e -> {
            boolean result = OnOffSettingBox.display("Irrigation System",
                    "Do you want to turn on/off irrigation system?", "ON", "OFF");
            BackendMain.irrigation.setOnOff(result);
        });

        MenuItem nutrientSwitch = new MenuItem("Turn on/off Nutrient...");
        nutrientSwitch.setOnAction(e -> {
            boolean result = OnOffSettingBox.display("Nutrient Refilling",
                    "Do you want to turn on/off nutrient supply?", "ON", "OFF");
            BackendMain.currentStatus.setIfHaveNutrient(result);
        });

        changeSetting.getItems().addAll(setTemp, tempSwitch, new SeparatorMenuItem(),
                setWater, waterSwitch, new SeparatorMenuItem(), nutrientSwitch);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(check, changeSetting);

        Group root = new Group();

        Image background = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/background2.png"));
        ImageView backView = new ImageView(background);
        Image imageBird = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/bird_2.gif"));
        ImageView viewBird = new ImageView(imageBird);

        Image nutrient = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/Fertilizer - E-Z Grow.gif"));
        viewNutrient = new ImageView(nutrient);
        viewNutrient.setOpacity(0);

        Image fire = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/WzGHb0V.gif"));
        viewFire = new ImageView(fire);
        viewFire.setOpacity(0);

        Image frozen = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/giphy (1).gif"));
        viewFrozen = new ImageView(frozen);
        viewFrozen.setOpacity(0);

        Image rain = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/rain.gif"));
        viewRain = new ImageView(rain);
        viewRain.setOpacity(0);

        Image pest = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/bugs_report_reading_hc.gif"));
        viewPest = new ImageView(pest);
        viewPest.setOpacity(0);

        Image heat = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/Heating and Cooling Icon.png"));
        viewHeat = new ImageView(heat);

        Image water = new Image(MainClass.class.getClassLoader().getResourceAsStream("images/eco-green-watering.png"));
        viewWater = new ImageView(water);


        backView.setTranslateX(0);
        backView.setTranslateY(0);

        viewBird.setTranslateX(100);
        viewBird.setTranslateY(200);

        viewHeat.setTranslateX(700);
        viewHeat.setTranslateY(620);
        viewHeat.setFitHeight(120);
        viewHeat.setFitWidth(120);
        viewHeat.setOpacity(1.0);

        viewWater.setTranslateX(600);
        viewWater.setTranslateY(630);
        viewWater.setFitHeight(100);
        viewWater.setFitWidth(100);
        viewWater.setOpacity(1.0);

        viewNutrient.setTranslateX(650);
        viewNutrient.setTranslateY(200);
        viewNutrient.setFitHeight(300);
        viewNutrient.setFitWidth(250);

        viewRain.setTranslateX(10);
        viewRain.setTranslateY(200);

        viewFire.setTranslateX(80);
        viewFire.setTranslateY(100);
        viewFire.setFitWidth(500);
        viewFire.setFitHeight(400);

        viewFrozen.setTranslateX(20);
        viewFrozen.setTranslateY(20);
        viewFrozen.setFitHeight(900);
        viewFrozen.setFitWidth(1000);

        viewPest.setTranslateX(30);
        viewPest.setTranslateY(500);

        root.getChildren().addAll(backView, viewBird, viewNutrient, viewFire, viewFrozen,
                viewRain, viewPest, viewHeat, viewWater);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);
        borderPane.setTop(menuBar);

        mainScene = new Scene(borderPane, 1000, 1200);
        stage.setScene(mainScene);
        stage.show();
    }
}