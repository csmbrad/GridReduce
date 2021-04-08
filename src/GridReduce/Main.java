package GridReduce;

import GridReduce.boundary.GridReduceApp;
import GridReduce.model.Model;

/** Main driver to launch the app with default configuration.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class Main {

	/** Main function to start the app. Creates instances of Model and GridReduceApp.
	 * @param args
	 */
	public static void main(String[] args) {
		// write your code here
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		app.setVisible(true); 	
	}
}
