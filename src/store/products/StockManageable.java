// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

/**
 * Interface for objects whose stock can be managed.
 * Allows increasing and decreasing stock values.
 */
public interface StockManageable {

    /**
     * Returns the current stock amount.
     *
     * @return stock amount
     */
    int getStock();

    /**
     * Increases the stock by the given amount.
     *
     * @param amount amount to add
     * @return true if the amount is valid and stock updated
     */
    boolean increaseStock(int amount);

    /**
     * Decreases the stock by the given amount.
     *
     * @param amount amount to remove
     * @return true if the amount is valid and stock updated
     */
    boolean decreaseStock(int amount);
}
