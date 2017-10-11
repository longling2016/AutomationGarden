package Backend;

import java.util.TimerTask;

/**
 * Created by longlingwang on 2/15/16.
 */
public class CurrentStatus extends TimerTask {
    private int[] currentStatus;
    private boolean haveNutrient;

    public CurrentStatus(int[] initialStatus, boolean initialNutrient) {
        currentStatus = initialStatus;
        haveNutrient = initialNutrient;
    }

    public void setCurrentTemp(int currentTemp) {
        currentStatus[0] = currentTemp;
    }

    public void setCurrentWater(int currentWater) {
        currentStatus[1] = currentWater;
    }

    public void setIfHaveNutrient(boolean outOrNot) {
        haveNutrient = outOrNot;
    }

    public int getCurrentTemp() {
        return currentStatus[0];
    }

    public int getCurrentWater() {
        return currentStatus[1];
    }

    public String ifHaveNutrient() {
        if (haveNutrient) {
            return "ON";
        } else {
            return "OFF";
        }
    }

    @Override
    public void run() {
        if (currentStatus[0] > 20) {
            currentStatus[0]--;
        }
        if (currentStatus[1] > 2) {
            currentStatus[1] -= 3;
        }
    }
}
