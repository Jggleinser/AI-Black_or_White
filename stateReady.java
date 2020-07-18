package Aiproj1;

import java.util.BitSet;

public class stateReady {
	int iteration;
	boolean [][] board = new boolean[5][5];
	
	stateReady(BitSet data)
	{
		iteration = 0;
		int k = 0;
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				board[i][j] = data.get(k);
				k++;
			}
		}
		
	}
	stateReady(boolean[][] boardP)
	{
		board = boardP;
	}
	
	public void setIteration (int it)
	{
		iteration = it; 
	}
	
	public void incIteration ()
	{
		this.iteration++;
	}
	
	public Integer getIteration()
	{
		return this.iteration;
	}
	
	public boolean[][] getBoard ()
	{
		return board;
	}
	
	
	public stateReady flip(int x, int y)
	{
		
		
		boolean [][] flippedBoard = new boolean[5][5];
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				flippedBoard[i][j] = this.board[i][j];
			}
		}
		
		
		for(int i = 0; i < 5; i++)
		{
			if (flippedBoard[x][i] == false)
				flippedBoard[x][i] = true;
			else
				flippedBoard[x][i] = false;
			
			if (flippedBoard[i][y] == true)
				flippedBoard[i][y] = false;
			else
				flippedBoard[i][y] = true;
		}
			if (flippedBoard[x][y] == false)
				flippedBoard[x][y] = true;
			else
				flippedBoard[x][y] = false;
			
			stateReady temp = new stateReady(flippedBoard);
			temp.setIteration(this.getIteration());
			return temp;
		
	}
	
	public BitSet toBitSet()
	{
		BitSet temp = new BitSet(25);
		int k = 0;
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if (board[i][j] == true)
				{
					temp.set(k);
				}
				else
					temp.set(k, false);
				
				k++;
			}
		}
		return temp;
	}
	
	public void showBoard()
	{
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				int x;
				if (this.board[i][j] == false)
				{
					x = 0;
				}
				else
					x = 1;
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}

}
