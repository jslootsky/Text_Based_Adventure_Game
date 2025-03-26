package game;

class Game {
	private Room[] allRooms;
	private Door[] allDoors;
	private static boolean lightsOn;
	private static boolean gameEnd;
	
	public Game(Room[] listOfRooms, Door[] listOfDoors) {
		allRooms = listOfRooms;
		this.allDoors = listOfDoors;
		lightsOn = false;
		gameEnd = false;
	}
	
	public static void intro() {
		System.out.println("You awake on the floor of a long hallway. It reminds you of a hospital or a corporate office building.\n"
				+ "Red emergency lights dot the edges of the hallway, only slightly lighting your path.\n"
				+ "An alarm which has clearly been blaring for a long time now drones on in the background. A robotic female voice states in an uncharacteristically\n"
				+ "relaxed voice, \"It has been ... 0 ... days since the last mass outbreak\". "
				+ "Checking the pockets of your orange jumpsuit you notice a keycard with your face on it. \n"
				+ "What is the name written on it?");
	}
	
	//prints a random negative response
	public static void getNegativeResponse() {
		int randNum = (int)(Math.random() * 3);
		if(randNum == 0) {
			System.out.println("I do not know what you mean");
		}else if(randNum == 1) {
			System.out.println("I do not understand");
		}else if(randNum == 2) {
			System.out.println("I am not sure what you are referring to");
		}
		if(randNum == 0) {
			System.out.println("Hint: you can type 'help' at any time for a list of possible commands.");
		}else if(randNum == 1) {
			System.out.println("Hint: if you are trying to use or take an item, exact wording and spelling counts, minus the asterisk");
		}else if(randNum == 2) {
			System.out.println("Hint: it is recommended that you visit every room possible, especially after you turn on the lights");
		}
	}
	
	//finds the keyword that specifies which object the player is referring to. command can be "move to" or "look at" and keyword is everything following commmand
	public static String findKeyword(String userIn, String command) {
		String keyword = "";
		keyword = userIn.substring(command.length() + 1);
		return keyword;
	}
	
	//returns the first instance of the room with the same name as String name. Returns null if not found
	public Room findRoom(String name) {
		Room result = new Room("null");
		for(int i = 0; i < allRooms.length; i++) {
			if(allRooms[i].getName().equals(name.toLowerCase()) || allRooms[i].getAltName().equals(name.toLowerCase())) {
				result = allRooms[i];
				return result;
			}
		}
		return result;
	}
	
	//returns the proper door which the user is standing at or null if the door does not exist in the list
	public Door findDoor(String name) {
		Door result = new Door("null");
		for(int i = 0; i < allDoors.length; i++) {
			if(allDoors[i].getDoorName().equals(name.toLowerCase())) {
				result = allDoors[i];
				return result;
			}
		}
		return result;
	}
	
	public void getHelp() {
		System.out.println("Available commands are 'look', 'look at', 'move to', 'take', 'inventory', and 'use.'\n"
				+ "Enter 'exit' or 'quit' to end the game.");
		System.out.println("'look' gives a description of your surroundings. The description changes depending on visibility.\n"
				+ "'look at' gives a description of a specified door/room.\n"
				+ "'move to' moves the player to a room. If that room is locked, then the player stands in front of the door and the user acts accordingly.\n"
				+ "'take' allows the user to take an object from a room that is specified with capitalized spelling and an asterisk. Certain objects are stationary.\n"
				+ "'inventory' lists all items in the user's inventory.\n"
				+ "'use' is a context-based command that does something different based on what object/item the user specifies. \n"
				+ "ex: entering 'use door 8' opens door 8 if the player has the key in their inventory.");
	}
	
	public static boolean areLightsOn() {
		return lightsOn;
	}
	
	public static void turnLightsOn() {
		lightsOn = true;
	}
	
	public static boolean gameEnd() {
		return gameEnd;
	}
	
	public static void endGame() {
		gameEnd = true;
	}
	
}
