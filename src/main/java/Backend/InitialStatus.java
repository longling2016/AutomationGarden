package Backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by longlingwang on 2/15/16.
 */
public class InitialStatus {
    private int temp;
    private int waterContent;
    private boolean nutrient;
    static Logger logger = LoggerFactory.getLogger(InitialStatus.class);

    public InitialStatus(int temp, int waterContent, boolean nutrient) {
        this.temp = temp;
        this.waterContent = waterContent;
        this.nutrient = nutrient;
        logger.info("Initial Temperature: " + waterContent + "ºC\n" + "Initial Water Content: " +
                waterContent + "%\n" + "If we have Nutrient: " + nutrient);
    }

    public int getTemp() {
        return temp;
    }

    public int getWaterContent() {
        return waterContent;
    }

    public boolean haveNutrient() {
        return nutrient;
    }
}
