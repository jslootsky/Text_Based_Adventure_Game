# Text-Based Adventure Game

## Overview
Welcome to the **Text-Based Adventure Game**! This game is a narrative-driven experience where players explore a mysterious facility, interact with objects, and uncover the story through command-based gameplay.

## Features
- 🏛️ **Exploration**: Move between different rooms, each with unique descriptions and items.
- 🔑 **Inventory System**: Pick up and use items to unlock new areas and progress the story.
- 🎭 **Interactive Storytelling**: Experience a horror/sci-fi narrative that reacts to your choices.
- 🏆 **Game Logic**: Includes puzzles, keycard access, and environmental storytelling.

## Installation
### **Clone the Repository**
```sh
git clone https://github.com/jslootsky/Text_Based_Adventure_Game.git
cd Text_Based_Adventure_Game
```

### **Compile the Game**
Make sure you have Java installed. Then, compile the game using:
```sh
javac -d bin src/game/*.java
```

### **Run the Game**
```sh
java -cp bin game.GameMain
```

## Gameplay Commands
- `look` - Describe your surroundings.
- `look at [object]` - Get details about a specific object.
- `move to [room]` - Travel to a different location.
- `take [item]` - Pick up an item.
- `use [item]` - Use an item in your inventory.
- `inventory` - Check your collected items.
- `help` - Display available commands.

## File Structure
```
Text_Based_Adventure_Game/
│── src/
│   ├── game/
│   │   ├── GameMain.java  # Main game entry point
│   │   ├── Player.java    # Player class and inventory handling
│   │   ├── Room.java      # Room and environment logic
│   │   ├── Door.java      # Door mechanics and interactions
│   │   ├── Game.java      # Core game logic
│   │   ├── Item.java      # Item class for in-game objects
│── bin/                  # Compiled .class files
│── README.md             # Project documentation
```

## Contributing
Feel free to fork the project, submit pull requests, or suggest features via the **Issues** tab.

## License
This project is open-source under the **MIT License**.

---

Enjoy the adventure, and good luck uncovering the facility's secrets! 🎮🔥