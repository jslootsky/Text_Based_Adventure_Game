package game;

import java.util.Scanner;

public class GameMain {
	public static void main (String[] args) {
		Scanner reader = new Scanner(System.in);
		String userIn = "";
		
		//in-game items
		Item IDCard1 = new Item("ID Card");
		Item IDCard2 = new Item("Upgraded ID Card");
		Item maintenanceKey = new Item("Maintenance Worker Keycard");
		Item blowtorch = new Item("Blowtorch");
		Item rustedKey = new Item("Rusted Key");
		Item research = new Item("Research Documents");
		Item notePaper = new Item("Mysterious Note");
		Item tape = new Item("Electrical Tape");
		Item gloves = new Item("Insulated Gloves");
		Item IDUpgrade = new Item ("Upgrade Terminal", false);
		Item keypad = new Item("Keypad", false);
		Item bible = new Item("Holy Bible", false);
		Item breakerBox = new Item("Breaker Box", false);
		String lookAtCommand = "look at";
		String moveToCommand = "move to";
		
		//rooms
		Item[] hallwayInventory = new Item[0];
		Room hallway = new Room("hallway", hallwayInventory, "hallway");
		
		Item[] room1Inventory = {keypad, bible};
		Door door1 = new Door("door 1", rustedKey);
		Room room1 = new Room("room 1", room1Inventory, "director's office");
		room1.setDoor(door1);
		room1.setLock(true);
		room1.setHasEntity(false);
		
		Item[] room2Inventory = {IDUpgrade};
		Door door2 = new Door("door 2", blowtorch);
		Room room2 = new Room("room 2", room2Inventory, "security office");
		room2.setDoor(door2);
		room2.setLock(true);
		room2.setHasEntity(false);
		
		Item[] room3Inventory = {research};
		Door door3 = new Door("door 3", IDCard2);
		Room room3 = new Room("room 3", room3Inventory, "research office");
		room3.setDoor(door3);
		room3.setLock(true);
		room3.setHasEntity(false);
		
		Item[] room4Inventory = {breakerBox, rustedKey};
		Door door4 = new Door("door 4", maintenanceKey);
		Room room4 = new Room("room 4", room4Inventory, "electrical room");
		room4.setDoor(door4);
		room4.setLock(true);
		room4.setHasEntity(false);
		
		Item[] room5Inventory = {tape};
		Door door5 = new Door("door 5");
		Room room5 = new Room("room 5", room5Inventory, "medical bay");
		room5.setDoor(door5);
		room5.setLock(false);
		room5.setHasEntity(false);
		
		Item[] room6Inventory = {maintenanceKey};
		Door door6 = new Door("door 6");
		Room room6 = new Room("room 6", room6Inventory, "communications room");
		room6.setDoor(door6);
		room6.setLock(false);
		room6.setHasEntity(false);
		
		Item[] room7Inventory = {notePaper};
		Door door7 = new Door("door 7", rustedKey);
		Room room7 = new Room("room 7", room7Inventory, "meeting room");
		room7.setDoor(door7);
		room7.setLock(false);
		room7.setHasEntity(false);
		
		Item[] room8Inventory = {blowtorch, gloves};
		Door door8 = new Door("door 8");
		Room room8 = new Room("room 8", room8Inventory, "reactor room");
		room8.setDoor(door8);
		room8.setLock(false);
		room8.setHasEntity(false);
		
		//door/room array for game
		Door[] allDoors = {door1, door2, door3, door4, door5, door6, door7, door8};
		Room[] allRooms = {hallway, room1, room2, room3, room4, room5, room6, room7, room8};
		Game game = new Game(allRooms, allDoors);
		
		//game intro
		Game.intro();
		String name = "";
		name = reader.nextLine();
		Player player = new Player(name, allRooms, game);
		player.add(IDCard1);
		
		System.out.println("Your surroundings make it difficult to see clearly. Are you sure this is your name? (y/n)");
		player.changeName();
		player.moveTo(hallway);
		
		//regular game
		userIn = "";
		String keyword = "";
		System.out.println("You can type 'help' at any time to see a list of possible commands.");
		while(!userIn.equals("quit") && !userIn.equals("exit") && !Game.gameEnd()) {
			System.out.println("");
			userIn = reader.nextLine();
			if(userIn.equals("look")) {
				player.look();
			}else if(userIn.length() >= lookAtCommand.length() && userIn.substring(0, lookAtCommand.length()).equals("look at")) {
				//player.moveTo(hallway);
				if(!Game.areLightsOn()) {
					System.out.println("The dim red of the emergency lights make it nearly impossible to see inside, barely allowing enough lights to see the doors.");
				}
				keyword = Game.findKeyword(userIn, "look at");
				Room target = game.findRoom(keyword);
				Door targetDoor = game.findDoor(keyword);
				if(target.isDoorLocked() || targetDoor.getLock()) {
					System.out.println("The door to the room is locked");
				}
				player.lookAt(keyword);
			}else if(userIn.length() >= moveToCommand.length() && userIn.substring(0, moveToCommand.length()).equals("move to")){
				keyword = Game.findKeyword(userIn, "move to");
				if(keyword.indexOf("door") >= 0) {
					Door targetDoor = game.findDoor(keyword);
					player.moveToDoor(targetDoor);
				}else {
					Room targetRoom = game.findRoom(keyword);
					player.moveTo(targetRoom);
				}
			}else if(userIn.length() >= "open inventory".length() && userIn.equals("open inventory") || userIn.equals("inventory")) {
				player.checkInventory();
			}else if(userIn.length() >= "take".length() && userIn.substring(0, "take".length()).equals("take")) {
				keyword = userIn.substring("take".length() + 1);
				player.take(keyword);
			}else if(userIn.length() >= "use".length() && userIn.substring(0, "use".length()).equals("use")) {
				keyword = Game.findKeyword(userIn, "use");
				if(userIn.indexOf("door") >= 0) {
					Door targetDoor = game.findDoor(keyword);
					if(player.hasItem(targetDoor.getKey()) && targetDoor.getLock()) {
						System.out.println("You use " + targetDoor.getKey().getName() + ". " + targetDoor.getDoorName() + " is now open.");
						targetDoor.getRoom().openDoor();
					}else {
						if(targetDoor.getLock()) {
							System.out.println("The door won't seem to budge.");
						}else {
							System.out.println("The door is already open.");
						}
							
					}
				}else if(userIn.indexOf("Upgrade Terminal") >= 0) {
					if(Game.areLightsOn()) {
						System.out.println("As you insert your ID Card, the machine whirs to life. After some sputtering and shaking, a tray drops out with a new ID Card\n"
								+ "The card reads: Dr. " + player.getName() + ", Research Department. ALL ACCESS");
						player.add(IDCard2);
						System.out.println("You take the Upgraded ID Card");
						player.getRoom().takeItem("Upgraded ID Card");
					}else {
						Game.getNegativeResponse();
					}
				}else {
					keyword = Game.findKeyword(userIn, "use");
					player.use(keyword);
				}
			}else if(userIn.length() >= "help".length() && userIn.equals("help")) {
				game.getHelp();
			}
			else {
				Game.getNegativeResponse();
			}
			
		}
		reader.close();
	}
}

