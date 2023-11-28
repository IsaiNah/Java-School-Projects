// adapted from Paul Hyde's example 

public class StrangeBankTester {

    private static StrangeBank buffer = new StrangeBank();
	
	  private static void show( String s ) {
	
		String threadName = Thread.currentThread().getName();
		System.out.println( threadName + ": " + s );
	}

	public static void main( String[] args ) {
	
		Runnable r1 = new Runnable() {
		
				public void run() {
					try {
						String str = "ONE DOLLAR";
						
						Thread.sleep(450);

						buffer.deposit(str);
						show( "First deposit: >>>>>>> " + str );

						str = "TWO EUROS";
						buffer.deposit(str);
						show( "Second deposit: >>>>>>> " + str );

						str = "THREE POUNDS";
						buffer.deposit(str);
						show( "Third deposit: >>>>>>> " + str );
						
					} catch ( InterruptedException ie ) { ie.printStackTrace(); }
				}
			};

		Runnable r2 = new Runnable() {
				public void run() {
					try {
						String data;

						data = buffer.withdraw();
						show( "First withdraw: <<<<<<<< " + data );

						Thread.sleep(450);

						data = buffer.withdraw();
						show( "Second withdraw: <<<<<<<< " + data );

						data = buffer.withdraw();
						show( "Third withdraw: <<<<<<<< " + data );
						
					} catch ( InterruptedException ie ) { ie.printStackTrace(); }
				}
			};

		Thread t1 = new Thread( r1, "You"),
		              t2 = new Thread( r2, "Your friend");
			   
		t2.start();
		t1.start();
	}
}

  

