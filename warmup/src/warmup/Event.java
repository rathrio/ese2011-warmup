package warmup;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Event implements Comparable<Event> {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private SimpleDateFormat simpleDateFormatter;
	private boolean isPublic;
	
	
	public Event(String name, String startDate, String endDate) {
		this.name = name;
		this.simpleDateFormatter = new SimpleDateFormat("dd.MM.yy kk:mm");
		this.startDate = parse(startDate);
		this.endDate = parse(endDate);
		this.isPublic = false;
	}
	
	private Date parse(String strDate) {
		Date date = null;
		try {
			date = simpleDateFormatter.parse(strDate);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return date;
	}

	public void share() {
		this.isPublic = true;
	}
	
	public void unshare() {
		this.isPublic = false;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	@Override
	public int compareTo(Event otherEvent) {
		Date otherStartDate = otherEvent.getStartDate();
		if (startDate.before(otherStartDate)) {
			return -1;
		}
		if (startDate.after(otherStartDate)) {
			return 1;
		}
		return 0;
	}
	
	public String toString() {
		return this.name;
	}
}