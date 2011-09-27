package warmupTests;

import static org.junit.Assert.*;

import java.util.Date;


import org.junit.*;

import warmup.Event;
import warmup.User;

public class EventTest {
	
	private User owner;
	private Date firstStartDate;
	private Date firstEndDate;
	private Date secondStartDate;
	private Date secondEndDate;
	private Date thirdStartDate;
	private Date thirdEndDate;
	private Date testDate;
	
	
	@Before
	public void init() {
		this.owner = new User("testUser");
	}
	
	@Test
	public void shouldBeNamedCorrectly() {
		firstStartDate = owner.parseStringToDate("23.09.11 18:00");
		firstEndDate = owner.parseStringToDate("23.09.11 20:00");
		Event lunch = new Event("Lunch with Mom", firstStartDate, firstEndDate, false);
		assertEquals("Lunch with Mom", lunch.getName());
	}
	
	@Test
	public void shouldHaveStartAndEndDate() {
		firstStartDate = owner.parseStringToDate("23.09.11 18:00");
		firstEndDate = owner.parseStringToDate("23.09.11 20:00");
		Event testEvent = new Event("testEvent", firstStartDate, firstEndDate, false);
		assertEquals(firstStartDate, testEvent.getStartDate());
		assertEquals(firstEndDate, testEvent.getEndDate());
	}
	
	@Test
	public void shouldChangeVisibility() {
		firstStartDate = owner.parseStringToDate("23.09.11 18:00");
		firstEndDate = owner.parseStringToDate("23.09.11 20:00");
		Event event = new Event("testEvent", firstStartDate, firstEndDate, false);
		assertFalse(event.isPublic());
		event.setPublic();
		assertTrue(event.isPublic());
		event.setPrivate();
		assertFalse(event.isPublic());
	}
	
	@Test
	public void shouldBeComparable() {
		this.firstStartDate = owner.parseStringToDate("12.04.95 14:00");
		this.firstEndDate = owner.parseStringToDate("12.04.1995 15:00");
		Event firstEvent = new Event("firstEvent", firstStartDate, firstEndDate, false);
		this.secondStartDate = owner.parseStringToDate("12.04.95 10:00");
		this.secondEndDate = owner.parseStringToDate("12.04.95 12:30");
		Event secondEvent = new Event("secondEvent", secondStartDate, secondEndDate, false);
		this.thirdStartDate = owner.parseStringToDate("12.04.95 14:00");
		this.thirdEndDate = owner.parseStringToDate("12.04.1995 18:00");
		Event thirdEvent = new Event("thirdEvent", thirdStartDate, thirdEndDate, false);
		assertEquals(firstEvent.compareTo(secondEvent), 1);
		assertEquals(secondEvent.compareTo(firstEvent), -1);
		assertEquals(thirdEvent.compareTo(firstEvent), 0);
	}
	
	@Test
	public void shouldGetStartAndEndDate() {
		this.firstStartDate = owner.parseStringToDate("12.04.95 12:36");
		this.firstEndDate = owner.parseStringToDate("12.04.95 14:12");
		Event testEvent = new Event("testEvent", firstStartDate, firstEndDate, false);
		Date testStartDate = owner.parseStringToDate("12.04.95 12:36");
		Date testEndDate = owner.parseStringToDate("12.04.95 14:12");
		assertEquals(testStartDate, testEvent.getStartDate());
		assertEquals(testEndDate, testEvent.getEndDate());
	}
	
	@Test
	public void shouldHappenOnSpecificDate() {
		this.firstStartDate = owner.parseStringToDate("24.09.11 15:00");
		this.firstEndDate = owner.parseStringToDate("24.09.11 18:00");
		this.testDate = owner.parseStringToDate("24.09.11 23:00");
		Event testEvent = new Event("Fuetzfues Birthday", firstStartDate, firstEndDate, false);
		assertTrue(testEvent.happensOn(testDate));
	}
	
	@Test
	public void shouldHappenOnSpecificDateIfEventBeginsBeforeSpecifiedDay() {
		this.firstStartDate = owner.parseStringToDate("23.09.11 15:00");
		this.firstEndDate = owner.parseStringToDate("24.09.11 18:00");
		this.testDate = owner.parseStringToDate("24.09.11 23:00");
		Event testEvent = new Event("Fuetzfues Birthday", firstStartDate, firstEndDate, false);
		assertTrue(testEvent.happensOn(testDate));
	}
	
	@Test
	public void shouldHappenOnSpecificDateAfterSpecifiedDay() {
		this.firstStartDate = owner.parseStringToDate("24.09.11 15:00");
		this.firstEndDate = owner.parseStringToDate("25.09.11 18:00");
		this.testDate = owner.parseStringToDate("24.09.11 23:00");
		Event testEvent = new Event("Fuetzfues Birthday", firstStartDate, firstEndDate, false);
		assertTrue(testEvent.happensOn(testDate));
	}
	
	@Test
	public void shouldHappenOnSpecificDateIfEventSpansOverSpecifiedDay() {
		this.firstStartDate = owner.parseStringToDate("23.09.11 15:00");
		this.firstEndDate = owner.parseStringToDate("25.09.11 18:00");
		this.testDate = owner.parseStringToDate("24.09.11 23:00");
		Event testEvent = new Event("Fuetzfues Birthday", firstStartDate, firstEndDate, false);
		assertTrue(testEvent.happensOn(testDate));
		this.firstStartDate = owner.parseStringToDate("15.06.09 15:00");
		this.firstEndDate = owner.parseStringToDate("27.11.15 18:00");
		this.testDate = owner.parseStringToDate("24.09.11 23:00");
		testEvent = new Event("Fuetzfues Birthday", firstStartDate, firstEndDate, false);
		assertTrue(testEvent.happensOn(testDate));
	}
	
}
