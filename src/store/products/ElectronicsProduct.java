package store.products;

/**
 * Represents an electronics product.
 */
public class ElectronicsProduct extends Product{

    private int warrantyMonths;

    /**
     * Creates a new electronics product.
     * @param name product name
     * @param price product price
     * @param stock available stock
     * @param warrantyMonths warranty period in months
     * @param imagePath image path
     */
    public ElectronicsProduct(String name,double price,int stock,int warrantyMonths,String imagePath){
        super(
                name,
                price,
                stock,
                Category.ELECTRONICS,
                "Electronics product with "+warrantyMonths+" months warranty",
                imagePath
        );
        this.warrantyMonths=warrantyMonths;
    }

    /**
     * @return warranty period in months
     */
    public int getWarrantyMonths(){
        return warrantyMonths;
    }
}
