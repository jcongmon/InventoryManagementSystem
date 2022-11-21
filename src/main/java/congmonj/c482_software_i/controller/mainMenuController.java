package congmonj.c482_software_i.controller;

import congmonj.c482_software_i.model.Inventory;
import congmonj.c482_software_i.model.Part;
import congmonj.c482_software_i.model.Product;
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
 * Controller for the main menu form.
 * <p>
 * RUNTIME ERROR: Modifying parts/products or deleting a part/product needs a table view selection or the program gives a NullPointerException. Fixed with a try/catch block.
 * RUNTIME ERROR: Searching for a part/product gives a NumberFormatException if enters an int/string for search. Fixed with a try/catch block.
 * LOGIC ERROR: Used if/else statements in the search bar functionality to differentiate between int/strings/empty string.
 *
 * @author Jonathan Congmon
 */
public class mainMenuController {
    Stage stage;
    Parent scene;

    /**
     * Text field to search the parts' table view for a part.
     */
    @FXML
    private TextField partIdTxt;

    /**
     * Text field to search the products' table view for a part.
     */
    @FXML
    private TextField productIdTxt;

    /**
     * Table view of parts.
     */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * Table view of products.
     */
    @FXML
    private TableView<Product> productsTableView;

    /**
     * Part ID column in the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * Name column in the parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Inventory column in the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    /**
     * Price column in the parts table.
     */
    @FXML
    private TableColumn<Part, Double> partCostCol;

    /**
     * Product ID column in the products table.
     */
    @FXML
    private TableColumn<Product, Integer> productIdCol;

    /**
     * Name column in the products table.
     */
    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * Inventory column in the products table.
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    /**
     * Price column in the products table.
     */
    @FXML
    private TableColumn<Product, Double> productCostCol;

    /**
     * Add button on the main menu form leading to the add parts form.
     *
     * @param event Clicking the add button under the parts table.
     * @throws IOException from FXMLLoader.load()
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/congmonj/c482_software_i/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Modify button on the main menu form leading to the modify parts form.
     *
     * @param event Clicking the modify button under the parts table.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NullPointerException, when user does not select an item from the parts table to modify.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/congmonj/c482_software_i/modifyPart.fxml"));
            loader.load();
            modifyPartController MPController = loader.getController();
            MPController.sendPart(partsTableView.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
        }
    }

    /**
     * Delete button on the main menu form for the parts table.
     *
     * @param event Clicking the delete button under the parts table.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NullPointerException, when user does not select an item from the parts table to delete.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Part will be deleted. Continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        }
    }

    /**
     * Add button on the main menu form leading to the add products form.
     *
     * @param event Clicking the add button under the products table.
     * @throws IOException from FXMLLoader.load()
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/congmonj/c482_software_i/addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Modify button on the main menu form leading to the modify product form.
     *
     * @param event Clicking the modify button under the products table.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NullPointerException, when user does not select an item from the product table to modify.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/congmonj/c482_software_i/modifyProduct.fxml"));
            loader.load();
            modifyProductController MPController = loader.getController();
            MPController.sendProduct(productsTableView.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a product to modify.");
            alert.showAndWait();
        }
    }

    /**
     * Delete button on the main menu form for the products table.
     *
     * @param event Clicking the delete button under the products table.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NullPointerException, when user does not select an item from the product table to delete.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        try {
            Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            if (selectedProduct.getAllAssociatedParts().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Associated Parts");
                alert.setContentText("Current product has parts associated with it. Remove parts before deletion.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Product will be deleted. Continue?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        }
    }

    /**
     * Exit button on the main menu form.
     *
     * @param event Clicking the exit button.
     * @throws IOException from FXMLLoader.load()
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Search bar on the main menu form for the parts table.
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
        String textName = partIdTxt.getText();
        if (textName.isEmpty())
            partsTableView.setItems(Inventory.getAllParts());
        else {
            try {
                Integer.parseInt(textName);
                int textId = Integer.parseInt(partIdTxt.getText());
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

    /**
     * Search bar on the main menu form for the products table.
     *
     * @param event Pressing the enter button while the search bar is selected.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NumberFormatException, when a user searches for ID(int) vs Name(String).
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionProductSearch(ActionEvent event) throws IOException {
        ObservableList<Product> filteredParts = FXCollections.observableArrayList();
        String textName = productIdTxt.getText();
        if (textName.isEmpty())
            productsTableView.setItems(Inventory.getAllProducts());
        else {
            try {
                Integer.parseInt(textName);
                int textId = Integer.parseInt(productIdTxt.getText());
                for (Product product : Inventory.getAllProducts()) {
                    if (product.getId() == textId)
                        filteredParts.add(product);
                }
                if (filteredParts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ID Error");
                    alert.setContentText("There is no product with that ID.");
                    alert.showAndWait();
                } else {
                    productsTableView.setItems(filteredParts);
                }
            } catch (NumberFormatException e) {
                for (Product product : Inventory.getAllProducts()) {
                    if ((product.getName().toLowerCase()).contains(textName.toLowerCase()))
                        filteredParts.add(product);
                }
                if (filteredParts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Name Error");
                    alert.setContentText("There is no product with that name.");
                    alert.showAndWait();
                } else {
                    productsTableView.setItems(filteredParts);
                }
            }
        }
    }

    /**
     * Populates the add parts and products tables.
     */
    @FXML
    public void initialize() {
        partsTableView.setItems(Inventory.getAllParts());
        productsTableView.setItems(Inventory.getAllProducts());
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
