package GridReduce.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import GridReduce.boundary.GridReduceApp;
import GridReduce.controller.ResetBoardController;
import GridReduce.model.Model;

class TestResetBoardController {

	@Test
	void test() {
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		ResetBoardController reset = new ResetBoardController(model, app);
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1, -1)));
		model.setSelectedPiece(3);
		model.setGameLost(true);
		model.setGameWon(true);
		app.getGameStatusLabel().setText("testing");
		reset.resetBoard();
		assertEquals(model.getPuzzle().getPieces(), model.getPuzzle().getOriginalPosition());
		assertTrue(model.getSelectedPiece() == -1);
		assertFalse(model.isGameLost());
		assertFalse(model.isGameWon());
		assertTrue(app.getGameStatusLabel().getText() == "Ongoing");
	}

}
