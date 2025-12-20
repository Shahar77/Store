// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog.controller;

import store.engine.StoreEngine;
import store.gui.catalog.ProductCatalogPanel;
import store.products.Category;
import store.products.Product;

import javax.swing.*;
import java.util.List;

/**
 * Controller for product catalog UI.
 */
public class CatalogController{
    private final StoreEngine engine;

    /**
     * Creates a new catalog controller.
     * @param engine store engine
     */
    public CatalogController(StoreEngine engine){
        this.engine=engine;
    }

    /**
     * Creates the catalog panel.
     * @return panel
     */
    public JPanel createCatalogPanel(){
        List<Product> products=engine.getAvailableProducts();
        return new ProductCatalogPanel(products,this);
    }

    /**
     * Filters products by text and category.
     * If category is null, returns all categories.
     * @param text search text
     * @param category category or null
     * @return filtered list
     */
    public List<Product> filter(String text,Category category){
        String t=text==null?"":text.trim().toLowerCase();
        return engine.getAvailableProducts().stream()
                .filter(p->t.isEmpty()||p.getName().toLowerCase().contains(t))
                .filter(p->category==null||p.getCategory()==category)
                .toList();
    }

    /**
     * Adds product to active customer's cart.
     * @param product product
     * @param quantity quantity
     * @return true if added
     */
    public boolean addToCart(Product product,int quantity){
        return engine.addToCart(product,quantity);
    }
}
