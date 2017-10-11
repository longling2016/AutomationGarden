import Backend.CurrentStatus;
import Backend.InitialStatus;
import UI.EventAlert;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.applet.Main;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by longlingwang on 2/15/16.
 */
public class RandomEvent extends TimerTask {
    private int event;
    private InitialStatus initialStatus;
    private CurrentStatus currentStatus;
    private Random accident = new Random();
    static Logger logger = LoggerFactory.getLogger(RandomEvent.class);

    public RandomEvent(InitialStatus initialStatus, CurrentStatus currentStatus) {
        this.initialStatus = initialStatus;
        this.currentStatus = currentStatus;
    }

    @Override
    public void run() {
        event = accident.nextInt(10);
        if (event == 0 && initialStatus.haveNutrient()) { // nutrient is out!!
            currentStatus.setIfHaveNutrient(false);
            logger.info("Event happening: Nutrient is ran out!! Need refilling.");

            FadeTransition ft = new FadeTransition();
            ft.setNode(MainClass.viewNutrient);
            ft.setDuration(new Duration(12000));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            EventAlert.show("Nutrient is ran out!! Refilling...");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentStatus.setIfHaveNutrient(true);
            logger.info("Nutrient refilling is done.");
        } else if (event == 2) { // fire is on!!
            currentStatus.setCurrentTemp(100);
            currentStatus.setCurrentWater(0);

            FadeTransition ft = new FadeTransition();
            ft.setNode(MainClass.viewFire);
            ft.setDuration(new Duration(12000));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            logger.info("Event happening: Fire is on!! System is trying to save the plants.");
            EventAlert.show("Fire is on!! Saving plants by water spraying and cooling down..");
        } else if (event == 4) { // Frozen weather!!
            currentStatus.setCurrentTemp(0);

            FadeTransition ft = new FadeTransition();
            ft.setNode(MainClass.viewFrozen);
            ft.setDuration(new Duration(12000));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            logger.info("Event happening: Frozen weather!! Saving plants by heating up.");
            EventAlert.show("Frozen weather!! Saving plants by heating up...");
        } else if (event == 6) { // It is raining
            currentStatus.setCurrentWater(100);

            FadeTransition ft = new FadeTransition();
            ft.setNode(MainClass.viewRain);
            ft.setDuration(new Duration(12000));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            logger.info("Event happening: Raining today. No need for watering.");
            EventAlert.show("It is raining. Plants love it. No need for watering today. Enjoy the rain :)");
        } else if (event == 8) { // Pests attack
            logger.info("Event happening: Plants under pest attack!!");

            FadeTransition ft = new FadeTransition();
            ft.setNode(MainClass.viewPest);
            ft.setDuration(new Duration(12000));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

            EventAlert.show("Plants under pest attack!! Pesticide spraying right now... Please stay away from the garden");
        }
    }
}

