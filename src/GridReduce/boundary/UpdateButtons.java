package GridReduce.boundary;

import GridReduce.model.ValidMoves;

/** UI class for enabling and disabling the desired move buttons for an App.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class UpdateButtons {
	/** Enables or disables movement ("arrow") buttons based on the ValidMoves object provided.
	 * @param app	The GridReduceApp object which contains the buttons
	 * @param moves	The ValidMoves object specifying which buttons should be enabled and disabled.
	 */
	public static void enableButtons(GridReduceApp app, ValidMoves moves) {
		app.getUpButton().setEnabled(moves.getUp());
		app.getDownButton().setEnabled(moves.getDown());
		app.getRightButton().setEnabled(moves.getRight());
		app.getLeftButton().setEnabled(moves.getLeft());
	}
}
