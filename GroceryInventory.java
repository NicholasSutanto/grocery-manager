package hartmannlauteklesutanto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that manages the inventory for the program.
 *
 * @author Michael J Hartmann
 * @author Nicholas Sutanto
 * @author Yeabsira Y Tekle
 * @author Nghiep Thu Lau
 * @version 12/3/2017
 */
public class GroceryInventory {

    //implement a private inventory field
    private ArrayList<Grocery> groceryInventory;

    /**
     * Default constructor.
     */
    public GroceryInventory() {
        groceryInventory = new ArrayList<>();
        readInventory();
    }

    /**
     * Method updates the current grocery list.
     */
    public void updateGrocery() {
        writeInventory();
    }

    /**
     * Adds a product to the inventory. Prevents adding a duplicate product
     *
     * @param grocery the product to add
     * @return true if the product is added, false if not added
     */
    public boolean addGrocery(Grocery grocery) {
        if (!groceryInventory.contains(grocery)) {
            groceryInventory.add(grocery);
            writeInventory();
            return true;
        }
        return false;
    }

    /**
     * Deletes a product from the inventory.
     *
     * @param grocery object selected to be deleted
     */
    public void deleteGrocery(Grocery grocery) {
        if (groceryInventory.remove(grocery)) {
            writeInventory();
        }
    }

    /**
     * Gives permission to the inventory of products as a sorted array.
     *
     * @return the sorted array
     */
    public Grocery[] getSortedArray() {
        Collections.sort(groceryInventory);
        return groceryInventory.toArray(new Grocery[groceryInventory.size()]);
    }

    /**
     * Private method to write collection to a file.
     *
     * @return true if saved, false if not
     */
    private boolean writeInventory() {
        boolean success = true;
        try (FileOutputStream fos = new FileOutputStream("grocery.ser");
                ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(groceryInventory);
        } catch (Exception ex) {
            System.out.println("Cannot write to file:\n"
                    + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Private method to read collection from a file.
     *
     * @return true if read, false if not
     */
    private boolean readInventory() {
        boolean success = true;
        File ser = new File("grocery.ser");
        if (ser.exists()) {
            try (FileInputStream fis = new FileInputStream("grocery.ser");
                    ObjectInputStream input = new ObjectInputStream(fis)) {
                groceryInventory = (ArrayList<Grocery>) input.readObject();
            } catch (Exception ex) {
                System.out.println("Cannot read from file:\n"
                        + ex.getMessage());
                success = false;
            }
        }
        return success;
    }
}
