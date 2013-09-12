package org.example

import spock.lang.Specification
import spock.lang.Unroll


class NumberHelperSpec extends spock.lang.Specification {
    def "Test division of numbers by 2"() {
		given: "Some number"
		def n = 150
		
		expect: "Dividing by 2 halves the number"
		assert n / 2 == 75
	}























    /*
	def "Number helper finds positive numbers"() {
		given: "A number helper instance"
		def helper = new GroovyNumberHelper()
		
		when: "I search for positive numbers in a list"
		def result = helper.findPositives(sourceList)
		
		then: "Only the +ve numbers are returned (as a list)"
		result == expected
		
		where:
		sourceList       |       expected
		null             |         []
		[]               |         []
		[-1, -10]        |         [-1]
		[1, -1, 0, 2]    |         [1, 0, 2]
		[1, 20, 100]     |         [1, 20, 100]
    }
    */
}
