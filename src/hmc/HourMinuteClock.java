import java.util.Random;

// Run "java -ea" to enable assert() [Invariant()]

/* @author Adriano J. Holanda
   @license public domain
   
   Implementation of the specification HourMinuteClock.tla
*/

public class HourMinuteClock {
	private int hr, min;
	private int TIME_TO_WAIT = 1000;

	public void Invariant(int hour, int minute) {
		assert(hour >= 1 && hour <= 12);
		assert(minute >= 0 && minute <= 59);
	}

	public void Init() {
		// Choose initial value for clock in [1,12]:[0,59]
		hr = 1 + new Random().nextInt(12);
		min = 1 + new Random().nextInt(50);
		Invariant(hr, min);
		print(hr, min);
	}

	public void Next() {
		while(true) {
			min = (min + 1) % 60;
			if (min == 0)
				hr = (hr % 12) + 1;
			
			Invariant(hr, min);
			
			System.out.print(" -> ");
			print(hr, min);

			try{
				Thread.sleep(TIME_TO_WAIT);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	 private void print(int hr, int min) {
		 String[] pref = {"", ""};
		 int[] time = new int[]{hr, min};

		 for (int i=0; i<2; i++)
			 if (time[i] < 10)
				 pref[i] = "0";
		 
		 System.out.println(pref[0] + hr + ":" + pref[1] + min);
	 }
	
	public static void main(String args[]) {
		HourMinuteClock hmc = new HourMinuteClock();
		hmc.Init();
		hmc.Next();
	}
}
