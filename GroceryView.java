package hartmannlauteklesutanto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

/**
 * Data class representing program GUI.
 *
 * @author Michael J Hartmann
 * @author Nicholas Sutanto
 * @author Yeabsira Y Tekle
 * @author Nghiep Thu Lau
 * @version 12/3/2017
 */
public class GroceryView extends JFrame {

    //private fields
    private JComboBox<Grocery> cboGrocery;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTextField txtDescription;
    private JComboBox cboCategory;
    private JTextField txtQuantity;
    private JTextField txtCost;
    private JTextField txtPrice;
    private JTextArea txtProfit;
    private GroceryInventory inventory;

    /**
     * Constructor called to construct GUI of program
     *
     * @param title
     */
    public GroceryView(String title) {
        super(title);
        this.setMinimumSize(new Dimension(475, 350));
        inventory = new GroceryInventory();
        addComponents();
        addEventHandlers();
        initializeDisplay();
    }

    /**
     * Private method used to add the components to the frame
     */
    private void addComponents() {
        //split frame into three panels
        JPanel pnlMain = new JPanel(new BorderLayout());
        JPanel pnlBottom = new JPanel(new BorderLayout());
        JPanel pnlRSide = new JPanel(new BorderLayout());
        //create list at top of main panel
        cboGrocery = new JComboBox<>(inventory.getSortedArray());
        pnlMain.add(cboGrocery, BorderLayout.NORTH);
        //create controls at right side
        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(
                new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
        btnAdd = new JButton("      ADD      ");
        btnUpdate = new JButton("   UPDATE  ");
        btnDelete = new JButton("   DELETE   ");
        pnlControls.add(btnAdd);
        pnlControls.add(btnUpdate);
        pnlControls.add(btnDelete);
        pnlControls.setBackground(new Color(12, 27, 124));
        pnlRSide.add(pnlControls, BorderLayout.EAST);
        //create details panel
        JPanel pnlDetails = new JPanel();
        pnlDetails.setLayout(
                new BoxLayout(pnlDetails, BoxLayout.Y_AXIS));
        JPanel pnlDescription = new JPanel();
        JPanel pnlCategory = new JPanel();
        JPanel pnlQuantity = new JPanel();
        JPanel pnlCost = new JPanel();
        JPanel pnlPrice = new JPanel();
        //description panel
        pnlDescription.setLayout(
                new BoxLayout(pnlDescription, BoxLayout.Y_AXIS));
        JLabel lblDescription = new JLabel("Description:");
        txtDescription = new JTextField(25);
        pnlDescription.add(lblDescription).setForeground(Color.white);
        pnlDescription.add(txtDescription);
        pnlDetails.add(pnlDescription).setBackground(new Color(12, 27, 124));
        //category panel
        pnlCategory.setLayout(
                new BoxLayout(pnlCategory, BoxLayout.Y_AXIS));
        JLabel lblCategory = new JLabel("Category");
        String[] categoryList = {"Produce", "Seafood and Meat",
            "Dairy, Eggs, & Cheese", "Wine & Spirits", "Bread & Bakery",
            "Deli & Prepared Foods", "Grocery", "Frozen", "Other"};
        cboCategory = new JComboBox(categoryList);
        pnlCategory.add(lblCategory).setForeground(Color.white);
        pnlCategory.add(cboCategory);
        pnlDetails.add(pnlCategory).setBackground(new Color(12, 27, 124));
        //quantity panel
        pnlQuantity.setLayout(
                new BoxLayout(pnlQuantity, BoxLayout.Y_AXIS));
        JLabel lblQuantity = new JLabel("Quantity:");
        txtQuantity = new JTextField(25);
        pnlQuantity.add(lblQuantity).setForeground(Color.white);
        pnlQuantity.add(txtQuantity);
        pnlDetails.add(pnlQuantity).setBackground(new Color(12, 27, 124));
        //cost panel
        pnlCost.setLayout(
                new BoxLayout(pnlCost, BoxLayout.Y_AXIS));
        JLabel lblCost = new JLabel("Store Cost (Example: 1.00):");
        txtCost = new JTextField(25);
        pnlCost.add(lblCost).setForeground(Color.white);
        pnlCost.add(txtCost);
        pnlDetails.add(pnlCost).setBackground(new Color(12, 27, 124));
        //price panel
        pnlPrice.setLayout(
                new BoxLayout(pnlPrice, BoxLayout.Y_AXIS));
        JLabel lblPrice = new JLabel("Customer Price (Example: 2.00):");
        txtPrice = new JTextField(25);
        pnlPrice.add(lblPrice).setForeground(Color.WHITE);
        pnlPrice.add(txtPrice);
        pnlDetails.add(pnlPrice).setBackground(new Color(12, 27, 124));
        ;
        //add details panel
        pnlMain.add(pnlDetails);
        //create profit data text area here
        txtProfit = new JTextArea();
        txtProfit.setFont(new Font("Arial", Font.PLAIN, 16));
        txtProfit.setBackground(new Color(185, 200, 220));
        txtProfit.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        txtProfit.setText("Profit Calculation \n");
        pnlBottom.add(txtProfit);
        //add three panels to frame
        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlBottom, BorderLayout.SOUTH);
        this.add(pnlRSide, BorderLayout.EAST);

    }

