package congmonj.c482_software_i.model;

/**
 * In-House subclass of the Parts class.
 *
 * LOGIC ERROR: Needed to use the super() function to ensure the constructor can be called correctly.
 * @author Jonathan Congmon
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor of the InHouse class.
     *
     * @param id        Part ID of part
     * @param name      Name of part
     * @param price     Price of part
     * @param stock     Part ID of part
     * @param min       Minimum inventory of part
     * @param max       Maximum inventory of part
     * @param machineId Machine ID of part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Gets machine ID.
     *
     * @return Machine ID
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets machine ID.
     *
     * @param machineId Machine ID
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
