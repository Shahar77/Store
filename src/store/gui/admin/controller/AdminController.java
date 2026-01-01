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

public class AdminController {
    private final StoreEngine engine;
    private final StoreFileManager fileManager;

    private final ExecutorService adminExecutor = Executors.newSingleThreadExecutor();

    public AdminController(StoreEngine engine) {
        this.engine = engine;
        this.fileManager = new StoreFileManager(engine);
    }

    public Future<?> addSimpleProduct(String name, double price, int stock, String description,
                                      Category category, String imagePath) {
        return adminExecutor.submit(() -> {
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

    public Future<?> updateStockByName(String name, Category category, int newStock) {
        return adminExecutor.submit(() -> {
            synchronized (engine) {
                Product target = findByNameAndCategory(engine.getAllProducts(), name, category);
                if (target == null) {
                    System.out.println("Admin: product not found: " + name + " " + category);
                    return;
                }

                int current = target.getStock();
                int diff = newStock - current;

                if (diff > 0) target.increaseStock(diff);
                else if (diff < 0) target.decreaseStock(-diff);
            }
        });
    }

    public Future<?> saveProducts(String path) {
        return adminExecutor.submit(() -> {
            synchronized (engine) {
                fileManager.saveProductsToFile(path);
            }
        });
    }

    public Future<?> loadProducts(String path) {
        return adminExecutor.submit(() -> {
            synchronized (engine) {
                fileManager.loadProductsFromFile(path);
            }
        });
    }

    public void shutdown() {
        adminExecutor.shutdown();
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
