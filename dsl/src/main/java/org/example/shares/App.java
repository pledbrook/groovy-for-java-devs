package org.example.shares;

import groovy.lang.*;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.*;
import java.util.Collection;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Must provide a path to a Groovy script.");
            System.exit(1);
        }

        String scriptContent = loadGroovyScript(args[0]);

        Binding binding = new Binding();
        GroovyShell shell = createShellWithCustomScript(binding);
        TradesScript script = (TradesScript) shell.parse(scriptContent);
        script.run();

        System.out.println("Result");
        System.out.println("------");
        System.out.println();
        System.out.println(generateOutput(script.getHoldings()));
    }
    
    private static String generateOutput(Collection<StockHolding> holdings) {
        StringBuilder buffer = new StringBuilder();
        for (StockHolding holding : holdings) {
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

    private static GroovyShell createShellWithCustomScript(Binding binding) {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass("org.example.shares.TradesScript");
        return new GroovyShell(binding, config);
    }
}
