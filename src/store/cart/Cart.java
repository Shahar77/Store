// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.cart;

import store.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for a customer.
 * The cart holds a list of CartItem objects and allows item management.
 */
public class Cart {

    private List<CartItem> items;

    /**
     * Creates an empty shopping cart.
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart with the given quantity.
     * If the product already exists in the cart, its quantity is increased.
     *
     * @param p product to add
     * @param quantity quantity to add
     * @return true if the item was added or updated successfully
     */
    public boolean addItem(Product p, int quantity) {
        if (p == null || quantity <= 0) return false;

        for (CartItem ci : items) {
            if (ci.getProduct().equals(p)) {
                ci.setQuantity(ci.getQuantity() + quantity);
                return true;
            }
        }

        items.add(new CartItem(p, quantity));
        return true;
    }

    /**
     * Removes a product from the cart.
     *
     * @param p product to remove
     * @return true if the product was found and removed
     */
    public boolean removeItem(Product p) {
        if (p == null) return false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().equals(p)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Decreases the quantity of a product in the cart by one.
     * If the quantity becomes zero, the item is removed.
     *
     * @param p product to decrease
     * @return true if the cart was updated
     */
    public boolean decreaseItem(Product p) {
        if (p == null) return false;

        for (int i = 0; i < items.size(); i++) {
            CartItem ci = items.get(i);
            if (ci.getProduct().equals(p)) {
                if (ci.getQuantity() > 1) {
                    ci.setQuantity(ci.getQuantity() - 1);
                } else {
                    items.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @return total cost
     */
    public double calculateTotal() {
        double total = 0;
        for (CartItem ci : items) {
            total += ci.getTotalPrice();
        }
        return total;
    }

    /**
     * Removes all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Returns the list of cart items.
     *
     * @return list of CartItem objects
     */
    public List<CartItem> getItems() {
        return items;
    }
}
