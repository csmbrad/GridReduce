package GridReduce.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestValidMoves {

	@Test
	void testSetUp() {
		ValidMoves moves = new ValidMoves(false, false, false, false);
		moves.setUp(true);
		assertTrue(moves.up);
	}

	@Test
	void testSetDown() {
		ValidMoves moves = new ValidMoves(false, false, false, false);
		moves.setDown(true);
		assertTrue(moves.down);
	}

	@Test
	void testSetLeft() {
		ValidMoves moves = new ValidMoves(false, false, false, false);
		moves.setLeft(true);
		assertTrue(moves.left);
	}

	@Test
	void testSetRight() {
		ValidMoves moves = new ValidMoves(false, false, false, false);
		moves.setRight(true);
		assertTrue(moves.right);
	}

	@Test
	void testGetUp() {
		ValidMoves moves = new ValidMoves(true, false, false, false);
		assertTrue(moves.getUp());
	}

	@Test
	void testGetDown() {
		ValidMoves moves = new ValidMoves(false, true, false, false);
		assertTrue(moves.getDown());
	}

	@Test
	void testGetLeft() {
		ValidMoves moves = new ValidMoves(false, false, true, false);
		assertTrue(moves.getLeft());
	}

	@Test
	void testGetRight() {
		ValidMoves moves = new ValidMoves(false, false, false, true);
		assertTrue(moves.getRight());
	}
}
