package game;

import java.util.ArrayList;

class Room {
	private String roomName;
	private boolean doorLocked;
	private ArrayList<Item> inventory;
	private boolean hasEntity;
	//the list of items that are in the room and can be found
	
	private String altName;
	private Door door;
	
	//constructor, instantiates a list of items in the array, and copies every element from tempInventory to the arraylist inventory
	public Room(String roomName) {
		this.roomName = roomName; 
	}
	
	public Room(String roomName, Item[] tempInventory, String alternateRoomName) {
		this.altName = alternateRoomName;
		this.roomName = roomName;
		
		inventory = new ArrayList<Item>();
		
		for(int i = 0; i < tempInventory.length; i++) {
			inventory.add(tempInventory[i]);
		}
	}
	
	//returns if the door is locked or not. If true, door is locked. If false, door is unlocked
	public boolean isDoorLocked() {
		return doorLocked;
	}
	
	//unlocks the door and opens it (for player function)
	public void openDoor() {
		doorLocked = false;
	}
	
	//locks the door and closes it (for player function)
	public void lockDoor() {
		doorLocked = true;
	}
	
	//returns everything in the room - FOR DEBUGGING
	public void getInventory() {
		for(int i = 0; i < inventory.size(); i++) {
			System.out.println((i + 1) + ". " + inventory.get(i).getName());
		}	
	}
	
	//takes an Item from the room's inventory
	//assumes that no two items have the same name
	public void takeItem(String itemName) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(itemName)) {
				inventory.remove(i);
			}
		}
	}
	
	//only activates if the entity is inside the room
	public void entityWarning() {
		if(hasEntity()) {
			int randNum = (int)(Math.random() * 4);
			if(randNum == 0) {
				System.out.println("You hear raspy breathing on the other side. A sense of dread washes over you");
			}
		}
	}
	
	//returns the name of the room
	public String getName() {
		return roomName;
	}
	
	//returns if the entity is in the room
	public boolean hasEntity() {
		return hasEntity;
	}
	
	//changes the entity status of the room. Entity is present if true and not present if false
	public void setHasEntity(boolean hasEntity) {
		this.hasEntity = hasEntity;
	}
	
	//changes the room to locked or unlocked. If true, the door is considered locked and unlocked if false (for debugging/pre-game)
	public void setLock(boolean lock) {
		this.doorLocked = lock;
		this.door.setLock(lock);
	}

	//returns the alternative name for the room
	public String getAltName() {
		return altName;
	}
	
	//sets the associated door with the appropriate room
	public void setDoor(Door door) {
		this.door = door;
		door.setRoom(this);
	}
	
	//returns the door associated with the room (room 1 has door 1)
	public Door getDoor() {
		return door;
	}
	
	//determines if a room has a desired item
	public boolean hasItem(String itemName) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	//
	public Item getItem(String itemName) {
		Item result = new Item("null");
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(itemName)) {
				result = inventory.get(i);
			}
		}
		return result;
	}
}

class Item {
	private String itemName;
	private boolean isFound;
	private boolean canTake;
	private boolean isUsed;
	
	
	public Item (String name) {
		itemName = name;
		isFound = false;
		canTake = true;
		isUsed = false;
	}
	
	public Item (String name, boolean canTake) {
		itemName = name;
		isFound = false;
		this.canTake = canTake;
		isUsed = false;
	}
	
	//returns the name of the item.
	public String getName() {
		return itemName;
	}
	
	//returns boolean that is true if the item is found and false if not
	public boolean isFound() {
		return isFound;
	}
	
	//sets the isFound status to true
	public void setFoundToTrue() {
		isFound = true;
	}

	//returns true if the item can be added to the inventory
	public boolean canTake() {
		return canTake;
	}

	//FOR INTRO USE changes the ability of the item to be taken
	public void setCanTake(boolean canTake) {
		this.canTake = canTake;
	}
	
	//returns if an item is used
	public boolean isUsed() {
		return isUsed;
	}
}//room
