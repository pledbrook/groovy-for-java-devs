package org.springframework.samples.travel.services

import spock.lang.Specification;
import spock.lang.Unroll;

class NumbersServiceTest extends Specification {
	
	@Unroll("Testing argument #numbers")
	def "Test findPositives()"() {
		given: "I have a number service"
		def service = new NumbersService()
		
		when: "I pass in a list of numbers"
		def result = service.findPositives(numbers)
		
		then: "only the positive numbers are returned"
		result == expected
		
		where:
		numbers        |  expected
		null           |  []
		[]             |  []
		[-1, -3]       |  []
		[1, 0, -2, 3, -5] | [1, 0, 3]
	}
}
