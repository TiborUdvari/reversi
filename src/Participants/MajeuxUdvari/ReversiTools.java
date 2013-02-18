
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
	|*							M�thodes Static 						*|
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
		if(move == null)
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
	
	static
		{
		Map<Integer, String> aMap = new HashMap<>();
		
		aMap.put(0, "R");
		aMap.put(1, "B");
		aMap.put(2, "_");
		
		myMap = Collections.unmodifiableMap(aMap);
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
	
	}
