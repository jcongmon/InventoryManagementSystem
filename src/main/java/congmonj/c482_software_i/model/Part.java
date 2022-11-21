package congmonj.c482_software_i.model;

/**
 * Abstract Part class
 *
 * LOGIC ERROR: Set as an abstract class so parts cannot be instantiated and must have subclasses that can access the part class.
 * @author Jonathan Congmon
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor of the Part class.
     *
     * @param id    Part ID of part
     * @param name  Name of part
     * @param price Price of part
     * @param stock Inventory of part
     * @param min   Minimum inventory of part
     * @param max   Maximum inventory of part
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets part ID.
     *
     * @return part ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets part ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets part name.
     *
     * @return part name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets part name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets part price.
     *
     * @return part price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets part price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets part current inventory.
     *
     * @return part current inventory
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets part inventory.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets part minimum inventory.
     *
     * @return part minimum inventory
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets part minimum inventory.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets part maximum inventory.
     *
     * @return part maximum inventory
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets part maximum inventory.
     */
    public void setMax(int max) {
        this.max = max;
    }
}
