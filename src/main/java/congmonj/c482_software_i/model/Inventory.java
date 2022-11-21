package congmonj.c482_software_i.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class with dependencies on the Parts abstract class and Product class.
 *
 * LOGIC ERROR: Made all observable lists private to ensure the lists cannot be accessed by accident.
 * @author Jonathan Congmon
 */
public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static final ObservableList<Part> lookupListParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> lookupListProducts = FXCollections.observableArrayList();

    /**
     * Adds part to the allParts observable list.
     *
     * @param newPart New part to add.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds product to the allProducts observable list.
     *
     * @param newProduct New product to add.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks for part in allParts that matches the partID parameter.
     *
     * @param partId Part to search for based on ID.
     * @return Part found to return, else returns null.
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts)
            if (part.getId() == partId)
                return part;
        return null;
    }

    /**
     * Looks for part in allProducts that matches the productID parameter.
     *
     * @param productId Product to search for based on ID.
     * @return Product found to return, else returns null.
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts)
            if (product.getId() == productId)
                return product;
        return null;
    }

    /**
     * Looks for part in allParts that matches the partName parameter.
     *
     * @param partName Part to search for based on the name.
     * @return Returns and observable list with parts that match search string.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        if (!lookupListParts.isEmpty())
            lookupListParts.clear();
        for (Part part : allParts) {
            if (part.getName().contains(partName))
                lookupListParts.add(part);
        }
        if (lookupListParts.isEmpty())
            return allParts;
        else
            return lookupListParts;
    }

    /**
     * Looks for product in allProducts that matches the productName parameter.
     *
     * @param productName Part to search for based on the name.
     * @return Returns and observable list with parts that match search string.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        if (!lookupListProducts.isEmpty())
            lookupListProducts.clear();
        for (Product product : allProducts) {
            if (product.getName().contains(productName))
                lookupListProducts.add(product);
        }
        if (lookupListProducts.isEmpty())
            return allProducts;
        else
            return lookupListProducts;
    }

    /**
     * Looks for part in allParts and updates that part.
     *
     * @param index        Part ID
     * @param selectedPart Part to be updated
     */
    public static void updatePart(int index, Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++)
            if (allParts.get(i).getId() == index)
                Inventory.getAllParts().set(i, selectedPart);
    }

    /**
     * Looks for product in allProducts and updates that product.
     *
     * @param index      Product ID to be updated
     * @param newProduct Product object to be updated
     */
    public static void updateProduct(int index, Product newProduct) {
        for (int i = 0; i < allProducts.size(); i++)
            if (allProducts.get(i).getId() == index)
                Inventory.getAllProducts().set(i, newProduct);
    }

    /**
     * Looks for part in allParts that matches the selectedPart parameter to delete.
     *
     * @param selectedPart Part to be deleted
     */
    public static boolean deletePart(Part selectedPart) {
        for (Part part : allParts)
            if (part.getId() == selectedPart.getId()) {
                allParts.remove(selectedPart);
                return true;
            }
        return false;
    }

    /**
     * Looks for product in allProducts that matches the selectedProduct parameter to delete.
     *
     * @param selectedProduct Product to be deleted
     */
    public static boolean deleteProduct(Product selectedProduct) {
        for (Product product : allProducts)
            if (product.getId() == selectedProduct.getId()) {
                allProducts.remove(selectedProduct);
                return true;
            }
        return false;
    }

    /**
     * Returns the allParts ObservableList.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns the allProducts ObservableList.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
