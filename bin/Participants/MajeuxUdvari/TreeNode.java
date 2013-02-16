
package Participants.MajeuxUdvari;

import Othello.Move;

/**
 * Represents the state of the current Reversi Game
 * @author udvaritibor
 *
 */
public class TreeNode
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public TreeNode()
		{
		board = new int[8][8];
		
		// Initial values
		for(int i = 0; i < board.length; i++)
			{
			for(int j = 0; j < board.length; j++)
				{
				board[i][j] = EMPTY;
				}
			}
		
		board[3][3] = BLUE;
		board[3][4] = RED;
		board[4][3] = RED;
		board[4][4] = BLUE;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/**
	 * Returns a value representing the strength of a given configuration
	 * @return evalValue
	 */
	public int eval()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	
	/**
	 * Returns if the game is finished
	 * @return
	 */
	public boolean isFinal()
		{
		return false;
		}
	
	/**
	 * Gets array of possible moves
	 * @return
	 */
	public Move[] getOperators()
		{
		Move[] moves = new Move[10];
		return moves;
		}
	
	/**
	 * Gets a new TreeNode with the given move applied
	 * @param move : operator to apply
	 * @return new instance of TreeNode with move applied
	 */
	public TreeNode apply(Move move)
		{
		//TODO handle current player swtching 
		
		return null;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/**
	 * Checks move validity 
	 * 
	 * Empty case 
	 * 
	 * 
	 * 
	 * @param move
	 * @param player the current player 
	 * @return if the move is valid
	 */
	private boolean isValidMove(Move move, int player)
		{
		if (board[move.i][move.j] == EMPTY) { return false; }
		
		for(Move direction:arrDirections)
			{
			//Move over enemy players 
			Move currentLocation = move;
			do
				{
				currentLocation = Helpers.add(currentLocation, direction);
				} while(Helpers.isInsideBoard(currentLocation) && Helpers.enemyPlayer(player) == board[currentLocation.i][currentLocation.j]); // currentLoc is enemy / and is in the playing field
			
			//Valid if the move currentLocation contains one of our checkers
			if (Helpers.isInsideBoard(currentLocation) && board[currentLocation.i][currentLocation.j] == player) { return true; }
			}
		
		return false;
		}
	
	/**
	 * Executes a move and flips 
	 * @param move
	 * @param player
	 * @return
	 */
	public boolean executeMove(Move move, int player)
		{
		if (board[move.i][move.j] != EMPTY) { return false; }
		
		for(Move direction:arrDirections)
			{
			//Move over enemy players 
			Move currentLocation = move;
			do
				{
				currentLocation = Helpers.add(currentLocation, direction);
				} while(Helpers.isInsideBoard(currentLocation) && Helpers.enemyPlayer(player) == board[currentLocation.i][currentLocation.j]); // currentLoc is enemy / and is in the playing field
			
			//Valid if the move currentLocation contains one of our checkers
			if (Helpers.isInsideBoard(currentLocation) && board[currentLocation.i][currentLocation.j] == player)
				{
				flipBetween(move, Helpers.subtract(currentLocation, direction), direction);
				System.out.println("VAlid");
				return true;
				}
			}
		return false;
		}
	
	public void printState()
		{
		for(int i = 0; i < board.length; i++)
			{
			for(int j = 0; j < board.length; j++)
				{
				System.out.print(board[i][j]+  " ");
				}
			System.out.println();
			}
		}
	
	private void flipBetween(Move start, Move finish, Move direction)
		{
		System.out.println("flipbetween boucle");
		
		while(!Helpers.equals(start, finish))
			{
			System.out.println("FLipping inside boucle");
			start = Helpers.add(start, direction);
			flip(start);
			}
		}
	
	private void flip(Move position)
		{
		board[position.i][position.j] = (board[position.i][position.j] + 1) % 2;
		}
	
	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int EMPTY = 2;
	//Directions 
	
	public final Move dirUp = new Move(0, -1);
	public final Move dirDown = new Move(0, 1);
	public final Move dirRight = new Move(1, 0);
	public final Move dirLeft = new Move(-1, 0);
	public final Move dirUpRight = new Move(1, 1);
	public final Move dirUpLeft = new Move(-1, -1);
	public final Move dirDownLeft = new Move(-1, 1);
	public final Move dirDownRight = new Move(1, 1);
	
	final Move arrDirections[] = { dirUp, dirDown, dirRight, dirLeft, dirUpLeft, dirUpRight, dirDownLeft, dirDownRight };
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int[][] board;
	
	/**
	 * Red or blue player 
	 */
	private E_Player currentPlayer;
	
	}
