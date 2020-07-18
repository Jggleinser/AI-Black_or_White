package gameViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class client {
	private static int boardState[][] = new int[5][5];
	public static String helpX;
	public static String helpY = "";
	public static String getMoves = "";
	public static boolean solutionFound = true;

	
	public static void main (String args[]) throws FileNotFoundException
	{
		
		
		randomizeBoard();
		
		while (true)
		{
			
			showBoard();
			choice();
		
			if (checkWin())
			{
				System.out.println("Game is complete");
				break;
			}
		}
		
	
	}
	
	
	public static void mostlyRandom() //will flip for awhile until there's a guaranteed winnable board further from the winning state
	{
		Random rand = new Random();
		boardState = new int[5][5];
		int i = 0; 
		while (i < 15)
		{
			flip(rand.nextInt(5), rand.nextInt(5));
			i++;
		}
	}
	

	
	
	public static void choice() throws FileNotFoundException
	{
		
		Scanner scan = new Scanner(System.in);
		System.out.println("For help, enter 9 - to continue press any number. ");
		int help = scan.nextInt();
		if (help == 9)
		{
			getHelp();
		}
		
		System.out.println("Enter the row of your choice: (1-5)");
		int x = scan.nextInt() - 1;
		System.out.println("Enter the column of your choice: (1-5)");
		int y = scan.nextInt() - 1;
		
		flip(x,y);
		
	}
	
	
	
	public static String boardToString()
	{
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				s.append(boardState[i][j]);
			}
		}
		return s.toString();
		
	}
	
	public static boolean checkWin()
	{
		if (boardToString().contains("0000000000000000000000000") || boardToString().contains("1111111111111111111111111"))
		{
			return true;
			
		}
		else
		{
			return false;
		}
			
	}
	
	public static void getHelp() throws FileNotFoundException
	{
		if (findString())
		{
			System.out.println("X: " + helpX + " " + "Y: " + helpY + " You are: " + getMoves + " moves away");

		}
		else
		{
			System.out.println("There is no solution. Try again");
			mostlyRandom();
			showBoard();
		}

	}
	
	public static boolean findString () throws FileNotFoundException
	{
		String solution = boardToString();
		
		File myFile = new File("DB.txt");
		if (myFile.exists())
		{
			Scanner scanner = new Scanner(myFile);
			while (scanner.hasNextLine())
			{
				final String line = scanner.nextLine();
				if (line.contains(solution))
				{
					String[] split = line.split("\\s+");
					getMoves = split[1];
					helpX = split[2];
					Integer x = Integer.parseInt(helpX) + 1;
					helpX = x.toString();
					helpY = split[3];
					Integer y = Integer.parseInt(helpY) + 1;
					helpY = y.toString();
					
					scanner.close();
					solutionFound = true;
					return true;
				}
				
			
					
			}
		
			
		}
		else
			{
				System.out.println("File does not exist");
				return false;
			}
		solutionFound = false;
		return false;
		
		
	}
	
	public static void flip(int x, int y)
	{
		for(int i = 0; i < 5; i++)
		{
			if (boardState[x][i] == 0)
			boardState[x][i] = 1;
			else
				boardState[x][i] = 0;
			
			if (boardState[i][y] == 0)
				boardState[i][y] = 1;
			else
				boardState[i][y] = 0;
		}
			if (boardState[x][y] == 0)
				boardState[x][y] = 1;
			else
				boardState[x][y] = 0;
		
	}
	
	public static void showBoard()
	{
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				System.out.print(boardState[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void randomizeBoard()
	{
		Random rand = new Random();
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				
				boardState[i][j] = (int) Math.round(Math.random());
			}
		} 
	}
}


