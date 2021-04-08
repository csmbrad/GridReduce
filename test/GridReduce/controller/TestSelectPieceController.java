package GridReduce.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import GridReduce.boundary.GridReduceApp;
import GridReduce.controller.SelectPieceController;
import GridReduce.model.Model;

class TestSelectPieceController {

	@Test
	void testProcess() {
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		SelectPieceController selector = new SelectPieceController(model, app);
		Point point = new Point(50, 50);
		selector.process(point);
		assertTrue(model.getSelectedPiece() == 0);
		point = new Point(100,50);
		selector.process(point);
		assertTrue(model.getSelectedPiece() == 1);
		selector.process(point);
		assertTrue(model.getSelectedPiece() == -1);
		model.setGameOver(true);
		selector.process(point);
		assertTrue(model.getSelectedPiece() == -1);
	}

}
