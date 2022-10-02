

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class App {
    public static void main(String[] args) {
        User user = null;

        boolean stayInLoop = true;

        while(stayInLoop){

            if(user == null){
                try {
                    user = signInOrCreateNewUSer();
                }catch(IOException exception){
                    System.out.println("There was an error creating while your account! Try again..");
                    continue;
                }
            }

            displayOptions(user);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            try{
                String res = bufferedReader.readLine();
          
                
            switch(res){
                case "1":
                    boolean depositeSuccessful = false;
                    while(!depositeSuccessful){
                        try{
                        System.out.println("How much amount would you like to add ");
                        String depositeMoney = bufferedReader.readLine();
    
                        Float convertedDepositeMoney = isvalidNumber(depositeMoney);
                        if(convertedDepositeMoney != null){
                            user.depositeMoney(convertedDepositeMoney);
                            displayOptions(user);
                            depositeSuccessful = true;
                        } else {
                            System.out.println("That's not a valid number");
                        }
                    }
                    catch(Exception exception){
                        System.out.println("Error while reading the data.");
                    }
                }
                    break;
                case "2":
                    boolean withdrawSuccessful = false;
                    while(!withdrawSuccessful){
                       try{
                         System.out.println("How much money do you want to withdraw..");
                        String withdrawMoney = bufferedReader.readLine();

                        Float convertedWithdrawMoney = isvalidNumber(withdrawMoney);
                        if(convertedWithdrawMoney != null){
                            user.withdrawMoney(convertedWithdrawMoney);
                            displayOptions(user);
                            withdrawSuccessful = true;
                        } else {
                            System.out.println("That's not a valid number");
                        }
                    }
                    catch (Exception exception){
                        System.out.println("Error while reading the data.");
                    }
                    }
                    break;
                case "3":
                    displayAccountBalance(user);
                    break;
                case "4":
                    stayInLoop = false;
                    break;

                default:
                    System.out.println("Invalid Choice, please TRY AGAIN...");
                    break;
                
            }
        } catch(Exception exception){
            System.out.println("Error while reading the data.");
        }
        }
    }

    private static User signInOrCreateNewUSer() throws IOException{
        System.out.println("Welcome to the Bank Of Java...!");
        System.out.println("Let's get started and create an account for you.");
        System.out.println("Your First Name : ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstName = reader.readLine();

        System.out.println("Your Last Name : ");
        String lastName = reader.readLine();

        System.out.println("Your email address : ");
        String email = reader.readLine();

        String pin = "";
        boolean invalidPin = false;
        while(!invalidPin){
            System.out.println("Make your PIN (4 Digits only)");
            pin = reader.readLine();

            if(pin.length() == 4){
                Float floatpin = isvalidNumber(pin);
                if(floatpin == null){
                    System.out.println("PIN is Inavalid ");
                } else {
                    invalidPin = true;
                }
            } else {
                System.out.println("PIN was not four digits. TRY AGAIN ");
            }
        }

        User user = new User(firstName, lastName, pin, email, 0f);

        System.out.println("Would you like make an initial deposite? Type yes if you want to ");
        String response = reader.readLine();

        if("yes".equalsIgnoreCase(response)){
            System.out.println("Great, How much money do you want deposite");
            String amount = reader.readLine();
            Float convertedAmount = isvalidNumber(amount);

            if(convertedAmount != null){
                user.depositeMoney(convertedAmount);
            } else {
                System.out.println("That's not a valid number!");
            }
        }

        return user;
    }

    private static Float isvalidNumber(String value){
        try {
            return Float.parseFloat(value);
        } catch(Exception e){
            return null;
        }
    }

    public static void displayOptions(User user){
        System.out.println("Hello " + user.getFirstName() + " What can Bank Of Java do for you?");
        System.out.println("1. Deposite");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("Exit");
        System.out.println("Enter your choice");

    }

    public static void displayAccountBalance(User user){
        System.out.println("Your updated balance is Rs." + user.getAccountBalance() + "/-");
    }
}
