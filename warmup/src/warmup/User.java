package warmup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private String name;
	private Calendar cal;
	private SimpleDateFormat simpleDateFormatter;
	
	public User(String name) {
		this.name = name;
		this.cal = new Calendar(this, "Home");
		this.simpleDateFormatter = new SimpleDateFormat("dd.MM.yy kk:mm");
	}
	
	public void createEvent() {
		String eventName = getStringInput();
		Date startDate = getDateInput();
		Date endDate = getDateInput();
		boolean isPublic = getBooleanInput();
		assert (startDate.before(endDate));
		Event event = new Event(eventName, startDate, endDate, isPublic);
		cal.addEvent(event);
	}

	public ArrayList<Event> getVisibleEventsOnSpecificDayFrom(User user, Date date) {
		Calendar cal = user.getCalendar();
		ArrayList<Event> visibleEvents = new ArrayList<Event>();
		if (this.equals(user)) {
			for (Event e : cal.getEvents()) {
				if (e.happensOn(date)) {
					visibleEvents.add(e);
				}
			}
		} else {
			for (Event e : cal.getPublicEvents()) {
				if (e.happensOn(date)) {
					visibleEvents.add(e);
				}
			}
		}
		return visibleEvents;
	}
	
	public String toString() {
		return this.name;
	}
	
	public Calendar getCalendar() {
		return this.cal;
	}
	
	private boolean getBooleanInput() {
		String input = ""; //implement input method for user interaction
		return input.equalsIgnoreCase("y");
	}

	private String getStringInput() {
		//TODO
		String input = ""; //implement input method for user interaction
		return input;
	}

	private Date getDateInput() {
		//TODO
		String input = ""; //implement input method for user interaction
		Date inputDate = parseStringToDate(input);
		return inputDate;
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

}
