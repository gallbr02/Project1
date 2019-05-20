import java.util.Date;

public class Account {

	private int id = 0;
	private double balance = 0;
	private double annualInterestRate= 0;
	private Date dateCreated = new Date();
	
	public Account (){
		
	}
	public Account ( int id, double initialBalance){
		this.id= id;
		this.balance=balance;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public double getbalance(){
		return balance;
	}
	public void setBalance(double balance){
		this.balance=balance;
	}
	public double getAnnualInterestRate (){
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate){
		this.annualInterestRate = annualInterestRate;
	}
	public Date getDateCreated(){
		return dateCreated;
	}
	public double getMonthlyInterestRate(){
		return annualInterestRate/12;
	}
	public double getMonthlyInterest(){
		return balance * getMonthlyInterestRate();
	}
	public void withdraw(int amount){
		this.balance = balance - amount;
	}
	public void deposit(int amount1){
		this.balance = balance + amount1;
	}

}
