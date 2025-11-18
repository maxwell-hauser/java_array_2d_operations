import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Array2DOperations extends Frame implements ActionListener
{
	static final long serialVersionUID = Integer.MAX_VALUE;
	String command = "About", found, row, col, key;
	static Font f0 = new Font("SansSerif", Font.BOLD, 12);
	static Font f1 = new Font("SansSerif", Font.BOLD, 16);
	static Font f2 = new Font("SansSerif", Font.BOLD, 20);
	static Font f3 = new Font("SansSerif", Font.BOLD, 24);

	private static int currentY;
	private int minimum;
	private int maximum;
	private double average, SD;
	private int[][] a = null, b = null, c = null;
	private String[] d = new String[4];
	
	public static void main(String[] args)
	{
		Frame frame = new Array2DOperations();
		frame.setResizable(true);
		frame.setSize(1000,800);
		frame.setVisible(true);
	}
    
	public Array2DOperations()
	{
		setTitle("2D Array Operations");
		
		MenuBar mb = new MenuBar();
		setMenuBar(mb);
		
		Menu fileMenu = new Menu("File");
		mb.add(fileMenu);
		
		MenuItem miAbout = new MenuItem("About");
		miAbout.addActionListener(this);
		fileMenu.add(miAbout);
		
		MenuItem miExit = new MenuItem("Exit");
		miExit.addActionListener(this);
		fileMenu.add(miExit);
		
		Menu actionMenu = new Menu("Two Dimensional Array");
		mb.add(actionMenu);
		
		MenuItem miCreate2D = new MenuItem("Create New Array");
		miCreate2D.addActionListener(this);
		actionMenu.add(miCreate2D);
		
		Menu statsMenu2D = new Menu("Statistics");
		actionMenu.add(statsMenu2D);
		
		MenuItem miArrayMin = new MenuItem("Array Minimum");
		miArrayMin.addActionListener(this);
		statsMenu2D.add(miArrayMin);

		MenuItem miArrayMax = new MenuItem("Array Maximum");
		miArrayMax.addActionListener(this);
		statsMenu2D.add(miArrayMax);
		
		MenuItem miArrayAvg = new MenuItem("Array Average");
		miArrayAvg.addActionListener(this);
		statsMenu2D.add(miArrayAvg);
		
		MenuItem miArraySD = new MenuItem("Array Standard Deviation");
		miArraySD.addActionListener(this);
		statsMenu2D.add(miArraySD);
		
		Menu opsMenu = new Menu("Operations");
		actionMenu.add(opsMenu);
		
		MenuItem miArraySearch = new MenuItem("Array Search");
		miArraySearch.addActionListener(this);
		opsMenu.add(miArraySearch);
		
		MenuItem miAdd = new MenuItem("Array Add");
		miAdd.addActionListener(this);
		opsMenu.add(miAdd);
		
		MenuItem miSubtract = new MenuItem("Array Subtract");
		miSubtract.addActionListener(this);
		opsMenu.add(miSubtract);
	
		MenuItem miMultiply = new MenuItem("Array Multiply");
		miMultiply.addActionListener(this);
		opsMenu.add(miMultiply);
			
		WindowListener l = new WindowAdapter()
		{					
			public void windowClosing(WindowEvent ev)
			{
				System.exit(0);
			}
			
			public void windowActivated(WindowEvent ev)
			{
				repaint();
			}
			
			public void windowStateChanged(WindowEvent ev)
			{
				repaint();
			}
		};
		
		ComponentListener k = new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e) 
			{
        		repaint();           
    		}
		};
		
		this.addWindowListener(l);
		this.addComponentListener(k);
	}
    
	public void actionPerformed (ActionEvent ev)
	{//Figure out which command was issued. Take action accordingly.
		
		command = ev.getActionCommand();
			
		switch(command)
		{
			case "About":
			{
				repaint();
				break;
			}
			
			case "Exit":
			{
				System.exit(0);
			}
			
			case "Create New Array":
			{
				a = TwoDArray.initializeArray(a, command);		
				repaint();
				break;
			}
			
			case "Array Minimum":
			{	
				a = TwoDArray.initializeArray(a, command);
				minimum = TwoDArray.getMin(a);
				repaint();
				break;
			}
			
			case "Array Maximum":
			{
				a = TwoDArray.initializeArray(a, command);
				maximum = TwoDArray.getMax(a);
				repaint();
				break;
			}
		
			case "Array Average":
			{
				a = TwoDArray.initializeArray(a, command);
				average = TwoDArray.getAvg(a);
				repaint();
				break;
			}
			
			case "Array Standard Deviation":
			{
				a = TwoDArray.initializeArray(a, command);	
				SD = TwoDArray.getSD(a);
				repaint();
				break;
			}
			
			case "Array Search":
			{
				a = TwoDArray.initializeArray(a, command);	
				d = TwoDArray.searchArray(a);
				found = d[0];
				key = d[1];
				row = d[2];
				col = d[3];
				repaint();
				break;
			}
			
			case "Array Add":
			{
				if (a == null)
				{
					a = TwoDArray.initializeArray(a, command);
					b = TwoDArray.initializeArray(b, command);
					c = TwoDArray.addArray(a, b);
				}
				
				else
				{
					b = TwoDArray.initializeArray(b, command);
					c = TwoDArray.addArray(a, b);	
				}
				repaint();
				break;
			}
			
			case "Array Subtract":
			{
				if (a == null)
				{
					a = TwoDArray.initializeArray(a, command);
					b = TwoDArray.initializeArray(b, command);
					c = TwoDArray.subtractArray(a, b);
				}
				
				else
				{
					b = TwoDArray.initializeArray(b, command);
					c = TwoDArray.subtractArray(a, b);	
				}
				repaint();
				break;
			}
			
			case "Array Multiply":
			{
				a = TwoDArray.initializeArray(a, command);
				b = TwoDArray.initializeArray(b, command);
				try {
					c = TwoDArray.multiplyArray(a, b);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(this,
						ex.getMessage(),
						"Matrix Dimension Error",
						JOptionPane.ERROR_MESSAGE);
				}
				repaint();
				break;
			}
		} //Switch
	} //Method
	
        
	public void paint(Graphics g)
	{//Check command issued. Take action accordingly.
		int ww = this.getWidth();
		switch(command)
		{
			case "About":
			{
				g.setFont(f3);
				g.drawString("2D Array Operations", (int)(ww/6), 50);
				g.setFont(f1);
				g.drawString("Create, analyze, and combine integer matrices.", (int)(ww/6), 80);
                
				g.drawLine(0, 110, (int)(ww), 110);
				g.drawLine(0, 111, (int)(ww), 111);
				g.drawLine(0, 112, (int)(ww), 112);

				g.setFont(f2);
				g.drawString("Statistics", (int)(ww/6), 150);
				g.setFont(f1);
				g.drawString("• Minimum / Maximum", (int)(ww/6), 175);
				g.drawString("• Average", (int)(ww/6), 195);
				g.drawString("• Standard deviation", (int)(ww/6), 215);

				g.setFont(f2);
				g.drawString("Operations", (int)(ww/6), 255);
				g.setFont(f1);
				g.drawString("• Search", (int)(ww/6), 280);
				g.drawString("• Add / Subtract", (int)(ww/6), 300);
				g.drawString("• Multiply (A×B)", (int)(ww/6), 320);
				break;
			}
		
			case "Create New Array":
			{	
				displayOriginalArray(g, ww);
				displaySingleArray(g, a, ww/2 - (10 * TwoDArray.getNumberOfDigits(TwoDArray.getHigh()) * a[0].length/2), 100);
				break;
			}
			
			case "Array Minimum":
			{
				displayOriginalArray(g, ww);
				currentY = displaySingleArray(g, a, ww/2 - (10 * TwoDArray.getNumberOfDigits(TwoDArray.getHigh()) * a[0].length/2), 100);
	
				g.setFont(f1);
				g.setColor(Color.RED);
				g.drawString("Array Minimum: " + minimum, ww/2 - 85, currentY + 25);
				break;
			}
			
			case "Array Maximum":
			{
				displayOriginalArray(g, ww);
				currentY = displaySingleArray(g, a, ww/2 - (10 * TwoDArray.getNumberOfDigits(TwoDArray.getHigh()) * a[0].length/2), 100);
				
				g.setFont(f1);
				g.setColor(Color.RED);
				g.drawString("Array Maximum: " + maximum, ww/2 - 85, currentY + 25);
				break;
			}
			
			case "Array Average":
			{
				displayOriginalArray(g, ww);			
				currentY = displaySingleArray(g, a, ww/2 - (10 * TwoDArray.getNumberOfDigits(TwoDArray.getHigh()) * a[0].length/2), 100);
	
				g.setFont(f1);
				g.setColor(Color.RED);
				g.drawString("Array Average: " + average, ww/2 - 85, currentY + 25);
				break;				
			}
			
			case "Array Standard Deviation":
			{
				displayOriginalArray(g, ww);
				currentY = displaySingleArray(g, a, ww/2 - (10 * TwoDArray.getNumberOfDigits(TwoDArray.getHigh()) * a[0].length/2), 100);
	
				g.setFont(f1);
				g.setColor(Color.RED);
				g.drawString("Array Deviation: " + SD, ww/2 - 85, currentY + 25);
				break;				
			}
			
			case "Array Search":
			{
				displayOriginalArray(g, ww);
				currentY = displaySingleArray(g, a, ww/2 - (10 * TwoDArray.getNumberOfDigits(TwoDArray.getHigh()) * a[0].length/2), 100);
				
				g.setFont(f1);
				g.setColor(Color.RED);
				
				if ("true".equals(found))
				{
					g.drawString("Key " + key + " found at [" + row + "][" + col + "]", ww/2 - 85, currentY + 25);
				}
				else
				{
					g.drawString("Key " + key + " not found.", ww/2 - 85, currentY + 25);
				}
				break;		
			}
			
			case "Array Add":
			{
				currentY = displayMultipleArrays(g, a, b, c, ww);
				g.drawString("+", ww/2 - 10, currentY - (20 * a.length/2));
				break;
			}
				
			case "Array Subtract":
			{
				currentY = displayMultipleArrays(g, a, b, c, ww);
				g.drawString("-", ww/2 - 10, currentY - (20 * a.length/2));
				break;
			}
			
			case "Array Multiply":
			{
				currentY = displayMultipleArrays(g, a, b, c, ww);
				g.drawString("*", ww/2 - 10, currentY - (20 * a.length/2));
				break;
			}
		}
	}
	
	public static void displayOriginalArray(Graphics g, int ww)
	{
		g.setFont(f3);
		g.setColor(Color.RED);
		g.drawString("Original Array", ww/2 - 90, 60);
		
		g.setFont(f0);
		g.setColor(Color.BLACK);
	}
	
	public static int displayMultipleArrays(Graphics g, int[][] a, int[][] b, int[][] c, int ww)
	{
		g.setFont(f0);
		g.setColor(Color.BLACK);
		currentY = displaySingleArray(g, a, ww/4 - (25 * a[0].length/2), 100);
				   displaySingleArray(g, b, 3*ww/4 - (25 * b[0].length/2), 100);
				   displaySingleArray(g, c, ww/2 - (25 * c[0].length/2), currentY + 50);	
		
		g.setFont(f3);
		g.setColor(Color.RED);
		g.drawString("A", ww/4 - 10, 80);
		g.drawString("B", 3*ww/4 - 10, 80);
		g.drawString("C", ww/2 - 10, currentY + 30);
		return currentY;
	}
	
	public static int displaySingleArray(Graphics g, int[][] a, int x, int y)
	{
		displayGrid(g, a, x, y);
		int x1 = x;
		for (int row = 0; row < a.length; row++)
		{
			x = x1;
			for (int col = 0; col < a[row].length; col++)
			{
				g.drawString(Integer.toString(a[row][col]), x, y);
				x += 30;
			}
			y+=20;
		}
		return y;
	}
	
	public static void displayGrid(Graphics g, int[][] a, int x, int y)
	{
		int y1 = y;
		for (int row = 0; row < a.length + 1; row++)
		{
			g.drawLine(x - 3, y - 15, (x - 3) + (30 * a[0].length), y - 15);
			y+=20;
		}
		
		y = y1;
		for (int col = 0; col < a[0].length + 1; col++)
		{
			g.drawLine(x - 3, y - 15, x - 3, (y - 15) + (20 * a.length));
			x += 30;
		}
	}
}