    /**
     * Private method used to set up the event handlers.
     */
    private void addEventHandlers() {
        //update event handler
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtDescription.getText().isEmpty()
                        && !txtQuantity.getText().isEmpty()
                        && !txtCost.getText().isEmpty()
                        && !txtPrice.getText().isEmpty()) {
                    //Set new grocery object data to grocery object selected
                    Grocery g = (Grocery) cboGrocery.getSelectedItem();
                    g.setDescription(txtDescription.getText().toUpperCase());
                    g.setCategory(cboCategory.getSelectedIndex());
                    g.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    g.setStoreCost(Double.parseDouble(txtCost.getText()));
                    g.setCustomerPrice(Double.parseDouble(txtPrice.getText()));
                    //update grocery list, combo box, txtarea profit
                    inventory.updateGrocery();
                    cboGrocery.updateUI();
                    txtProfit.setText("Profit Calculation"
                            + "\n" + "(Quantity * (Customer Price - Store Cost))"
                            + "\n" + "$ ");
                    txtProfit.append(String.format("%.2f", g.getProfit()));
                }
            }
        });
        //delete event handler
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnDelete.getText().equals("   CANCEL   ")) {
                    //cancel a new request
                    btnAdd.setText("      ADD      ");
                    btnDelete.setText("   DELETE   ");
                    btnUpdate.setEnabled(true);
                    if (cboGrocery.getItemCount() > 0) {
                        cboGrocery.setSelectedIndex(0);
                    }
                } else {
                    //delete current contact
                    if (cboGrocery.getItemCount() > 0) {
                        int reply = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this entry?");
                        if (reply == JOptionPane.YES_OPTION) {
                            inventory.deleteGrocery((Grocery) cboGrocery.getSelectedItem());
                            cboGrocery.removeItem((Grocery) cboGrocery.getSelectedItem());
                            cboGrocery.updateUI();
                        }
                    }
                }
            }
        });
        //add button event handler
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new event handler
                if (btnAdd.getText().equals("      ADD      ")) {
                    txtDescription.setEnabled(true);
                    txtQuantity.setEnabled(true);
                    txtCost.setEnabled(true);
                    txtPrice.setEnabled(true);

                    txtDescription.setText("");
                    cboCategory.setSelectedIndex(0);
                    txtQuantity.setText("");
                    txtCost.setText("");
                    txtPrice.setText("");

                    btnAdd.setText("   SUBMIT  ");
                    btnDelete.setText("   CANCEL   ");
                    btnUpdate.setEnabled(false);
                    txtProfit.setText("Profit Calculation");
                } else {
                    if (!txtDescription.getText().isEmpty()
                            && !txtQuantity.getText().isEmpty()
                            && !txtCost.getText().isEmpty()
                            && !txtPrice.getText().isEmpty()) {

                        Grocery g = new Grocery(
                                txtDescription.getText().toUpperCase(),
                                cboCategory.getSelectedIndex(),
                                Integer.parseInt(txtQuantity.getText()),
                                Double.parseDouble(txtCost.getText()),
                                Double.parseDouble(txtPrice.getText()));
                        if (inventory.addGrocery(g)) {
                            cboGrocery.setModel(
                                    new DefaultComboBoxModel<>(
                                            inventory.getSortedArray()));
                            cboGrocery.setSelectedItem(g);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Duplicate name - cannot add.");
                            cboGrocery.setSelectedIndex(0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please enter values in all fields");
                        return;
                    }
                    btnAdd.setText("      ADD      ");
                    btnDelete.setText("   DELETE   ");
                    btnUpdate.setEnabled(true);
                }
            }
        });
        //combo box inventory event handler
        cboGrocery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboGrocery.getItemCount() > 0
                        && cboGrocery.getSelectedItem() != null) {
                    Grocery g = (Grocery) cboGrocery.getSelectedItem();
                    txtDescription.setText(g.getDescription());
                    cboCategory.setSelectedIndex(g.getCategory());
                    txtQuantity.setText(String.valueOf(g.getQuantity()));
                    txtCost.setText(String.format("%.2f", g.getStoreCost()));
                    txtPrice.setText(String.format("%.2f", g.getCustomerPrice()));
                    txtProfit.setText("Profit Calculation"
                            + "\n" + "(Quantity * (Customer Price - Store Cost))"
                            + "\n" + "$ ");
                    txtProfit.append(String.format("%.2f", g.getProfit()));
                } else {
                    txtDescription.setText("");
                    cboCategory.setSelectedIndex(0);
                    txtQuantity.setText("");
                    txtCost.setText("");
                    txtPrice.setText("");
                    txtDescription.setEnabled(false);
                    txtQuantity.setEnabled(false);
                    txtCost.setEnabled(false);
                    txtPrice.setEnabled(false);
                    txtProfit.setText("Profit Calculation \n");
                }
            }
        });
    }

    /**
     * Private method used to set the selected contact to the first in the
     * collection or to disable the text fields if not contacts exist.
     */
    private void initializeDisplay() {
        if (cboGrocery.getItemCount() > 0) {
            cboGrocery.setSelectedIndex(0);
        } else {
            txtDescription.setEnabled(false);
            cboCategory.setSelectedIndex(0);
            txtQuantity.setEnabled(false);
            txtCost.setEnabled(false);
            txtPrice.setEnabled(false);
        }
    }
}
