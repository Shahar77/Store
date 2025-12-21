// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.cart;

import store.products.Product;

/**
 * Represents a single item inside a shopping cart.
 * Contains a product and its quantity.
 */
public class CartItem {

    private Product product;
    private int quantity;

    /**
     * Creates a new CartItem with the given product and quantity.
     *
     * @param product product to store
     * @param quantity initial quantity
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Returns the product of this cart item.
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Returns the quantity of the product.
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets a new quantity for the product.
     *
     * @param q new quantity
     * @return true if quantity is valid and updated
     */
    public boolean setQuantity(int q) {
        if (q > 0) {
            this.quantity = q;
            return true;
        }
        return false;
    }

    /**
     * Calculates the total price for this cart item.
     *
     * @return total price
     */
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    /**
     * Two CartItem objects are equal if they contain the same product.
     *
     * @param o object to compare
     * @return true if products are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartItem)) return false;
        CartItem other = (CartItem) o;
        return product.equals(other.product);
    }
}
