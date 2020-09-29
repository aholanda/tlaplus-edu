import java.util.Scanner;

// run java -ae
// to enable the assertions

public class GCD {
	// VARIABLES
	private int x, y;

	// Initialization
	public void Init(int M, int N) {
		this.x = M;
		this.y = N;
	}

	// Next states for computation
	public int Next() {
		while(true) {
			if (x < y)
				y = y - x;
			else if (y < x)
				x = x - y;
			else { // x == y
				return x;
			}
		}
	}

	// Execution
	public static void main(String args[]) {
		int M, N; // CONSTANTS
		int xy;   // storage for the greatest common divisor of x and y
		GCD gcd = new GCD(); // GCD instance
		Scanner keyboard = new Scanner(System.in);

		// Get the value for M from keyboard
		System.out.print("Enter a positive integer M: ");
		M = keyboard.nextInt();
		assert(M>0);

		// Get the value for N from keyboard
		System.out.print("Enter a positive integer N: ");
		N = keyboard.nextInt();
		assert(N>0);

		gcd.Init(M, N);
		xy = gcd.Next();

		// print the greatest common divisor in the standard
		// output
		System.out.println("GCD(" + M + "," + N + ")=" + xy);
	}
}
