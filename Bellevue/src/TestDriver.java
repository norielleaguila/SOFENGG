import java.util.Scanner;

import test.CollectionModule;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class TestDriver {

	public static void main(String[] ARG) {
		Scanner sc = new Scanner(System.in);
		int input;
		
		do{
			System.out.println("MODULES");
			System.out.println(
							"[0] - Exit\n" + 
							"[1] - All\n" +
							"[2] - Collection\n" + 
							"\n"
					);
			
			System.out.print("Test module: ");
			input = sc.nextInt();
			
			test(input, ARG);
			
		}while(input != 0);
		
		sc.close();
	}
	
	public static void test(int module, String[] args){
		switch(module){
		case 0:
			System.out.println("Terminating test.");
			break;
		case 1:
			Driver.main(args);
			break;
		case 2:
			CollectionModule.main(args);
			break;
		}
	}

}
