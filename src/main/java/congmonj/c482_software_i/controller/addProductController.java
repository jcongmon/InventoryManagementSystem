package congmonj.c482_software_i.controller;

import congmonj.c482_software_i.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller for the addProduct form.
 * <p>
 * RUNTIME ERROR: Adding parts without selecting a part to the product gave a NullPointerException. Fixed with a try/catch block.
 * LOGIC ERROR: Utilizing the search bar, we have to ensure we differentiate integers, strings, and check if the search bar is empty. Used if/else statements with a try/catch block.
 *
 * @author Jonathan Congmon
 */
public class addProductController {

    Stage stage;
    Parent scene;

    /**
     * Price column in the parts table.
     */
    @FXML
    private TableColumn<Part, Double> partCostCol;

    /**
     * Part ID column in the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * Inventory column in the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    /**
     * Name column in the parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Table view of parts.
     */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * Price column in the associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> productCostCol;

    /**
     * Text field to input product price for the product.
     */
    @FXML
    private TextField productCostTxt;

    /**
     * Part ID column in the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> productIdCol;

    /**
     * Text field for the ID of the product.
     */
    @FXML
    private TextField productIdTxt;

    /**
     * Inventory column in the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> productInventoryCol;

    /**
     * Text field to input the inventory of the product.
     */
    @FXML
    private TextField productInventoryTxt;

    /**
     * Text field to input the max inventory of the product.
     */
    @FXML
    private TextField productMaxTxt;

    /**
     * Text field to input the min inventory of the product.
     */
    @FXML
    private TextField productMinTxt;

    /**
     * Name column in the associated parts table.
     */
    @FXML
    private TableColumn<Part, String> productNameCol;

    /**
     * Text field to input the name of the product.
     */
    @FXML
    private TextField productNameTxt;

    /**
     * Text field to search the parts' table view for a part.
     */
    @FXML
    private TextField productSearchTxt;

    /**
     * Table view of associated parts.
     */
    @FXML
    private TableView<Part> productTableView;

    ObservableList<Part> tempParts = FXCollections.observableArrayList(); // auxiliary space for save/cancel feature

    /**
     * Add button on the addProduct form.
     *
     * @param event Clicking the add button.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NullPointerException, when user does not select an item from the parts table to add.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionAdd(ActionEvent event) throws IOException {
        try {
            Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            tempParts.add(selectedPart);
            productTableView.setItems(tempParts);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a part to add.");
            alert.showAndWait();
        }
    }

    /**
     * Cancel button on addProduct form.
     *
     * @param event Clicking the cancel button.
     * @throws IOException from FXMLLoader.load()
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Values will not be saved. Continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            tempParts.clear();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/congmonj/c482_software_i/mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Remove button on addProduct form.
     *
     * @param event Clicking the remove button.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NullPointerException, when user does not select an item from the associated parts' table to remove.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionRemove(ActionEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Part will be deleted. Continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                tempParts.remove(productTableView.getSelectionModel().getSelectedItem());
            }
        } catch (NullPointerException e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setContentText("Please select a part to delete.");
            alert1.showAndWait();
        }
    }

    /**
     * Search bar on addProduct form.
     *
     * @param event Pressing the enter button while the search bar is selected.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NumberFormatException, when a user searches for ID(int) vs Name(String).
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionPartsSearch(ActionEvent event) throws IOException {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        String textName = productSearchTxt.getText();
        if (textName.isEmpty())
            partsTableView.setItems(Inventory.getAllParts());
        else {
            try {
                Integer.parseInt(textName);
                int textId = Integer.parseInt(productSearchTxt.getText());
                for (Part part : Inventory.getAllParts()) {
                    if (part.getId() == textId)
                        filteredParts.add(part);
                }
                if (filteredParts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ID Error");
                    alert.setContentText("There is no part with that ID.");
                    alert.showAndWait();
                } else {
                    partsTableView.setItems(filteredParts);
                }
            } catch (NumberFormatException e) {
                for (Part part : Inventory.getAllParts()) {
                    if ((part.getName().toLowerCase()).contains(textName.toLowerCase()))
                        filteredParts.add(part);
                }
                if (filteredParts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Name Error");
                    alert.setContentText("There is no part with that name.");
                    alert.showAndWait();
                } else {
                    partsTableView.setItems(filteredParts);
                }
            }
        }
    }

    static int idCounter = 1002; // To ensure different IDs

    /**
     * Save button on modifyProduct form.
     *
     * @param event Clicking the save button.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NumberFormatException, when user does not enter a valid type for the text fields.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if (Integer.parseInt(productMaxTxt.getText()) < Integer.parseInt(productMinTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Min/Max");
                alert.setContentText("Min is greater than max. Set valid values.");
                alert.showAndWait();
            } else if (Integer.parseInt(productInventoryTxt.getText()) > Integer.parseInt(productMaxTxt.getText()) ||
                    Integer.parseInt(productInventoryTxt.getText()) < Integer.parseInt(productMinTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inventory Count");
                alert.setContentText("The inventory value is not between min and max.");
                alert.showAndWait();
            } else {
                int productId = idCounter;
                idCounter++;
                String productName = productNameTxt.getText();
                Double productCost = Double.parseDouble(productCostTxt.getText());
                int productInventory = Integer.parseInt(productInventoryTxt.getText());
                int productMax = Integer.parseInt(productMaxTxt.getText());
                int productMin = Integer.parseInt(productMinTxt.getText());
                Product newProduct = new Product(productId, productName, productCost, productInventory, productMin, productMax);
                Inventory.addProduct(newProduct);
                for (Part part : tempParts) {
                    newProduct.addAssociatedPart(part);
                }
                tempParts.clear();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/congmonj/c482_software_i/mainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
        }
    }
    // TODO JAVADOCS, FIGURE OUT HOW TO EXPORT JAVADOCS TO SEPARATE FILE

    /**
     * Populates the auto-generated ID and add parts and associated parts tables.
     */
    @FXML
    public void initialize() {
        productIdTxt.setText(String.valueOf(idCounter));
        partsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}