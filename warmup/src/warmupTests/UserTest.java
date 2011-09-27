package warmupTests;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import warmup.*;

public class UserTest {
	
	private User firstUser;
	private User secondUser;
	private Calendar firstCalendar;
	private ArrayList<Event> eventsVisibleToSecondUser;
	
	@Before
	public void init() {
		this.firstUser = new User("firstUser");
		this.secondUser = new User("secondUser");
		this.firstCalendar = firstUser.getCalendar();
		this.eventsVisibleToSecondUser = new ArrayList<Event>();
	}
	
	@Test
	public void shouldHaveAName() {
		assertEquals("firstUser", firstUser.toString());
	}
	
	@Test
	public void shouldInitCalendar() {
		assertFalse(firstUser.getCalendar().equals(null));
	}
	
	@Test
	public void shouldParseDateCorrectly() {
		String strDate = "12.04.95 14:40";
		Date date = firstUser.parseStringToDate(strDate);
		assertEquals("Wed Apr 12 14:40:00 CEST 1995", date.toString());
	}
	
	@Test
	public void shouldNotGetPrivateEvent() {
		Date startDate = firstUser.parseStringToDate("23.09.11 18:00");
		Date endDate = firstUser.parseStringToDate("30.09.11 17:00");
		Event event = new Event("event", startDate, endDate, false);
		firstCalendar.addEvent(event);
		Date testDay = secondUser.parseStringToDate("24.09.11 13:30");
		eventsVisibleToSecondUser = secondUser.getVisibleEventsOnSpecificDayFrom(firstUser, testDay);
		assertFalse(eventsVisibleToSecondUser.contains(event));
	}
	
	@Test
	public void shouldGetPublicEvent() {
		Date startDate = firstUser.parseStringToDate("24.09.11 17:00");
		Date endDate = firstUser.parseStringToDate("24.09.11 18:00");
		Event event = new Event("event", startDate, endDate, true);
		firstCalendar.addEvent(event);
		Date testDay = secondUser.parseStringToDate("24.09.11 13:30");
		eventsVisibleToSecondUser = secondUser.getVisibleEventsOnSpecificDayFrom(firstUser, testDay);
		assertTrue(eventsVisibleToSecondUser.contains(event));
	}
	
	@Test
	public void shouldGetEventsOverMultipleDays() {
		Date startDate = firstUser.parseStringToDate("22.09.11 17:00");
		Date endDate = firstUser.parseStringToDate("26.09.11 18:00");
		Event event = new Event("event", startDate, endDate, true);
		firstCalendar.addEvent(event);
		Date testDay = secondUser.parseStringToDate("24.09.11 13:30");
		eventsVisibleToSecondUser = secondUser.getVisibleEventsOnSpecificDayFrom(firstUser, testDay);
		assertTrue(eventsVisibleToSecondUser.contains(event));
	}
	
	@Test
	public void shouldNotGetEventOnOtherDate() {
		Date startDate = firstUser.parseStringToDate("25.09.11 17:00");
		Date endDate = firstUser.parseStringToDate("25.09.11 18:00");
		Event event = new Event("event", startDate, endDate, true);
		firstCalendar.addEvent(event);
		Date testDay = secondUser.parseStringToDate("24.09.11 13:30");
		eventsVisibleToSecondUser = secondUser.getVisibleEventsOnSpecificDayFrom(firstUser, testDay);
		assertFalse(eventsVisibleToSecondUser.contains(event));
		assertTrue(eventsVisibleToSecondUser.isEmpty());
	}
	
	@Test
	public void shouldGetEventPartiallyMatchingDay() {
		Date startDate = firstUser.parseStringToDate("23.09.11 17:00");
		Date endDate = firstUser.parseStringToDate("24.09.11 18:00");
		Date startDate2 = firstUser.parseStringToDate("24.09.11 17:00");
		Date endDate2 = firstUser.parseStringToDate("25.09.11 18:00");
		Event event = new Event("event1", startDate, endDate, true);
		Event event2 = new Event("event2", startDate2, endDate2, true);
		firstCalendar.addEvent(event);
		firstCalendar.addEvent(event2);
		Date testDay = secondUser.parseStringToDate("24.09.11 13:30");
		eventsVisibleToSecondUser = secondUser.getVisibleEventsOnSpecificDayFrom(firstUser, testDay);
		assertTrue(eventsVisibleToSecondUser.contains(event));
		assertTrue(eventsVisibleToSecondUser.contains(event2));
	}

}
