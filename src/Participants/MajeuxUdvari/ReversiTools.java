
package Participants.MajeuxUdvari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Othello.Move;

/**
 * Operations udes by the game 
 * @author udvaritibor
 *
 */
public class ReversiTools
	{
	
	/*------------------------------------------------------------------*\
	|*							Méthodes Static 						*|
	\*-----------------------------------------------------------------*/
	
	public static ArrayList<Move> getValidMoves(int player, GameState gameState)
		{
		return getValidMoves(player, gameState.getBoard());
		}
	
	public static ArrayList<Move> getValidMoves(int player, int[][] board)
		{
		ArrayList<Move> listValidMoves = new ArrayList<Move>();
		
		for(int row = 0; row < board.length; row++)
			{
			for(int col = 0; col < board[row].length; col++)
				{
				Move move = new Move(row, col);
				if (isValid(player, move, board))
					{
					listValidMoves.add(move);
					}
				}
			}
		
		return listValidMoves;
		}
	
	public static boolean isValid(int player, Move move, GameState gameState)
		{
		return isValid(player, move, gameState.getBoard());
		}
	
	public static boolean isValid(int player, Move move, int board[][])
		{
		if (board[move.i][move.j] != GameState.EMPTY) { return false; }
		
		for(Move direction:GameState.arrDirections)
			{
			int x = move.i;
			int y = move.j;
			//It has had to encounter at least one enemy 
			int distance = 0;
			try
				{
				do
					{
					x += direction.i;
					y += direction.j;
					distance++;
					} while(board[x][y] == flippedValue(player));
				
				//Valid -> flip
				if (board[x][y] == player && distance > 1) { return true; }
				}
			catch (ArrayIndexOutOfBoundsException e)
				{
				//went out of array, will try other direction
				continue;
				}
			}
		return false;
		}
	
	public static void applyMove(GameState gameState, int player, Move move)
		{
		ReversiTools.applyMove(gameState.getBoard(), player, move);
		gameState.setMoves(gameState.getMoves() + 1);
		}
	
	/**
	 * Apply a move to the board
	 * @player : the color of a player
	 * 
	 * ASSUMPTION : already checked as valid move ! 
	 */
	public static void applyMove(int[][] board, int player, Move move)
		{
		if (move == null)
			{
			//TODO check this 
			System.out.println("Move was empty");
			return;
			}
		// Apply the checker
		board[move.i][move.j] = player;
		
		// Go around and flip where necessary 
		
		for(Move direction:GameState.arrDirections)
			{
			int x = move.i;
			int y = move.j;
			try
				{
				do
					{
					x += direction.i;
					y += direction.j;
					
					} while(board[x][y] == flippedValue(player));
				
				//Valid -> flip
				if (board[x][y] == player)
					{
					flipCheckersBetweenPoints(x, y, move.i, move.j, board);
					}
				}
			catch (ArrayIndexOutOfBoundsException e)
				{
				//went out of array, will try other direction
				}
			}
		
		}
	
	/**
	 * Flips checkers only BETWEEM extremities / does not flip points (i1,j1) or (i2, j2)
	 */
	public static void flipCheckersBetweenPoints(int i1, int j1, int i2, int j2, int[][] board)
		{
		int incX = getIncrementor(i1, i2);
		int incY = getIncrementor(j1, j2);
		
		int x = i1 + incX;
		int y = j1 + incY;
		
		while(x != i2 || y != j2)
			{
			board[x][y] = flippedValue(board[x][y]);
			x += incX;
			y += incY;
			}
		
		}
	
	public static int getIncrementor(int n, int m)
		{
		return (n - m) == 0 ? 0 : (m - n) / Math.abs(m - n);
		}
	
	public static int flippedValue(int n)
		{
		return (n + 1) % 2;
		}
	
	public static void printGameState(GameState gameState)
		{
		System.out.println("Current board state : ");
		print2DArray(gameState.getBoard());
		}
	
	public static void print2DArray(int[][] array)
		{
		print2DArray(array, myMap);
		}
	
	public static void print2DArray(int[][] array, Map<Integer, String> translationDict)
		{
		for(int row = 0; row < array.length; row++)
			{
			for(int col = 0; col < array[row].length; col++)
				{
				printTranslatedChar(array[row][col], translationDict);
				}
			System.out.println();
			}
		}
	
	private static void printTranslatedChar(int n, Map<Integer, String> translationDict)
		{
		if (translationDict == null)
			{
			System.out.print(n + " ");
			}
		else
			{
			System.out.print(" " + translationDict.get(Integer.valueOf(n)) + " ");
			}
		}
	
	public static void fixMove(Move move)
		{
		if (move != null)
			{
			int temp = move.i;
			move.i = move.j;
			move.j = temp;
			}
		}
	
	private static final Map<Integer, String> myMap;
	private static int[][] arrayPositionValues;
	
	static
		{
		Map<Integer, String> aMap = new HashMap<>();
		
		aMap.put(0, "R");
		aMap.put(1, "B");
		aMap.put(2, "_");
		
		myMap = Collections.unmodifiableMap(aMap);
		
		arrayPositionValues = new int[8][8];
		arrayPositionValues[0][0] = 99;
		arrayPositionValues[0][1] = -8;
		arrayPositionValues[0][2] = 8;
		arrayPositionValues[0][3] = 6;
		
		arrayPositionValues[1][0] = -8;
		arrayPositionValues[1][1] = -24;
		arrayPositionValues[1][2] = -4;
		arrayPositionValues[1][3] = -3;
		
		arrayPositionValues[2][0] = 8;
		arrayPositionValues[2][1] = -4;
		arrayPositionValues[2][2] = 7;
		arrayPositionValues[2][3] = 4;
		
		arrayPositionValues[3][0] = 6;
		arrayPositionValues[3][1] = -3;
		arrayPositionValues[3][2] = 4;
		arrayPositionValues[3][3] = 0;
		
		for(int j = 0; j <= 3; j++)
			{
			
			for(int i = 0; i <= 3; i++)
				{
				arrayPositionValues[7 - i][j] = arrayPositionValues[i][j];
				}
			}
		
		for(int i = 0; i <= 7; i++)
			{
			
			for(int j = 0; j <= 3; j++)
				{
				arrayPositionValues[i][7 - j] = arrayPositionValues[i][j];
				}
			}
		
		}
	
	public static int[][] deepCopy2DRegularArray(int[][] source)
		{
		int n = source.length;
		int[][] destination = new int[n][n];
		
		for(int i = 0; i < n; i++)
			{
			for(int j = 0; j < n; j++)
				{
				destination[i][j] = source[i][j];
				}
			}
		
		return destination;
		}
	
	/*------------------------------------------------------------------*\
	|*							Mini Max								*|
	\*------------------------------------------------------------------*/
	
	public static Object[] minimax(GameState root, int depth, int minOrMax)
		{
		// minimax = 1 -> maximize
		// minimax = -1 minimize
		if (depth == 0 || root.isFinal()) { return new Object[] { root.eval(), null }; }
		
		//int optValue = Integer.MAX_VALUE * minOrMax;
		
		int optValue = -10000 * minOrMax;
		Move optOp = null;
		
		for(Move move:root.getOperators())
			{
			GameState newState = root.apply(move);
			Object[] arrayEvalAndMove = minimax(newState, depth - 1, -minOrMax);
			int eval = (int)arrayEvalAndMove[0];
			
			if (eval * minOrMax > optValue * minOrMax)
				{
				optValue = eval;
				optOp = move;
				}
			}
		
		return new Object[] { optValue, new Move(optOp.i, optOp.j) };
		}
	
	public static Move getMoveMinimax(GameState root, int depth)
		{
		//Computer starts so we maximize first
		Object[] results = minimax(root, depth, 1);
		return (Move)results[1];
		}
	
	/*------------------------------------------------------------------*\
	|*							Alpha Beta								*|
	\*------------------------------------------------------------------*/
	
	public static Object[] alphaBeta(GameState root, int depth, int minOrMax, int parentValue)
		{
		// minimax = 1 -> maximize
		// minimax = -1 minimize
		System.out.println("P1");
		if (depth == 0 || root.isFinal()) { return new Object[] { root.eval(), null }; }
		System.out.println("P2");
		int optValue = Integer.MAX_VALUE * minOrMax;
		Move optOp = null;
		System.out.println("P3");
		for(Move move:root.getOperators())
			{
			GameState newState = root.apply(move);
			Object[] arrayEvalAndMove = alphaBeta(newState, depth - 1, -minOrMax, optValue);
			int eval = (int)arrayEvalAndMove[0];
			//Move resultMove = (Move)arrayEvalAndMove[1];
			System.out.println("P4");
			if (eval * minOrMax > optValue * minOrMax)
				{
				optValue = eval;
				optOp = move;
				if (optValue * minOrMax > parentValue * minOrMax)
					{
					break;
					}
				}
			}
		
		return new Object[] { optValue, new Move(optOp.i, optOp.j) };
		}
	
	public static Move getMoveAlphaBeta(GameState root, int depth, int parentValue)
		{
		// Computer makes first move, and tries to maximize so minOrMax = 1
		// ParentValue is not calculated 
		Object[] results = alphaBeta(root, depth, 1, parentValue);
		return (Move)results[1];
		}
	
	/*------------------------------------------------------------------*\
	|*						Evaluation functions		 				*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/**
	 * Evaluates the state with a static table found here 
	 * http://www.allaine.com/pages/pagesowiggli/sons.html
	 * @return
	 */
	public static int evalWithStaticTable(int player, GameState gameState)
		{
		int[][] board = gameState.getBoard();
		
		int scoreRed = 0;
		int scoreBlue = 0;
		
		for(int i = 0; i < 8; i++)
			{
			for(int j = 0; j < 8; j++)
				{
				
				switch(board[i][j])
					{
					case GameState.RED:
						scoreRed += arrayPositionValues[i][j];
						break;
					
					case GameState.BLUE:
						scoreBlue += arrayPositionValues[i][j];
						break;
					
					}
				
				}
			}
		
		return player == GameState.RED ? scoreRed - scoreBlue : scoreBlue - scoreRed;
		}
	
	public static int evalWithFrontiere(int player, GameState gameState)
		{
		
		int scoreRed = 0;
		int scoreBlue = 0;
		
		int[][] board = gameState.getBoard();
		
		for(int i = 0; i < 8; i++)
			{
			for(int j = 0; j < 8; j++)
				{
				if (board[i][j] == GameState.EMPTY) continue;
				if (isOnFrontiere(i, j, board))
					{
					switch(board[i][j])
						{
						case GameState.RED:
							scoreRed++;
							break;
						
						case GameState.BLUE:
							scoreBlue++;
							break;
						
						}
					}
				}
			}
		
		return player == GameState.RED ? scoreRed - scoreBlue : scoreBlue - scoreRed;
		}
	
	private static boolean isOnFrontiere(int i, int j, int[][] board)
		{
		
		for(Move direction:GameState.arrDirections)
			{
			try
				{
				if (board[i + direction.i][j + direction.j] == GameState.EMPTY) { return true; }
				}
			catch (ArrayIndexOutOfBoundsException e)
				{
				//do nothing
				}
			}
		
		return false;
		}
	
	
	public static int evalDefinitiveCheckers(GameState gameState, int player)
		{
		
		int scoreRed = 0;
		int scoreBlue = 0;
		
		scoreRed += getFixedCheckersNumRec(gameState.getBoard(), GameState.RED, 0, 0, 5, 5, GameState.dirDownRight, 0);
		scoreRed += getFixedCheckersNumRec(gameState.getBoard(), GameState.RED, 0, 7, 5, 5, GameState.dirDownLeft, 0);
		scoreRed += getFixedCheckersNumRec(gameState.getBoard(), GameState.RED, 7, 7, 5, 5, GameState.dirUpLeft, 0);
		scoreRed += getFixedCheckersNumRec(gameState.getBoard(), GameState.RED, 7, 0, 5, 5, GameState.dirUpRight, 0);

		scoreBlue += getFixedCheckersNumRec(gameState.getBoard(), GameState.BLUE, 0, 0, 5, 5, GameState.dirDownRight, 0);
		scoreBlue += getFixedCheckersNumRec(gameState.getBoard(), GameState.BLUE, 0, 7, 5, 5, GameState.dirDownLeft, 0);
		scoreBlue += getFixedCheckersNumRec(gameState.getBoard(), GameState.BLUE, 7, 7, 5, 5, GameState.dirUpLeft, 0);
		scoreBlue += getFixedCheckersNumRec(gameState.getBoard(), GameState.BLUE, 7, 0, 5, 5, GameState.dirUpRight, 0);
		
		return player == GameState.RED ? scoreRed - scoreBlue : scoreBlue - scoreRed;
		}
	
	private static int getFixedCheckersNumRec(int[][] board, int player, int startI, int startJ, int limitX, int limitY, Move direction, int score)
		{
		
		if(limitX < startI && limitY < startJ)
			return 0;
		
		int incX = direction.i;
		int incY = direction.j;
		
		for(int i = startI; i != limitX; i += incX)
			{
			if (board[i][startJ] == player)
				{
				score++;
				}
			else
				{
				limitX = i;
				break;
				}
			}
		
		for(int j = startJ; j != limitY; j += incY)
			{
			if (board[startI][j] == player)
				{
				score++;
				}
			else
				{
				limitX = j;
				break;
				}
			}
		
		return score + getFixedCheckersNumRec(board, player, startI + incX, startJ + incY, limitX, limitY, direction, score);
		
		}
	
	}
