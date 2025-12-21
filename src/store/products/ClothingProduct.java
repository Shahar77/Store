package store.products;

/**
 * Represents a clothing product.
 */
public class ClothingProduct extends Product{

    private String size;

    /**
     * Creates a new clothing product.
     * @param name clothing name
     * @param price clothing price
     * @param stock available stock
     * @param size clothing size
     * @param imagePath image path
     */
    public ClothingProduct(String name,double price,int stock,String size,String imagePath){
        super(
                name,
                price,
                stock,
                Category.CLOTHING,
                "Clothing item, size "+size,
                imagePath
        );
        this.size=size;
    }

    /**
     * @return clothing size
     */
    public String getSize(){
        return size;
    }
}
