package warmup;

import java.util.ArrayList;

public class CalendarApplication {
	
	private ArrayList<User> users;
	
	public CalendarApplication() {
		this.users = new ArrayList<User>();
	}

	@ForTestingOnly
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public static void main(String[] args) {
		new CalendarApplication();
	}

}
