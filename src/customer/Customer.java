package customer;

public class Customer 
{
	private String cID;
	private String cName;
	private double balance;
	private int point;

	public Customer(String cID, String cName, double balance, int point)
	{
		this.cID = cID;
		this.cName = cName;
		this.balance = balance;
		this.point = point;
	}

	public String getcID() {
		return cID;
	}

	public String getcName() {
		return cName;
	}

	public double getBalance() {
		return balance;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	/*
	 * shown when new transaction after login in successfully
	 */
	public void printDetails()
	{		
		System.out.printf("\n"
						+ "Welcome, %s \n"
						+ "Your current credit = $%.2f \n"
						+ "Your current loyalty point = %d \n"
						+ "\n", cName, balance, point);
	}	
	
	/*
	 * when pay
	 * check with balance
	 * reduce balance accordingly when payment successful
	 */
	public boolean paid(double amt)
	{
		if(balance < amt)
			return false;
		else
		{
			balance -= amt;
			return true;
		}
	}
	
	/*
	 * called by sale staff
	 * top up balance
	 * 1. anytime
	 * 2. payment not enough credit
	 * prints result
	 */
	public void topUp(double amt)
	{
		balance += amt;
		System.out.printf("\nTop up successfully! New Balance = $%.2f \n\n", balance);
	}
}