# OctoMAN
This is a simple puzzle game in the vein of Pokemon,
Zelda, et alii. The sprites were given to us, but the tiles
come from yours truly :)

## Building / Running
If you're a corrector reading this, you likely haven't
been provided with a jar archive, so the easiest way
to run the game would be to compile the project (via IDE
or terminal) and run the `Play` class, which starts the game.

## Controls

### On the grid
| Input       | Effect          |
| ------------- |-------------|
| WASD / Arrows  | Move around |
| J              | Cycle to the next item in the inveotory|
| K              | Sprint |
| L              | Interact with whatever is in front of you |

### On the title screen
| Input | Effect |
| -------- | ---- |
| W/S/Up/Down | Change selected prompt |
| L           | Confirm selection |
### End screen
| Input | Effect |
| ----- | ------ |
| L     | Go back to title screen |


## Mechanics

### 0 General
The sprites in the character select room let you change
your sprite by interacting with them.

The animals walking around can be talked to.

The orb at the end of each level will warp you back to the level select.

### 1 Physics
Boulders can be pushed around, if nothing is blocking them.

### 2 Environmental Studies
Birds move around and block your path, all you need to do is walk to the end of
the level. (You can talk to the birds, but they don't have much to say)

### 3 Mathematics
You can collect weights in your inventory by interacting
with a pedestal containing a weight. Interacting with
an empty pedestal will place one of the current weight types
selected on it. The scales will initially be gray, indicating
that no weight is on them. When red, they indicate which side
of the scale is heavier, and when green, the scale is balanced.

### 4 Life Sciences
You can interact with the potions here to make a clone of yourself.
Clones will mimic your movement, and can interact with objects the
same as you can. Clones cannot, however, trigger door transitions,
you need to go through those yourself. A potion will not be consumed
if the clone wouldn't have been able to spawn, usually because
something was on the tile behind the player.

### 5 Electrical Engineering
You can collect torches to increase the radius of vision
in the dark.

### 6 Chemistry
Many tiles are covered in acid, and are slippery (the green tiles),
moving onto one of the tiles in a certain direction will keep you
moving in that direction.

### 7 Microtechnics
Certain portions of the circuit can be flipped between 2 orientations.
The circuits act similarly to electrical circuit, as soon as any
break forms, no more current is carried.

### 8 Computer Science
Each door is unlocked after stepping on every button on the floor.
Stepping on a button twice resets the room. If you're savvy, you'll
recognise this as a variation of the "Hamiltonian Circuit problem",
which is NP-Complete 8^)
