package GridReduce.boundary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GridReduce.model.Model;

class testPuzzlePanel {

	@Test
	void testComputeX() {
		Model model = new Model();
		PuzzlePannel panel = new PuzzlePannel(model);
		assertEquals(panel.computeX(6), 0);
		assertEquals(panel.computeX(4), 1);
		assertEquals(panel.computeX(2), 2);
	}
	
	@Test
	void testComputeY() {
		Model model = new Model();
		PuzzlePannel panel = new PuzzlePannel(model);
		assertEquals(panel.computeY(6), 2);
		assertEquals(panel.computeY(4), 1);
		assertEquals(panel.computeY(2), 0);
		assertEquals(panel.computeY(9), -1);
	}

}
