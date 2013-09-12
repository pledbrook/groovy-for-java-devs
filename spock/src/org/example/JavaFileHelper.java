package org.example;

import java.io.*;

public class JavaFileHelper {
    void populateMessageBackwards(File file, String msg) throws IOException {
        if (file.exists()) {
            throw new IllegalArgumentException(file.getPath() + " already exists");
        }
        
        PrintWriter writer = new PrintWriter(file);
        writer.write(new StringBuilder(msg).reverse().toString());
        writer.close();
    }
}
