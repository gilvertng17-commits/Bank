import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class bank {
	
	private static HashMap<String, Integer> bankAccount = new HashMap<>();
	private static HashMap<String, Long> bankCard = new HashMap<>();
  	private static HashMap<String, Double> balance = new HashMap<>();
  	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static double total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));      	
		
		//Data Types
		String user;
		
		//Bank Greetings
		System.out.println("*******************");
		System.out.println("DANGAL GREETINGS!!!");
		System.out.println("*******************");
		
      	while(true) {
          System.out.print("[1]Register [2]Login [3}Exit: ");
		  user = reader.readLine();
          
          switch(user) {
              case"1":
                handleRegister();
                break;
              
              case"2":
              	handleLogin();
              	break;
              
              case"3":
              	return;
              	
              default:
              System.out.println("Invalid Input");
          }
          
        }
	}
	
	//Register
    private static void handleRegister() throws IOException {

      	//Data Types
      	String name;
      	double userBalance = 0;
      	int userAge;
      	int requiredAge = 21;
      	int cvv;
      	long cardNumber = 0;
      	
    try {
    //Age
    System.out.print("Your Age: ");
    userAge = Integer.parseInt(reader.readLine());
    
    	if (userAge >= requiredAge) {
    	
    	//Name, CVV
		System.out.print("Your Name: ");
      	name = reader.readLine();
		
		System.out.print("Create CVV: ");
		cvv = Integer.parseInt(reader.readLine());
		
		//random
    		Random random = new Random();
    	
		//Card Generator
		cardNumber = 100000000 + (Math.abs(random.nextLong()));
		bankAccount.put(name, cvv);
		bankCard.put(name, cardNumber);
		balance.put(name, userBalance);
		
		System.out.println("***********************");
		System.out.println("Bank Account Registered");
		System.out.println("***********************");
		} else {
			System.out.println("*****************************************");
			System.out.println("SORRY, The required age to register is 21");
			System.out.println("*****************************************");
		}
    	} catch (NumberFormatException e) {
    		System.out.println("INVALID");
    	}
	}
	private static void handleLogin() throws IOException {
		
		//Data types
		String user;
		String name = null;
		long cardNumber =0;
		int cvv = 0;
		double userBalance = 0;
		double balanceInput;
		boolean x = true;
		
		//Login
		try {
		System.out.print("Name: ");
		name = reader.readLine();
		
		System.out.print("CVV: ");
		cvv = Integer.parseInt(reader.readLine());
		} catch (Exception e) {
			System.out.println("INVALID");
		}
		
		if (bankAccount.containsKey(name) && bankAccount.get(name).equals(cvv)) {
			cardNumber = bankCard.get(name);
			userBalance = balance.get(name);
			
		while(x) {
		System.out.println("*******************************");
		System.out.println("Account Name: "+ name);
		System.out.println("Account Number: "+ cardNumber);
		System.out.println("Account Balance: $"+ userBalance);
		System.out.println("*******************************");
		System.out.println("[1]Deposit Money");
		System.out.println("[2]Withdraw Money");
		System.out.println("[3]Send Money");
		System.out.println("[4]Logout Bank");
		System.out.println("[5]Exit Bank");
		System.out.println("***********************");
		user = reader.readLine();
		
		switch(user) {
		
		//Deposit
		case"1":
			System.out.print("Input Money: $");
			balanceInput = Double.parseDouble(reader.readLine());
			
			//Arithmetic for balance account
			userBalance = balanceInput + userBalance;
			
			//Save
			balance.put(name, userBalance);
			break;
            
		//Withdraw
		case"2":
			System.out.print("Input Money: $");
			balanceInput = Double.parseDouble(reader.readLine());
			
			//Arithmetic for balance account
			userBalance = userBalance - balanceInput;
			
			//Save
			balance.put(name, userBalance);
			break;
		
		case"3":
			handleSendMoney(name);
			userBalance = balance.get(name);
			break;
			
		//Logout
        case"4":
            x = false;
            break;
            
        //Exit
        case"5":
        	System.out.println("THANKYOU");
        	System.exit(0);
        		
        default:
        		System.out.println("INVALID");
        	
		}
				}
		} else {
			System.out.println("*****************");
			System.out.println("Wrong Name or CVV");
			System.out.println("*****************");
			
		}
	}
	private static void handleSendMoney(String user) throws IOException {
		
		//Data types
		String receiver = null;
		long cardNumber;
		double inputMoney;
		double userBalance = balance.get(user);
		double receiverBalance;
		
		System.out.print("Name of Receiver: ");
		receiver = reader.readLine();
		
		System.out.print("Cardnumber of Receiver: ");
		cardNumber = Long.parseLong(reader.readLine());
		
		if (bankAccount.containsKey(receiver) && bankCard.get(receiver).equals(cardNumber)) {
			
			//Send
			try {
				System.out.print("Input Money: $");
				inputMoney = Double.parseDouble(reader.readLine());
				
				//Receiver Balance
				receiverBalance = balance.get(receiver);
				if (userBalance >= inputMoney) {
					
					//Arithmetic for transfer
					userBalance = userBalance - inputMoney;
					receiverBalance = inputMoney + receiverBalance;
					
					//Save Balance
					balance.put(user, userBalance);
					balance.put(receiver, receiverBalance);
					
					System.out.println("*************");
					System.out.println("TRANSFER DONE");
					System.out.println("*************");
					
				} else {
					System.out.println("INVALID AMOUNT");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("INVALID");
			}
			
		} else if (user.equals(receiver)) {
			System.out.println("***************************");
			System.out.println("YOU CANNOT SEND TO YOURSELF");
			System.out.println("***************************");
			
		} else {
			System.out.println("************************");
			System.out.println("WRONG NAME OR CARDNUMBER");
			System.out.println("************************");
		}
		
	}

	public static double getTotal() {
		return total;
	}

	public static void setTotal(double total) {
		bank.total = total;
	}
}
