package salesapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SalesSlipTest {

    @Test
    void itemTotalIsPriceTimesQuantity() {
        SalesItem item = new SalesItem("Pen", 1.50, 4);
        assertEquals(6.00, item.getTotal(), 0.001);
    }

    @Test
    void slipTotalsAllItems() {
        SalesSlip slip = new SalesSlip();
        slip.addItem("Pen", 1.50, 4);      // 6.00
        slip.addItem("Notebook", 3.25, 2); // 6.50
        assertEquals(2, slip.getItemCount());
        assertEquals(12.50, slip.getTotalSales(), 0.001);
    }

    @Test
    void toStringContainsItemDetails() {
        SalesItem item = new SalesItem("Pen", 1.50, 4);
        String s = item.toString();
        assertTrue(s.contains("Pen") && s.contains("1.50") && s.contains("4"));
    }

    @Test
    void rejectsPriceOf100OrMore() {
        assertThrows(IllegalArgumentException.class, () -> new SalesItem("TV", 100, 1));
    }

    @Test
    void rejectsBadQuantityAndName() {
        assertThrows(IllegalArgumentException.class, () -> new SalesItem("Pen", 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new SalesItem(" ", 1, 1));
    }
}