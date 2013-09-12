package org.example

class GroovyFileHelper {
    void populateMessageBackwards(file, msg) {
        if (file.exists()) {
            throw new IllegalArgumentException("${file.path} already exists")
        }
        file.write(msg.reverse())
    }
}
