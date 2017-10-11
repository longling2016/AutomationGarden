import Backend.CurrentStatus;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created by longlingwang on 2/15/16.
 */
public class TempController extends TimerTask {
    private CurrentStatus currentStatus;
    private int maxTemp;
    private int minTemp;
    private boolean onOff = true;
    static Logger logger = LoggerFactory.getLogger(TempController.class);

    public TempController(CurrentStatus currentStatus, int maxTemp, int minTemp) {
        this.currentStatus = currentStatus;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        logger.info("Initial max temperature for heater: " + maxTemp + "ºC\n" +
                "Initial min temperature for heater: " +
                minTemp + "ºC\n" + "If heater is on: " + onOff);
    }

    public void setMaxTemp(int maxTemp) {
        if (maxTemp != 0) {
            this.maxTemp = maxTemp;
            logger.info("The max temperature for heater was changed to: " + maxTemp + "ºC");
        }
    }

    public void setMinTemp(int minTemp) {
        if (minTemp != 0) {
            this.minTemp = minTemp;
            logger.info("The min temperature for heater was changed to: " + minTemp + "ºC");
        }
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
        logger.info("User turn on/off heating system: now if the heater is on- " + onOff);
    }

    public String getOnOff() {
        return onOff == true ? "ON" : "OFF";
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    @Override
    public void run() {
        if (onOff) {
            if (currentStatus.getCurrentTemp() < minTemp) {
                currentStatus.setCurrentTemp(currentStatus.getCurrentTemp() + 15);
                logger.info("Current temperature was raised by heating system for 15ºC");
                logger.info("Current temperature: " + currentStatus.getCurrentTemp() + "ºC");
                FadeTransition ft = new FadeTransition();
                ft.setNode(MainClass.viewHeat);
                ft.setDuration(new Duration(1000));
                ft.setFromValue(1.0);
                ft.setToValue(0.2);
                ft.setCycleCount(8);
                ft.setAutoReverse(true);
                ft.play();
            } else if (currentStatus.getCurrentTemp() > maxTemp) {
                currentStatus.setCurrentTemp(currentStatus.getCurrentTemp() - 35);
                logger.info("Fire is on! Current temperature was cooled down for 35ºC");
                logger.info("Current temperature: " + currentStatus.getCurrentTemp() + "ºC");
                FadeTransition ft = new FadeTransition();
                ft.setNode(MainClass.viewHeat);
                ft.setDuration(new Duration(1000));
                ft.setFromValue(1.0);
                ft.setToValue(0.2);
                ft.setCycleCount(8);
                ft.setAutoReverse(true);
                ft.play();
            }
        }
    }
}
