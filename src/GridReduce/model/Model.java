package GridReduce.model;

import java.util.ArrayList;
import java.util.Arrays;

/** Stores information about the game and its current puzzle.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class Model {
	Puzzle puzzle;
	boolean won;
	boolean lost;
	Integer selectedPieceIndex;

	/** Class constructor to use default parameters for the start of a game. Sets the size of the puzzle, and the default board configuration.
	 */
	public Model() {
		puzzle = new Puzzle(9, 4);
		ArrayList<Integer> defaultBoard = new ArrayList<>(Arrays.asList(7, 2, 8, 1, 4, 9, 6, 3, 5)); // (7, 2, 8, 1, 4, 9, 6, 3, 5)); 7, -1, 8, -1, 4, -1, 6, -1, 5
		puzzle.setPuzzleArrayList(defaultBoard);
		setPuzzle(puzzle);
	}

	/** Checks to see which moves are valid based on the currently selected piece/
	 * @return	a ValidMoves object indicating which moves are avaliable from the current position.
	 */
	public ValidMoves avaliableMoves() {
		ValidMoves moves = new ValidMoves(false, false, false, false);
		if (selectedPieceIndex == -1) {
			return moves;
		}
		int currPiece = puzzle.getPieces().get(selectedPieceIndex);
		// check left availability
		boolean isntLeftmostColumn = (selectedPieceIndex % 3 != 0);
		if (isntLeftmostColumn) {
			boolean isLeftPositionNotEmpty = (puzzle.getPieces().get(selectedPieceIndex - 1) != -1);
			// isLeftValidMove, checks if subtracting the selected piece from the
			// piece to the left would result in a negative number
			// (leftOfSelected - Selected > 0 and is whole)
			boolean isLeftValidMove = ((puzzle.getPieces().get(selectedPieceIndex - 1) - currPiece) > 0);
			if (isLeftPositionNotEmpty && isLeftValidMove) {
				moves.setLeft(true);
			}
		}
		// check right availability
		boolean isntRightmostColumn = (selectedPieceIndex % 3 != 2);
		if (isntRightmostColumn) {
			boolean isRightPositionNotEmpty = (puzzle.getPieces().get(selectedPieceIndex + 1) != -1);
			// isRightValidMove, checks if adding the selected piece to the piece on
			// the right would be a valid number.
			// (rightOfSelected + selected > 0 and is whole)
			boolean isRightValidMove = ((puzzle.getPieces().get(selectedPieceIndex + 1) + currPiece) > 0);
			if (isRightPositionNotEmpty && isRightValidMove) {
				moves.setRight(true);
			}
		}
		// check up availability
		boolean isntTopRow = (selectedPieceIndex > 2);
		if (isntTopRow) {
			boolean isAbovePositionNotEmpty = (puzzle.getPieces().get(selectedPieceIndex - 3) != -1);
			// isAboveValidMove, checks to see if multiplying the piece above by the
			// selected piece would be a valid move.
			// (aboveSelected * selected > 0 and whole.)
			boolean isAboveValidMove = ((puzzle.getPieces().get(selectedPieceIndex - 3) * currPiece) > 0);
			if (isAbovePositionNotEmpty && isAboveValidMove) {
				moves.setUp(true);
			}
		}
		// check down availability
		boolean isntBottomRow = (selectedPieceIndex < 6);
		if (isntBottomRow) {
			boolean isBelowPositionNotEmpty = (puzzle.getPieces().get(selectedPieceIndex + 3) != -1);
			// isBelowValidMove, checks to see if dividing the number below by the
			// selected number would be a valid move.
			// (belowSelected / selected > 0 and whole.)
			boolean isBelowDivisionPositive = ((puzzle.getPieces().get(selectedPieceIndex + 3) / currPiece) > 0);
			boolean isBelowDivisionWhole = ((puzzle.getPieces().get(selectedPieceIndex + 3) % currPiece) == 0);
			boolean isBelowValidMove = (isBelowDivisionPositive && isBelowDivisionWhole);
			if (isBelowPositionNotEmpty && isBelowValidMove) {
				moves.setDown(true);
			}
		}
		return moves;
	}

	/** Checks if the game is in a win or loss state and sets the game over flag.
	 */
	public void checkGameOver() {
		boolean loss = this.checkLoss();
		boolean win = this.checkWin();
		if (win) {
			this.setGameOver(true);
		}
		if (loss) {
			this.setGameOver(false);
		}
	}

	/** Checks to see if the game is in a winning state, meaning the only existing piece is in the center and is valid.
	 * @return	A boolean, true if the game is won.
	 */
	public boolean checkWin() {
		boolean negOneOnly = true;
		for (int i = 0; i < 9; i++) {
			if (!(i == 4)) {
				if (puzzle.getPieces().get(i) != -1) {
					negOneOnly = false;
				}
			}
		}
		if (puzzle.getPieces().get(4) != -1) {
			return negOneOnly;
		} else
			return false;
	}

	/** Checks if the game is in a loss state. The game is lost if there is any tile with no valid neighbors (other than the central tile), or if there are no valid moves to be made.
	 * @return	A boolean, true if the game is lost. 
	 */
	public boolean checkLoss() {
		// you have lost if: there is any tile without valid neighbors that isn't in the
		// center, or, if there are only 2 tiles left, if there is no valid move you can
		// make between them.
		// first: count tiles.
		int count = 0;
		ArrayList<Integer> validTiles = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			if (puzzle.getPieces().get(i) != -1) {
				count++;
				validTiles.add(i);
			}
		}

		// if there is only 1 tile, check to see if its in the center.
		if (count == 1) {
			if (validTiles.get(0) == 4) {
				return false;
			} else
				return true;
		}
		
		// if there are 2 tiles, make sure they're next to each other. if not, loss
		// if they are but there is no valid move between them, loss.
		if (count >= 2) {
			// if the center is -1, we have lost
			if (puzzle.getPieces().get(4) == -1) {
				return true;
			}
			// check if there is a tile with no neighbors
			for (int i = 0; i < validTiles.size(); i++) {
				int index = validTiles.get(i);
				boolean hasAnyNeighbor = false;
				if (!(index % 3 == 0)) {
					// we are not in the leftmost row, check to see if there is a neighbor to the
					// left.
					if (validTiles.contains(index - 1)) {
						hasAnyNeighbor = true;
					}
				}
				if (!(index % 3 == 2)) {
					// we are not in the rightmost row, check to see if there is a neighbor to the
					// right.
					if (validTiles.contains(index + 1)) {
						hasAnyNeighbor = true;
					}
				}
				// check if there is a valid tile above
				if (validTiles.contains(index - 3)) {
					hasAnyNeighbor = true;
				}
				// check to see if there is a valid tile below
				if (validTiles.contains(index + 3)) {
					hasAnyNeighbor = true;
				}
				if (!hasAnyNeighbor) {
					return true;
				} else {
					continue;
				}
			}
			// now that we know there aren't any isolated tiles, we have to know if there
			// are any valid moves we can make at all.
			// idea: check each valid tile, check to see if it has any valid neighbors.
			// if after checking all directions it has a valid neighbor, add true to a list,
			// otherwise add false
			// after the loop, if there aren't any trues in the list there are no valid
			// moves to make, loss.
			ArrayList<Boolean> validityOfMoves = new ArrayList<>();
			for (int i = 0; i < validTiles.size(); i++) {
				int index = validTiles.get(i);
				if(index == 4) {
					continue;
				}
				boolean hasValidMove = false;
				if (!(index % 3 == 0)) {
					// we are not in the leftmost row, check to see if there is a neighbor to the
					// left.
					if (validTiles.contains(index - 1)) {
						// try the subtraction, if its positive we have a move
						if ((puzzle.getPieces().get(index - 1)) - (puzzle.getPieces().get(index)) >= 0) {
							hasValidMove = true;
						}
					}
				}
				if (!(index % 3 == 2)) {
					// we are not in the rightmost row, check to see if there is a neighbor to the
					// right.
					if (validTiles.contains(index + 1)) {
						// no need to try anything, addition is always valid if the right neighbor
						// exists.
						hasValidMove = true;
					}
				}
				// check if there is a valid tile above
				if (validTiles.contains(index - 3)) {
					// no need to try anything, multiplication is always valid if the upwards
					// neighbor exists.
					hasValidMove = true;
				}
				// check to see if there is a valid tile below
				if (validTiles.contains(index + 3)) {
					// check to see if the division would a) be positive b) be whole
					if ((((puzzle.getPieces().get(index + 3)) / (puzzle.getPieces().get(index))) > 0)
							&& (((puzzle.getPieces().get(index + 3)) % (puzzle.getPieces().get(index))) == 0)) {
						hasValidMove = true;
					}
				}
				validityOfMoves.add(hasValidMove);
			}
			// if validity of moves contains any true, we have not lost the game
			for (int i = 0; i < validityOfMoves.size(); i++) {
				if (validityOfMoves.get(i) == true) {
					return false;
				}
			}
			return true;
		}
		return true;
	}

	/** Prepares the game for its initial state. Sets the puzzle as provided, and default starting configuration.
	 * @param p	The puzzle to be used.
	 */
	public void setPuzzle(Puzzle p) {
		puzzle = p;
		won = false;
		lost = false;
		selectedPieceIndex = -1;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setSelectedPiece(Integer i) {
		selectedPieceIndex = i;
	}

	public void clearSelectedPiece() {
		selectedPieceIndex = -1;
	}

	public Integer getSelectedPiece() {
		return selectedPieceIndex;
	}

	public boolean isGameWon() {
		return won;
	}

	public boolean isGameLost() {
		return lost;
	}

	public void setGameWon(boolean bool) {
		won = bool;
	}

	public void setGameLost(boolean bool) {
		lost = bool;
	}

	/** Sets both gameWon and GameLost based on the given boolean.
	 * @param gameWon	A boolean, true for game won, false for game lost.
	 */
	public void setGameOver(boolean gameWon) {
		if (gameWon) {
			this.setGameWon(true);
			this.setGameLost(false);
		} else {
			this.setGameWon(false);
			this.setGameLost(true);
		}
	}
}
