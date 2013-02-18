
package Participants.MajeuxUdvari;

import java.util.ArrayList;

import Othello.Move;

/**
 * Represents the state of the current Reversi Game
 * @author udvaritibor
 *
 */
public class GameState
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public GameState(int currentPlayer, int ourPlayer)
		{
		this(currentPlayer, createStandardBoard(), ourPlayer);
		}
	
	public GameState(int currentPlayer, int[][] board, int ourPlayer)
		{
		this.currentPlayer = currentPlayer;
		this.board = board;
		this.ourPlayer = ourPlayer;
		this.moves = 0;
		}
	
	private static int[][] createStandardBoard()
		{
		int[][] standardBoard = new int[8][8];
		
		for(int i = 0; i < standardBoard.length; i++)
			{
			for(int j = 0; j < standardBoard.length; j++)
				{
				standardBoard[i][j] = EMPTY;
				}
			}
		
		standardBoard[3][3] = BLUE;
		standardBoard[3][4] = RED;
		standardBoard[4][3] = RED;
		standardBoard[4][4] = BLUE;
		
		return standardBoard;
		}
	
	/**
	 * Returns a deep copy of our Game State object
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException
		{
		GameState clonedState = new GameState(currentPlayer, ReversiTools.deepCopy2DRegularArray(board), ourPlayer);
		return clonedState;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void switchCurrentPlayer()
		{
		currentPlayer = ReversiTools.flippedValue(currentPlayer);
		}
	
	/*------------------------------------------------------------------*\
	|*						   Alpha Beta Methods						*|
	\*------------------------------------------------------------------*/
	
	/**
	 * Returns a value representing the strength of a given configuration
	 * 
	 * Simple stupid implementation : number of our checkers - number of enemy checkers 
	 * 
	 * @return evalValue
	 */
	public int eval()
		{
		
		int red = 0;
		int blue = 0;
		
		for(int i = 0; i < board.length; i++)
			{
			for(int j = 0; j < board[i].length; j++)
				{
				switch(board[i][j])
					{
					case GameState.RED:
						red++;
						break;
					case GameState.BLUE:
						blue++;
						break;
					default:
						break;
					}
				}
			}
		
		int eval = ourPlayer == GameState.RED ? red - blue : blue - red;
		
		return eval;
		}
	
	
	
	
	/**
	 * Returns if the game is finished
	 * 
	 * Current state : checks if all the moves have been made 
	 * 
	 * @return
	 */
	public boolean isFinal()
		{
		//TODO check case where both players can not move
		return moves == MAX_MOVES || this.getOperators().size() == 0;
		}
	
	/**
	 * Gets array of possible moves
	 * @return
	 */
	public ArrayList<Move> getOperators()
		{
		return ReversiTools.getValidMoves(currentPlayer, board);
		}
	
	/**
	 * Gets a new TreeNode with the given move applied
	 * @param move : operator to apply
	 * @return new instance of TreeNode with move applied
	 */
	public GameState apply(Move move)
		{
		
		GameState result = null;
		
		try
			{
			result = (GameState)this.clone();
			}
		catch (CloneNotSupportedException e)
			{
			e.printStackTrace();
			}
		
		ReversiTools.applyMove(result, currentPlayer, move);
		result.switchCurrentPlayer();
		
		return result;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int[][] getBoard()
		{
		return this.board;
		}
	
	public int getMoves()
		{
		return moves;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setMoves(int moves)
		{
		this.moves = moves;
		}
	
	
	//Colors
	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int EMPTY = 2;
	
	//Directions 
	public final static Move dirUp = new Move(0, -1);
	public final static Move dirDown = new Move(0, 1);
	public final static Move dirRight = new Move(1, 0);
	public final static Move dirLeft = new Move(-1, 0);
	public final static Move dirUpRight = new Move(1, -1);
	public final static Move dirUpLeft = new Move(-1, -1);
	public final static Move dirDownLeft = new Move(-1, 1);
	public final static Move dirDownRight = new Move(1, 1);
	
	public static final Move arrDirections[] = { dirUp, dirDown, dirRight, dirLeft, dirUpLeft, dirUpRight, dirDownLeft, dirDownRight };
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	public int[][] board;
	private int currentPlayer;
	private int moves;
	private int ourPlayer;
	
	//Constants 
	private static final int MAX_MOVES = 60; // for quick checking of eval function
	
	}
