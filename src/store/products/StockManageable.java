// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118

package store.products;

/**
 * Interface for products that manage stock quantity.
 * Implementing classes must provide access to current stock
 * and support decreasing stock after purchase.
 */
public interface StockManageable{

    /**
     * Returns the current available stock.
     * @return number of items in stock
     */
    int getStock();

    /**
     * Decreases the stock by the given amount if possible.
     * The operation should fail if the amount is non-positive
     * or if there is not enough stock.
     *
     * @param amount number of units to decrease
     * @return true if stock was successfully decreased,false otherwise
     */
    boolean decreaseStock(int amount);
}
