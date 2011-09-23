package warmup;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Calendar {
	
	private String name;
	private PriorityQueue<Event> events;
	private User owner;
	
	public Calendar(User owner, String name) {
		this.owner = owner;
		this.name = name;
		this.events = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
	}
	
	public String getName() {
		return this.name;
	}

	public ArrayList<Event> getEvents() {
		return new ArrayList<Event>(this.events);
	}

	public boolean isEmpty() {
		return this.events.isEmpty();
	}

	public Object getNextEvent() {
		return this.events.peek();
	}
	
}
