// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents a clothing product.
 * Includes clothing size information.
 */
public class ClothingProduct extends Product{

    private String size;

    /**
     * Creates a new clothing product.
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description product description
     * @param color display color
     * @param imagePath path to image
     * @param size clothing size
     */
    public ClothingProduct(String name,double price,int stock,String description,Color color,String imagePath,String size){
        super(name,price,stock,description,Category.CLOTHING,color,imagePath);
        this.size=size;
    }

    /**
     * @return clothing size
     */
    public String getSize(){
        return size;
    }

    /**
     * @return detailed clothing description
     */
    @Override
    public String toString(){
        return super.toString()+
               "\nSize: "+size;
    }
}

