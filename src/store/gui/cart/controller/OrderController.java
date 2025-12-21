package store.gui.cart.controller;

import store.cart.Cart;
import store.orders.Order;
import store.orders.OrderManager;

public class OrderController {

    public static Order checkout(Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            return null;
        }

        Order order = new Order(
                OrderManager.getOrders().size() + 1,
                cart.getItems(),
                cart.calculateTotal()
        );

        order.pay();

        OrderManager.add(order);

        cart.clear();

        return order;
    }
}
