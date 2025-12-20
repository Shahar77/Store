// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents a clothing product.
 */
public class ClothingProduct extends Product{
    private String size;

    /**
     * Creates a new clothing product.
     */
    public ClothingProduct(String name,double price,int stock,String description,Color color,String imagePath,String size){
        super(name,price,stock,description,Category.CLOTHING,color,imagePath);
        this.size=size;
    }

    /**
     * Returns detailed clothing info.
     */
    @Override
    public String getDisplayDetails(){
        return super.getDisplayDetails()+
               "\nSize: "+size;
    }
}
