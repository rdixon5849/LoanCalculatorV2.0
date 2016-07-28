package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LoanCalculatorTable
{
	@FXML
	TableView<LoanProperties> month;	
	@FXML
	TableColumn<LoanProperties, Integer> monthNumber;	
	@FXML
	TableColumn<LoanProperties, Double> interestPayment;	
	@FXML
	TableColumn<LoanProperties, Double> principalPayment;	
	@FXML
	TableColumn<LoanProperties, Double> remainingLoan;
}
