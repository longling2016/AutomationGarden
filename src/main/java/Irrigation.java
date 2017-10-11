import Backend.CurrentStatus;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created by longlingwang on 2/15/16.
 */
public class Irrigation extends TimerTask {
    private CurrentStatus currentStatus;
    private int maxWater;
    private int minWater;
    private boolean onOff = true;
    static Logger logger = LoggerFactory.getLogger(Irrigation.class);

    public Irrigation(CurrentStatus currentStatus, int maxWater, int minWater) {
        this.currentStatus = currentStatus;
        this.maxWater = maxWater;
        this.minWater = minWater;
        logger.info("Initial max water level for irrigation: " + maxWater + "%\n" +
                "Initial min water level for irrigation: " +
                minWater + "%\n" + "If irrigation is on: " + onOff);
    }

    public void setMaxWater(int maxWater) {
        if (maxWater != 0) {
            this.maxWater = maxWater;
            logger.info("The max water level for irrigation was changed to: " + maxWater + "%");
        }
    }

    public void setMinWater(int minWater) {
        if (minWater != 0) {
            this.minWater = minWater;
            logger.info("The min water level for irrigation was changed to: " + minWater + "%");
        }
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
        logger.info("User turn on/off irrigation system: now if the irrigation is on- " + onOff);
    }

    public String getOnOff() {
        return onOff == true ? "ON" : "OFF";
    }

    public int getMaxWater() {
        return maxWater;
    }

    public int getMinWater() {
        return minWater;
    }

    @Override
    public void run() {
        if (onOff) {
            if (currentStatus.getCurrentWater() < minWater) {
                currentStatus.setCurrentWater(currentStatus.getCurrentWater() + 20);
                logger.info("Current water content was raised by irrigation system for 20%");
                logger.info("Current water content: " + currentStatus.getCurrentWater() + "%");
                FadeTransition ft = new FadeTransition();
                ft.setNode(MainClass.viewWater);
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

