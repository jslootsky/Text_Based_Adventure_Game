package game;

import java.util.Scanner;
import java.util.ArrayList;

class Player{
	private boolean alive;
	private String name;
	private ArrayList<Item> inventory;
	private Room location;
	Scanner reader = new Scanner(System.in);
	private Room[] listOfRooms;
	private Game game;
	
	public Player(String name, Room[] listOfRooms, Game game) {
		alive = true;
		this.name = name;
		inventory = new ArrayList<Item>();
		//room 1 = listOfRooms[1] , hallway = listOfRooms[0]
		this.listOfRooms = listOfRooms;
		this.game = game;
	}
	
	public void use(String keyword) {
		if(keyword.equals("Holy Bible") && location.equals(listOfRooms[1])) {
			if(Game.areLightsOn()) {
				if(hasItem("Mysterious Note")) {
					System.out.println("As you pull the book off the shelf, it suddenly no longer budges. You hear a metallic flick on the other side of the room, revealing a Keypad*. ");
				}else {
					Game.getNegativeResponse();
				}
			}else {
				Game.getNegativeResponse();
			}
		}else if(keyword.equals("Mysterious Note")) {
			if(hasItem("Mysterious Note")) {
				System.out.println("You read the note.\n"
						+ "In case of emergency: Hayden's office is your only escape. Use the book that millions of people have shaped their lives around, the book that millions have died for, the book that many believe "
						+ "offers their salvation.\nAt the bottom is a string of numbers that currently mean nothing to you.");
			}else {
				Game.getNegativeResponse();
			}
		}else if(keyword.equals("Keypad")) {
			if(location.equals(listOfRooms[1]) && hasItem("Mysterious Note") && hasItem("Research Documents")) {
				System.out.println("\nYou check the Mysterious Note you found earlier and enter the string of numbers into the keypad, causing the entire bookcase to slide open, revealing a long, narrow hallway.\n"
						+ "At the end of the hallway is a red door labeled 'EXIT'. This is your salvation.\n"
						+ "Research in hand, the only evidence of what you have experienced, you sprint towards the door, bashing it open with your shoulder and clumsily stumbling out.\n"
						+ "As you examine your surroundings, you notice something is very, very wrong. What was once presumably a bustling metropolis is now devoid of people.\n"
						+ "However, that does not mean that it is devoid of sound. Far off explosions and unworldly bellows can be heard. Distant alarms blare, sounding like that of bomb raid sirens.\n"
						+ "As you look over at the horizon, you expect to see the single, bright-orange ball we call the sun, but that is not the case. \n"
						+ "Instead, in addition to the sun, two more objects similar in size can be seen, but a deep red. They are not constantly burning, occasionally disappearing in unison from top to bottom like someone is covering them.\n"
						+ "You are filled with a horrifying, primal dread that the objects in the sky are nothing like the sun, but blinking eyes.");
				Game.endGame();
			}else if(location.equals(listOfRooms[1]) && hasItem("Mysterious Note")) {
				System.out.println("You cannot leave yet. If nobody knows what happened hear or can preserve the work done, many lives would have been lost for nothing. You must find proof of this place's existence.");
			}else {
				Game.getNegativeResponse();
			}
		}else if(keyword.equals("Breaker Box")) {
			if(location.equals(listOfRooms[4]) && hasItem("Insulated Gloves") && hasItem("Electrical Tape")) {
				System.out.println("Putting on the rubber Insulated Gloves, you open the sparking breaker box to reveal several exposed wires.\n"
						+ "Carefully tying the matching, exposed copper wire to each other and wrapping it in electrical tape, the lights suddenly flick on and your surroundings are revealed.\n"
						+ "Everything looks different now. You wonder what may have been revealed.");
				Game.turnLightsOn();
			}else if(location.equals(listOfRooms[4]) && hasItem("Insulated Gloves")) {
				System.out.println("Putting on the rubber Insulated Gloves, you open the sparking breaker box to reveal several exposed wires. However, with no way to fix it, you simply fiddle with the wires, "
						+ "not actually getting anywhere.");
			}else if(location.equals(listOfRooms[4]) && hasItem("Electrical Tape")) {
				System.out.println("Opening the breaker box reveals several exposed, sparking copper wires. Your hands begin to sweat as you take the Electrical tape and begin to fix the damage.\n"
						+ "However, as you touch the exposed copper with your sweaty hands, 240 Volts of electricity course through your heart, killing you instantly.");
				Game.endGame();
			}else {
				Game.getNegativeResponse();
			}
		}else if(keyword.equals("Blowtorch")) {
			System.out.println("The modified blowtorch is rated to cut right through very thick metal.");
		}else if(keyword.equals("Maintenance Worker Keycard")) {
			System.out.println("This is required for maintenance workers to open doors necessary to maintain the facility.");
		}else if(keyword.equals("Upgraded Keycard")) {
			System.out.println("This keycard allows the holder to open restricted rooms.");
		}else if(keyword.equals("Research Documents")) {
			System.out.println("This folder of papers contains diagrams, blueprints, reports, and other information detailing the research done at this facility.");
		}else if(keyword.equals("ID Card")) {
			System.out.println(getName() + ", D-Class, Clearance 1");
		}else {
			Game.getNegativeResponse();
		}
	}
	
