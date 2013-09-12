package org.example

//import groovy.io.FileType

if (args.size() == 0 || args.size() > 2) {
    usage()
    System.exit 1
}

def extension = args[0]
def searchDir = new File(args.size() > 1 ? args[1] : '.')
def count = 0

def filesInDir = searchDir.listFiles()
for (File f in filesInDir) {
    if (new Filename(f).ext == extension) count++
}

println "Found $count files"


// END OF SCRIPT

def usage() {
    println "USAGE: countFilesOfType EXT [DIR]"
}

class Filename {

    File sourcePath
    String baseName
    String ext
    
    Filename(File source) {
        this.sourcePath = source
        
        def filename = source.name
        def p = filename.lastIndexOf('.')
        if (p == -1) {
            baseName = filename
            ext = ""
        }
        else {
            baseName = filename[0..<p]
            ext = filename[(p + 1)..-1]
        }
    }
    
    def getFullName() {
        return this.baseName + '.' + this.ext
    }

}
