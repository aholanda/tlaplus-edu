import java.util.Scanner;
import java.util.Random;

/*
  @author Adriano J. Holanda
  @license public domain

  This program is the implemention of the specification
  MoneyTransfer.tla. It uses threads to achieve the concurrent
  behavior.
*/

class Account {
	private int balance;

	public Account(int bal) {
		this.balance = bal;
	}

	public int getBalance() {
		return this.balance;
	}
	
	public int increase(int howMuch) {
		this.balance += howMuch;

		return this.balance;
	}

	public int decrease(int howMuch) {
		int bal = this.balance;

		bal -= howMuch;
		if (bal < 0)
			return -1;
		else
			this.balance = bal;

		return this.balance;
	}

	public String toString() {
		return " " + this.balance + " "; 
	}
}

class Operation implements Runnable {
	private Thread t;
	private String type;
	private Account acc;
	private int money;
	static String pc = "SUB";
	
	public Operation(String type, Account acc, int money) {
		this.type = type;
		this.acc = acc;
		this.money = money;
	}
   
	public void start () {
		System.out.println("Starting " +  type);
		if (t == null) {
			t = new Thread (this, type);
			t.start ();
		}
	}

	// Thread.join() is not used because the operations could not
	// be in the same program. They rely on some form of
	// communication like pc to handle concurrency.
	public void run() {
		int tm = 1 + new Random().nextInt(1000); // [1,1000]
		int ret;
		System.out.println("Running " +  type + " " + money + " after " + tm + "ms");
		try {
			while(true) {
				Thread.sleep(tm);
				if (type.equals("Subtract") && pc.equals("SUB")) {
					System.out.println("SubSUB" +  type + " " + money);
					ret = acc.decrease(money);
					if (ret == -1) {
						System.out.print("ERROR: \"Subtract\" operation failed, reason: not enough funds.");
						System.out.println(" Operation \"Add\" cancelled.");
						pc = "DONE";
						break;
					}
					pc = "ADD";
					break;
				} else if (type.equals( "Add") && pc.equals("ADD")) {
					acc.increase(money);
					pc = "DONE";
					break;
				}  else {
					if (pc == "DONE")
						break;
				}
			}
			
		} catch (InterruptedException e) {
			System.out.println("Thread " +  type + " interrupted.");
		}
		System.out.println("Thread " +  type + " exiting.");
	}
}

public class MoneyTransferTLA {
	Account accA, accB;
	int money;
	
	public void Init() {
		int b; // temporary variable
		Scanner keyboard = new Scanner(System.in);
		
		// Get the value for A account balance from keyboard
		System.out.print("Enter the integer balance for account A: ");
		b = keyboard.nextInt();
		accA = new Account(b);
		//assert(b>0);

		// Get the value for B account balance from keyboard
		System.out.print("Enter the integer balance for account B: ");
		b = keyboard.nextInt();
		accB = new Account(b);
		//assert(b>0);
		
		// Get the value to transfer from A to B
		System.out.print("Enter the integer quantity to transfer from A to B: ");
		b = keyboard.nextInt();
		money = b;
		//assert(b>=0);
	}

	public void Next() {
		Operation A = new Operation("Add", accA, money);
		A.start();
		Operation B = new Operation("Subtract", accB, money);
		B.start();
	}

	public static void main(String args[]) {
		MoneyTransfer mt = new MoneyTransfer();
		mt.Init();
		mt.Next();
		
		// Wait for threads for termination
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}

		int tot = mt.accA.getBalance() + mt.accB.getBalance();
		System.out.println(" A=" + mt.accA + "B=" + mt.accB + " TOTAL=" + tot);
	}
}
