package congmonj.c482_software_i.controller;

import congmonj.c482_software_i.model.InHouse;
import congmonj.c482_software_i.model.Inventory;
import congmonj.c482_software_i.model.Outsourced;
import congmonj.c482_software_i.model.Part;
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
 * Controller for the modify parts form.
 * <p>
 * RUNTIME ERRORS: Upon saving, if no information is entered, gives a NumberFormatException. Used try/catch blocks to fix with a warning.
 * LOGIC ERRORS: Ensuring inventory is between min and max, used if/else statements within the try/catch block.
 *
 * @author Jonathan Congmon
 */
public class modifyPartController {
    Stage stage;
    Parent scene;

    /**
     * In-house radio button on the modifyPart form.
     */
    @FXML
    private RadioButton modifyPartInHouseRadioBtn;

    /**
     * Outsourced radio button on the modifyPart form.
     */
    @FXML
    private RadioButton modifyPartOutsourcedRadioBtn;

    /**
     * Price text field on the modifyPart form.
     */
    @FXML
    private TextField partCostTxt;

    /**
     * Part ID text field on the modifyPart form.
     */
    @FXML
    private TextField partIdTxt;

    /**
     * Inventory text field on the modifyPart form.
     */
    @FXML
    private TextField partInventoryTxt;

    /**
     * MachineID/Company name text field on the modifyPart form.
     */
    @FXML
    private TextField toggleTxt;

    /**
     * Max text field on the modifyPart form.
     */
    @FXML
    private TextField partMaxTxt;

    /**
     * Min text field on the modifyPart form.
     */
    @FXML
    private TextField partMinTxt;

    /**
     * Name text field on the modifyPart form.
     */
    @FXML
    private TextField partNameTxt;

    /**
     * Machine ID/Company name label on the modifyPart form.
     */
    @FXML
    private Label toggleLbl;

    /**
     * Cancel button on the modifyPart form.
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
     * Save button on modifyPart form.
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
            } else if (Integer.parseInt(partInventoryTxt.getText()) > Integer.parseInt(partMaxTxt.getText()) || Integer.parseInt(partInventoryTxt.getText()) < Integer.parseInt(partMinTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inventory Count");
                alert.setContentText("The inventory value is not between min and max.");
                alert.showAndWait();
            } else {
                int partId = Integer.parseInt(partIdTxt.getText());
                String partName = partNameTxt.getText();
                Double partCost = Double.parseDouble(partCostTxt.getText());
                int partInventory = Integer.parseInt(partInventoryTxt.getText());
                int partMax = Integer.parseInt(partMaxTxt.getText());
                int partMin = Integer.parseInt(partMinTxt.getText());
                int partMachineId;
                String companyName;
                if (modifyPartInHouseRadioBtn.isSelected()) {
                    partMachineId = Integer.parseInt(toggleTxt.getText());
                    InHouse part = new InHouse(partId, partName, partCost, partInventory, partMin, partMax, partMachineId);
                    Inventory.updatePart(partId, part);
                } else if (modifyPartOutsourcedRadioBtn.isSelected()) {
                    companyName = toggleTxt.getText();
                    Outsourced part = new Outsourced(partId, partName, partCost, partInventory, partMin, partMax, companyName);
                    Inventory.updatePart(partId, part);
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

    /**
     * Used by the mainMenu form to populate the modifyParts form
     * with information about the part to be modified.
     *
     * @param part Part to be modified.
     */
    public void sendPart(Part part) {
        partIdTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        partInventoryTxt.setText(String.valueOf(part.getStock()));
        partCostTxt.setText(String.valueOf(part.getPrice()));
        partMinTxt.setText(String.valueOf(part.getMin()));
        partMaxTxt.setText(String.valueOf(part.getMax()));

        if (part instanceof InHouse) {
            modifyPartInHouseRadioBtn.setSelected(true);
            toggleTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
        } else if (part instanceof Outsourced) {
            modifyPartOutsourcedRadioBtn.setSelected(true);
            toggleLbl.setText("Company Name");
            toggleTxt.setText(String.valueOf(((Outsourced) part).getCompanyName()));
        }
    }
}