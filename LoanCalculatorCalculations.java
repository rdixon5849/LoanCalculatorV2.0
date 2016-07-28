package application;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LoanCalculatorCalculations
{
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	// Method to calculate the value of the interest+the loan Principal
	public double calculateLoanTotal(double principal, double interestRate, double periods)
	{
		double r = interestRate/12;
		double payment = principal*(((r*Math.pow((1+r),periods))/(Math.pow((1+r),periods)-1)));
		return payment;
	}

	// Method to get the number of months of the loan
	public int findMonths(double years)
	{
		years = years * 12;
		return (int) Math.round(years);
	}

	/*
	 *  v1.1 edit: Method that outputs a message if the size is not appropriate.
	 */
	public boolean checkSize(double value, double size)
	{
		if (value >= size)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * v1.1 edit: method to check that any of the values do not equal zero or less
	 */
	public boolean checkZero(double value)
	{
		if(value<=0)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * v2.0 method to find each class month info
	 */
	public double[] findMonthInfo(double principal, int totalMonths, double interest, double monthNumber)
	{
		double[] answer = new double[4];
		double c = interest/12;
		double payment = principal*(((c*Math.pow((1+c),totalMonths))/(Math.pow((1+c),totalMonths)-1)));
		
		double r = payment/totalMonths;
		
		answer[0]=totalMonths;
		//amount paid to principal
		answer[1]=r*monthNumber;
		//amount paid to interest
		answer[2]=payment-answer[1];
		//amount remaining
		answer[3]=(payment*totalMonths)-(payment*monthNumber);
		
		for(int i =0; i<answer.length; i++)
		{
			String value = formatter.format(answer[i]);
			answer[i] = Double.parseDouble(value);
		}
		
		return answer;
	}
	
}
