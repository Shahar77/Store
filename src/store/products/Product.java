package store.products;

import store.core.StoreEntity;

/**
 * Abstract base class for all products in the store.
 * Contains common fields and behavior shared by all product types.
 */
public abstract class Product implements StoreEntity{

    private String name;
    private double price;
    private int stock;
    private Category category;
    private String description;
    private String imagePath;

    /**
     * Creates a new product.
     * @param name product name
     * @param price product price
     * @param stock available stock
     * @param category product category
     * @param description product description
     * @param imagePath path to product image
     */
    public Product(String name,double price,int stock,Category category,String description,String imagePath){
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.category=category;
        this.description=description;
        this.imagePath=imagePath;
    }

    /**
     * @return product name
     */
    public String getName(){
        return name;
    }

    /**
     * @return product price
     */
    public double getPrice(){
        return price;
    }

    /**
     * @return available stock
     */
    public int getStock(){
        return stock;
    }

    /**
     * Decreases stock by one unit.
     */
    public void decreaseStock(){
        if(stock>0){
            stock--;
        }
    }

    /**
     * @return product category
     */
    public Category getCategory(){
        return category;
    }

    /**
     * @return product description
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return image path for GUI display
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * @return display name for GUI components
     */
    @Override
    public String getDisplayName(){
        return name;
    }

    /**
     * @return detailed description for GUI components
     */
    @Override
    public String getDisplayDetails(){
        return name+" - "+price+"₪";
    }
}