	//returns if the player is alive or not (boolean)
	public boolean getAliveStatus() {
		return alive;
	}
	
	//changes the state of the player to alive or dead
	public void changeAliveStatus() {
		alive = !alive;
	}
	
	//returns the name of the player
	public String getName() {
		return name;
	}
	
	//changes the name of the player in case of a spelling mistake
	public void changeName() {
		//Scanner reader = new Scanner(System.in);
		String newName = "";
		
		String userIn = reader.nextLine();
		while(true) {
			if(!userIn.toLowerCase().equals("y") && !userIn.toLowerCase().equals("n")) {
				Game.getNegativeResponse();
				userIn = reader.nextLine();
			}else {
				if(userIn.toLowerCase().equals("y")) {
					System.out.println("Your eyes sharpen. The card reads: " + getName() + ", D-Class. Clearance 1");
					break;
				}else if(userIn.toLowerCase().equals("n")) {
					System.out.println("What does the card say? ");
					newName = reader.nextLine();
					this.name = newName;
					System.out.println("Your eyes lose focus for a moment and then return to normal. The card reads: Dr. " + getName() + ", Research Department. Clearance 1");
					break;
				}
			}
		}
	}
	
	//returns the room which the player is in
	public Room getRoom() {
		return location;
	}
	
	//adds item to player's inventory
	public void take(String itemName) {
		if(itemName.equals("Upgrade Terminal")) {
			System.out.println("It is bolted to the ground, making it impossible to pick it up.");
		}else if(itemName.equals("Holy Bible")) {
			System.out.println("It is probably best to leave it on the shelf.");
		}else if(itemName.equals("Keypad")) {
			System.out.println("The keypad is bolted onto the wall. Taking it is impossible.");		
		}else if(itemName.equals("Breaker Box")) {
			System.out.println("The Breaker Box is bolted onto the wall. Taking it is impossible.");
		}else if(location.hasItem(itemName)) {
			System.out.println("You take the " + itemName);
			inventory.add(location.getItem(itemName));
			location.takeItem(itemName);
		}else {
			Game.getNegativeResponse();
		}
	}
	
