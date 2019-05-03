/* Jasmin Agustin
 * CECS 274 - 05
 * Project 5 - Binary Search Tree
 * 12/8/2016
 */

import java.util.Random;
import java.util.Scanner;
public class BinarySearchTree {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		BST myTree = new BST();
		int userInput = 0;
		while (userInput != 'Q' && userInput != 'q'){
			myTree.printTree();
			myTree.printStatistics();
			userInput = Menu();
			switch(userInput){
				case 'A':
				case 'a':
					System.out.println("Please enter the number you would like to add.");
					int addN = keyboard.nextInt();
					myTree.add(addN);
					break;
				case 'B':
				case 'b':
					myTree.balance();
					break;
				case 'D':
				case 'd':
					System.out.println("Please enter the number you would like to delete.");
					int deleteN = keyboard.nextInt();
					myTree.delete(deleteN);
					break;
				case 'F':
				case 'f':
					System.out.println("Please enter the number you are trying to find.");
					int findN = keyboard.nextInt();
					myTree.find(findN);
					break;
				case 'I':
				case 'i':
					for (int i = 1; i <= 31; i++ ){
						myTree.add(intGenerator());
					}
					myTree.balance();
					break;
				case 'N':
				case 'n':
					myTree.newTree();
					break;
				case 'Q':
				case 'q':
					System.out.println("You have chosen to quit the code.");
					break;
				default:
					System.out.println("Error. Invalid input please try again.");
					break;
			}
		}
		
	}//end of main
	
	public static char Menu(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nMenu:\n"
			+ "(A)dd Item\n"
			+ "(B)alance Tree\n"
			+ "(D)elete Item\n"
			+ "(F)ind Item\n"
			+ "(I)nitialize Tree\n"
			+ "(N)ew Tree\n"
			+ "(Q)uit\n");
		char UserInput = keyboard.next().charAt(0);
		return UserInput;
	}
	
	public static int intGenerator(){
		Random rand = new Random();
		int number = rand.nextInt(1000) + 100;
		return number;
	}
	
}//end of class
