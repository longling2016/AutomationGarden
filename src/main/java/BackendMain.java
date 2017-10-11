import Backend.CurrentStatus;
import Backend.InitialStatus;

import java.util.Timer;


public class BackendMain {
    public static InitialStatus initialStatus;
    public static CurrentStatus currentStatus;
    public static TempController tempController;
    public static Irrigation irrigation;
    public static RandomEvent randomEvent;

    public static void kickOutAsyncJobs() throws InterruptedException {

        initialStatus = new InitialStatus(25, 60, true);

        int[] status = new int[2];
        status[0] = initialStatus.getTemp();
        status[1] = initialStatus.getWaterContent();
        currentStatus = new CurrentStatus(status, initialStatus.haveNutrient());

        tempController = new TempController(currentStatus, 38, 23);

        irrigation = new Irrigation(currentStatus, 80, 40);

        randomEvent = new RandomEvent(initialStatus, currentStatus);

        Timer timerForCurrentStatus = new Timer();
        timerForCurrentStatus.scheduleAtFixedRate(currentStatus, 1, 30 * 1000);

        Timer timerForTempControl = new Timer();
        timerForTempControl.scheduleAtFixedRate(tempController, 2, 10 * 1000);

        Timer timerForWater = new Timer();
        timerForWater.scheduleAtFixedRate(irrigation, 3, 10 * 1000);

        Timer timerForAccident = new Timer();
        timerForAccident.scheduleAtFixedRate(randomEvent, 60 * 000,  10 * 1000);

    }
}