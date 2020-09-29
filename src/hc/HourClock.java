import java.util.Random;

// Run "java -ea" to enable assert() [Invariant()]

public class HourClock {
	private int hr;
	private int TIME_TO_WAIT = 1000;

	public void Invariant(int hour) {
		assert(hour >= 1 && hour <= 12);
	}

	public void Init() {
		// Choose initial value for clock in [1,12]
		hr = 1 + new Random().nextInt(12);
		Invariant(hr);
		System.out.print(hr);
	}

	public void Next() {
		while(true) {
			hr = (hr % 12) + 1;
			Invariant(hr);
                        
			System.out.print(" -> " + hr);
			try{
				Thread.sleep(TIME_TO_WAIT);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public static void main(String args[]) {
		HourClock hc = new HourClock();
		hc.Init();
		hc.Next();
	}
}
