package UI;

/**
 * Created by longlingwang on 2/16/16.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CropTableView {
    // This CropTableView refers to the template online
//    https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/020_tableViewAddingAndDeleting/Main.java

    static Stage stage;
    static TableView<Crops> table;
    static TextField nameInput, priceInput, quantityInput;
    static Logger logger = LoggerFactory.getLogger(CropTableView.class);

    public static void display() {
        stage = new Stage();
        stage.setTitle("Crops in Secret Garden");

        TableColumn<Crops, String> nameColumn = new TableColumn<>("Crop Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Crops, Double> priceColumn = new TableColumn<>("Price Per Pound");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Crops, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameInput = new TextField();
        nameInput.setPromptText("Crop Name");
        nameInput.setMinWidth(200);

        priceInput = new TextField();
        priceInput.setPromptText("Price Per Pound");

        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addButton = new Button("Add Crop");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete Crop");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);

        table = new TableView<>();
        table.setItems(getCrops());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    public static void addButtonClicked() {
        try {
            Crops crops = new Crops();
            crops.setName(nameInput.getText());
            crops.setPrice(Double.parseDouble(priceInput.getText()));
            crops.setQuantity(Integer.parseInt(quantityInput.getText()));
            table.getItems().add(crops);
            nameInput.clear();
            priceInput.clear();
            quantityInput.clear();
            logger.info("User adds one crop into the garden.");
        } catch (NumberFormatException e) {
            AlertBox.display("Invalid input! Try again.");
        }
    }

    public static void deleteButtonClicked() {
        ObservableList<Crops> cropSelected, allCrops;
        allCrops = table.getItems();
        cropSelected = table.getSelectionModel().getSelectedItems();
        logger.info("User removes one crop from the garden.");
        cropSelected.forEach(allCrops::remove);
    }

    public static ObservableList<Crops> getCrops() {
        ObservableList<Crops> crops = FXCollections.observableArrayList();
        crops.add(new Crops("Watermelon", 2.78, 700));
        crops.add(new Crops("Grape", 3.98, 200));
        crops.add(new Crops("Blue Rose", 99.99, 66));
        crops.add(new Crops("Cucumber", 5.89, 107));
        crops.add(new Crops("Tomato", 1.49, 856));
        logger.info("Five kinds of Plants in the garden right now: Watermelon, Grape, Blue Rose, Cucumber, Tomato.");
        return crops;
    }
}




