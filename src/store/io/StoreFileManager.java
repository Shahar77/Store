package store.io;

import store.engine.StoreEngine;
import store.io.products.ProductCsvLoader;
import store.io.products.ProductCsvWriter;
import store.products.Product;

import java.util.List;

public class StoreFileManager {

    private final StoreEngine engine;

    public StoreFileManager(StoreEngine engine) {
        this.engine = engine;
    }

    public void loadProductsFromFile(String path) {
        try {
            List<Product> loaded = ProductCsvLoader.load(path);
            engine.setProducts(loaded);
            System.out.println("Loaded products: " + loaded.size());
        } catch (Exception ex) {
            System.out.println("Failed to load products: " + ex.getMessage());
        }
    }

    public void saveProductsToFile(String path) {
        try {
            ProductCsvWriter.write(engine.getAllProducts(), path);
            System.out.println("Saved products: " + engine.getAllProducts().size());
        } catch (Exception ex) {
            System.out.println("Failed to save products: " + ex.getMessage());
        }
    }
}
