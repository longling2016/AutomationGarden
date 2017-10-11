package UI; /**
 * Created by longlingwang on 2/15/16.
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OnOffSettingBox {

    static boolean onOff;

    public static boolean display(String title, String message, String onYes, String offNo) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        Label label = new Label();
        label.setText(message);
        label.setPadding(new Insets(20, 20, 20, 20));

        Button onButton = new Button(onYes);
        Button offButton = new Button(offNo);

        onButton.setOnAction(e -> {
            onOff = true;
            window.close();
        });
        offButton.setOnAction(e -> {
            onOff = false;
            window.close();
        });

        HBox onOffChoice = new HBox();
        onOffChoice.setPadding(new Insets(10, 20, 30, 20));
        onOffChoice.setSpacing(60);
        onOffChoice.getChildren().addAll(onButton, offButton);
        onOffChoice.setAlignment(Pos.BASELINE_CENTER);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, onOffChoice);
        layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return onOff;
    }
}