	public void add(Item item) {
		inventory.add(item);
	}
	
	
	//prints all items in inventory
	public void checkInventory() {
		for(int i = 0; i < inventory.size(); i++) {
			System.out.println((i + 1) + ". " + inventory.get(i).getName());
		}
	}
	//prints description of the player's surroundings, depending on the value of their location
	public void look() {
		if(location.getName().equals("hallway")) {
			System.out.println("You are at the end of a corporate style hallway.\n"
					+ "On your left are 4 doors numbered 1, 3, 5, and 7 and on your right another 4 doors numbered 2, 4, 6, and 8.\n"
					+ "There are no doors or visible exits at either end of the hallway.");
			if(!Game.areLightsOn()) {
				System.out.println("Red emergency lights dot the corners of the hallway. Someone has cut the power.");
			}
		}else if(location.getName().equals("room 1")) {//exec office
			if(Game.areLightsOn()) {
				System.out.println("The large desk in the back of the room is absolutely covered in notebooks, folders, papers, and pens\n"
						+ "The bookshelf behind the desk features several classic works such as The Prince, The Iliad, 1984, Ulysses, and even a Holy Bible.");
			}else {
				System.out.println("A lack of windows or strong light sources makes seeing anything discernable nearly impossible");
			}
		}else if(location.getName().equals("room 2")) {//sec office
			if(Game.areLightsOn()) {
				System.out.println("The security office contains several lockers used for personal belongings, weapons, and armor. The walls are lined with hooks meant for the same.\n"
						+ "In the corner is a desk with security cameras that are all disabled and an Upgrade Terminal* bolted to the ground used for upgrading security access.");
			}else {
				System.out.println("No ambient light from the hallway is able to enter the room. You are standing in pitch black darkness.");
			}
			//done
		}else if(location.getName().equals("room 3")) {//research office
			if(Game.areLightsOn()) {
			System.out.println("You stand in a near blinding white room home to many different instruments that you recognize and certain tools that you do not.\n"
					+ "In the center of the room, similar to the ones lining the walls, is an even larger tank, but the surrounding glass is shattered and the liquid emptied, giving the room\n"
					+ "an aroma of phosphorous and bleach. ");
				if(listOfRooms[3].hasItem("Research Documents")) {
					System.out.println("On one of the office desks lies a thick binder of Research Documents*. It is the only physical evidence left intact.");
				}
			}else {
				System.out.println("The only outlines in the room you can make are some tables, chairs, and shelves. You are standing in near pitch black darkness.\n"
						+ "The room has a strong aroma of cleaning chemicals");
			}
			//done
		}else if(location.getName().equals("room 4")) {//electrical room
			if(Game.areLightsOn()) {
				System.out.println("The flickering lightbulb reveals a damp, dingy room with a large A/C unit that has been sucking in air from all around the facility and a breaker box.");
				if(listOfRooms[4].hasItem("Rusted Key")) {
					System.out.println("On a rack near the door hangs an old, Rusted Key*. On the key are the words 'Hayden'.");
				}
			}else {
				System.out.println("As you enter a room, a wall of damp, moist air hits you. The sickly sweet smell of death lingers in the air as you stand in the pitch black of the room.\n"
						+ "On the back wall is a slightly ajar Breaker Box*, only revealed by the momentary flashes of light coming from it.");
			}
			//done
		}else if(location.getName().equals("room 5")) {//med room
			if(Game.areLightsOn()) {
				System.out.println("As you enter the room, your sense of smell is assaulted with the scent of bleach and other cleaning chemicals.\n"
						+ "An operating table, seemingly thrown, is on its side with bottles of assorted medicines and pills strewn about.");
			}else {
				if(listOfRooms[5].hasItem("Electrical Tape")) {
					System.out.println("The white tile of the room and the dim glow from the emergency exit lights reveals a roll of black Electrical Tape* conveniently laying in the doorway.\n"
							+ "It almost seems like it was purposely placed.");
				}else {
					System.out.println("The dim glow from the hallway only reveals the tile immediately next to the doorway.");
				}
			}
			//done
		}else if(location.getName().equals("room 6")) {//comms room
			if(Game.areLightsOn()) {
				System.out.println("In the back of the room sits a large ham radio with dials, screens, and a keypad. A man, if one can even call him that, is slumped over on the wall, motionless.\n"
						+ "What once was a janitor is now a mess contained in a janitorial uniform too gruesome to describe.");
			}else {
				System.out.println("The dim light of the hallway barely lights the room, showing the outline of a few chairs and a table with a large box on top.");
				if(listOfRooms[6].hasItem("Maintenance Worker Keycard")) {
					System.out.println("The dim red light reveals a Maintenance Worker Keycard* attached to a piece of string. What once was a normal black color is now wet with blood and a slight red.");
				}
			}
			//done
		}else if(location.getName().equals("room 7")) {//meeting room
			if(Game.areLightsOn()) {
				System.out.println("Inside the room is a long desk with a newton's cradle in the middle. On one side of the room is a white projector screen and on the other side is"
						+ " a board of post-it notes.\nWritten on them are various reminders, rules, names, and dates.");
				if(listOfRooms[7].hasItem("Mysterious Note")) {
					System.out.println("One Mysterious Note* stands out among the rest.");
				}
			}else {
				System.out.println("The lack of any sort of light leaves you standing in pitch-black darkness. The only thing illuminated is the doorway leading to the hallway.");
			}
			//done
		}else if(location.getName().equals("room 8")) {//reactor room
			if(Game.areLightsOn()) {
				System.out.println("At the back of a room is a large reactor. Its design is unfamiliar to you, resembling a furnace but with flames burning colors unknown to human eyes."
						+ "\nEven though you can see clearly, the light does not make the machine any more recognizable.");
				System.out.println("You notice a toolbox that is wide open, containing an assortment of random metal fragments.");
				if(listOfRooms[8].hasItem("Blowtorch"))
					System.out.println("\nThe only recognizable object is a metal canister that resembles a Blowtorch*.");
			}else {
				if(listOfRooms[8].hasItem("Insulated Gloves")) {
					System.out.println("The faint glow from the reactor reveals a pair of Insulated Gloves* haphazardly lying on the ground.");
				}else {
					System.out.println("The faint glow from the reactor slightly reveals the room, but leaves most of it obscured.");
				}
			}
		}else {
			System.out.println("null");
		}
	}
	
