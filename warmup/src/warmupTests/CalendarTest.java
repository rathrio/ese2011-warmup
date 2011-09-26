package warmupTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

import warmup.*;

public class CalendarTest {
	
	private Calendar cal;
	private CalendarApplication calApp;
	private User owner;
	private Date firstStartDate;
	private Date firstEndDate;
	private Date secondStartDate;
	private Date secondEndDate;
	
	@Before
	public void init() {
		calApp = new CalendarApplication();
		owner = calApp.getUsers().get(0);
		cal = owner.getCalendar();
		firstStartDate = owner.parseStringToDate("01.01.01 12:00");
		firstEndDate = owner.parseStringToDate("01.01.01 13:00");
		secondStartDate = owner.parseStringToDate("01.01.01 06:00");
		secondEndDate = owner.parseStringToDate("01.01.01 07:00");
	}
	
	@Test
	public void shouldHaveNameHome() {
		assertEquals(cal.getName(), "Home");
	}
	
	@Test
	public void shouldGetNextEvent() {
		Event firstEvent = new Event("dinner with your ex", firstStartDate, firstEndDate);
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate);
		cal.addEvent(firstEvent);
		assertEquals(cal.getNextEvent(), firstEvent);
		cal.addEvent(secondEvent);
		assertEquals(cal.getNextEvent(), secondEvent);
	}
	
	@Test
	public void shouldAddEvent() {
		Event testEvent = new Event("testEvent", firstStartDate, firstEndDate);
		assertTrue(cal.isEmpty());
		cal.addEvent(testEvent);
		assertFalse(cal.isEmpty());
	}
	
	@Test
	public void shouldAddSecondEventBeforeFirstEvent() {
		assertTrue(cal.isEmpty());
		Event firstEvent = new Event("firstEvent", firstStartDate, firstEndDate);
		cal.addEvent(firstEvent);
		assertEquals(cal.getEvents().size(), 1);
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate);
		cal.addEvent(secondEvent);
		assertEquals(cal.getEvents().size(), 2);
		ArrayList<Event> events = cal.getEvents();
		assertTrue(secondEvent.getStartDate().before(firstEvent.getStartDate()));
		assertEquals(events.get(0), secondEvent);
	}
	
}
