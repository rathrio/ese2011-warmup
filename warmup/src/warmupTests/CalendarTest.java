package warmupTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


import org.junit.Before;
import org.junit.Test;

import warmup.*;

public class CalendarTest {
	
	private Calendar cal;
	private User owner;
	private User otherUser;
	private Date firstStartDate;
	private Date firstEndDate;
	private Date secondStartDate;
	private Date secondEndDate;
	private Date thirdStartDate;
	private Date thirdEndDate;
	
	@Before
	public void init() {
		owner = new User("testUser");
		otherUser = new User("otherUser");
		cal = owner.getCalendar();
		firstStartDate = owner.parseStringToDate("01.01.01 12:00");
		firstEndDate = owner.parseStringToDate("01.01.01 13:00");
		secondStartDate = owner.parseStringToDate("01.01.01 06:00");
		secondEndDate = owner.parseStringToDate("01.01.01 07:00");
		thirdStartDate = owner.parseStringToDate("01.01.01 19:00");
		thirdEndDate = owner.parseStringToDate("01.01.01 23:00");
	}
	
	@Test
	public void shouldHaveNameHomeAndOwner() {
		assertEquals(cal.getName(), "Home");
		assertEquals(cal.getOwner(), owner);
	}
	
	@Test
	public void shouldAddEvent() {
		Event testEvent = new Event("testEvent", firstStartDate, firstEndDate, false);
		assertTrue(cal.isEmpty());
		cal.addEvent(testEvent);
		assertFalse(cal.isEmpty());
	}
	
	@Test
	public void shouldGetNextEvent() {
		Event firstEvent = new Event("dinner with your ex", firstStartDate, firstEndDate, false);
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate, false);
		cal.addEvent(firstEvent);
		assertEquals(cal.getNextEvent(), firstEvent);
		cal.addEvent(secondEvent);
		assertEquals(cal.getNextEvent(), secondEvent);
	}
	
	@Test
	public void shouldAddSecondEventBeforeFirstEvent() {
		assertTrue(cal.isEmpty());
		Event firstEvent = new Event("firstEvent", firstStartDate, firstEndDate, false);
		cal.addEvent(firstEvent);
		assertEquals(cal.getEvents().size(), 1);
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate, false);
		cal.addEvent(secondEvent);
		assertEquals(cal.getEvents().size(), 2);
		ArrayList<Event> events = cal.getEvents();
		assertTrue(secondEvent.getStartDate().before(firstEvent.getStartDate()));
		assertEquals(events.get(0), secondEvent);
	}
	
	@Test
	public void shouldGetIterator() {
		firstStartDate = owner.parseStringToDate("12.04.95 11:55");
		firstEndDate = owner.parseStringToDate("12.04.95 13:00");
		Event firstEvent = new Event("firstEvent", firstStartDate, firstEndDate, true);
		
		secondStartDate = owner.parseStringToDate("14.04.95 15:00");
		secondEndDate = owner.parseStringToDate("14.04.95 18:00");
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate, true);
		
		cal.addEvent(firstEvent);
		cal.addEvent(secondEvent);
		
		Iterator<Event> eventsIterator = cal.iterator();
		assertEquals(firstEvent, eventsIterator.next());
		assertEquals(secondEvent, eventsIterator.next());
	}
	
	@Test
	public void shouldNotIterateOverSecondEvent() {
		firstStartDate = owner.parseStringToDate("18.04.95 11:55");
		firstEndDate = owner.parseStringToDate("20.04.95 13:00");
		Event firstEvent = new Event("firstEvent", firstStartDate, firstEndDate, true);
		
		secondStartDate = owner.parseStringToDate("24.04.95 15:00");
		secondEndDate = owner.parseStringToDate("30.04.95 18:00");
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate, false);
		
		thirdStartDate = owner.parseStringToDate("30.04.95 15:00");
		thirdEndDate = owner.parseStringToDate("02.05.95 18:00");
		Event thirdEvent = new Event("thirdEvent", thirdStartDate, thirdEndDate, true);
		
		cal.addEvent(firstEvent);
		cal.addEvent(secondEvent);
		cal.addEvent(thirdEvent);
		
		Iterator<Event> eventsIterator = cal.getEventsAfter(otherUser, firstStartDate);
		assertEquals(firstEvent, eventsIterator.next());
		assertEquals(thirdEvent, eventsIterator.next());
	}
	
	@Test
	public void shouldGetIteratorOverEventsAfterSpecificDate() {
		firstStartDate = owner.parseStringToDate("18.04.95 11:55");
		firstEndDate = owner.parseStringToDate("20.04.95 13:00");
		Event firstEvent = new Event("firstEvent", firstStartDate, firstEndDate, true);
		
		secondStartDate = owner.parseStringToDate("24.04.95 15:00");
		secondEndDate = owner.parseStringToDate("30.04.95 18:00");
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate, true);
		
		thirdStartDate = owner.parseStringToDate("30.04.95 15:00");
		thirdEndDate = owner.parseStringToDate("02.05.95 18:00");
		Event thirdEvent = new Event("thirdEvent", thirdStartDate, thirdEndDate, true);
		
		cal.addEvent(firstEvent);
		cal.addEvent(secondEvent);
		cal.addEvent(thirdEvent);
		
		Date testDate = owner.parseStringToDate("22.04.95 16:00");
		
		Iterator<Event> eventsIterator = cal.getEventsAfter(otherUser, testDate);
		assertEquals(secondEvent, eventsIterator.next());
		assertEquals(thirdEvent, eventsIterator.next());
	}
	
}
