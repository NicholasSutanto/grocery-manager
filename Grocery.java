package hartmannlauteklesutanto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data class representing grocery.
 *
 * @author Michael J Hartmann
 * @author Nicholas Sutanto
 * @author Yeabsira Y Tekle
 * @author Nghiep Thu Lau
 * @version 12/3/2017
 */
public class Grocery implements Comparable<Grocery>, Serializable {

    //Private fields
    private String description;
    private int category;
    private int quantity;
    private double storeCost;
    private double customerPrice;

    /**
     * Constructor for Grocery class used to construct grocery food items
     *
     * @param description
     * @param category
     * @param quantity
     * @param storeCost
     * @param customerPrice
     */
    public Grocery(
            String description,
            int category,
            int quantity,
            double storeCost,
            double customerPrice) {
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.storeCost = storeCost;
        this.customerPrice = customerPrice;
    }

    /**
     * Gives access to customer price which is defined as price customer pays
     *
     * @return Customer Price
     */
    public double getCustomerPrice() {
        return customerPrice;
    }

    /**
     * Sets customer price
     *
     * @param customerPrice the cost of the product the customer is paying
     */
    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }

    /**
     * Gives access to store cost
     *
     * @return the store cost
     */
    public double getStoreCost() {
        return storeCost;
    }

    /**
     * Sets the store cost.
     *
     * @param storeCost the cost of products the store pays supplier(s)
     */
    public void setStoreCost(double storeCost) {
        this.storeCost = storeCost;
    }

    /**
     * Gives access to quantity
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity
     *
     * @param quantity the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gives access to category
     *
     * @return the category
     */
    public int getCategory() {
        return category;
    }

    /**
     * Sets the category
     *
     * @param category the category of the product
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * Gives access to description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     *
     * @param description the description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Calculates the profit made by the grocery store using the quantity, store
     * cost, and customer price data fields
     *
     * @return the profit
     */
    public double getProfit() {
        return quantity * (customerPrice - storeCost);
    }

    /**
     * Provides a string representation of the grocery data.
     *
     * @return the grocery item's description
     */
    @Override
    public String toString() {
        return description.toUpperCase();
    }

    /**
     * Creates a unique identifier for the grocery item.
     *
     * @return the unique identifier
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    /**
     * Determines if one grocery item has the same description as another.
     *
     * @param obj the other contact
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grocery other = (Grocery) obj;
        if (!Objects.equals(this.description.toUpperCase(),
                other.description.toUpperCase())) {
            return false;
        }
        return true;
    }

    /**
     * Compares this grocery object with another for sorting purposes.
     *
     * @param o the other grocery item
     * @return a number less than 0 if this contact should come before the
     * other, 0 if they are the same, and a number greater than 0 if this
     * grocery item should come after the other grocery item. The description
     * field is used in this comparison test.
     */
    @Override
    public int compareTo(Grocery o) {
        if (o != null) {
            return this.description.compareToIgnoreCase(o.description);
        }
        return 1;
    }
}
