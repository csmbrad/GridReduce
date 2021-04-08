package GridReduce.controller;

import GridReduce.boundary.GridReduceApp;
import GridReduce.boundary.UpdateButtons;
import GridReduce.model.Model;
import GridReduce.model.ValidMoves;

/** Controller to reset the game to its original state.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class ResetBoardController {
	Model model;
	GridReduceApp app;

	public ResetBoardController(Model model, GridReduceApp app) {
		this.model = model;
		this.app = app;
	}
	
	/** Resets the puzzle to its original configuration, resetting buttons, clearing selected pieces, and updating status.
	 */
	public void resetBoard() {
		model.getPuzzle().resetPuzzle();
		UpdateButtons.enableButtons(app, new ValidMoves(false, false, false, false));
		model.clearSelectedPiece();
		model.setGameWon(false);
		model.setGameLost(false);
		app.getGameStatusLabel().setText("Ongoing");
		app.repaint();
	}
}
