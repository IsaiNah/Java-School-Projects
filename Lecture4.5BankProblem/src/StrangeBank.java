// adapted from Paul Hydes's example

public class StrangeBank 
{

	private String currency;

	public StrangeBank() {
		currency = null;
	}

	public synchronized void deposit( String s ) throws InterruptedException {

		show( "in deposit: BEGINNING" );

		while ( currency != null ) {

			show( "in deposit: before wait()" );
			wait();
			show( "in deposit: after wait()");
		}

		currency = s;
		show( "in deposit: before notifyAll()" );
		notifyAll();
		show( "in deposit: after notifyAll()" );
		
		show( "in deposit: END");
	}

	public synchronized String withdraw()
						throws InterruptedException {

		show( "in withdraw: BEGINNING" );

		while ( currency == null ) {

			show( "in withdraw: before wait()" );
			wait();
			show( "in withdraw: after wait()" );
		}

		String s2 = currency;
		currency = null;

		show( "in withdraw: before notifyAll()" );
		notifyAll();
		show( "in withdraw: after notifyAll()" );
		
		show( "in withdraw: END" );
		return s2;
	}

	private static void show(String description) {

		String t = Thread.currentThread().getName();
		System.out.println( t + ": " + description );
	}
}