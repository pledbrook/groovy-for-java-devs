package org.springframework.samples.travel.web;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;

import org.springframework.samples.travel.dsl.StockHolding;
import org.springframework.samples.travel.dsl.TradesDsl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StocksController {

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    public String console() {
        return "stocks/console";
    }

    @RequestMapping(value = "/console", method = RequestMethod.POST)
    public String runScript(String scriptContent, Model model) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(scriptContent);
        
        TradesDsl tradesDsl = new TradesDsl();
        Closure c = (Closure) binding.getVariable("trades");
        c.setDelegate(tradesDsl);
        c.call();
        
        model.addAttribute("output", generateOutput(tradesDsl));
        return "stocks/console";
    }
    
    private String generateOutput(TradesDsl dsl) {
        StringBuilder buffer = new StringBuilder();
        for (StockHolding holding : dsl.getHoldings()) {
            buffer.append(holding.getEpic()).append(": ");
            buffer.append(holding.getQuantity()).append("<br/>\n");
        }
        
        return buffer.toString();
    }
}
