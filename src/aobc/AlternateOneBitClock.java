import java.util.Random;

public class AlternateOneBitClock {
	private int TIME_TO_WAIT = 1000;
	private int clk;
	
	
	public void Init() {
		// Choose initial value for clock in [0,1]
		this.clk = new Random().nextInt(2);
		System.out.print(this.clk);
	}

	public void Next() {
		while(true) {
			if (this.clk == 0)
				this.clk = 1;
			else
				this.clk = 0;
			
			System.out.print(" -> " + this.clk);
			try{
				Thread.sleep(TIME_TO_WAIT);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public static void main(String args[]) {
		AlternateOneBitClock ac = new AlternateOneBitClock();
		ac.Init();
		ac.Next();
	}
}
