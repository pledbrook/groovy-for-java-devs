class StandaloneTests extends GroovyTestCase {
    void testStringSize() {
        assertEquals 4, "four".size()
    }
    
    void testNullFileConstructor() {
        shouldFail(MalformedURLException) {
            new URL("invalidURL")
        }
    }
}
