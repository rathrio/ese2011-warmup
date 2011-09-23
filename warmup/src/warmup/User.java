package warmup;

public class User {
	
	private String name;
	private Calendar cal;
	
	public User(String name) {
		this.name = name;
		this.cal = new Calendar(this, "Home");
	}
	
	public String getName() {
		return this.name;
	}
	
	public Calendar getCal() {
		return this.cal;
	}
	
	public void createEvent() {
		String eventName = getEventName();
		String startDate = getDateInput();
		String endDate = getDateInput();
		Event event = new Event(eventName, startDate, endDate);
		cal.addEvent(event);
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