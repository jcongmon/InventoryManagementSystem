package congmonj.c482_software_i.controller;

import congmonj.c482_software_i.model.InHouse;
import congmonj.c482_software_i.model.Inventory;
import congmonj.c482_software_i.model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller for the add parts form.
 * <p>
 * RUNTIME ERRORS: Upon saving, if no information is entered, gives a NumberFormatException. Used try/catch blocks to fix with a warning.
 * LOGIC ERRORS: Ensuring inventory is between min and max, used if/else statements within the try/catch block.
 *
 * @author Jonathan Congmon
 */
public class addPartController {
    Stage stage;
    Parent scene;

    /**
     * In-house radio button on the addPart form.
     */
    @FXML
    private RadioButton addPartInHouseRadioBtn;

    /**
     * Outsourced radio button on the addPart form.
     */
    @FXML
    private RadioButton addPartOutsourcedRadioBtn;

    /**
     * MachineID/Company name label on the addPart form.
     */
    @FXML
    private Label toggleLbl;

    /**
     * Price text field on the addPart form.
     */
    @FXML
    private TextField partCostTxt;

    /**
     * Part ID text field on the addPart form.
     */
    @FXML
    private TextField partIdTxt;

    /**
     * Inventory text field on the addPart form.
     */
    @FXML
    private TextField partInventoryTxt;

    /**
     * MachineID/Company name text field on the addPart form.
     */
    @FXML
    private TextField toggleTxt;

    /**
     * Max text field on the addPart form.
     */
    @FXML
    private TextField partMaxTxt;

    /**
     * Min text field on the addPart form.
     */
    @FXML
    private TextField partMinTxt;

    /**
     * Part name text field on the addPart form.
     */
    @FXML
    private TextField partNameTxt;

    /**
     * Cancel button on the addPart form.
     *
     * @param event Clicking the cancel button
     * @throws IOException from FXMLLoader.load()
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Values will not be saved. Continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/congmonj/c482_software_i/mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * In-House radio button selected sets the label text to Machine ID.
     *
     * @param event Clicking the In-House radio button.
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        toggleLbl.setText("Machine ID");
    }

    /**
     * Outsourced radio button selected sets the label text to Company Name.
     *
     * @param event Clicking the Outsourced radio button.
     */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        toggleLbl.setText("Company Name");
    }

    static int idCounter = 4; // To ensure different IDs.

    /**
     * Save button on add parts form.
     *
     * @param event Clicking the save button.
     * @throws IOException from FXMLLoader.load()
     *                     <p>
     *                     RUNTIME ERROR: NumberFormatException, when inputs do not match expected input types.
     *                     Fixed with Try/Catch blocks.
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            if (Integer.parseInt(partMaxTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Min/Max");
                alert.setContentText("Min is greater than max. Set valid values.");
                alert.showAndWait();
            } else if (Integer.parseInt(partInventoryTxt.getText()) > Integer.parseInt(partMaxTxt.getText()) ||
                    Integer.parseInt(partInventoryTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inventory Count");
                alert.setContentText("The inventory value is not between min and max.");
                alert.showAndWait();
            } else {
                int partId = idCounter;
                idCounter++;
                String partName = partNameTxt.getText();
                Double partCost = Double.parseDouble(partCostTxt.getText());
                int partInventory = Integer.parseInt(partInventoryTxt.getText());
                int partMax = Integer.parseInt(partMaxTxt.getText());
                int partMin = Integer.parseInt(partMinTxt.getText());
                int partMachineId;
                String companyName;
                if (addPartInHouseRadioBtn.isSelected()) {
                    partMachineId = Integer.parseInt(toggleTxt.getText());
                    Inventory.addPart(new InHouse(partId, partName, partCost, partInventory, partMin, partMax, partMachineId));
                } else if (addPartOutsourcedRadioBtn.isSelected()) {
                    companyName = toggleTxt.getText();
                    Inventory.addPart(new Outsourced(partId, partName, partCost, partInventory, partMin, partMax, companyName));
                }
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

    /**
     * Initializes the new part ID and ensures that a radio button is chosen.
     */
    @FXML
    public void initialize() {
        partIdTxt.setText(String.valueOf(idCounter));
        addPartInHouseRadioBtn.setSelected(true);
    }
}