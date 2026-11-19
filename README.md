import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ATM variables
        int correctPIN = 1234;
        int enteredPIN;
        int attempts = 0;
        double balance = 1000;
        int choice;

        // Transaction history array
        String[] transactions = new String[100];
        int transactionCount = 0;

        // Login system
        while (attempts < 3) {

            System.out.print("Enter your PIN: ");
            enteredPIN = input.nextInt();

            if (enteredPIN == correctPIN) {
                System.out.println("Login successful!");
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN!");

                System.out.println("Attempts remaining: "
                        + (3 - attempts));
            }
        }

        // Check login attempts
        if (attempts == 3) {
            System.out.println("Your account is blocked!");
            input.close();
            return;
        }

        // Main ATM menu
        while (true) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Your balance is: "
                            + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double deposit = input.nextDouble();

                    if (deposit > 0) {
                        balance += deposit;

                        transactions[transactionCount] =
                                "Deposited: " + deposit;

                        transactionCount++;

                        System.out.println("Deposit successful!");
                        System.out.println("New balance: " + balance);
                    } else {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawal = input.nextDouble();

                    if (withdrawal <= 0) {
                        System.out.println("Invalid amount!");

                    } else if (withdrawal <= balance) {

                        balance -= withdrawal;

                        transactions[transactionCount] =
                                "Withdrawn: " + withdrawal;

                        transactionCount++;

                        System.out.println("Withdrawal successful!");
                        System.out.println("New balance: " + balance);

                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;

                case 4:
                    System.out.println("\nTRANSACTION HISTORY");

                    if (transactionCount == 0) {
                        System.out.println("No transactions yet.");
                    }

                    for (int i = 0; i < transactionCount; i++) {
                        System.out.println(transactions[i]);
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using our ATM!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
