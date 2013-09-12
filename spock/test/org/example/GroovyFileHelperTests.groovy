package org.example

class GroovyFileHelperTests extends GroovyTestCase {
    void testFileContents() {
        def testFile = new Expando()
        def testMsg = "This is some data"
        
        testFile.exists = {-> false}
        testFile.write = { String msg -> content = msg }
        
        def helper = new GroovyFileHelper()
        helper.populateMessageBackwards(testFile, testMsg)
        
        assertEquals "Message in file is incorrect",
                "atad emos si sihT",
                testFile.content
    }
    
    void testFileDoesNotExist() {
        def testFile = new Expando()
        def testMsg = "This is some data"
        
        testFile.exists = {-> true}
        testFile.path = "/var/tmp/output.txt"
        
        def exMsg = shouldFail(IllegalArgumentException) {
            def helper = new GroovyFileHelper()
            helper.populateMessageBackwards(testFile, testMsg)
        }
        
        assertEquals "Error message is incorrect",
                "/var/tmp/output.txt already exists",
                exMsg
    }
}
