// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.io.products;

import store.products.Category;
import store.products.Product;
import store.products.SimpleProduct;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads products from a CSV file.
 * Expected CSV header:
 * name,price,stock,description,category,imagePath
 */
public class ProductCsvLoader {

    /**
     * Loads products from a CSV file.
     *
     * @param path file path
     * @return list of loaded products
     * @throws IOException if reading fails
     */
    public static List<Product> load(String path) throws IOException {
        List<Product> products = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (firstLine) {
                firstLine = false;
                if (line.toLowerCase().startsWith("name,price,stock")) {
                    continue; // skip header
                }
            }

            String[] parts = line.split(",", -1);
            if (parts.length < 6) continue;

            String name = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());
            String description = parts[3].trim();
            Category category = Category.valueOf(parts[4].trim().toUpperCase());
            String imagePath = parts[5].trim();

            products.add(new SimpleProduct(
                    name, price, stock, description, category, Color.WHITE, imagePath
            ));
        }

        br.close();
        return products;
    }
}
