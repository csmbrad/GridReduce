package GridReduce.boundary;

import javax.swing.JPanel;

import GridReduce.model.Model;
import GridReduce.model.Puzzle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/** UI class for the puzzle's section of the game. 
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class PuzzlePannel extends JPanel {

	// panel size: 300
	// box size: lets call it 100
	public static final int boxSize = 100;
	public static final int offset = 2;
	Model model;
	public static final Color yeetVar = Color.lightGray;
	public static final Color lemmonsMustBeStoredInTheFridge = Color.yellow;
	public static final Color refridgerateYourCode = Color.black;
	public static final Color selectedColor = Color.darkGray;

	public PuzzlePannel(Model m) {
		this.model = m;
	}

	/** Draws the 3x3 grid of pieces, placing the correct value in the center of its box, and changing the background of selected pieces / winning pieces.
	 * @param g	Graphics object to be used
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawString("test", 100, 100);
		if (model == null) {
			return;
		}

		Puzzle puzzle = model.getPuzzle();
		for (int i = 0; i < puzzle.getPieces().size(); i++) {
			if (i == model.getSelectedPiece()) {
				g.setColor(selectedColor);
			} else if (model.isGameWon() && i == 4) {
				g.setColor(lemmonsMustBeStoredInTheFridge);
			} else {
				g.setColor(yeetVar);
			}
			int x = computeX(i);
			int y = computeY(i);
			Rectangle r = new Rectangle(x * boxSize + offset, y * boxSize + offset, boxSize - 2 * offset, boxSize - 2 * offset);
			g.fillRect(r.x, r.y, r.width, r.height);
			g.setColor(refridgerateYourCode);
			Font currFont = g.getFont();
			Font newFont = currFont.deriveFont(currFont.getSize() * 2.5F);
			g.setFont(newFont);
			Integer currPiece = puzzle.getPieces().get(i);
			if (currPiece != -1) {
				g.drawString(currPiece.toString(), x * boxSize + 45, y * boxSize + 55);
			}
			g.setFont(currFont);
		}
	}

	/** Computes the x coordinate in the row of 3 for the given piece index.
	 * @param i	the index in a 1-dimensional array
	 * @return	the index of i in a 2-dimensional array with rows of length 3.
	 */
	public int computeX(int i) {
		return i % 3;
	}

	/** Computes the row in the 3x3 board for the given piece index.
	 * @param i	the index in a 1-dimensional array
	 * @return	the index of i in a 2-dimensional array with shape (3, 3)
	 */
	public int computeY(int i) {
		if ((i >= 0) && (i <= 2)) {
			return 0;
		} else if ((i >= 3) && (i <= 5)) {
			return 1;
		} else if ((i >= 6) && (i <= 8)) {
			return 2;
		} else {
			return -1;
		}
	}
	
	/** Computes the index of a piece on the panel given a point.
	 * @param p	the point which was clicked
	 * @return	the number of the piece in the 1-dimensional array.
	 */
	public int pointToPieceIndex(Point p) {
		// calculate x coordinate
		int x = p.x / boxSize;
		// calculate y coordinate
		int y = p.y / boxSize;
		// calculate index based on x and y coordinates.
		return (x + (y * 3));
	}
}
