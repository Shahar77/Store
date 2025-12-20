// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

/**
 * Represents an item that has a price.
 */
public interface PricedItem{

    /**
     * Returns the price of the item.
     * @return price
     */
    double getPrice();

    /**
     * Updates the price.
     * @param price new price
     * @return true if valid
     */
    boolean setPrice(double price);
}