	//prints description of a certain object
	public void lookAt(String keyword) {
		if(keyword.toLowerCase().equals("door 1") || keyword.toLowerCase().equals("1") || keyword.toLowerCase().equals("room 1")) {
			System.out.println("You look over at a set of double doors with the room number one on the right side. Below it says 'Dr. Samuel Hayden, Executive Director.'"
					+ " This is the director's office.");
			if(!listOfRooms[1].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the office is a long, mahogany desk with appropriately furnished shelves, a book case, hardwood flooring as opposed to the concrete of the hall,"
						+ " and chairs presumably used for meetings. \nThe lack of windows and natural lighting is replaced by warmly-tinted lightbulbs and a tasteful chandelier.");
			}
		}else if(keyword.toLowerCase().equals("door 2") || keyword.toLowerCase().equals("2") || keyword.toLowerCase().equals("room 2")) {
			System.out.println("On the right side of a gated and closed door is a sign with the letter 2 on it and below the words 'SECURITY'. The gate is a thick metal and would require intense heat to cut open."
					+ " This is the security office.");
			if(!listOfRooms[2].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the room are lockers that were once full of weapons and protective equipment now entirely empty."
						+ " It seems like anyone who was inside left in a hurry.");
			}
		}else if(keyword.toLowerCase().equals("door 3") || keyword.toLowerCase().equals("3") || keyword.toLowerCase().equals("room 3")) {
			System.out.println("You look over at the set of clear doors signifying a decontamination chamber. \nTo the right of the entrance is a sign with the number 3 and below states "
					+ "RESEARCH AND DEVELOPMENT - RESTRICTED ACCESS. This is the research office.");

			if(!listOfRooms[3].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Through the layers of glass, medical instruments, desks, and chairs can be seen.");
			}
		}else if(keyword.toLowerCase().equals("door 4") || keyword.toLowerCase().equals("4") || keyword.toLowerCase().equals("room 4")) {
			System.out.println("You look at an unassuming wooden door. To the right is a sign with the number 4 and the words 'MAINTENANCE'. This is the maintenance room.");

			if(!listOfRooms[4].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the room is a cabinet of unsorted tools and a breaker box.");
			}
		}else if(keyword.toLowerCase().equals("door 5") || keyword.toLowerCase().equals("5") || keyword.toLowerCase().equals("room 5")) {
			System.out.println("Looking over at a set of opaque sliding doors you see a sign with the number 5 and the words 'TRAUMA CENTER.' This is the medical office.");

			if(!listOfRooms[5].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the room is a normal looking doctor's office. It seems as if a struggle occurred.");
			}
		}else if(keyword.toLowerCase().equals("door 6") || keyword.toLowerCase().equals("6") || keyword.toLowerCase().equals("room 6")) {
			System.out.println("You look over at an unassuming wooden door with a sign to its right with the number 6 and below reading 'COMMUNICATIONS'. This is the communications office.");
			if(!listOfRooms[6].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the room is a basic ham radio set and an open cabinet with random electronics strewn about. ");
			}
		}else if(keyword.toLowerCase().equals("door 7") || keyword.toLowerCase().equals("7") || keyword.toLowerCase().equals("room 7")) {
			System.out.println("You look over at a set of large double doors and a sign to the right with the number 7 as well as the words 'MEETING OFFICE.' This is the meeting room.");
			if(!listOfRooms[7].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the meeting room is a long desk with roughly a dozen chairs and a board full of notes and other seemingly random writing.");
			}
		}else if(keyword.toLowerCase().equals("door 8") || keyword.toLowerCase().equals("8") || keyword.toLowerCase().equals("room 8")) {
			System.out.println("You look over at an ominious metal door with a small slit and a sign marked with the number 8 and writing which says 'REACTOR ROOM'. This is the reactor.");
			if(!listOfRooms[8].isDoorLocked() && Game.areLightsOn()) {
				System.out.println("Inside the room is a large metal reactor.\n"
						+ "A conveniently placed toolbox presumably for maintenance workers is bolted on the wall to the side.");
			}
		}else if(keyword.toLowerCase().equals("door")) {
			System.out.println("Which door?");
			keyword = reader.nextLine();
		}else {
			Game.getNegativeResponse();
		}
	}
	
	//changes the value of the player's location
	public void moveTo(Room room) {
		if(!room.isDoorLocked()) {
			System.out.println("You are now in the " + room.getAltName());
			location = room;
		}else {
			System.out.println("You stand in front of " + room.getDoor().getDoorName());
			location = game.findRoom("hallway");
			if(room.hasEntity()) {
				System.out.println("Faint, raspy breathing can be heard on the other side of the door. You should be the only person in the facility");
			}
		}
	}
	
	public void moveToDoor(Door door) {
		Room room = door.getRoom();
		System.out.println("You stand in front of " + door.getDoorName());
		if(room.hasEntity()) {
			System.out.println("You hear a faint breathing on the other side of the door. There should be nobody in this facility but you");
		}
	}
	
	public boolean hasItem(Item item) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasItem(String itemName) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
}//class player