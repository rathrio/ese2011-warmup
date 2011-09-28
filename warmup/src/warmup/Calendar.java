package warmup;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;


public class Calendar implements Iterable<Event>{
	
	private String name;
	private User owner;
	private PriorityQueue<Event> events;
	private PriorityQueue<Event> publicEvents;
	
	public Calendar(User owner, String name) {
		this.owner = owner;
		this.name = name;
		this.events = new PriorityQueue<Event>();
		this.publicEvents = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event event) {
		if (event.isPublic()) {
			this.publicEvents.add(event);
		}
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
	
	public boolean isOwner(User user) {
		return owner.equals(user);
	}
	
	public ArrayList<Event> getPublicEvents() {
		return new ArrayList<Event>(this.publicEvents);
	}

	@Override
	public Iterator<Event> iterator() {
		ArrayList<Event> orderedEvents = new ArrayList<Event>();
		PriorityQueue<Event> events = new PriorityQueue<Event>(this.events);
		while (!events.isEmpty()) {
			orderedEvents.add(events.poll());
		}
		return orderedEvents.iterator();
	}

	public Iterator<Event> getEventsAfter(User user, Date startingDate)  {
		ArrayList<Event> iterableEvents = new ArrayList<Event>();
		if (user.equals(this.owner)) {
			for (Event event : this.events) {
				if (!event.getStartDate().before(startingDate)) {
					iterableEvents.add(event);
				}
			}
		} else {
			for (Event event : this.publicEvents) {
				if (!event.getStartDate().before(startingDate)) {
					iterableEvents.add(event);
				}
			}
		}
		return iterableEvents.iterator();
	}

	public Object getOwner() {
		return this.owner;
	}
	
}
