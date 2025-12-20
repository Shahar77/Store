// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.gui.catalog;

import store.gui.catalog.controller.CatalogController;
import store.products.Category;
import store.products.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Catalog screen: shows products grid, search and category filter.
 */
public class ProductCatalogPanel extends JPanel{
    private final CatalogController controller;
    private final JPanel gridPanel;
    private final JTextField searchField;
    private final JComboBox<Category> categoryBox;

    /**
     * Constructs catalog panel.
     * @param products initial products
     * @param controller controller
     */
    public ProductCatalogPanel(List<Product> products,CatalogController controller){
        this.controller=controller;
        setLayout(new BorderLayout());

        JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("Search:"));
        searchField=new JTextField(20);
        topPanel.add(searchField);

        JButton searchButton=new JButton("Search");
        topPanel.add(searchButton);

        topPanel.add(new JLabel("Category:"));
        categoryBox=new JComboBox<>();
        categoryBox.addItem(null);
        for(Category c:Category.values())categoryBox.addItem(c);
        categoryBox.setSelectedItem(null);

        categoryBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,Object value,int index,boolean isSelected,boolean cellHasFocus){
                super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
                setText(value==null?"All Categories":value.toString());
                return this;
            }
        });

        topPanel.add(categoryBox);
        add(topPanel,BorderLayout.NORTH);

        gridPanel=new JPanel(new GridLayout(0,3,10,10));
        gridPanel.setBackground(Color.WHITE);
        add(new JScrollPane(gridPanel),BorderLayout.CENTER);

        updateGrid(products);

        searchButton.addActionListener(e->applyFilters());
        categoryBox.addActionListener(e->applyFilters());
    }

    /**
     * Applies search and category filters.
     */
    private void applyFilters(){
        String text=searchField.getText();
        Category category=(Category)categoryBox.getSelectedItem();
        List<Product> result=controller.filter(text,category);
        updateGrid(result);
    }

    /**
     * Updates grid UI with given products.
     * @param products products
     */
    private void updateGrid(List<Product> products){
        gridPanel.removeAll();
        for(Product p:products){
            gridPanel.add(new ProductCard(p,controller));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
