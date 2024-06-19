import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: ₹" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: ₹" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

public class Bank {
    private List<Account> accounts;
    private Scanner scanner;

    public Bank() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        accounts.add(new Account(accountNumber));
        System.out.println("Account created successfully.");
    }

    public void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ₹");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ₹");
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                System.out.println("₹" + amount + " withdrawn successfully.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer() {
        System.out.print("Enter sender's account number: ");
        String senderAccountNumber = scanner.next();
        Account senderAccount = getAccount(senderAccountNumber);
        if (senderAccount != null) {
            System.out.print("Enter recipient's account number: ");
            String recipientAccountNumber = scanner.next();
            Account recipientAccount = getAccount(recipientAccountNumber);
            if (recipientAccount != null) {
                System.out.print("Enter amount to transfer: ₹");
                double amount = scanner.nextDouble();
                if (senderAccount.withdraw(amount)) {
                    recipientAccount.deposit(amount);
                    System.out.println("₹" + amount + " transferred successfully.");
                }
            } else {
                System.out.println("Recipient's account not found.");
            }
        } else {
            System.out.println("Sender's account not found.");
        }
    }

    public void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = getAccount(accountNumber);
        if (account != null) {
            List<String> history = account.getTransactionHistory();
            System.out.println("Transaction History for Account " + accountNumber + ":");
            for (String transaction : history) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void viewAccountBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Balance for Account " + accountNumber + ": ₹" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private Account getAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 7) {
            System.out.println("\nWelcome to the Bank of Java");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. View Account Balance");
            System.out.println("7. Exit");
            System.out.print("Enter an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.deposit();
                    break;
                case 3:
                    bank.withdraw();
                    break;
                case 4:
                    bank.transfer();
                    break;
                case 5:
                    bank.viewTransactionHistory();
                    break;
                case 6:
                    bank.viewAccountBalance();
                    break;
                case 7:
                    System.out.println("Thank you for banking with us. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
        scanner.close();
    }
}