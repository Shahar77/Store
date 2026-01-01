/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.io;

import store.engine.StoreEngine;
import store.io.products.ProductCsvLoader;
import store.io.products.ProductCsvWriter;
import store.products.Product;

import java.util.List;

/**
 * Manages loading and saving store data to files.
 */
public class StoreFileManager {

    /**
     * Shared store engine.
     */
    private final StoreEngine engine;

    /**
     * Creates a new StoreFileManager.
     *
     * @param engine store engine instance
     */
    public StoreFileManager(StoreEngine engine) {
        this.engine=engine;
    }

    /**
     * Loads products from a CSV file and updates the store engine.
     *
     * @param path file path
     */
    public void loadProductsFromFile(String path) {
        try{
            List<Product> loaded=ProductCsvLoader.load(path);
            engine.setProducts(loaded);
            System.out.println("Loaded products: "+loaded.size());
        } catch(Exception ex){
            System.out.println("Failed to load products: "+ex.getMessage());
        }
    }

    /**
     * Saves all products to a CSV file.
     *
     * @param path file path
     */
    public void saveProductsToFile(String path) {
        try{
            ProductCsvWriter.write(engine.getAllProducts(),path);
            System.out.println(
                    "Saved products: "+engine.getAllProducts().size()
            );
        } catch(Exception ex){
            System.out.println("Failed to save products: "+ex.getMessage());
        }
    }
}
