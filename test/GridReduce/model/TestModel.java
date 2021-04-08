package GridReduce.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestModel {

	@Test
	void testSetPuzzle() {
		Model model = new Model();
		Puzzle puzzle = new Puzzle(5, 10);
		model.setPuzzle(puzzle);
		assertEquals(model.puzzle, puzzle);
	}

	@Test
	void testGetPuzzle() {
		Model model = new Model();
		Puzzle puzzle = new Puzzle(15, 25);
		model.setPuzzle(puzzle);
		assertEquals(model.getPuzzle(), puzzle);
	}

	@Test
	void testSetSelectedPiece() {
		Model model = new Model();
		model.setSelectedPiece(3);
		assertEquals(model.selectedPieceIndex, 3);
	}

	@Test
	void testClearSelectedPiece() {
		Model model = new Model();
		model.setSelectedPiece(10);
		model.clearSelectedPiece();
		assertEquals(model.getSelectedPiece(), -1);
	}

	@Test
	void testIsGameWon() {
		Model model = new Model();
		model.won = true;
		assertTrue(model.isGameWon());
	}

	@Test
	void testIsGameLost() {
		Model model = new Model();
		model.lost = true;
		assertTrue(model.isGameLost());
	}

	@Test
	void testSetGameWon() {
		Model model = new Model();
		model.setGameWon(true);
		assertTrue(model.isGameWon());
	}

	@Test
	void testSetGameLost() {
		Model model = new Model();
		model.setGameLost(true);
		assertTrue(model.isGameLost());
	}

	@Test
	void testSetGameOver() {
		Model model = new Model();
		model.setGameOver(true);
		assertTrue(model.isGameWon());
		assertFalse(model.isGameLost());
		model.setGameOver(false);
		assertTrue(model.isGameLost());
		assertFalse(model.isGameWon());
	}

	@Test
	void testCheckWin() {
		Model model = new Model();
		assertFalse(model.checkWin());
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, -1, -1, -1, 4, -1, -1, -1, -1)));
		assertTrue(model.checkWin());
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(1, 1, 1, 1, -1, 1, 1, 1, 1)));
		assertFalse(model.checkWin());
	}

	@Test
	void testCheckLoss() {
		// | || || |_
		Model model = new Model();
		assertFalse(model.checkLoss());
		// when the center piece is invalid
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(1, 1, 1, 1, -1, 1, 1, 1, 1)));
		assertTrue(model.checkLoss());
		// when the count is 1 and the only piece is the center
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, -1, -1, -1, 1, -1, -1, -1, -1)));
		assertFalse(model.checkLoss());
		// when the count is 1 and the only piece is not in the center
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(1, -1, -1, -1, -1, -1, -1, -1, -1)));
		assertTrue(model.checkLoss());
		// when a piece has no neighbor
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(1, -1, -1, -1, 1, -1, -1, 1, 1)));
		assertTrue(model.checkLoss());
		// when there are neighbors but no valid moves
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, -1, -1, -1, 4, 126, -1, -1, -1)));
		assertTrue(model.checkLoss());
		// when the count is 0. this doesn't make sense, for breaking my game I say...
		// you lose. ahahaha. I am evil.
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1, -1)));
		assertTrue(model.checkLoss());
	}

	@Test
	void testCheckGameOver() {
		Model model = new Model();
		model.checkGameOver();
		// game is neither lost nor won
		assertFalse(model.isGameLost());
		assertFalse(model.isGameWon());
		// game is lost
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, 1, -1, -1, -1, -1, -1, -1, -1)));
		model.checkGameOver();
		assertFalse(model.isGameWon());
		assertTrue(model.isGameLost());
		// game is won
		model.getPuzzle().setPieces(new ArrayList<>(Arrays.asList(-1, -1, -1, -1, 1, -1, -1, -1, -1)));
		model.checkGameOver();
		assertTrue(model.isGameWon());
		assertFalse(model.isGameLost());
	}

	@Test
	void testAvaliableMoves() {
		Model model = new Model();
		// when the selected index is -1
		model.setSelectedPiece(-1);
		ValidMoves moves = model.avaliableMoves();
		assertFalse(moves.getUp());
		assertFalse(moves.getDown());
		assertFalse(moves.getLeft());
		assertFalse(moves.getRight());
		// when the selected piece index is not -1
		//default puzzle, with selected piece index 2 should result in all false again
		model.setSelectedPiece(2);
		ValidMoves moves2 = model.avaliableMoves();
		assertFalse(moves2.getUp());
		assertFalse(moves2.getDown());
		assertFalse(moves2.getLeft());
		assertFalse(moves2.getRight());
		// selected piece index of 1 should result in only left, right, and down being true
		model.setSelectedPiece(1);
		ValidMoves moves3 = model.avaliableMoves();
		assertFalse(moves3.getUp());
		assertTrue(moves3.getDown());
		assertTrue(moves3.getLeft());
		assertTrue(moves3.getRight());
		// selected piece index of 8 should result in only up being true
		model.setSelectedPiece(8);
		ValidMoves moves4 = model.avaliableMoves();
		assertTrue(moves4.getUp());
		assertFalse(moves4.getDown());
		assertFalse(moves4.getLeft());
		assertFalse(moves4.getRight());
		// selected: 0
		model.setSelectedPiece(0);
		ValidMoves moves5 = model.avaliableMoves();
		assertFalse(moves5.getUp());
		assertFalse(moves5.getDown());
		assertFalse(moves5.getLeft());
		assertTrue(moves5.getRight());
	}

}
