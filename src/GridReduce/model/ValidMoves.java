package GridReduce.model;

/** Class to represent a set of directions. 
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class ValidMoves {
	boolean up;
	boolean down;
	boolean left;
	boolean right;

	/** Class constructor specifying all values.
	 * @param up	True if the up move is valid.
	 * @param down	True if the down move is valid.
	 * @param left	True if the left move is valid.
	 * @param right	True if the right move is valid.
	 */
	public ValidMoves(boolean up, boolean down, boolean left, boolean right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	public boolean getUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean getDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean getLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean getRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
}
