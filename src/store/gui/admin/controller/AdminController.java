/**
 * Submitted by:
 * Sarah Gabay - ID 329185771
 * Shahar Ezra - ID 329186118
 */
package store.gui.admin.controller;

import store.engine.StoreEngine;
import store.io.StoreFileManager;
import store.products.Category;
import store.products.Product;
import store.products.SimpleProduct;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Controller responsible for administrative actions in the store.
 * Handles product management, stock updates and file operations
 * in a thread-safe manner.
 */
public class AdminController {

    /**
     * Store engine (shared model).
     */
    private final StoreEngine engine;

    /**
     * File manager for saving and loading store data.
     */
    private final StoreFileManager fileManager;

    /**
     * Single-thread executor for admin operations.
     */
    private final ExecutorService adminExecutor=Executors.newSingleThreadExecutor();

    /**
     * Creates a new AdminController.
     *
     * @param engine store engine instance
     */
    public AdminController(StoreEngine engine) {
        this.engine=engine;
        this.fileManager=new StoreFileManager(engine);
    }

    /**
     * Adds a new simple product to the store asynchronously.
     *
     * @param name product name
     * @param price product price
     * @param stock initial stock amount
     * @param description product description
     * @param category product category
     * @param imagePath path to product image
     * @return Future representing the task
     */
    public Future<?> addSimpleProduct(String name,double price,int stock,String description,
                                      Category category,String imagePath) {
        return adminExecutor.submit(() -> {
            synchronized(engine){
                SimpleProduct p=new SimpleProduct(
                        name,
                        price,
                        stock,
                        description,
                        category,
                        Color.WHITE,
                        imagePath
                );
                engine.addProduct(p);
            }
        });
    }

    /**
     * Updates the stock of a product by name and category.
     *
     * @param name product name
     * @param category product category
     * @param newStock new stock amount
     * @return Future representing the task
     */
    public Future<?> updateStockByName(String name,Category category,int newStock) {
        return adminExecutor.submit(() -> {
            synchronized(engine){
                Product target=findByNameAndCategory(engine.getAllProducts(),name,category);
                if(target==null){
                    System.out.println("Admin: product not found: "+name+" "+category);
                    return;
                }

                int current=target.getStock();
                int diff=newStock-current;

                if(diff>0){
                    target.increaseStock(diff);
                } else if(diff<0){
                    target.decreaseStock(-diff);
                }
            }
        });
    }

    /**
     * Saves all products to a file.
     *
     * @param path file path
     * @return Future representing the task
     */
    public Future<?> saveProducts(String path) {
        return adminExecutor.submit(() -> {
            synchronized(engine){
                fileManager.saveProductsToFile(path);
            }
        });
    }

    /**
     * Loads products from a file.
     *
     * @param path file path
     * @return Future representing the task
     */
    public Future<?> loadProducts(String path) {
        return adminExecutor.submit(() -> {
            synchronized(engine){
                fileManager.loadProductsFromFile(path);
            }
        });
    }

    /**
     * Shuts down the admin executor service.
     */
    public void shutdown() {
        adminExecutor.shutdown();
    }

    /**
     * Finds a product by name and category.
     *
     * @param products list of products
     * @param name product name
     * @param category product category
     * @return matching product or null if not found
     */
    private Product findByNameAndCategory(List<Product> products,String name,Category category) {
        for(Product p:products){
            if(p.getName().equalsIgnoreCase(name) && p.getCategory()==category){
                return p;
            }
        }
        return null;
    }
}
