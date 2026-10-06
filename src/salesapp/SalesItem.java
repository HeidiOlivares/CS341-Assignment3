package salesapp;

/**
 * Models a single item on the sales list: name, price, and quantity.
 */
public class SalesItem {
    private String name;
    private double price;
    private int quantity;

    public SalesItem(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0 || price >= 100) {
            throw new IllegalArgumentException("Price must be at least $0 and less than $100.");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive whole number.");
        }
        this.quantity = quantity;
    }

    /** Calculation: price * quantity for this item. */
    public double getTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s  |  $%.2f x %d  =  $%.2f", name, price, quantity, getTotal());
    }
}