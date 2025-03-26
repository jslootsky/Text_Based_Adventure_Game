package game;

class Door {
	private String doorName;
	private Room room;
	private Item key;
	private boolean isLocked;
	
	public Door (String doorName) {
		this.doorName = doorName;
	}
	
	public Door (String doorName, Item key) {
		this.doorName = doorName;
		this.key = key;
	}
	
	public String getDoorName() {
		return doorName;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Item getKey() {
		return key;
	}
	
	public void setKey(Item key) {
		this.key = key;
	}
	
	public void setLock(Boolean lock) {
		this.isLocked = lock;
	}
	
	public boolean getLock() {
		return isLocked;
	}
	
}
