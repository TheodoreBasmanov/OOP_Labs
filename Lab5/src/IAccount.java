
public interface IAccount {
	void withdraw(double summ);
	void putIn(double summ);
	void transfer(double summ, int accountID);
}
