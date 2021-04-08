package GridReduce.controller;

import java.util.ArrayList;

import GridReduce.boundary.GridReduceApp;
import GridReduce.boundary.UpdateButtons;
import GridReduce.model.Model;
import GridReduce.model.ValidMoves;

/** Controller to perform a move on the Model.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class MovePieceController {

	Model model;
	GridReduceApp app;

	public MovePieceController(Model m, GridReduceApp a) {
		this.model = m;
		this.app = a;
	}

	/** Given a ValidMoves object with only one move true, which indicates the direction of the desired move, makes sure the move will
	 * be valid, and if so, makes the move in that direction.
	 * @param move	a ValidMoves object with only one direction set to true, 
	 */
	public void move(ValidMoves move) {
		if (model.getSelectedPiece() == -1) {
			return;
		}
		if(model.isGameWon() || model.isGameLost()) {
			return;
		}
		ArrayList<Integer> pieces = model.getPuzzle().getPieces();
		// try to make the move
		// reset the selected piece if succesfull, repaint no matter what.
		if (move.getUp()) {
			int result = pieces.get(model.getSelectedPiece()) * pieces.get(model.getSelectedPiece() - 3);
			if ((result % 1 == 0) && (result > 0)) {
				pieces.set(model.getSelectedPiece(), -1);
				pieces.set(model.getSelectedPiece() - 3, result);
				cleanup();
			}
		} else if (move.getDown()) {
			int result = pieces.get(model.getSelectedPiece() + 3) / pieces.get(model.getSelectedPiece());
			if ((result % 1 == 0) && (result > 0)) {
				pieces.set(model.getSelectedPiece(), -1);
				pieces.set(model.getSelectedPiece() + 3, result);
				cleanup();
			}
		} else if (move.getLeft()) {
			int result = pieces.get(model.getSelectedPiece() - 1) - pieces.get(model.getSelectedPiece());
			if ((result % 1 == 0) && (result > 0)) {
				pieces.set(model.getSelectedPiece(), -1);
				pieces.set(model.getSelectedPiece() - 1, result);
				cleanup();
			}
		} else if (move.getRight()) {
			int result = pieces.get(model.getSelectedPiece() + 1) + pieces.get(model.getSelectedPiece());
			if ((result % 1 == 0) && (result > 0)) {
				pieces.set(model.getSelectedPiece(), -1);
				pieces.set(model.getSelectedPiece() + 1, result);
				cleanup();
			}
		}
	}
	
	/** Function to reset everything after making a move. Resets buttons, clears the selected piece, and handles game-overs. 
	 */
	public void cleanup() {
		UpdateButtons.enableButtons(app, new ValidMoves(false, false, false, false));
		model.clearSelectedPiece();
		model.checkGameOver();
		if(model.isGameLost()) {
			app.getGameStatusLabel().setText("Game is Lost");
		}
		if(model.isGameWon()) {
			app.getGameStatusLabel().setText("Game is Won");
		}
		app.repaint();
	}
}
