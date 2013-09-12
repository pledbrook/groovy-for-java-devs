package org.springframework.samples.travel.domain.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Hackety hackety hack
 *
 * Hack - our client (Simple XML on Android) doesn't know how to parse much and it'snot easy to teach it how to parse anything
 * since the entire Simple XML API is all package-private classes that are usually also final.
 *
 * So, we change it in JAXB2, which is also difficult. Either we use a {@link javax.xml.datatype.XMLGregorianCalendar}
 * for our data types in our entities (which would both be ugly and possibly cause problems since our entities also need
 * to work with JPA) or we teach JAXB how to 'adapt' a Date to and from a String,  This class is an attempt
 * to do the latter.
 *
 *
 * @author Josh Long
 */
public class DateFormatXmlAdapter extends XmlAdapter<String, Date> {

  private DateTimeFormatter df =  ISODateTimeFormat.date();

  public Date unmarshal(String date) throws Exception {
    return df.parseDateTime(date).toDate();
  }

  public String marshal(Date date) throws Exception {
    return df.print(new DateTime(date));
  }
}
