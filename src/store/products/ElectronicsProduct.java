// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents an electronic product.
 */
public class ElectronicsProduct extends Product{
    private int warrantyMonths;
    private String brand;

    /**
     * Creates a new electronics product.
     */
    public ElectronicsProduct(String name,double price,int stock,String description,Color color,String imagePath,int warrantyMonths,String brand){
        super(name,price,stock,description,Category.ELECTRONICS,color,imagePath);
        this.warrantyMonths=warrantyMonths;
        this.brand=brand;
    }

    /**
     * Returns detailed electronics info.
     */
    @Override
    public String getDisplayDetails(){
        return super.getDisplayDetails()+
               "\nBrand: "+brand+
               "\nWarranty: "+warrantyMonths+" months";
    }
}
