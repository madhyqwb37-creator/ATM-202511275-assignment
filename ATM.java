import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final int CORRECT_PIN = 6767;
        final int MAX_ATTEMPTS = 3;

        double balance = 0;
        String[] transactions = new String[100]; 
        int count = 0;

        
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < MAX_ATTEMPTS && !loggedIn) {
            System.out.print("Enter your PIN: ");
            int pin = input.nextInt();

            if (pin == CORRECT_PIN) {
                loggedIn = true;
                System.out.println("Login successful. Welcome!");
            } else {
                attempts++;
                System.out.println("Wrong PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Access denied. Too many failed attempts.");
            input.close();
            return;
        }

        // 2) Main menu: loop until the user chooses exit
        int choice;
        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction history");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current balance: " + balance);
                    break;

                case 2: {
                    System.out.print("Enter amount to deposit: ");
                    double deposit = input.nextDouble();
                    if (deposit <= 0) {
                        System.out.println("Invalid amount.");
                    } else {
                        balance += deposit;
                        transactions[count++] = "Deposit: " + deposit + " | Balance: " + balance;
                        System.out.println("Deposit successful. New balance: " + balance);
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = input.nextDouble();
                    if (withdraw <= 0) {
                        System.out.println("Invalid amount.");
                    } else if (withdraw > balance) {
                        System.out.println("Error: insufficient balance.");
                    } else {
                        balance -= withdraw;
                        transactions[count++] = "Withdrawal: " + withdraw + " | Balance: " + balance;
                        System.out.println("Withdrawal successful. New balance: " + balance);
                    }
                    break;
                }

                case 4:
                    if (count == 0) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("----- Transaction History -----");
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + ". " + transactions[i]);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        } while (choice != 5);

        input.close();
    }
}