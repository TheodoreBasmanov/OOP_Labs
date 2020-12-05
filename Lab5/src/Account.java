import java.util.ArrayList;

public class Account implements IAccount{
	static ArrayList<Account> accounts = new ArrayList<Account>();
	private static int ID = 1; 
	int id;
	double moneySumm;
	Client client;
	double percent;
	Account(Client client){
		id = ID;
		ID++;
		moneySumm = 0;
		accounts.add(this);
	}
	@Override
	public void withdraw(double summ) {
		
	}
	@Override
	public void putIn(double summ) {
		
	}
	@Override
	public void transfer(double summ, int accountID) {	
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Account)) {
			return false;
		}
		Account c = (Account) o;
		return id == c.id;
	}
	
}
