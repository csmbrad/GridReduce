package GridReduce.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import GridReduce.boundary.GridReduceApp;
import GridReduce.controller.MovePieceController;
import GridReduce.model.Model;
import GridReduce.model.ValidMoves;

class TestMovePieceController {

	@Test
	void testCleanup() {
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		MovePieceController mover = new MovePieceController(model, app);
		model.setSelectedPiece(3);
		// condition: game not lost or won
		mover.cleanup();
		assertTrue(model.getSelectedPiece() == -1);
		assertTrue(app.getGameStatusLabel().getText().equals("Ongoing"));
		//condition: game lost
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(1, -1, -1, -1, -1, -1, -1, -1, -1)));
		model.setGameLost(false);
		model.setGameWon(false);
		mover.cleanup();
		assertTrue(model.isGameLost());
		assertTrue(app.getGameStatusLabel().getText().equals("Game is Lost"));
		//condition: game won
		model.setGameLost(false);
		model.setGameWon(false);
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1,-1,-1,-1, 1, -1, -1, -1, -1)));
		mover.cleanup();
		assertTrue(model.isGameWon());
		assertTrue(app.getGameStatusLabel().getText().equals("Game is Won"));
	}
	
	@Test
	void testMoveGuards() {
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		MovePieceController mover = new MovePieceController(model, app);
		// selected piece is -1
		model.setSelectedPiece(-1);
		mover.move(new ValidMoves(false, false, false, false));
		assertEquals(model.getPuzzle().getPieces(), model.getPuzzle().getOriginalPosition());
		// game is won/lost
		model.setSelectedPiece(4);
		model.setGameWon(true);
		model.setGameLost(false);
		mover.move(new ValidMoves(false, false, false, false));
		assertEquals(model.getPuzzle().getPieces(), model.getPuzzle().getOriginalPosition());
		model.setGameWon(false);
		model.setGameLost(true);
		mover.move(new ValidMoves(false, false, false, false));
		assertEquals(model.getPuzzle().getPieces(), model.getPuzzle().getOriginalPosition());
	}
	
	@Test
	void testMove() {
		Model model = new Model();
		GridReduceApp app = new GridReduceApp(model);
		MovePieceController mover = new MovePieceController(model, app);
		// move right
		model.setSelectedPiece(0);
		mover.move(new ValidMoves(false, false, false, true));
		assertEquals(model.getPuzzle().getPieces(), new ArrayList<>(Arrays.asList(-1, 9, 8, 1, 4, 9, 6, 3, 5)));
		// move left
		model.setSelectedPiece(2);
		mover.move(new ValidMoves(false, false, true, false));
		assertEquals(model.getPuzzle().getPieces(), new ArrayList<>(Arrays.asList(-1, 1, -1, 1, 4, 9, 6, 3, 5)));
		//move down
		model.setSelectedPiece(1);
		mover.move(new ValidMoves(false, true, false, false));
		assertEquals(model.getPuzzle().getPieces(), new ArrayList<>(Arrays.asList(-1, -1, -1, 1, 4, 9, 6, 3, 5)));
		// making some more moves so that we can move up
		model.setSelectedPiece(3);
		mover.move(new ValidMoves(false, false, false, true));
		model.setSelectedPiece(6);
		mover.move(new ValidMoves(false, false, false, true));
		model.setSelectedPiece(8);
		mover.move(new ValidMoves(false, false, true, false));
		// move up
		model.setSelectedPiece(7);
		mover.move(new ValidMoves(true, false, false, false));
		assertEquals(model.getPuzzle().getPieces(), new ArrayList<>(Arrays.asList(-1, -1, -1, -1, 20, 9, -1, -1, -1)));
	}

}
