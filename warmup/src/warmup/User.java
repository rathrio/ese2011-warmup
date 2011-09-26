package warmup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private String name;
	private Calendar cal;
	private CalendarApplication calApp;
	private SimpleDateFormat simpleDateFormatter;
	
	public User(String name, CalendarApplication calendarApplication) {
		this.name = name;
		this.cal = new Calendar(this, "Home");
		this.calApp = calendarApplication;
		this.simpleDateFormatter = new SimpleDateFormat("dd.MM.yy kk:mm");
	}
	
	public String toString() {
		return this.name;
	}
	
	public Calendar getCalendar() {
		return this.cal;
	}
	
	public void createEvent() {
		String eventName = getEventName();
		String strStartDate = getDateInput();
		String strEndDate = getDateInput();
		Date startDate = parseStringToDate(strStartDate);
		Date endDate = parseStringToDate(strEndDate);
		assert (startDate.before(endDate));
		Event event = new Event(eventName, startDate, endDate);
		cal.addEvent(event);
	}
	
	@ForTestingOnly
	public Date parseStringToDate(String strDate) {
		Date date = null;
		try {
			date = simpleDateFormatter.parse(strDate);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return date;
	}
	
	public ArrayList<Event> getVisibleEventsFrom(User user, Date date) {
		ArrayList<Event> visibleEvents = new ArrayList<Event>();
		ArrayList<Event> events = user.getCalendar().getEvents();
		for (Event event : events) {
			if (event.happensOn(date)) {
				if (user.equals(this)) {
					visibleEvents.add(event);
				} else {
					if (event.isPublic()) {
						visibleEvents.add(event);
					}
				}
			}
		}
		return visibleEvents;
	}
	
	@ForTestingOnly
	public void shareCalendar() {
		ArrayList<Event> events = cal.getEvents();
		for (Event event : events) {
			event.setPublic();
		}
	}

	private String getEventName() {
		//TODO
		return null;
	}

	private String getDateInput() {
		//TODO
		return null;
	}


}
