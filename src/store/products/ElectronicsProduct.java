// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents an electronics product.
 * Includes warranty duration and brand.
 */
public class ElectronicsProduct extends Product{

    private int warrantyMonths;
    private String brand;

    /**
     * Creates a new electronics product.
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description product description
     * @param color display color
     * @param imagePath path to image
     * @param warrantyMonths warranty duration in months
     * @param brand product brand
     */
    public ElectronicsProduct(String name,double price,int stock,String description,Color color,String imagePath,int warrantyMonths,String brand){
        super(name,price,stock,description,Category.ELECTRONICS,color,imagePath);
        this.warrantyMonths=warrantyMonths;
        this.brand=brand;
    }

    /**
     * @return warranty duration in months
     */
    public int getWarrantyMonths(){
        return warrantyMonths;
    }

    /**
     * @return product brand
     */
    public String getBrand(){
        return brand;
    }

    /**
     * @return detailed electronics description
     */
    @Override
    public String toString(){
        return super.toString()+
               "\nWarranty Months: "+warrantyMonths+
               "\nBrand: "+brand;
    }
}
