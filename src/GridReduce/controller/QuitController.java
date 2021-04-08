package GridReduce.controller;

import javax.swing.JOptionPane;

import GridReduce.boundary.GridReduceApp;

/** Controller to show a dialogue asking the user to confirm their intention to quit.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class QuitController {

	GridReduceApp app;

	public QuitController(GridReduceApp app) {
		this.app = app;
	}

	/** Handles creation of a pop-up box asking the user to confirm their intent to quit, and destroys the app if the user confirms.
	 * @return	a boolean corresponding to the user's intent to quit or not.
	 */
	public boolean quit() {
		int response = JOptionPane.showConfirmDialog(null, "Confirm Quit?", "Quit?", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			app.setVisible(false);
			app.dispose();
			return true;
		}
		return false;
	}

}
