// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.io.products;

import store.products.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Saves products to a CSV file.
 * Format:
 * name,price,stock,description,category,imagePath
 */
public class ProductCsvWriter {

    /**
     * Writes the given products list to a CSV file (overwrites file).
     *
     * @param products products to save
     * @param path output file path
     * @throws IOException if writing fails
     */
    public static void write(List<Product> products, String path) throws IOException {
        if (products == null || path == null) return;

        FileWriter writer = new FileWriter(path, false);
        writer.write("name,price,stock,description,category,imagePath\n");

        for (Product p : products) {
            writer.write(
                    safe(p.getName()) + "," +
                            p.getPrice() + "," +
                            p.getStock() + "," +
                            safe(p.getDescription()) + "," +
                            p.getCategory() + "," +
                            safe(p.getImagePath()) + "\n"
            );
        }

        writer.close();
    }

    private static String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " ");
    }
}
