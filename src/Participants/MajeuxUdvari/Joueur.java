
package Participants.MajeuxUdvari;

import java.util.Scanner;

import Othello.Move;

public class Joueur extends Othello.Joueur
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	/**
	 * Constructor of our custom player
	 * @param depth
	 * @param playerID 0 : joue les rouges, 1 : joue les bleus
	 */
	public Joueur(int depth, int playerID)
		{
		super();
		currentState = new GameState(ReversiTools.flippedValue(playerID), playerID);
		ReversiTools.printGameState(currentState);
		System.out.println("Depth var is " + depth);
		}
	
	Scanner stdin = new Scanner(System.in);
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	// Méthode appelée à chaque fois que vous devez jouer un coup.
	// move est le coup joué par l'adversaire
	public Move nextPlay(Move move)
		{
		if (move == null)
			{
			System.out.println("Got null move");
			}
		ReversiTools.fixMove(move);
		
		if (move != null)
			{
			System.out.println("Human : [" + move.i + "][" + move.j + "]");
			
			}
		//System.out.println("Move from " + move.i + " to " + move.j);
		// Ici, vous devrez
		// - Mettre à jour votre représentation du jeu en fonction du coup joué par l'adversaire
		
		//currentState.apply(move);
		
		ReversiTools.applyMove(currentState.getBoard(), playerID, move);
		currentState.switchCurrentPlayer();
		
		ReversiTools.printGameState(currentState);
		
		System.out.println("Getting minimax");
		Move moveToPlay = ReversiTools.getMoveMinimax(currentState, 5);
		System.out.println("Got minimax");
		
		if (moveToPlay != null)
			{
			System.out.println("Machine : [" + moveToPlay.i + "][" + moveToPlay.j + "]");
			}
		
		ReversiTools.applyMove(currentState.getBoard(), ReversiTools.flippedValue(playerID), moveToPlay);
		currentState.switchCurrentPlayer();
		
		ReversiTools.printGameState(currentState);
		//TODO check if null
		ReversiTools.fixMove(moveToPlay);
		
		//TODO if we do not have a move to play ... 
		
		return moveToPlay;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private GameState currentState;
	}
