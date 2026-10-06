package salesapp;

import java.util.ArrayList;

/**
 * Models the list of items being purchased.
 */
public class SalesSlip {
    private final ArrayList<SalesItem> items = new ArrayList<>();

    public void addItem(SalesItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        items.add(item);
    }

    public void addItem(String name, double price, int quantity) {
        addItem(new SalesItem(name, price, quantity));
    }

    public double getTotalSales() {
        double total = 0;
        for (SalesItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "(no items yet)";
        }
        StringBuilder sb = new StringBuilder();
        int n = 1;
        for (SalesItem item : items) {
            sb.append(n++).append(". ").append(item).append("\n");
        }
        return sb.toString();
    }
}