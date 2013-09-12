package org.springframework.samples.travel;

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.samples.travel.domain.Amenity;
import org.springframework.samples.travel.domain.Booking;
import org.springframework.samples.travel.domain.Hotel;
import org.springframework.samples.travel.domain.User;
import org.springframework.samples.travel.rest.RestConfiguration;

import spock.lang.Specification;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

class BookingMarshallingSpec extends Specification {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RestConfiguration)
    Jaxb2Marshaller jaxb2Marshaller = applicationContext.getBean(Jaxb2Marshaller)

    void "Test something"() {
        given: "An LA hotel"
        def hotel = new Hotel(
            address: "12332 Street St",
            city: "Los Angeles, CA",
            country: "USA",
            price: 242,
            id: 2168,
            zip: "90210",
            name: "The Hotel")

        and: "A user"
        def u = new User(name: "Name Of User", password: "PassWord", username: "UserName")

        and: "A booking by that user at that hotel"
        def booking = new Booking(
            amenities: [Amenity.LATE_CHECKOUT, Amenity.MINIBAR] as Set,
            beds: 2,
            hotel: hotel,
            id: 245L,
            smoking: false,
            user: u)
        booking.checkinDate = new DateTime(DateTimeZone.UTC).withDate(2012, 10, 21).toDate()
        booking.checkoutDate = new DateTime(DateTimeZone.UTC).withDate(2012, 10, 24).toDate()
        
        and: "An expected output"
        def expected = """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>\
<booking total="726" smoking="false" nights="3" id="245" creditCardExpiryYear="0" creditCardExpiryMonth="0" checkoutDate="2012-10-24" checkinDate="2012-10-21" beds="2">\
<amenities><amenity>LATE_CHECKOUT</amenity><amenity>MINIBAR</amenity></amenities>\
<hotel zip="90210" price="242" name="The Hotel" id="2168" country="USA" city="Los Angeles, CA" address="12332 Street St"/>\
<user username="UserName" password="PassWord" name="Name Of User"/></booking>"""

        when: "The booking is serialized"
        def hotelString = asString(jaxb2Marshaller, booking)

        then: "We get the expected output"
        hotelString == expected
    }

    private static String asString(Marshaller m, Object graph) throws Throwable {
        StringWriter w = null;
        try {
            w = new StringWriter();
            Result result = new StreamResult(w);
            m.marshal(graph, result);
        } finally {
            if (w != null) {
                w.close();
            }
        }
        return w.toString();
    }
}
