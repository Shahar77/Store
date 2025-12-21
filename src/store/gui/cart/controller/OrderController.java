package store.gui.cart.controller;

import store.cart.Cart;
import store.orders.Order;
import store.orders.OrderManager;

/**
 * OrderController handles the checkout action from the cart GUI.
 * It creates an Order from the current Cart, marks it as paid,
 * saves it in OrderManager, and clears the cart.
 *
 * Note: This controller does not use StoreEngine and keeps a simple in-memory flow.
 */
public class OrderController{

    /**
     * Performs checkout for the given cart.
     * If the cart is null or empty, returns null.
     * Otherwise creates a new Order, marks it as PAID, adds it to OrderManager,
     * clears the cart, and returns the created Order.
     *
     * @param cart shopping cart to checkout
     * @return created Order, or null if cart is null/empty
     */
    public static Order checkout(Cart cart){
        if(cart==null||cart.getItems().isEmpty()){
            return null;
        }

        Order order=new Order(
                OrderManager.getOrders().size()+1,
                cart.getItems(),
                cart.calculateTotal()
        );

        order.pay();
        OrderManager.add(order);
        cart.clear();

        return order;
    }
}
