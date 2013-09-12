package org.example.shares;

import groovy.lang.*;
import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Must provide a path to a Groovy script.");
            System.exit(1);
        }

        String scriptContent = loadGroovyScript(args[0]);

        Binding binding = new Binding();
        GroovyShell shell = createShell(binding);
        shell.evaluate(scriptContent);

        TradesDsl tradesDsl = new TradesDsl();
        Closure c = (Closure) binding.getVariable("trades");
        c.setDelegate(tradesDsl);
        c.call();

        System.out.println("Result");
        System.out.println("------");
        System.out.println();
        System.out.println(generateOutput(tradesDsl));
    }
    
    private static String generateOutput(TradesDsl dsl) {
        StringBuilder buffer = new StringBuilder();
        for (StockHolding holding : dsl.getHoldings()) {
            buffer.append(holding.getEpic()).append(": ");
            buffer.append(holding.getQuantity()).append("\n");
        }
        
        return buffer.toString();
    }
        
    private static String loadGroovyScript(String path) throws IOException {
        File scriptFile = new File(path);
        System.out.println("Loading script " + scriptFile);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(scriptFile)));
        String line = reader.readLine();
        StringBuilder buffer = new StringBuilder();
        while (line != null) {
            buffer.append(line).append('\n');
            line = reader.readLine();
        }

        return buffer.toString();
    }

    private static GroovyShell createShell(Binding binding) {
        return new GroovyShell(binding);
    }
}
