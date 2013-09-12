package org.example

import spock.lang.Specification

class GroovyFileHelperSpec extends Specification {
    def "Exception thrown if file exists"() {
		given: "A file helper and a file path"
		def helper = new GroovyFileHelper()
		def filePath = "test/path"
		
		and: "a mock of File"
		def mock = GroovyMock(Map) {
			1 * exists() >> true
			0 * write()
            getPath() >> filePath
		}
		
		when: "I try to populate a message that already exists"
		helper.populateMessageBackwards(mock, "some message")
		
		then:
		IllegalArgumentException ex = thrown()
		ex.message == "$filePath already exists"
	}

    def "When file doesn't exist"() {
        given: "A file helper and a file path"
        def helper = new GroovyFileHelper()
        def filePath = "test/path"

        and: "a mock of File"
        def mock = GroovyMock(Map) {
            1 * exists() >> false
        }

        when: "I try to populate a message in a new file"
        helper.populateMessageBackwards(mock, "some message")

        then: "the reverse message is written to the target file"
        1 * mock.write("egassem emos")
    }
}
