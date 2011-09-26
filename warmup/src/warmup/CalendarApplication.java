package warmup;

import java.util.ArrayList;

public class CalendarApplication {
	
	private ArrayList<User> users;
	
	public CalendarApplication() {
		this.users = new ArrayList<User>();
		initUsers();
	}

	private void initUsers() {
		createNewUser("Dani");
		createNewUser("Sepp");
		createNewUser("Bruno");
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public void createNewUser(String name) {
		User newUser = new User(name, this);
		this.users.add(newUser);
	}
	
	public static void main(String[] args) {
		new CalendarApplication();
	}

}
