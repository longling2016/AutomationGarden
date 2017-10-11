package UI;

/**
 * Created by longlingwang on 2/16/16.
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CurrentSetting {

    public static void display(int curTemp, int curWater, String nutrient, String temContr, String irrigContr) {
        Stage stage = new Stage();
        stage.setTitle("Current Status in Secret Garden");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label statusText1 = new Label("Current Temperature: " + curTemp + "ºC");
        Label statusText2 = new Label("Current Water Content: " + curWater + "%");
        Label statusText3 = new Label("Current Nutrient: " + nutrient);
        Label statusText4 = new Label("Temperature Controller: " + temContr);
        Label statusText5 = new Label("Irrigation: " + irrigContr);
        Button buttonOK = new Button("OK");
        buttonOK.setOnAction(e -> stage.close());

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(30, 30, 30, 30));
        vbox.getChildren().addAll(statusText1, statusText2, statusText3, statusText4, statusText5, buttonOK);
        vbox.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
