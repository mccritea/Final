package trouble3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Action {
	//crates the player object for each player
	public static Player[] createPlayers(){
		Player[] players = new Player[2];
		//System.out.print("Enter three letter nick name of Player1: ");
		players[0] = new Player(getName(), 0);
		//System.out.print("Enter three letter nick name of Player2: ");
		players[1] = new Player(getName(), 18);
		return players;
	}
	//trunkates the name to 3 char
	private static String getName(){
		return JOptionPane.showInputDialog("Enter player name:");
	}
}

class PieceButtonListener implements ActionListener{
	Piece clickedPiece;
	Player player;
	boolean clickable = false;
	public PieceButtonListener(Piece piece){
		clickedPiece = piece;
		player = piece.getOwner();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		//if clickable
		if(clickable){
			//check if piece is on the board
			if(clickedPiece.isOnBoard()){
				//move the piece
				player.checkAndMove(Die.getRoll(), clickedPiece);
				//make unclickable
				clickable = false;
			}
			else if(!clickedPiece.isOnBoard()){
				//put the piece on the board
				player.comeOut(clickedPiece);
				//make unclickable
				clickable = false;
			}
		}
		else{
			//update message
			TestGUI.southPanel.message.setText("Roll First");
		}
	}
}

class DieButtonListener implements ActionListener{
	boolean clickable = false;
	int roll;
	@Override
	public void actionPerformed(ActionEvent e){
		if(clickable){
		//roll the die
		Die.roll();
		roll = Die.getRoll();
		//if(player has available moves)
		//change the message
		TestGUI.southPanel.message.setText("Pick a piece to move by clicking the icon of the piece.");
		//make clickable PieceButtons
		pieceButtonsClickable(true);
		//make unclickable DieButton
		
		
		//MARK:Im WORKING HERE ALEKS
		TestGUI.westPanel.rollPanel.add(new ImageIcon(""));
		}
	}
	
	void pieceButtonsClickable(boolean state){
		//change the curentplayers pieceButtons to clickable
		
	}
	
	void rollButtonClickable(boolean state){
		//change the curentplayers pieceButtons to clickable
		
	}
}

class Turn {
	static int turnCounter = 0;
	static Player currentPlayer;
	
	static void nextTurn(){
		//increment the turnCounter
		if(Die.getRoll() != 6)
			turnCounter++;
		//set new currentPlayer
		currentPlayer = TestGUI.players[turnCounter % 2];
		
		//move the RollPanel to the next players side
		if(turnCounter % 2 == 0){
			//remove from current player
			TestGUI.eastPanel.remove(TestGUI.eastPanel.rollPanel);
			TestGUI.eastPanel.repaint();
			//add to the next player
			TestGUI.westPanel.rollPanel = new RollPanel();
			TestGUI.westPanel.repaint();
		}
		else{
			//remove from current player
			TestGUI.westPanel.remove(TestGUI.westPanel.rollPanel);
			TestGUI.westPanel.repaint();
			//add to the next player
			TestGUI.eastPanel.rollPanel = new RollPanel();
			TestGUI.eastPanel.repaint();
		}
		//end game display
		
	}
	
	static public boolean winCheck(){
		if(currentPlayer.hasWon())
			return true;
		return false;
	}
	
}

