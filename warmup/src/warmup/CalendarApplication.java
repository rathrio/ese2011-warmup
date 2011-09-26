package warmup;

import java.util.ArrayList;

public class CalendarApplication {
	
	private ArrayList<User> users;
	
	public CalendarApplication() {
		this.users = new ArrayList<User>();
		initUsers();
	}

	private void initUsers() {
		//Initialized like this for testing purposes.
		createNewUser("Dani");
		createNewUser("Sepp");
		createNewUser("Bruno");
	}
	
	@ForTestingOnly
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public void createNewUser(String name) {
		User newUser = new User(name);
		this.users.add(newUser);
	}
	
	public static void main(String[] args) {
		new CalendarApplication();
	}

}
