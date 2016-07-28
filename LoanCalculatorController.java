package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import application.LoanCalculatorCalculations;

public class LoanCalculatorController extends LoanCalculatorMenu implements Initializable
{
	//variables to control max inputs of the calculator
	private static final double LOANMAX = 100000000000.00;
	private static final double INTERESTMAX = 1.0;
	private static final int YEARMAX = 151;
	
	//to format the output value
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	//call classes
	LoanCalculatorMenu menu = new LoanCalculatorMenu();
	LoanCalculatorCalculations calc = new LoanCalculatorCalculations();
	LoanCalculatorTable tbl = new LoanCalculatorTable();
	
	/*
	 *  Variable to be accessible in the code
	 *  v1.1 edit: changed years to double to process values with decimals
	 */
	private double loanAmount;
	private double loanInterest;
	private double years;

	// the fxml elements
	@FXML
	Button calcButton;
	@FXML
	TextField dollarAmount;
	@FXML
	TextField yearAmount;
	@FXML
	TextField interestAmount;
	@FXML
	Label outputValue;
	@FXML
	MenuBar mBar;
	@FXML
	Menu fileMenu;
	@FXML
	MenuItem close;
	@FXML
	Menu helpMenu;
	@FXML
	MenuItem about;
	@FXML
	MenuItem clear;
	
	@FXML
	TableView<LoanProperties> monthInfo;
	@FXML
	TableColumn<LoanProperties, Integer> monthNumber;	
	@FXML
	TableColumn<LoanProperties, Double> interestPayment;	
	@FXML
	TableColumn<LoanProperties, Double> principalPayment;	
	@FXML
	TableColumn<LoanProperties, Double> remainingLoan;
	
	ObservableList<LoanProperties> data = FXCollections.observableArrayList(new LoanProperties(0,0.0,0.0,0.0));	

	// Method to set the values of these to doubles rather than strings
	private void assignValues()
	{
		/*
		 * v1.1 edit: Set this if to catch for null strings rather than use an
		 * exception that would close the program
		 */
		if (!dollarAmount.getText().isEmpty() && !yearAmount.getText().isEmpty() && !interestAmount.getText().isEmpty())
		{
			outputText();
		}
		else
		{
			outputValue.setText("One or more of your fields is blank!");
		}
	}

	/*
	 * V1.1 edit: method that writes out the information for the user to use
	 */
	private void outputText()
	{
		// Because of the NumberTextField class, there is no concern about
		// these values being anything other than numbers and periods
		setLoanAmount(Double.parseDouble(dollarAmount.getText()));
		setYears(Math.round(Double.parseDouble(yearAmount.getText())));
		setLoanInterest(Double.parseDouble(interestAmount.getText()));
		
		// must reset outputValue's text because it is concatenated later.
		outputValue.setText("");

		//check the different fields for emptiness
		if (calc.checkSize(getLoanAmount(), LOANMAX)||calc.checkZero(getLoanAmount()))
		{
			outputValue.setText("Your Loan Amount is invalid.\n");
		}
		if (calc.checkSize(getYears(), YEARMAX)||calc.checkZero(getYears()))
		{
			outputValue.setText(outputValue.getText() + "The loan time is invalid.\n");
		}
		if (calc.checkSize(getLoanInterest(), INTERESTMAX)||calc.checkZero(getLoanInterest()))
		{
			outputValue.setText(outputValue.getText() + "The interest rate is invalid.");
		}

		if (outputValue.getText() == "")
		{
			// after this years is changed to months that is why the new name
			double value = calc.calculateLoanTotal(getLoanAmount(), getLoanInterest(), calc.findMonths(getYears()));
			outputValue.setText("Your total is: $" + formatter.format(value) + " per month.");
		}
	}

	// Method to perform the functionality of the button clicking.
	public void buttonClick(ActionEvent event)
	{
		assignValues();
		fillTable();
	}
	
	//Method to fill the table with the information that is given from the month to month
	private void fillTable()
	{		
		data.clear();
		int months = (int)calc.findMonths(getYears());
		for(int i = 1; i<months+1; i++)
		{
			double[] fancyData = new double[4];
			fancyData = calc.findMonthInfo(getLoanAmount(), months, getLoanInterest(), i);
			data.add(new LoanProperties(i,fancyData[2],fancyData[1],fancyData[3]));
		}
		monthInfo.setItems(data);
	}

	public void closeClick(ActionEvent event)
	{
		menu.closeClick(event);
	}
	
	public void aboutClick(ActionEvent event)
	{
		menu.aboutClick(event);		
	}
	
	public void clearFields(ActionEvent event)
	{
		data.clear();
		outputValue.setText("");
		dollarAmount.setText("");
		yearAmount.setText("");
		interestAmount.setText("");
		monthInfo.setItems(data);
	}

	// accessors and mutators
	public double getLoanAmount()
	{
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount)
	{
		this.loanAmount = loanAmount;
	}

	public double getLoanInterest()
	{
		return loanInterest;
	}

	public void setLoanInterest(double loanInterest)
	{
		this.loanInterest = loanInterest;
	}

	public double getYears()
	{
		return years;
	}

	public void setYears(double years)
	{
		this.years = years;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		assert calcButton != null : "fx:id=\"beginButton\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert dollarAmount != null : "fx:id=\"beginButton\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert yearAmount != null : "fx:id=\"beginButton\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert interestAmount != null : "fx:id=\"beginButton\" was not injected: check your FXML file 'LoanView.fxml'.";
		assert outputValue != null : "fx:id=\"beginButton\" was not injected: check your FXML file 'LoanView.fxml'.";

		monthNumber.setCellValueFactory(new PropertyValueFactory<LoanProperties, Integer>("monthNumber"));	
		interestPayment.setCellValueFactory(new PropertyValueFactory<LoanProperties, Double>("interestPayment"));
		principalPayment.setCellValueFactory(new PropertyValueFactory<LoanProperties, Double>("principalPayment"));
		remainingLoan.setCellValueFactory(new PropertyValueFactory<LoanProperties, Double>("remainingLoan"));
		
		System.out.println(this.getClass().getSimpleName() + ".initialize");
		
	}

}
