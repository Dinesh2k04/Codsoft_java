import java.util.Scanner;
class BankAccount 
{
    private double balance;
    public BankAccount(double initialBalance) 
    {
        this.balance = initialBalance;
    }
    public double getBalance() 
    {
        return balance;
    }
    public void deposit(double amount)	
    {
        if (amount > 0) 
	{
            balance += amount;
            System.out.println("Deposit successful. Amount deposited: $" + amount);
        } 
	else 
	{
            System.out.println("Invalid amount. Deposit failed.");
        }
    }
    public void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
	{
            balance -= amount;
            System.out.println("Withdrawal successful. Amount withdrawn: $" + amount);
        } 
	else if (amount > balance) 
	{
            System.out.println("Insufficient balance. Withdrawal failed.");
        } 
	else 
	{
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }
}
public class ATM {
    private BankAccount account;
    public ATM(BankAccount account) 
    {
        this.account = account;
    }
    public void checkBalance() 
    {
        System.out.println("Your current balance is: $" + account.getBalance());
    }
    public void deposit(double amount) 
    {
        account.deposit(amount);
    }
    public void withdraw(double amount) 
    {
        account.withdraw(amount);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount(1000);
        ATM atm = new ATM(userAccount);
        while (true) 
	{
            System.out.println("\nWelcome to the ATM");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) 
	    {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Enter the valid option");
            }
        }
    }
}
