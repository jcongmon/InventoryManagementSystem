package congmonj.c482_software_i.model;

/**
 * Outsourced subclass of the Parts class.
 *
 * LOGIC ERROR: Needed to use the super() function to ensure the constructor can be called correctly.
 * @author Jonathan Congmon
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructor of the InHouse class.
     *
     * @param id          Part ID of part
     * @param name        Name of part
     * @param price       Price of part
     * @param stock       Part ID of part
     * @param min         Minimum inventory of part
     * @param max         Maximum inventory of part
     * @param companyName Company name of part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Gets Company name.
     *
     * @return Company Name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets Company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
