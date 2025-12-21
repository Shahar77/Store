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
 * Responsible for creating the catalog panel,
 * filtering products by text/category,
 * and adding products to cart via StoreEngine.
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
     * If category is null => no category filtering.
     * If text is null/empty => no text filtering.
     * @param text search text
     * @param category selected category or null for all
     * @return filtered list of products
     */
    public List<Product> filter(String text,Category category){
        List<Product> result=new ArrayList<>();
        List<Product> products=engine.getAvailableProducts();

        String t=(text==null)?"":text.trim().toLowerCase();

        for(int i=0;i<products.size();i++){
            Product p=products.get(i);

            if(!t.isEmpty()&&!p.getName().toLowerCase().contains(t)){
                continue;
            }

            if(category!=null&&p.getCategory()!=category){
                continue;
            }

            result.add(p);
        }
        return result;
    }



    /**
     * Adds product to cart and updates cart UI.
     * @param p product
     */
    public void addToCart(Product p){
        engine.addToCart(p);
    }


}
