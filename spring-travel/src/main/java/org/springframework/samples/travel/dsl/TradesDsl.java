package org.springframework.samples.travel.dsl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import groovy.lang.GroovyObjectSupport;

public class TradesDsl extends GroovyObjectSupport {
    private int currentQuantity;
    private Map<String, StockHolding> holdingMap = new HashMap<String, StockHolding>();
    
    public Collection<StockHolding> getHoldings() {
        return Collections.unmodifiableCollection(holdingMap.values());
    }
    
    public TradesDsl buy(int quantity) {
        currentQuantity = quantity;
        return this;
    }
    
    public TradesDsl sell(int quantity) {
        currentQuantity = -quantity;
        return this;
    }
    
    public TradesDsl of(String epic) {
        StockHolding holding = holdingMap.get(epic);
        if (holding == null) holding = new StockHolding(epic);
        
        holding = new StockHolding(holding.getEpic(), holding.getQuantity() + currentQuantity);
        holdingMap.put(epic, holding);
        return this;
    }
    
    public Object getProperty(String name) { return name; }
}
