package GridReduce.boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GridReduce.controller.MovePieceController;
import GridReduce.controller.QuitController;
import GridReduce.controller.ResetBoardController;
import GridReduce.controller.SelectPieceController;
import GridReduce.model.Model;
import GridReduce.model.ValidMoves;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

/** UI class for the main window of the game.
 * @author 	Brad Cosma bcosma@wpi.edu
 * @version 1.0
 * @since 	1.0
 */
public class GridReduceApp extends JFrame {

	private JPanel contentPane;
	PuzzlePannel panel;
	Model model;
	JButton upButton;
	JButton downButton;
	JButton rightButton;
	JButton leftButton;
	JLabel gameStatusLabel;
	
	public JLabel getGameStatusLabel() {
		return gameStatusLabel;
	}

	public PuzzlePannel getPuzzlePannel() {
		return panel;
	}

	public JButton getUpButton() {
		return upButton;
	}

	public JButton getDownButton() {
		return downButton;
	}

	public JButton getRightButton() {
		return rightButton;
	}

	public JButton getLeftButton() {
		return leftButton;
	}

	/** Creates the frame containing the puzzle, reset/quit/move buttons, and status. Created using Eclipse WindowBuilder.
	 * @param m	The model object describing the current state of the game
	 */
	public GridReduceApp(Model m) {
		super();
		this.model = m; 

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new PuzzlePannel(m);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				new SelectPieceController(model, GridReduceApp.this).process(me.getPoint());
			}
		});

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetBoardController(model, GridReduceApp.this).resetBoard();
			}
		});


		JButton btnQuit = new JButton("Quit?");
		btnQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new QuitController(GridReduceApp.this).quit();
			}
		});

		upButton = new JButton("^");
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, GridReduceApp.this).move(new ValidMoves(true, false, false, false));
			}
		});

		downButton = new JButton("v");
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, GridReduceApp.this).move(new ValidMoves(false, true, false, false));
			}
		});
		
		rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, GridReduceApp.this).move(new ValidMoves(false, false, false, true));
			}
		});

		leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovePieceController(model, GridReduceApp.this).move(new ValidMoves(false, false, true, false));
			}
		});

		JLabel lblGameStatus = new JLabel("Game Status:");

		gameStatusLabel = new JLabel("Ongoing");
		
		((JFrame) SwingUtilities.getWindowAncestor(contentPane)).addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new QuitController(GridReduceApp.this).quit();
			}
		});

		JSeparator separator = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnQuit))
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblGameStatus, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gameStatusLabel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(43)))
					.addGap(6))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addComponent(leftButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(downButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(upButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rightButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnQuit))
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGameStatus)
						.addComponent(gameStatusLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(upButton)
							.addGap(18)
							.addComponent(downButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(leftButton)
								.addComponent(rightButton))
							.addGap(19)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		UpdateButtons.enableButtons(this, new ValidMoves(false, false, false, false));
	}
}
