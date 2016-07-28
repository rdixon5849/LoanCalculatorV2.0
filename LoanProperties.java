package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class LoanProperties
{
	private final SimpleIntegerProperty monthNumber;
	private final SimpleDoubleProperty interestPayment;
	private final SimpleDoubleProperty principalPayment;
	private final SimpleDoubleProperty remainingLoan;

	public LoanProperties(int month, double interestPayment, double principalPayment, double remainingLoan)
	{
		this.monthNumber = new SimpleIntegerProperty(month);
		this.interestPayment = new SimpleDoubleProperty(interestPayment);
		this.principalPayment = new SimpleDoubleProperty(principalPayment);
		this.remainingLoan = new SimpleDoubleProperty(remainingLoan);
	}

	public Integer getMonthNumber()
	{
		return monthNumber.get();
	}

	public Double getInterestPayment()
	{
		return interestPayment.get();
	}

	public Double getPrincipalPayment()
	{
		return principalPayment.get();
	}

	public Double getRemainingLoan()
	{
		return remainingLoan.get();
	}

}
