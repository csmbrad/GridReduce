package GridReduce.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestPuzzle {

	@Test
	void testPuzzleSizes() {
		Puzzle puzzle = new Puzzle(3, 4);
		assertEquals(puzzle.squareSize, 3);
		assertEquals(puzzle.winningIndex, 4);
	}

	@Test
	void testSetPuzzleArray() {
		ArrayList<Integer> start = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
		Puzzle puzzle = new Puzzle(3, 4);
		puzzle.setPuzzleArrayList(start);
		assertEquals(puzzle.getPieces(), start);
		assertEquals(puzzle.getOriginalPosition(), start);
	}

	@Test
	void testClearPuzzle() {
		ArrayList<Integer> start = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
		Puzzle puzzle = new Puzzle(3, 4);
		puzzle.setPuzzleArrayList(start);
		puzzle.clearPuzzleArrayList();
		ArrayList<Integer> newList = new ArrayList<>();
		assertEquals(puzzle.getPieces(), newList);
		assertEquals(puzzle.getOriginalPosition(), newList);
	}

	@Test
	void testResetPuzzle() {
		ArrayList<Integer> start = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
		Puzzle puzzle = new Puzzle(3, 4);
		puzzle.setPuzzleArrayList(start);
		puzzle.getPieces().set(1, 37);
		assertNotEquals(puzzle.getPieces(), puzzle.getOriginalPosition());
		puzzle.resetPuzzle();
		assertEquals(puzzle.getPieces(), puzzle.getOriginalPosition());
	}
	@Test
	void testSetPieces() {
		ArrayList<Integer> start = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
		ArrayList<Integer> second = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
		Puzzle puzzle = new Puzzle(3, 4);
		assertEquals(puzzle.setPieces(start), start);
		assertEquals(puzzle.setPieces(second), second);
	}
	
	@Test
	void testGetOriginalPosition() {
		Puzzle puzzle = new Puzzle(3, 4);
		puzzle.setPuzzleArrayList(new ArrayList<>(Arrays.asList(7, 2, 8, 1, 4, 9, 6, 3, 5)));
		assertTrue(puzzle.getOriginalPosition().equals( new ArrayList<>(Arrays.asList(7, 2, 8, 1, 4, 9, 6, 3, 5))));
	}
	
	@Test
	void testSetOriginalPosition() {
		Puzzle puzzle = new Puzzle(3, 4);
		puzzle.setOriginalPosition(new ArrayList<>(Arrays.asList(1, 2, 3)));
		assertEquals(puzzle.getOriginalPosition(), new ArrayList<>(Arrays.asList(1, 2, 3)));
	}
	


}
