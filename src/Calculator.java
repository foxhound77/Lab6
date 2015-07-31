import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Calculator extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField display;
	private char[] oper = {'/','*','-','+'};
	private int[] num = {7,8,9,4,5,6,1,2,3,0};
	private String value = "0";
	private String operator = "=";
	
	public Calculator(){
	
		JFrame window = new JFrame("Calculator");
	    window.setSize(425,425);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
		JPanel frame = new JPanel();
		frame.setLayout(new BorderLayout());
	    window.add(frame);
	    
	    display = new JTextField();
		display.setEditable(false);   
	    display.setFont(new Font("Courier", Font.BOLD, 36));
	    display.setHorizontalAlignment(JTextField.RIGHT);
	    display.setBackground(Color.WHITE);
	    
	    JPanel numPanel = new JPanel();
	    numPanel.setLayout(new GridLayout(4, 3));
	    numPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
	    
	    JPanel operPanel = new JPanel();
	    operPanel.setBorder(new EmptyBorder(10, 0, 10, 10) );
	    operPanel.setLayout(new BoxLayout(operPanel, BoxLayout.Y_AXIS));
	    
	    frame.add(display,BorderLayout.NORTH);
	    frame.add(numPanel,BorderLayout.CENTER);
	    frame.add(operPanel,BorderLayout.EAST); //Instead of using BoarderLayout.East you can use "East"
	   
	    for (int i = 0; i < num.length ; i++) {
			JButton b = new JButton(Integer.toString(num[i]));
			b.setFont(new Font("Courier", Font.BOLD, 18));
			numPanel.add(b);
			b.addActionListener(this);
		}
	    
	    for (int i = 0; i < oper.length ; i++) {
			JButton b1 = new JButton(Character.toString(oper[i]));
			b1.setFont(new Font("Courier", Font.BOLD, 45));
			operPanel.add(b1);
			operPanel.add(Box.createRigidArea(new Dimension(0,10)));
			b1.addActionListener(this);
		}
	    	    
	    JButton equal = new JButton("=");
	    equal.setFont(new Font("Courier", Font.BOLD,40));
		equal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a)
			{
				try{
					calculate(Double.parseDouble(value),Double.parseDouble(display.getText()));
					operator = "=";
				}catch(NumberFormatException e){
					display.setText("SYNTAX Error");
					System.err.println("Caught NumberFormatException: " + e.getMessage());
				}
			}
		});
		
		JButton clear = new JButton("C");
	    clear.setFont(new Font("Courier", Font.BOLD, 32));
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a)
			{
				display.setText("");
				value = "0";
				operator = "=";
			}
		});
	    
		numPanel.add(clear);
	    numPanel.add(equal);
	    
		window.getRootPane().setDefaultButton(equal);
	    window.setVisible(true);
	}//end constructor

	//Action event handler for all the buttons except for C and =
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
	    if((cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/")) && operator.equals("="))
	    {
	    	operator = cmd;
	    	value = display.getText();
	    	display.setText("");
	    }
	    else
	    {
	    	display.setText(display.getText() + cmd);
	    }
	}
	
	public void calculate(double m, double n)
	{
		double result;
		switch(operator){
			case "+" :	
				result = m + n;
				break;
			case "-" : 
				result = m - n;
				break;
			case "*" : 
				result = m * n;
				break;
			case "/" : 
				result = m / n;
				break;
			default : 
				result = n;
		}
		display.setText("" + result);
	}
	
  	public static void main(String[] args) {
  		Calculator calc = new Calculator();
  	}

}
