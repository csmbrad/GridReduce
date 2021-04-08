package GridReduce.model;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;

/** Stores the current state of the puzzle and some basic information about it.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class Puzzle {
	private ArrayList<Integer> pieces = new ArrayList<>();
	private ArrayList<Integer> originalPosition = new ArrayList<>();
	public final int squareSize; // a value of 3 would indicate the square is 3x3, therefore 9 total squares.
	public final int winningIndex; // in a 3x3 puzzle, the middle index would be 4.

	/** Class constructor indicating the size and winning piece of a puzzle.
	 * @param squareSize	The size of one side of the square puzzle.
	 * @param winningIndex	The winning index of the puzzle. 
	 */
	public Puzzle(int squareSize, int winningIndex) {
		this.squareSize = squareSize;
		this.winningIndex = winningIndex;
	}

	/** Clears and resets the puzzle's list of values and original values to the given ArrayList.
	 * @param ar	an ArrayList specifying the new values of the puzzle and original values..
	 */
	public void setPuzzleArrayList(ArrayList<Integer> ar) {
		getPieces().clear();
		getPieces().addAll(ar);
		getOriginalPosition().clear();
		getOriginalPosition().addAll(ar);
	}

	/** Clears the current pieces and original position of the puzzle.
	 */
	public void clearPuzzleArrayList() {
		getPieces().clear();
		getOriginalPosition().clear();
	}

	/** Clears the puzzle's pieces and resets them to the puzzle's existing original position.
	 */
	public void resetPuzzle() {
		getPieces().clear();
		getPieces().addAll(getOriginalPosition());
	}

	public ArrayList<Integer> getPieces() {
		return pieces;
	}

	public ArrayList<Integer> setPieces(ArrayList<Integer> pieces) {
		this.pieces = pieces;
		return this.pieces;
	}

	public ArrayList<Integer> getOriginalPosition() {
		return originalPosition;
	}

	public void setOriginalPosition(ArrayList<Integer> originalPosition) {
		this.originalPosition = originalPosition;
	}

}
