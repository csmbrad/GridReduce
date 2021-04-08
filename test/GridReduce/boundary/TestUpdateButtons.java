package GridReduce.boundary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GridReduce.model.Model;
import GridReduce.model.ValidMoves;

class TestUpdateButtons {

	@Test
	void testEnableButtons() {
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		app.getUpButton().setEnabled(false);
		app.getDownButton().setEnabled(false);
		app.getRightButton().setEnabled(false);
		app.getLeftButton().setEnabled(false);
		ValidMoves up = new ValidMoves(true, false, false, false);
		UpdateButtons.enableButtons(app, up);
		assertTrue(app.getUpButton().isEnabled());
		assertFalse(app.getDownButton().isEnabled());
		assertFalse(app.getLeftButton().isEnabled());
		assertFalse(app.getRightButton().isEnabled());
		ValidMoves down = new ValidMoves(false, true, false, false);
		UpdateButtons.enableButtons(app, down);
		assertFalse(app.getUpButton().isEnabled());
		assertTrue(app.getDownButton().isEnabled());
		assertFalse(app.getLeftButton().isEnabled());
		assertFalse(app.getRightButton().isEnabled());
		ValidMoves left = new ValidMoves(false, false, true, false);
		UpdateButtons.enableButtons(app, left);
		assertFalse(app.getUpButton().isEnabled());
		assertFalse(app.getDownButton().isEnabled());
		assertTrue(app.getLeftButton().isEnabled());
		assertFalse(app.getRightButton().isEnabled());
		ValidMoves right = new ValidMoves(false, false, false, true);
		UpdateButtons.enableButtons(app, right);
		assertFalse(app.getUpButton().isEnabled());
		assertFalse(app.getDownButton().isEnabled());
		assertFalse(app.getLeftButton().isEnabled());
		assertTrue(app.getRightButton().isEnabled());
		ValidMoves none = new ValidMoves(false, false, false, false);
		UpdateButtons.enableButtons(app, none);
		assertFalse(app.getUpButton().isEnabled());
		assertFalse(app.getDownButton().isEnabled());
		assertFalse(app.getLeftButton().isEnabled());
		assertFalse(app.getRightButton().isEnabled());
	}

}
