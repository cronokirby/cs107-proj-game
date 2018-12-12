I've chosen to written this file in English because of my relative fluency in it compared to French,
as well as the abundance of obtuse terminology and jargon.

## Changes compared to the instructions
- `addActor` and `removeActor`
  Instead of having a rollback based system, where we first add or remove an actor,
  and then undo the change if that action wasn't allowed, instead I chose to only add the actor at the end,
  if this addition is allowed. Adding and removing from a linked list will never fail, so it's a bit absurd
  to program around this.
 - `leaveAreaCells` and `enterAreaCells`
  A problem I concieved as possible and then discovered with other people was that if an entity could enter
  a set of cells, but not leave the cells it was currently in, then it would end up being added via `enterAreaCells`.
  Instead I chose to have a method `transitionAreaCells` checking if the transaction leave + enter could be
  performed, in which case I would actually commit both of these changes at once. This makes the system
  much safer. 
 - Addition of `canEnter`
   `canEnter` became public because a few classes needed to check if they could enter an area in order to decide
   what to do. e.g. not spawning clones in a tile they couldn't occupy, or a mob not trying to move in a direction
   that's blocked
 
 
 ## Additions in Enigme
 The main deviation is the addition of a `Toggleable` interface, in order to encapsulate
 the common interaction of a player interacting with something like a lever or a pressure plate, and switching it
 between states.
 
 `SwitchActor` is used to share the behavior of some entity beign able to be in 2 different states,
 and display differently between these 2 states.
 
 Another complexity introduced is a form of debouncing in `PressureSwitch`, in order to avoid
 continuous state toggling every frame as the player is on top of the switch, the state of the last
 frame is kept and then compared to the current frame, in order to have the behavior of only switching state
 when the player first steps onto the switch
 
 
 ## OctoGame
 I decided to split out the new game from the rest of the project for clarity, and because less tile types
 were needed.
 
 The reader has likely noticed by this point that there are quite a few amount of classes in the game,
 and I feel quite sorry for whoever it is that has the pleasure of reading through all of them.
 
 I won't bore the reader by describing the exact functionality of each class, as playing the game, along
 with reading the code and documentation should provide a sufficient job at that.
 Instead this section should serve as an overview of some common patterns throughout the code, as well as a bit
 of insight into the overall architecture of the section.
 
 ### Split screens
 Instead of having a single area game as the entry point of the game, instead I have 3 seperate games the player
 transitions between:
 - The title screen, which lets the player start a new game, and see the scoreboard
 - The main game
 - The end screen which displays the time it took the player to finish the game

The transition direction is fixed, but the game decides when to transition from one game to the next via
an `isFinished` method, which the underlying game can set at its whim.

A scoreboard (we'll get to this class soon enough) is created and passed to both the main game and the title screen.
The title screen needs access to the scoreboard in order to display the scores, and the main game knows when it's finished,
and how it took (via a Timer) and can thus add this score to the scoreboard.

The scoreboard is a relatively simple class, supporting loading the scores from a file, and saving them to a file.
The main way the game interacts with the board once created is by adding a new score to it when the player finishes
the game. At the end of the game (OctoSwitcher) the scoreboard is saved.

### Hud Elements
A commonality between the elements of the hud is that they all want to be displayed anchored relative to an entity,
the player. This anchoring behavior is identical between the different elements composing the heads up display,
so an abstract class is given as a parent for them.

`Timer`, `OrbHolder`, and `Inventory` both inherit from this.

`Timer` is the simpler of these, so let's start there.
A timer us updated every frame with the delta time, and uses that delta to keep track of the time elapsed since the
beginning of the game. It knows better than any other class how to display these elapsed seconds as a nicely
formatted String, so a static `formatTime` method is provided for other methods to use, e.g. `ScoreBoard`
Although Timers display themselves, a `display` method providing the current time as a String was given
in order to have the state of the timer be displayed. This is used in the end screen, in order to display
the final time of completion

### Orb Holder

The orb holder displays the progress of the player, i.e. which levels they've completed. To this effect,
it keeps track of the `Orb`s (we'll get to this class soon) the player has collected, and which ones it hasn't.


### The Inventory
The inventory is used in one level :) The main point of interaction between it and the player is `addWeight`,
which lets the player add a weight it has collected. The player keeps track of user input, and
so also calls `incrementCursor` in order to move the cursor through the items of the inventory.

The inventory is anchored relative to the player, like `OrbHolder` and `Timer`.

It implements `Logic`, in order to signal when all the orbs have been collected, which is an important
checkpoint of the game.

### Joining the HUD together
The HUD is characterised by these 3 classes and automatically displays all of them relative to an anchor
point (the player) that can be set from the game.

## Actors
There are a lot of actors. Apologies again. I'll stick to describing the hierarchies between them.

- Portal
  This class unites the behavior of 3 somewhat distant classes: `Orb`, `Door`, and `ResetSwitch`.
  All of these can move the player to a different area and a different position when the player interacts with them:
  when an Orb is collected, it sends the player back to the level select, when a Door is entered, it moves
  the player to a different room, and when a ResetSwitch is activated twice, it resets the room (by sending the player
  back to its start).
  
  The interface provided consists of two methods: `getDestinationArea` which returns the title of the area
  the portal leads to, and `getDestinationPosition` which returns the position the player is warped to.

- Toggleable
  This is similar to one featured in Enigme, it allows us to join together the behavior
  of lights, switches et alii

- Talkable
  This is an interface for everything that can provide text, providing a `talk` method returning
  the text the entity uses
  
 - WanderingEntity
  A lot of mobs with different behavior share the same behavior of walking around randomly in a room:
  to this effect, an abstract class `WanderingEntity` was created, in order to share this logic.
  
 - Door
   There are a few different kinds of doors, but they just vary on the abstract concept of Door.
  
  - Wire
   The logic for circuits is somewhat involved, since I wanted to implement it to behave like actual electrical
   circuits: i.e. any break in the circuit causes current to dissipate. To this effect,
   wire contains the logic for electrical propagation, and is an interactor, so it can interact with other
   wires charging and uncharging them.
   
  ## Areas
  
  A common pattern is having an area consisting of many sub rooms, each of which should be reset whenever the player
  leaves and enters. `SubRoom` captures this logic.
  
  Each of the areas that contains sub rooms: `Physics`, `Electricity`, et omnes aliorum, implements
  a similar pattern: the class itself is abstract, and its constructor is hidden:
  instead it provides, via a static `subRooms` method, the list of subRooms inheriting from it.
  Each of this is a private static class, containing the logic to addActors into the sub room.
