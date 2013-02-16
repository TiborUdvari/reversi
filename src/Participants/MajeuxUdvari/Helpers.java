
package Participants.MajeuxUdvari;

import Othello.Move;

public class Helpers
	{
	
	/*------------------------------------------------------------------*\
	|*							Méthodes Static 						*|
	\*-----------------------------------------------------------------*/
	public static Move add(Move op1, Move op2)
		{
		return new Move(op1.i + op2.i, op1.j + op2.j);
		}
	
	public static Move subtract(Move position, Move direction)
		{
		return new Move(position.i - direction.i, position.j + direction.j);
		}
	
	public static boolean equals(Move position1, Move position2)
		{
		return position1.i == position2.i && position1.j == position2.j;
		}
	
	public static boolean isInsideBoard(Move move)
		{
		return inReversiBoardLimits(move.i) && inReversiBoardLimits(move.j);
		}
	
	public static boolean inReversiBoardLimits(int n)
		{
		return inLimits(n, 0, 7);
		}
	
	public static boolean inLimits(int num,int low,int high)
		{
		return num >= low && num <= high;
		}
	
	public static int enemyPlayer(int player)
		{
		return player == GameState.BLUE ? GameState.RED : GameState.BLUE;
		}
	
	
	
	}
