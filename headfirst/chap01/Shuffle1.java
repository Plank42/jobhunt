

class Shuffle1 {

	public static void main (String [] args) throws InterruptedException{
	
		int x = 3;

		while (x > 0) {

			if (x > 2) {
				System.out.print("a");
				Thread.sleep(1000);
			}
			if (x == 2) {
				System.out.print("b c");
				Thread.sleep(1000);
			}
			x = x-1; 
			System.out.print("-");			
			Thread.sleep(1000);
			if (x == 1) {
				System.out.print("d");
				x = x-1;
			}		
		}
		System.out.println("");	

	}
}