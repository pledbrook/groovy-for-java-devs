package org.springframework.samples.travel.web

import groovy.transform.TypeChecked
import java.security.Principal

import javax.inject.Inject

import org.springframework.samples.travel.domain.Booking
import org.springframework.samples.travel.services.BookingService
import org.springframework.samples.travel.domain.Hotel
import org.springframework.samples.travel.services.SearchCriteria
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@TypeChecked
@Controller
public class HotelsMvcController {

    @Inject private BookingService bookingService

    @RequestMapping(value = "/hotels/search", method = RequestMethod.GET)
    void search(SearchCriteria searchCriteria, Principal currentUser, Model model) {
        println ">> Hello there: ${model.asMap()}"
        if (currentUser != null) {
            List<Booking> booking = bookingService.findBookings(currentUser.getName());
            model.addAttribute(booking);
        }
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
    String list(SearchCriteria criteria, Model model) {
        List<Hotel> hotels = bookingService.findHotels(criteria);
        model.addAttribute(hotels);
        return "hotels/list";
    }

    @RequestMapping(value = "/hotels/{id}", method = RequestMethod.GET)
    String show(@PathVariable Long id, Model model) {
        model.addAttribute(bookingService.findHotelById(id));
        return "hotels/show";
    }

    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.DELETE)
    String deleteBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:../hotels/search";
    }

}
