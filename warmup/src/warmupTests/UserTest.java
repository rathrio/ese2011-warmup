package warmupTests;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import warmup.*;

public class UserTest {
	
	private CalendarApplication calApp;
	private User testUser;
	
	@Before
	public void init() {
		this.calApp = new CalendarApplication();
		this.testUser = new User("Simon");
	}
	
	@Test
	public void shouldHaveAName() {
		//this will not add the User to the calApp list of users
		assertEquals("Simon", testUser.toString());
	}
	
	@Test
	public void shouldInitCalendar() {
		assertFalse(testUser.getCalendar().equals(null));
	}
	
	@Test
	public void shouldParseDateCorrectly() {
		String strDate = "12.04.95 14:40";
		Date date = testUser.parseStringToDate(strDate);
		assertEquals("Wed Apr 12 14:40:00 CEST 1995", date.toString());
	}
	

	public void shouldGetAllVisibleEventsFromOtherCalendar() {
		User firstUser = calApp.getUsers().get(0);
		User secondUser = calApp.getUsers().get(1);
		Calendar firstCalendar = firstUser.getCalendar();
		Date startDate1 = firstUser.parseStringToDate("24.09.11 13:00");
		Date endDate1 = firstUser.parseStringToDate("24.09.11 17:00");
		Date startDate2 = firstUser.parseStringToDate("24.09.11 21:35");
		Date endDate2 = firstUser.parseStringToDate("25.09.11 02:30");
		Date startDate3 = firstUser.parseStringToDate("25.09.11 14:00");
		Date endDate3 = firstUser.parseStringToDate("25.09.11 18:00");
		Event event1 = new Event("event1", startDate1, endDate1);
		Event event2 = new Event("event2", startDate2, endDate2);
		Event event3 = new Event("event3", startDate3, endDate3);
		firstCalendar.addEvent(event1);
		firstCalendar.addEvent(event2);
		firstCalendar.addEvent(event3);
		firstUser.shareCalendar();
		Date testDay = secondUser.parseStringToDate("24.09.11 00:00");
		ArrayList<Event> eventsVisibleToSecondUser = secondUser.getVisibleEventsFrom(firstUser, testDay);
		ArrayList<Event> publicEventsOnThatDay = new ArrayList<Event>();
		ArrayList<Event> allEvents = firstCalendar.getEvents();
		publicEventsOnThatDay.add(event1);
		publicEventsOnThatDay.add(event2);
		assertEquals(eventsVisibleToSecondUser, publicEventsOnThatDay);
		assertNotSame(eventsVisibleToSecondUser, allEvents);
	}

}
