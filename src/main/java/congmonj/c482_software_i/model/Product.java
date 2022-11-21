package congmonj.c482_software_i.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class
 *
 * LOGIC ERROR: Used the final keyword for the associatedParts observable list to ensure it cannot be reused anywhere else.
 * @author Jonathan Congmon
 */
public class Product {
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor of the Product class.
     *
     * @param id    Product ID of product
     * @param name  Name of product
     * @param price Price of product
     * @param stock Inventory of product
     * @param min   Minimum inventory of product
     * @param max   Maximum inventory of product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Gets product ID.
     *
     * @return product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets product ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets product name.
     *
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets product price.
     *
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets product inventory.
     *
     * @return product inventory
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets product inventory.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets product minimum inventory.
     *
     * @return product minimum inventory
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets product minimum inventory.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets product maximum inventory.
     *
     * @return product maximum inventory
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets product maximum inventory.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds part to the associated parts observable list.
     *
     * @param part Part to add to associated parts.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Removes part from the associated parts observable list.
     *
     * @param selectedAssociatedPart Part to remove from associated parts.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        return false;
    }

    /**
     * Returns the associatedParts observable list.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
