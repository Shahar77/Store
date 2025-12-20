// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

/**
 * Represents an object with manageable stock.
 */
public interface StockManageable{

    /**
     * Returns current stock amount.
     * @return stock
     */
    int getStock();

    /**
     * Increases stock.
     * @param amount amount to add
     * @return true if valid
     */
    boolean increaseStock(int amount);

    /**
     * Decreases stock.
     * @param amount amount to remove
     * @return true if valid
     */
    boolean decreaseStock(int amount);
}
