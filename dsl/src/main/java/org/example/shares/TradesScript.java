package org.example.shares;

import groovy.lang.Script;

import java.util.Collection;

public class TradesScript extends Script {
    private TradesDsl dslDelegate = new TradesDsl();

    public Collection<StockHolding> getHoldings() {
        return this.dslDelegate.getHoldings();
    }

    public TradesScript buy(int quantity) {
        this.dslDelegate.buy(quantity);
        return this;
    }

    public TradesScript sell(int quantity) {
        this.dslDelegate.sell(quantity);
        return this;
    }

    public TradesScript of(String epic) {
        this.dslDelegate.of(epic);
        return this;
    }

    public Object getProperty(String name) { return name; }

    @Override
    public Object run() {
        throw new UnsupportedOperationException(
                getClass().getName() + " is not meant to be used directly. " +
                "It should instead be used as a base script");
    }
}
