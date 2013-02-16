
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
		//TODO change way of enum passing
		//currentState = new TreeNode();
		}
	
	Scanner stdin = new Scanner(System.in);
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	

	// M�thode appel�e � chaque fois que vous devez jouer un coup.
	// move est le coup jou� par l'adversaire
	public Move nextPlay(Move move)
		{
		System.out.println("Move from " + move.i + " to " + move.j);
		// Ici, vous devrez
		
		
		
		// - Mettre � jour votre repr�sentation du jeu en fonction du coup jou� par l'adversaire
		System.out.println("Got here");
		//currentState.executeMove(move, Helpers.enemyPlayer(playerID));
		currentState.printState();
		
		
		// - D�cider quel coup jouer (alpha-beta!!)
		// - Remettre � jour votre repr�sentation du jeu
		// - Retourner le coup choisi
		// Mais ici, on se contente de lire � la console:
		
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
		return result;
		//return new Move();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private TreeNode currentState;
	}

