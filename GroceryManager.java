package hartmannlauteklesutanto;

import javax.swing.JFrame;

/**
 * Main class that calls GUI of grocery store manager. A program intended to
 * assist with managing the inventory for the grocery store.
 *
 * @author Michael J Hartmann
 * @author Nicholas Sutanto
 * @author Yeabsira Y Tekle
 * @author Nghiep Thu Lau
 * @version 12/3/2017
 */
public class GroceryManager {

    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        GroceryView view = new GroceryView("Grocery Store Manager");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //makes JFrame as small as possible
        view.pack();
        //centers JFrame on screen
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
