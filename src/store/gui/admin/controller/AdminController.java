package store.gui.admin.controller;

import store.engine.StoreEngine;
import store.io.StoreFileManager;
import store.products.Category;
import store.products.Product;
import store.products.SimpleProduct;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminController {

    private final StoreEngine engine;
    private final StoreFileManager fileManager;

    // Thread אחד למנהל, כל פעולה נכנסת לתור מסודר
    private final ExecutorService adminExecutor = Executors.newSingleThreadExecutor();

    public AdminController(StoreEngine engine) {
        this.engine = engine;
        this.fileManager = new StoreFileManager(engine);
    }

    public void addSimpleProduct(String name, double price, int stock, String description, Category category, String imagePath) {
        adminExecutor.submit(() -> {
            synchronized (engine) {
                SimpleProduct p = new SimpleProduct(
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

    public void updateStockByName(String name, Category category, int newStock) {
        adminExecutor.submit(() -> {
            synchronized (engine) {
                Product target = findByNameAndCategory(engine.getAllProducts(), name, category);
                if (target == null) {
                    System.out.println("Admin: product not found: " + name + " " + category);
                    return;
                }

                int current = target.getStock();
                int diff = newStock - current;

                if (diff > 0) {
                    target.increaseStock(diff);
                } else if (diff < 0) {
                    target.decreaseStock(-diff);
                }
            }
        });
    }

    public void saveProducts(String path) {
        adminExecutor.submit(() -> {
            synchronized (engine) {
                fileManager.saveProductsToFile(path);
            }
        });
    }

    public void loadProducts(String path) {
        adminExecutor.submit(() -> {
            synchronized (engine) {
                fileManager.loadProductsFromFile(path);
            }
        });
    }

    private Product findByNameAndCategory(List<Product> products, String name, Category category) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name) && p.getCategory() == category) {
                return p;
            }
        }
        return null;
    }
}
