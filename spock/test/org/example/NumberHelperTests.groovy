package org.example

class NumberHelperTests extends GroovyTestCase {
    void testJavaHelper() {
        def helper = new JavaNumberHelper()
        def numbers = [4, 9, -3, 2112, -7]
        def positives = helper.findPositives(numbers as int[])
        assertEquals 'wrong number of elements', 3, positives.size()
        assertTrue 4 in positives
        assertTrue 9 in positives
        assertTrue 2112 in positives
    }
    
    void testGroovyHelper() {
        def helper = new GroovyNumberHelper()
        def numbers = [4, 9, -3, 2112, -7]
        def positives = helper.findPositives(numbers)
        assertEquals 'wrong number of elements', 3, positives.size()
        assertTrue 4 in positives
        assertTrue 9 in positives
        assertTrue 2112 in positives
    }
}
