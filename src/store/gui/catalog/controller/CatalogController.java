// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog.controller;

import store.engine.StoreEngine;
import store.products.Product;
import store.products.Category;
import store.gui.catalog.ProductCatalogPanel;

import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

/**
 * Controller for the product catalog screen.
 * Handles filtering and cart interactions.
 */
public class CatalogController{

    private StoreEngine engine;

    /**
     * Creates a catalog controller.
     * @param engine store engine instance
     */
    public CatalogController(StoreEngine engine){
        this.engine=engine;
    }

    /**
     * Creates the catalog panel for the GUI.
     * @return catalog panel
     */
    public JPanel createCatalogPanel(){
        return new ProductCatalogPanel(engine.getAvailableProducts(),this);
    }

    /**
     * Filters products by text and category.
     * @param text search text
     * @param category selected category or null
     * @return filtered list of products
     */
    public List<Product> filter(String text,Category category){
        List<Product> result=new ArrayList<>();
        List<Product> products=engine.getAvailableProducts();

        for(int i=0;i<products.size();i++){
            Product p=products.get(i);

            if(text!=null&&!text.isEmpty()){
                if(!p.getName().toLowerCase().contains(text.toLowerCase())){
                    continue;
                }
            }

            if(category!=null&&p.getCategory()!=category){
                continue;
            }

            result.add(p);
        }
        return result;
    }

    /**
     * Adds a product to the cart.
     * @param p product to add
     * @return true if added successfully
     */
    public boolean addToCart(Product p){
        return engine.addToCart(p);
    }
}
