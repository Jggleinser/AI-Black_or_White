package Aiproj1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class buildDB {
	static Set <BitSet> usedState = new HashSet <BitSet>();
	static Queue <stateReady> readyQueue = new LinkedList<>();
	
	public static void main (String args[] ) throws IOException
	{
		makeDB();	
	}
	
	public static void makeDB() throws IOException
	{
		FileWriter fileWriter = new FileWriter("DB.txt", true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		BitSet black = new BitSet(25);	//all black board
		black.set(0, 25, true); 
		readyQueue.add(new stateReady(black));
		readyQueue.add(new stateReady(new BitSet(25)));
		
		usedState.add(new BitSet(25));
		usedState.add(black);
		
		while (readyQueue.isEmpty() == false)
		{
			stateReady head = readyQueue.remove();
			
			for (int i = 0; i < 5; i++) 
			{
				for (int j = 0; j < 5; j++)
				{
					stateReady test = head.flip(i, j);	//flip if piece 
					
					if (usedState.add(test.toBitSet())) //if state hasnt been encountered before
					{
						test.incIteration();
						readyQueue.add(test);	//add to the queue to flip again
						
						printWriter.println(convertString(test, i, j));
						
					}
				}
			}
		
		}
		
		printWriter.close();
	}
	
	

	
	public static String convertString (stateReady obj, int x, int y)
	{
		StringBuffer toWrite = new StringBuffer();
		for (int i = 0; i < 5; i++)
		{
			for (int k = 0; k < 5; k++)
			{
				if (obj.getBoard()[i][k] == false)
				{
					toWrite.append("0");
				}
				else
					toWrite.append("1");
			}
		}
		
		toWrite.append(" " + obj.getIteration());
		
		toWrite.append(" " + x);
		
		toWrite.append(" " + y);
		
		
		return toWrite.toString();
	}
	

	

	
}
