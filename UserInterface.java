package Project4;

import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How to Play Go Fish(You VS Computer: \n"
				+ "After you enter in 'y' for yes, you and your opponent will be given seven cards each. \n"
				+ "You will go first by entering a rank (ace, two, three, etc.) and a suit (hearts, club, spade, etc.). \n"
				+ "If the computer does not have the card, the computer will ask for a card and update whether or not you have the card. \n"
				+ "Else, you will keep asking the computer for cards until the computer doesn't have any or until you have no cards. \n"
				+ "The game will continue until all pairs are made.");
		System.out.println("Do you want to play GoFish? y/n");
		String ans = sc.next();
		
		while(ans.equalsIgnoreCase("y")) {
			GoFish newGame = new GoFish();
			newGame.setUp();
			System.out.println("\nYour current hand: ");
			newGame.getYourHand().printList();
			newGame.checkForPairsUser();
			newGame.checkForPairsOpponent();
			
			//finding pairs
			while(newGame.getDeck().getLength() != 0) {
				//if user hand empty
				if(newGame.getYourHand().getLength() == 0) {
					for(int z = 0; (z < 8) && newGame.getDeck().getLength() != 0; z++) {
						Card c = newGame.getDeck().remove();
						newGame.getYourHand().add(c);
						newGame.checkForPairsUser();
					}
				}
				
				//if opponent hand empty
				if(newGame.getOpponentHand().getLength() == 0) {
					for(int z = 0; (z < 8) && newGame.getDeck().getLength() != 0; z++) {
						Card c = newGame.getDeck().remove();
						newGame.getOpponentHand().add(c);
						newGame.checkForPairsOpponent();
					}
				}
				
				//user goes first
				else {
					//user's turn
					boolean userTurn = true;
					Card c = null;
					while(userTurn && newGame.getYourHand().getLength() != 0) {
						c = null;
						while(c == null) {
							System.out.println("Choose a card to ask for.. ");
						
							//rank
							System.out.println("Rank (in words): ");
							String rank = sc.next();
						
							//suit
							System.out.println("Suit (in words): ");
							String suit = sc.next();
						
							c = newGame.newCardInput(rank, suit);
						}
						userTurn = newGame.userAsk(c);
					}
					
					//opponent's turn
					boolean turn = true;
					while(turn && newGame.getYourHand().getLength() != 0 && newGame.getOpponentHand().getLength() != 0) {
						turn = newGame.opponentAsk();
						if(newGame.getYourHand().getLength() == 0 || newGame.getOpponentHand().getLength() == 0) {
							if(newGame.getYourHand().getLength() == 0) {
								for(int i = 0; i < 8 && newGame.getDeck().getLength() != 0; i++) {
									Card r = newGame.getDeck().remove();
									newGame.getYourHand().add(r);
								}
							}
							if(newGame.getOpponentHand().getLength() == 0) {
								for(int i = 0; i < 8 && newGame.getDeck().getLength() != 0; i++) {
									Card r = newGame.getDeck().remove();
									newGame.getOpponentHand().add(r);
								}
							}
						}
					}
				}
				
			}
			
			while(newGame.getYourHand().getLength() != 0 && newGame.getOpponentHand().getLength() != 0) {
				boolean userTurn = false;
				Card c = null;
				while(userTurn && newGame.getYourHand().getLength() != 0) {
					c = null;
					while(c == null) {
						System.out.println("Choose a card to ask for.. ");
					
						//rank
						System.out.println("Rank (in words): ");
						String rank = sc.next();
					
						//suit
						System.out.println("Suit (in words): ");
						String suit = sc.next();
					
						c = newGame.newCardInput(rank, suit);
					}
					userTurn = newGame.userAsk(c);
				}
					
				//opponent's turn
				boolean turn = true;
				while(turn && newGame.getYourHand().getLength() != 0 && newGame.getOpponentHand().getLength() != 0) {
					turn = newGame.opponentAsk();
					if(newGame.getYourHand().getLength() == 0 || newGame.getOpponentHand().getLength() == 0) {
						if(newGame.getYourHand().getLength() == 0) {
							for(int i = 0; i < 8 && newGame.getDeck().getLength() != 0; i++) {
								Card r = newGame.getDeck().remove();
								newGame.getYourHand().add(r);
							}
						}
						if(newGame.getOpponentHand().getLength() == 0) {
							for(int i = 0; i < 8 && newGame.getDeck().getLength() != 0; i++) {
								Card r = newGame.getDeck().remove();
								newGame.getOpponentHand().add(r);
							}
						}
					}
				}
			}
			
			//ending game
			if((newGame.getOpponentPairs().getLength()/2) > (newGame.getYourPairs().getLength()/2)){
				System.out.println("Your opponent has won the game!");
			}
			if((newGame.getOpponentPairs().getLength()/2) < (newGame.getYourPairs().getLength()/2)){
				System.out.println("You have won the game!");
			}
			if((newGame.getOpponentPairs().getLength()/2) < (newGame.getYourPairs().getLength()/2)) {
				System.out.println("It is a tie!");
			}
			System.out.println("Do you want to play GoFish again? y/n");
			ans = sc.next();
			
		}
		
		
		sc.close();

	}

}
