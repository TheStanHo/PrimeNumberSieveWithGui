
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;


public class PrimeNumbers {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeNumbers window = new PrimeNumbers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrimeNumbers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Prime Number Sieve");
		frame.setBounds(100, 100, 609, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);;
		frame.setResizable(true);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblInsertMaxRange = new JLabel("Insert Max Range: ");
		lblInsertMaxRange.setBounds(92, 68, 233, 33);
		frame.getContentPane().add(lblInsertMaxRange);
		
		JLabel lblPrimeNumberSieve = new JLabel("Prime Number Sieve.");
		lblPrimeNumberSieve.setBounds(119, 0, 305, 33);
		frame.getContentPane().add(lblPrimeNumberSieve);
		
	
		
		textField = new JTextField();
		textField.setBounds(348, 65, 59, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSievecalculate = new JButton("Sieve(Calculate)");
		btnSievecalculate.addActionListener(new SieveButtonActionListener());
		btnSievecalculate.setBounds(154, 129, 239, 41);
		frame.getContentPane().add(btnSievecalculate);
		
		//Scrollpane sets the bounds for the paramter that is in its constructor
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 198, 577, 175);
		frame.getContentPane().add(scrollPane);
		
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText("Prime Numbers are: ");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		
	}
	
	private class SieveButtonActionListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			String valueFromUser = textField.getText();
			int value = Integer.parseInt(valueFromUser);
			
			int[] primeNumber = sieve(value);
			
		
				String stringOfPrime = Arrays.toString(primeNumber);
				System.out.println(stringOfPrime);
				textArea.setText("The Prime numbers are: " + stringOfPrime);
		}
		
	}
	
	public static int[] sieve(int max)  {
		boolean[] sieve = new boolean[max+1];
		
		sieve[0] = false; // we state that for index 0 and 1 
		sieve[1] = false; //it is always false as 0 and 1 not prime numbers
		
		// method below marks all the numbers true at the beginning
		for(int i = 2; i <= max; i++)  {
			sieve[i] = true;	
		}
		
		//for every multiple of i mark false
		for(int i = 2; i <= Math.sqrt(max); i++)  { 
			if(sieve[i] == true)  {
				for(int j = 2; j *i <= max; j++) { 
					sieve[j * i] = false; //every multiple of that increment e.g. at beginning is 2 will be marked false
				}						  //by definition of a prime number so will do 2 * 2 then 2 * 3 etc. 
			}
		}
		//count the number of prime numbers in the array
		int counter = 0; 
		for(int k = 0; k <= max; k++)  { // <= used so that it can add the max value also if it is a prime number
			if(sieve[k] == true)  {
				counter++;
			}
		}
		
	
		//Add true values to a new array
		int[] primeNumbers = new int[counter]; 
		for(int i = 0; i < primeNumbers.length; i++)  {
			for(int j = 0; j < sieve.length; j++)  {
				if(sieve[j]  == true)  {
					primeNumbers[i] = j;
					i++;//increment the index by 1
			}
			}
		}
		return primeNumbers;
	}
}
