package UI;

/**
 * Created by longlingwang on 2/16/16.
 */
public class Crops {
    // The functions in Crops class was corresponding to the CropTableView class, which also refers to
//    https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/020_tableViewAddingAndDeleting/Product.java
    private String cropName;
    private double price;
    private int quantity;

    public Crops() {
        this.cropName = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Crops(String name, double price, int quantity) {
        cropName = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        cropName = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return cropName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

