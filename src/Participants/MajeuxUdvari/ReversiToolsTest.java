
package Participants.MajeuxUdvari;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Othello.Move;

public class ReversiToolsTest
	{
	
	@Before
	public void setUp() throws Exception
		{
		initTestEnvironement();
		}
	
	private void initTestEnvironement()
		{
		boardSmall = new int[4][4];
		
		for(int i = 1; i <= 4; i++)
			{
			for(int j = 1; j <= 4; j++)
				{
				boardSmall[i - 1][j - 1] = GameState.EMPTY;
				}
			}
		
		boardSmall[0][2] = GameState.RED;
		boardSmall[1][2] = GameState.BLUE;
		boardSmall[2][2] = GameState.BLUE;
		boardSmall[3][2] = GameState.RED;
		
		boardSmall[0][0] = GameState.RED;
		boardSmall[1][1] = GameState.BLUE;
		boardSmall[3][3] = GameState.RED;
		
		boardSmall[0][1] = GameState.BLUE;
		boardSmall[0][3] = GameState.BLUE;
		
		boardSmall[2][1] = GameState.RED;
		
		gameState = new GameState();
		
		boardSmallScenario = new int[5][5];
		
		for(int i = 1; i <= 5; i++)
			{
			for(int j = 1; j <= 5; j++)
				{
				boardSmallScenario[i - 1][j - 1] = GameState.EMPTY;
				}
			}
		
		boardSmallScenario[0][0] = GameState.BLUE;
		boardSmallScenario[0][2] = GameState.BLUE;
		boardSmallScenario[1][1] = GameState.BLUE;
		boardSmallScenario[1][2] = GameState.BLUE;
		boardSmallScenario[4][4] = GameState.BLUE;
		
		boardSmallScenario[1][0] = GameState.RED;
		boardSmallScenario[2][1] = GameState.RED;
		boardSmallScenario[2][2] = GameState.RED;
		boardSmallScenario[2][3] = GameState.RED;
		boardSmallScenario[3][2] = GameState.RED;
		boardSmallScenario[3][3] = GameState.RED;
		boardSmallScenario[3][4] = GameState.RED;
		boardSmallScenario[4][3] = GameState.RED;
		
		boardSmallScenarioFlipInMultipleDirections = new int[5][5];
		boardSmallScenarioFlipInMultipleDirections[0][0] = GameState.BLUE;
		boardSmallScenarioFlipInMultipleDirections[0][1] = GameState.BLUE;
		boardSmallScenarioFlipInMultipleDirections[0][2] = GameState.BLUE;
		boardSmallScenarioFlipInMultipleDirections[0][3] = GameState.BLUE;
		boardSmallScenarioFlipInMultipleDirections[0][4] = GameState.BLUE;
		
		boardSmallScenarioFlipInMultipleDirections[1][0] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[1][1] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[1][2] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[1][3] = GameState.BLUE;
		boardSmallScenarioFlipInMultipleDirections[1][4] = GameState.BLUE;
		
		boardSmallScenarioFlipInMultipleDirections[2][0] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[2][1] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[2][2] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[2][3] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[2][4] = GameState.BLUE;
		
		boardSmallScenarioFlipInMultipleDirections[3][0] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[3][1] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[3][2] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[3][3] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[3][4] = GameState.RED;
		
		boardSmallScenarioFlipInMultipleDirections[4][0] = GameState.EMPTY;
		boardSmallScenarioFlipInMultipleDirections[4][1] = GameState.EMPTY;
		boardSmallScenarioFlipInMultipleDirections[4][2] = GameState.EMPTY;
		boardSmallScenarioFlipInMultipleDirections[4][3] = GameState.RED;
		boardSmallScenarioFlipInMultipleDirections[4][4] = GameState.RED;
		
		boardSmallScenarioMD2 = new int[3][3];
		boardSmallScenarioMD2[0][0] = GameState.BLUE;
		boardSmallScenarioMD2[0][1] = GameState.EMPTY;
		boardSmallScenarioMD2[0][2] = GameState.BLUE;
		
		boardSmallScenarioMD2[1][0] = GameState.RED;
		boardSmallScenarioMD2[1][1] = GameState.RED;
		boardSmallScenarioMD2[1][2] = GameState.RED;
		
		boardSmallScenarioMD2[2][0] = GameState.EMPTY;
		boardSmallScenarioMD2[2][1] = GameState.EMPTY;
		boardSmallScenarioMD2[2][2] = GameState.BLUE;
		}
	
	@Test
	public void testMultipleDirectionScenarion2()
		{
		System.out.println("Before:");
		ReversiTools.print2DArray(boardSmallScenarioMD2);
		
		ReversiTools.applyMove(boardSmallScenarioMD2, GameState.BLUE, new Move(2, 0));
		
		System.out.println("After:");
		ReversiTools.print2DArray(boardSmallScenarioMD2);
		
		assertEquals(GameState.BLUE, boardSmallScenarioMD2[2][0]);
		assertEquals(GameState.BLUE, boardSmallScenarioMD2[1][0]);
		assertEquals(GameState.BLUE, boardSmallScenarioMD2[1][1]);
		assertEquals(GameState.BLUE, boardSmallScenarioMD2[0][2]);
		assertEquals(GameState.BLUE, boardSmallScenarioMD2[2][2]);
		
		assertEquals(GameState.RED, boardSmallScenarioMD2[1][2]);
		}
	
	@Test
	public void testGetIncrementorDecrease()
		{
		int result = ReversiTools.getIncrementor(4, 1);
		int expected = -1;
		assertEquals(expected, result);
		
		}
	
	@Test
	public void testGetIncrementorSameLine()
		{
		int result = ReversiTools.getIncrementor(0, 0);
		int expected = 0;
		assertEquals(expected, result);
		
		}
	
	@Test
	public void testGetIncrementorIncrease()
		{
		int result = ReversiTools.getIncrementor(0, 4);
		int expected = 1;
		assertEquals(expected, result);
		}
	
	@Test
	public void testBoardSmallScenarioFlipInMultipleDirections()
		{
		ReversiTools.applyMove(boardSmallScenarioFlipInMultipleDirections, GameState.BLUE, new Move(4, 0));
		
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[0][0]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[0][1]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[0][2]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[0][3]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[0][4]);
		
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[1][3]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[2][2]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[3][1]);
		}
	
	@Test
	public void testFlipOnSmallBoardScenarioMultipleDirections()
		{
		boardSmallScenarioFlipInMultipleDirections[4][0] = GameState.BLUE;
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[1][3]);
		
		ReversiTools.flipCheckersBetweenPoints(4, 0, 1, 3, boardSmallScenarioFlipInMultipleDirections);
		
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[0][4]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[1][3]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[2][2]);
		assertEquals(GameState.BLUE, boardSmallScenarioFlipInMultipleDirections[3][1]);
		
		}
	
	@Test
	public void testIsValidOnSmallScenario()
		{
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(1, 1), boardSmallScenario));
		}
	
	@Test
	public void testGetValidMoves()
		{
		ArrayList<Move> result = ReversiTools.getValidMoves(GameState.RED, gameState);
		
		ArrayList<Move> expected = new ArrayList<Move>();
		expected.add(new Move(2, 3));
		expected.add(new Move(3, 2));
		expected.add(new Move(5, 4));
		expected.add(new Move(4, 5));
		
		assertEquals(result.size(), expected.size());
		//Forbidden to modify Move class so we must do some custom stuff 
		
		for(Move move:expected)
			{
			Assert.assertTrue(doesContainMove(result, move));
			}
		}
	
	/**
	 * Must do this manip because we aren't allowed to modify the Move class
	 */
	private boolean doesContainMove(ArrayList<Move> listMoves, Move move)
		{
		for(Move moveInList:listMoves)
			{
			if (areMovesEqual(move, moveInList)) { return true; }
			}
		return false;
		}
	
	private boolean areMovesEqual(Move m1, Move m2)
		{
		return m1.i == m2.i && m1.j == m2.j;
		}
	
	@Test
	public void testIsValidRedPlayer()
		{
		Assert.assertTrue(ReversiTools.isValid(GameState.RED, new Move(2, 3), gameState));
		Assert.assertTrue(ReversiTools.isValid(GameState.RED, new Move(3, 2), gameState));
		Assert.assertTrue(ReversiTools.isValid(GameState.RED, new Move(5, 4), gameState));
		Assert.assertTrue(ReversiTools.isValid(GameState.RED, new Move(4, 5), gameState));
		
		//Diagonal
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(2, 5), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(5, 2), gameState));
		
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(4, 2), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(5, 3), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(2, 4), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(3, 5), gameState));
		
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(3, 3), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(4, 4), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(3, 4), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.RED, new Move(4, 3), gameState));
		}
	
	@Test
	public void testIsValidBluePlayer()
		{
		Assert.assertTrue(ReversiTools.isValid(GameState.BLUE, new Move(4, 2), gameState));
		Assert.assertTrue(ReversiTools.isValid(GameState.BLUE, new Move(5, 3), gameState));
		Assert.assertTrue(ReversiTools.isValid(GameState.BLUE, new Move(3, 5), gameState));
		Assert.assertTrue(ReversiTools.isValid(GameState.BLUE, new Move(2, 4), gameState));
		
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(2, 2), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(2, 3), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(2, 5), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(4, 5), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(5, 5), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(5, 4), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(5, 2), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(3, 2), gameState));
		
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(3, 3), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(4, 4), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(3, 4), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(4, 3), gameState));
		
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(0, 0), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(0, 1), gameState));
		Assert.assertFalse(ReversiTools.isValid(GameState.BLUE, new Move(1, 0), gameState));
		}
	
	@Test
	public void testFlippedValueForRed()
		{
		int red = GameState.RED;
		int result = ReversiTools.flippedValue(red);
		assertEquals(GameState.BLUE, result);
		}
	
	@Test
	public void testFLipperdValueForBlue()
		{
		int expected = GameState.RED;
		int result = ReversiTools.flippedValue(GameState.BLUE);
		
		assertEquals(expected, result);
		}
	
	@Test
	public void testApplyMoveOnGameState()
		{
		int[][] board = gameState.getBoard();
		
		ReversiTools.applyMove(board, GameState.RED, new Move(2, 3));
		
		assertEquals(GameState.RED, board[2][3]);
		assertEquals(GameState.RED, board[3][3]);
		assertEquals(GameState.RED, board[4][3]);
		
		assertEquals(GameState.RED, board[3][4]);
		assertEquals(GameState.BLUE, board[4][4]);
		}
	
	@Test
	public void testApplyMove()
		{
		ReversiTools.applyMove(boardSmall, GameState.BLUE, new Move(3, 0));
		
		assertEquals(GameState.BLUE, boardSmall[3][0]);
		assertEquals(GameState.BLUE, boardSmall[1][2]);
		assertEquals(GameState.BLUE, boardSmall[2][1]);
		assertEquals(GameState.BLUE, boardSmall[0][3]);
		
		}
	
	@Test
	public void testFlipCheckersBetweenPointsHorizontal()
		{
		
		ReversiTools.flipCheckersBetweenPoints(0, 1, 0, 3, boardSmall);
		
		assertEquals(GameState.BLUE, boardSmall[0][1]);
		assertEquals(GameState.BLUE, boardSmall[0][2]);
		assertEquals(GameState.BLUE, boardSmall[0][3]);
		
		}
	
	@Test
	public void testFlipCheckersBetweenPointsHorizontalReverse()
		{
		
		ReversiTools.flipCheckersBetweenPoints(0, 3, 0, 1, boardSmall);
		
		assertEquals(GameState.BLUE, boardSmall[0][1]);
		assertEquals(GameState.BLUE, boardSmall[0][2]);
		assertEquals(GameState.BLUE, boardSmall[0][3]);
		
		}
	
	@Test
	public void testFlipCheckersBetweenPointsVertical()
		{
		
		ReversiTools.flipCheckersBetweenPoints(0, 2, 3, 2, boardSmall);
		
		assertEquals(GameState.RED, boardSmall[3][2]);
		assertEquals(GameState.RED, boardSmall[0][2]);
		assertEquals(GameState.RED, boardSmall[1][2]);
		assertEquals(GameState.RED, boardSmall[2][2]);
		
		}
	
	@Test
	public void testFlipCheckersBetweenPointsDiagonal()
		{
		
		ReversiTools.flipCheckersBetweenPoints(0, 0, 3, 3, boardSmall);
		
		assertEquals(GameState.RED, boardSmall[0][0]);
		assertEquals(GameState.RED, boardSmall[1][1]);
		assertEquals(GameState.RED, boardSmall[2][2]);
		assertEquals(GameState.RED, boardSmall[3][3]);
		
		}
	
	@Test
	public void testFlipCheckersBetweenPointsDiagonalReverse()
		{
		
		ReversiTools.flipCheckersBetweenPoints(0, 0, 3, 3, boardSmall);
		
		assertEquals(GameState.RED, boardSmall[0][0]);
		assertEquals(GameState.RED, boardSmall[1][1]);
		assertEquals(GameState.RED, boardSmall[2][2]);
		assertEquals(GameState.RED, boardSmall[3][3]);
		
		}
	
	@Test
	public void testFlipCheckersBetweenPointsVerticalReverse()
		{
		
		ReversiTools.flipCheckersBetweenPoints(3, 2, 0, 2, boardSmall);
		
		assertEquals(GameState.RED, boardSmall[1][2]);
		assertEquals(GameState.RED, boardSmall[2][2]);
		assertEquals(GameState.RED, boardSmall[3][2]);
		assertEquals(GameState.RED, boardSmall[0][2]);
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int[][] boardSmall;
	private GameState gameState;
	private int[][] boardSmallScenario;
	private int[][] boardSmallScenarioFlipInMultipleDirections;
	private int[][] boardSmallScenarioMD2;
	}
