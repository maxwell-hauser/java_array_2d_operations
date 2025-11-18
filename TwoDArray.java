import javax.swing.*;

public class TwoDArray 
{
	private static String input;
	private static String[] b;
	private static int[][] array, c, d;
	static int low, high, key, cell;
	private static int minimum = Integer.MAX_VALUE, maximum = Integer.MIN_VALUE, sum = 0;
	private static double average = 0.0, standardDeviation0 = 0.0, standardDeviation1 = 0.0;
	private static TwoDArray originalArray;
	
	public TwoDArray(int r, int c, int l, int h)
	{
		array = new int[r][c];
		for (int i=0; i<r; i++)
		{
			for (int j=0; j<c; j++)
			{
				array[i][j] = l + (int)((h-l+1)*Math.random());
			}
		}
	}

	public TwoDArray()
	{
		low = 1;
		high = 100;
		array = new int[10][10];
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				array[i][j] = low + (int)((high-low+1)*Math.random());
			}
		}
	}
	
	public int[][] getArray() {return array;}
	public static int getLow() {return low;}
	public static int getHigh() {return high;}
	
	public void createArray()
	{
		int r = promptInt("Please enter number of rows (1-50):", "# Rows of Two-Dimensional Array", 1, 50);
		int c = promptInt("Please enter number of columns (1-50):", "# Columns of Two-Dimensional Array", 1, 50);

		array = new int[r][c];
		low = promptInt("Please enter the lowest value (<= highest):", "Lowest Value in the Array", -100000, 100000);
		high = promptInt("Please enter the highest value (greater than lowest, up to 1000):", "Highest Value in the Array", Math.max(low + 1, -99999), 1000);

		for (int i=0; i<array.length; i++)
			for (int j=0; j<array[i].length; j++)
				array[i][j] = low + (int)((high-low+1)*Math.random());
	}

	public static int[][] initializeArray(int[][] a, String command)
	{
		if ("Create New Array".equals(command) || "Create Array".equals(command))
		{
			originalArray = new TwoDArray();
			originalArray.createArray();
			a = originalArray.getArray();
		}
		else if (a != null && ( "Array Minimum".equals(command) || "Array Maximum".equals(command)
							 || "Array Average".equals(command) || "Array Standard Deviation".equals(command)
							 || "Array Search".equals(command)))
		{
		}
		else if (a == null && ( "Array Minimum".equals(command) || "Array Maximum".equals(command)
							 || "Array Average".equals(command) || "Array Standard Deviation".equals(command)
							 || "Array Search".equals(command)))
		{
			originalArray = new TwoDArray();
			originalArray.createArray();
			a = originalArray.getArray();	
		}
		
		else
		{
			originalArray = new TwoDArray();
			originalArray.createArray();
			a = originalArray.getArray();
		}
		return a;
	}
				
	public static int getMin(int[][] a)
	{
		minimum = Integer.MAX_VALUE;
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				if (a[row][col] < minimum)
				{
					minimum = a[row][col];
				}
			}
		}
		return minimum;
	}
	
	public static int getMax(int[][] a)
	{
		maximum = Integer.MIN_VALUE;
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				if (a[row][col] > maximum)
				{
					maximum = a[row][col];
				}
			}
		}
		return maximum;
	}
	
	public static double getAvg(int[][] a)
	{
		sum = 0;
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				sum += a[row][col];
			}
		}
		average = sum/(double)(a.length * a[0].length);
		return roundDigits(average, 3);
	}
	
	public static double getSD(int[][] a)
	{
		standardDeviation0 = 0.0;
		standardDeviation1 = 0.0;
		double avg = getAvg(a);
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				standardDeviation0 += Math.pow(a[row][col] - avg, 2);
			}
		}
		standardDeviation1 = Math.pow((standardDeviation0/(a.length * a[0].length)), 0.5);
		return roundDigits(standardDeviation1, 3);
	}
	
	public static String[] searchArray(int[][] a)
	{
		while (true)
		{
			input = JOptionPane.showInputDialog(null, "Please enter search key.", "Enter Search Key", JOptionPane.QUESTION_MESSAGE);
			if (input == null)
			{
				b = new String[]{"false", "<cancelled>", "", ""};
				return b;
			}
			try
			{
				key = Integer.parseInt(input.trim());
				break;
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			}
		}

		b = new String[4];
		b[0] = "false";
		b[1] = Integer.toString(key);
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				if (a[row][col] == key)
				{
					b[0] = "true";
					b[2] = Integer.toString(row);
					b[3] = Integer.toString(col); 
				}	
			}
		}
		return b;	
	}

	public static int[][] addArray(int[][] a, int[][] b)
	{
		c = new int[a.length][a[0].length];
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				c[row][col] = a[row][col] + b[row][col];
			}
		}
		return c;
	}
	
	public static int[][] subtractArray(int[][] a, int[][] b)
	{
		c = new int[a.length][a[0].length];
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < a[row].length; col++)
			{
				c[row][col] = a[row][col] - b[row][col];
			}
		}
		return c;
	}
	
	public static int[][] multiplyArray(int[][] a, int[][] b)
	{
		// Validate matrix dimensions
		if (a == null || b == null) return null;
		if (a[0].length != b.length) throw new IllegalArgumentException("Incompatible matrix sizes for multiplication");

		d = new int[a.length][b[0].length];
		for (int row = 0; row < a.length; row++)
		{
			for (int col = 0; col < b[0].length; col++)
			{
				d[row][col] = multiplyArrayCell(a, b, row, col);
			}
		}
		return d;
	}
	
	public static int multiplyArrayCell(int[][] a, int[][] b, int row, int col)
	{
		cell = 0;
		for (int i = 0; i < b.length; i++)
		{
			cell += a[row][i] * b[i][col];
		}
		return cell;
	}
	
	public static int getNumberOfDigits(int n)
	{
		int p = 0;
		if (n == 1000)
		{
			p = 4;
		}
		
		else if (n >= 100 && n < 1000)
		{
			p = 3;
	    }
		
		else if (n >= 10 && n < 100)
		{
			p = 2;
		}
		
		return p;
	}
	
	public static double roundDigits(double x, int d)
	{
		return (Math.round(x*Math.pow(10, d))/Math.pow(10,d));
	}

	private static int promptInt(String message, String title, int min, int max)
	{
		while (true)
		{
			String s = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
			if (s == null) {
				JOptionPane.showMessageDialog(null, "Input cancelled. Please provide a value.", "Input Required", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			try
			{
				int v = Integer.parseInt(s.trim());
				if (v < min || v > max)
				{
					JOptionPane.showMessageDialog(null, "Please enter a value between " + min + " and " + max + ".", "Out of Range", JOptionPane.WARNING_MESSAGE);
					continue;
				}
				return v;
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}