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
 * Small UI card that represents a product inside the catalog grid.
 */
public class ProductCard extends JPanel{
    private final Product product;
    private final CatalogController controller;

    /**
     * Creates a product card.
     * @param product product
     * @param controller controller
     */
    public ProductCard(Product product,CatalogController controller){
        this.product=product;
        this.controller=controller;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        setBackground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setToolTipText(product.getDisplayDetails());

        JLabel imageLabel=createImageLabel(product.getImagePath());
        JLabel nameLabel=new JLabel(product.getName(),SwingConstants.CENTER);
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
                new ProductDetailsDialog(product,controller);
            }
        };

        addMouseListener(clickListener);
        imageLabel.addMouseListener(clickListener);
        infoPanel.addMouseListener(clickListener);
        nameLabel.addMouseListener(clickListener);
        priceLabel.addMouseListener(clickListener);
    }

    /**
     * Creates an image label from path, with fallback.
     * @param path image path
     * @return label
     */
    private JLabel createImageLabel(String path){
        JLabel label=new JLabel("",SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150,200));
        ImageIcon icon=null;

        if(path!=null&&!path.isEmpty()){
            icon=new ImageIcon(path);
        }

        if(icon==null||icon.getIconWidth()<=0){
            label.setText("No Image");
            return label;
        }

        Image scaled=icon.getImage().getScaledInstance(120,160,Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
        return label;
    }
}

