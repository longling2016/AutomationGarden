package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by longlingwang on 2/16/16.
 */
public class ChangingValueBox {
    public static int[] display(String title, int currentMax, int currentMin) {
        int[] temp = new int[2];

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);


        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Maximum value Label - constrains use (child, column, row)
        Label maxLabel = new Label("Maximum: ");
        GridPane.setConstraints(maxLabel, 0, 0);

        //Maximum Input
        TextField maxInput = new TextField();
        maxInput.setPromptText("Current: " + currentMax);// show current value
        GridPane.setConstraints(maxInput, 1, 0);

        //Minimum Label
        Label minLabel = new Label("Minimum: ");
        GridPane.setConstraints(minLabel, 0, 1);

        //Password Input
        TextField minInput = new TextField();
        minInput.setPromptText("Current: " + currentMin);//show current value
        GridPane.setConstraints(minInput, 1, 1);

        //ok button
        Button okButton = new Button("OK");
        GridPane.setConstraints(okButton, 1, 2);
        okButton.setOnAction(e -> {
            if (isInt(maxInput, minInput)) {
                temp[0] = Integer.parseInt(maxInput.getText());
                temp[1] = Integer.parseInt(minInput.getText());
                stage.close();
            }
        });

        //Add everything to grid
        grid.getChildren().addAll(maxLabel, maxInput, minLabel, minInput, okButton);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.showAndWait();
        return temp;
    }

    private static boolean isInt(TextField maxInput, TextField minInput) {
        try {
            int max = Integer.parseInt(maxInput.getText());
            int min = Integer.parseInt(minInput.getText());
            if (max >= 20 && max <= 80 && min >= 20 && min <= 80 && max >= min) {
                return true;
            } else {
                AlertBox.display("Invalid input! Please enter an integer between 20-80.\n " +
                        "Max value should not be smaller than Min value.");
                return false;
            }
        } catch (NumberFormatException e) {
            AlertBox.display("Invalid input! Please enter an integer between 20-80.\n " +
                    "Max value should not be smaller than Min value.");
            return false;
        }
    }
}
