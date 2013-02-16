
package Participants.MajeuxUdvari;

import java.util.ArrayList;
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
		currentState = new GameState();
		ReversiTools.printGameState(currentState);
		}
	
	Scanner stdin = new Scanner(System.in);
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	

	// Méthode appelée à chaque fois que vous devez jouer un coup.
	// move est le coup joué par l'adversaire
	public Move nextPlay(Move move)
		{
		
		
		ReversiTools.fixMove(move);
		System.out.println("Human : [" + move.i + "][" + move.j + "]");
		//System.out.println("Move from " + move.i + " to " + move.j);
		// Ici, vous devrez
		
		
		
		// - Mettre à jour votre représentation du jeu en fonction du coup joué par l'adversaire
		//currentState.executeMove(move, Helpers.enemyPlayer(playerID));
		
		if(playerID == GameState.BLUE)
			{
			System.out.println("PlayerID maps to blue");
			}
		else if(playerID == GameState.RED)
			{
			System.out.println("PlayerID maps to red");
			}
		else
			{
			System.out.println("Player ID is not mapped correctly");
			}
		
		ReversiTools.applyMove(currentState.getBoard(),playerID, move);
		
		ReversiTools.printGameState(currentState);
		
		
		
		// - Décider quel coup jouer (alpha-beta!!)
		// - Remettre à jour votre représentation du jeu
		// - Retourner le coup choisi
		// Mais ici, on se contente de lire à la console:
		/*
		Move result = null;
		if (move != null) System.out.println("Coup adverse: " + move.i + ", " + move.j);
		System.out.println("Votre coup: ");
		System.out.print("Colonne (-1 si aucun coup possible): ");
		int i = stdin.nextInt();
		if (i != -1)
			{
			System.out.print("Ligne: ");
			int j = stdin.nextInt();
			result = new Move(i, j);
			}
		*/
		ArrayList<Move> listMoves = ReversiTools.getValidMoves(ReversiTools.flippedValue(playerID), currentState);
		Move someMove = listMoves.get(0);
		
		Move result = someMove;
		
		System.out.println("Machine : [" + result.i + "][" + result.j + "]");

		
		ReversiTools.applyMove(currentState.getBoard(), ReversiTools.flippedValue(playerID), result);
		ReversiTools.printGameState(currentState);
		
		ReversiTools.fixMove(result);
		
		return result;
		//return new Move();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private GameState currentState;
	}

