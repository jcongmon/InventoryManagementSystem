package congmonj.c482_software_i;

import congmonj.c482_software_i.model.InHouse;
import congmonj.c482_software_i.model.Inventory;
import congmonj.c482_software_i.model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main subclass. Links FXML to controllers.
 * JavaDocs is located in the JavaDocs folder
 * FUTURE ENHANCEMENT: Updating inventory values as they are added/removed from products.
 * FUTURE ENHANCEMENT: Controlling which parts can be added to products. A fourth wheel cannot be added to a tricycle.
 * RUNTIME ERROR: TableViews were empty upon initialization. Needed to manually populate the tables under main().
 *
 * @author Jonathan Congmon
 */
public class Main extends Application {

    /**
     * Upon launch, opens the mainMenu form.
     *
     * @throws IOException from FXMLLoader.load()
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 350);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds initial objects to table views and launches applicaiton.
     */
    public static void main(String[] args) {
        InHouse part1 = new InHouse(1, "Brakes", 14.99, 10, 1, 20, 111);
        InHouse part2 = new InHouse(2, "Wheel", 10.99, 16, 1, 20, 121);
        InHouse part3 = new InHouse(3, "Seat", 14.99, 10, 1, 20, 131);
        Product product1 = new Product(1000, "Giant Bike", 299.99, 5, 1, 20);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        Product product2 = new Product(1001, "Tricycle", 99.99, 3, 1, 20);
        product2.addAssociatedPart(part2);
        product2.addAssociatedPart(part3);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        launch();
    }
}