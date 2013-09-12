package org.example

class GroovyNumberHelper {
    def findPositives(numbers) {
        numbers.findAll { it > 0 }
    }
}
