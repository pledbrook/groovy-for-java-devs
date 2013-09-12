package org.springframework.samples.travel.dsl;

public class StockHolding {

    private String epic;
    private int quantity;
    
    public StockHolding(String epic) {
        this(epic, 1000);
    }

    public StockHolding(String epic, int quantity) {
        this.epic = epic;
        this.quantity = quantity;
    }

    public String getEpic() { return epic; }
    public int getQuantity() { return quantity; }
}
