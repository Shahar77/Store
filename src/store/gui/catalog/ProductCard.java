// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.gui.catalog.controller.CatalogController;
import store.products.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * UI component that represents a single product in the catalog grid.
 * Displays product image, name and price and opens product details on click.
 */
public class ProductCard extends JPanel{

    private final Product product;
    private final CatalogController controller;

    /**
     * Creates a product card for the catalog grid.
     * @param product product to display
     * @param controller catalog controller
     */
    public ProductCard(Product product,CatalogController controller){
        this.product=product;
        this.controller=controller;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(180,260));
        setMinimumSize(new Dimension(180,260));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setToolTipText(product.getDisplayDetails());

        JLabel imageLabel=createImageLabel(product.getImagePath());

        JLabel nameLabel=new JLabel(product.getName(),SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        JLabel priceLabel=new JLabel("₪"+product.getPrice(),SwingConstants.CENTER);

        JPanel infoPanel=new JPanel(new GridLayout(2,1));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        add(imageLabel,BorderLayout.CENTER);
        add(infoPanel,BorderLayout.SOUTH);

        MouseAdapter clickListener=new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                openDetailsDialog();
            }
        };

        addMouseListener(clickListener);
        imageLabel.addMouseListener(clickListener);
        infoPanel.addMouseListener(clickListener);
        nameLabel.addMouseListener(clickListener);
        priceLabel.addMouseListener(clickListener);
    }

    /**
     * Opens the product details dialog for this product.
     */
    private void openDetailsDialog(){
        new ProductDetailsDialog(product,controller);
    }

    /**
     * Creates an image label from a given image path.
     * If the image does not exist, displays a fallback text.
     * @param path image path
     * @return image label
     */
    private JLabel createImageLabel(String path){
        JLabel label=new JLabel("",SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150,180));

        if(path!=null&&!path.isEmpty()){
            ImageIcon icon=new ImageIcon(path);
            if(icon.getIconWidth()>0){
                Image scaled=icon.getImage().getScaledInstance(120,160,Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));
                return label;
            }
        }

        label.setText("No Image");
        return label;
    }
}
