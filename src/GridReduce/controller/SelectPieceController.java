package GridReduce.controller;

import java.awt.Point;

import GridReduce.boundary.GridReduceApp;
import GridReduce.boundary.UpdateButtons;
import GridReduce.model.Model;
import GridReduce.model.ValidMoves;

/** Controller to handle selecting a piece in the PuzzlePanel.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class SelectPieceController {
	Model model;
	GridReduceApp app;

	public SelectPieceController(Model m, GridReduceApp a) {
		this.model = m;
		this.app = a;
	}

	/** Takes a point of coordinates on the PuzzlePanel, decodes which piece was clicked on. Then selects/unselects the piece and updates the buttons accordingly. 
	 * @param point	the clicked coordinates in the PuzzlePanel
	 */
	public void process(Point point) {
		if(model.isGameWon() || model.isGameLost()) {
			return;
		}
		int pieceIndex = app.getPuzzlePannel().pointToPieceIndex(point);

		if (model.getSelectedPiece() == pieceIndex) {
			model.clearSelectedPiece();
			UpdateButtons.enableButtons(app, new ValidMoves(false, false, false, false));
			app.repaint();
		} else if (model.getPuzzle().getPieces().get(pieceIndex) != -1) {
			model.clearSelectedPiece();
			model.setSelectedPiece(pieceIndex);
			ValidMoves moves = model.avaliableMoves();
			UpdateButtons.enableButtons(app, moves);
			app.repaint();
		}
	}

}
