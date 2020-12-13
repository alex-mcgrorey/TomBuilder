# TomBuilder v1.0

## Overview
TomBuilder is a lightweight Minecraft 1.16.4 Spigot Mod that allows players to build box shapes (walls, platforms, cubes, etc.) using materials from their inventory.
It is intended to reduce the monotony of building large structures while maintaining the Survival challenge of limited resources.

## Usage
### In-game
Right-click on a block while wielding a stick, then right-click again on the block in the opposite corner of the intended box.
If you have the required materials in your inventory, they will be removed and used in the construction.  

Note: players can only build through dirt and air, so use dirt blocks for any disposable marks within your construction zone.  Any blocks overwitten will NOT be returned!

### Installation
1. Run `mvn clean package` to build a .jar in the `./target` directory
2. Place the resulting .jar (`TomBuilder-<version>-RELEASE.jar`) in your Spigot Server's `./plugins` folder
3. Start your server normally
4. Get Building!
