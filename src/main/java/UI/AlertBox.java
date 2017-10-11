package UI; /**
 * Created by longlingwang on 2/15/16.
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String message) {
        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("ALERT!!!");
        alertWindow.setMinWidth(250);

        Label words = new Label();
        words.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> alertWindow.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(words, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(layout);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }

}
