## GridReduce Game

This is a Java puzzle game written using Swing for UI and abiding by the 
EBC (MVC) design paradigm. The objective of the game is to combine all
pieces in the grid into the central piece using four basic operations:

* Up: Multiplies the selected piece with the piece above it.
* Down: Divides the piece above the selected piece by the selected piece.
* Left: Subtracts the selected piece from the piece to the left of it.
* Right: Adds the selected piece to the one to the right of it.

Moves are only avaliable when they are valid. A move is valid when its 
result is an integer greater than 0. Moves can only be performed on
pieces with a value in them.

The game detects win/loss states. A win occurs when the only piece on
the board is in the middle. A loss occurs when:

* The center tile has no value
* There is only one tile, and it is not in the center
* There is an isolated tile (has no neighbors or valid moves)
* There are no valid moves that can be made, and the game is not won.

The app allows the user to reset the board and asks for confirmation to
quit. The app will not allow a user to atempt an invalid move by disabling
buttons that would lead to one.
