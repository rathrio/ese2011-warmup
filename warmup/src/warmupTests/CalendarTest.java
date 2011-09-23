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
	private User owner;
	
	@Before
	public void init() {
		owner = new User("Dani");
		cal = owner.getCal();
	}
	
	@Test
	public void shouldHaveNameHome() {
		assertEquals(cal.getName(), "Home");
	}
	
	@Test
	public void shouldGetNextEvent() {
		Event firstEvent = new Event("firstEvent", "01.01.01 12:00", "01.01.01 13:00");
		Event secondEvent = new Event("secondEvent", "01.01.01 06:00", "01.01.01 07:00");
		cal.addEvent(firstEvent);
		assertEquals(cal.getNextEvent(), firstEvent);
		cal.addEvent(secondEvent);
		assertEquals(cal.getNextEvent(), secondEvent);
	}
	
	@Test
	public void shouldAddEvent() {
		Event testEvent = new Event("testEvent", "01.01.01 00:00", "02.02.02 12:12");
		assertTrue(cal.isEmpty());
		cal.addEvent(testEvent);
		assertFalse(cal.isEmpty());
	}
	
	@Test
	public void shouldAddSecondEventBeforeFirstEvent() {
		assertTrue(cal.isEmpty());
		Event firstEvent = new Event("firstEvent", "01.01.01 12:00", "01.01.01 13:00");
		cal.addEvent(firstEvent);
		assertEquals(cal.getEvents().size(), 1);
		Event secondEvent = new Event("secondEvent", "01.01.01 06:00", "01.01.01 07:00");
		cal.addEvent(secondEvent);
		assertEquals(cal.getEvents().size(), 2);
		ArrayList<Event> events = cal.getEvents();
		assertTrue(secondEvent.getStartDate().before(firstEvent.getStartDate()));
		assertEquals(events.get(0), secondEvent);
	}
}
