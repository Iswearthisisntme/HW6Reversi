Text Based Game of Reversi

# Overview
Reversi runs on a Reversi Model which contains all the player actions and observations to play the game.
The Reversi Model does this by manipulating a Hexagonal Board
which is composed of Hexagonal Cells (aka Hexagons) located at specific Coordinates.
Hexagons are either empty or contain a Player Color which corresponds to each player (Black and White).

# Quick Start
```
  _ _ _ 
 _ X O _ 
_ O _ X _ 
 _ X O _ 
  _ _ _ 
```
Players take turns (Black goes first) making moves via the makePlay method,
which takes in two integer parameters as q and r coordinates of an axial coordinate system.
An example of such a legal move at the start of the game (pic above) could be makePlay(1, 1),
which changes the Hexagon's Color at Coordinate (1, 1) to be Black
and changes the Color of the Hexagon at (0, 1) to also be Black according to the rules of reversi. (pic below)
```
  _ _ _ 
 _ X O _ 
_ O _ X _ 
 _ X X X 
  _ _ _ 
```
The origin (0,0) of the board is located at the central hexagon equidistant from all 6 corners.
Player can also pass turns, but if two passes occur in a row the game ends.

# Key Components
The Reversi Model is the main driving component of the game,
as it's the main component which handles the moves player make
and translating them into changes to the HexagonalBoard.
Subcomponents of the Board include Hexagons and Coordinates,
with Coordinates being used to keep track of positional relationships between Hexagons
for actions like changing a Hexagon or a collection of Hexagons' Colors
Colors are analogous to the pieces of the game,
with White and Black being used as pieces for the players
and Empty being the initial state of a Hexagon being unoccuppied by a Color

The Textual View displays the state of the game board as a 2D String and,
when a valid move is made, updates and print new boards seperated by line breaks.

# Source Organization
src folder contain model and view folders,
model folder contain all model related classes and interfaces
(ReversiModel, Reversi, HexagonalBoardInterface, HexagonalBoard, Hexagon, Coordinate, and Color),
and view folder contains ReversiTextualView class to view the game