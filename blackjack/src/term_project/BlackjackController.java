package term_project;
import javax.swing.JOptionPane;

public class BlackjackController {
	private Dealer dealer;
	private HumanPlayer hand_player;
	private ComputerPlayer hand_dealer;
	private BlackjackFrame frame;
	private final int MAX_CARDS = 21;
	private final String NAME;
	
	BlackjackController(Dealer d) {
		dealer = d;
		NAME = JOptionPane.showInputDialog("Name?");
		hand_player = new HumanPlayer(MAX_CARDS, NAME);
		hand_dealer = new ComputerPlayer(MAX_CARDS);
		frame = new BlackjackFrame(this);
		firstGame();
		
	}
	
	public void firstGame() {	// also triggered by continue button
		hand_player.cardsReset(MAX_CARDS);
		hand_dealer.cardsReset(MAX_CARDS);
		
		// first round (give two cards each)
		dealer.dealOneTo(hand_player);
		dealer.dealOneTo(hand_dealer);
		dealer.dealOneTo(hand_player);
		dealer.dealOneTo(hand_dealer);

		if (hand_player.totalScore() == 21)	{ // lucky!
			hand_player.youWin();
			hand_player.youWin();
		}
		// show the result of the first game
		frame.getHitButton().setEnabled(true);
		frame.getStayButton().setEnabled(true);
		frame.update("");
		
	}
	
	public void hitted() {	// triggered by hit button
		if (hand_player.totalScore() < 21) {
			dealer.dealOneTo(hand_player);
			frame.update("");
		}
		
		if (hand_player.totalScore() == 21) {
			hand_player.youWin();
			hand_player.youWin();
			frame.getHitButton().setEnabled(false);
			frame.getStayButton().setEnabled(false);
			frame.update("Player");
		}
		else if (hand_player.totalScore() > 21) {
			hand_player.youLose();
			frame.getHitButton().setEnabled(false);
			frame.getStayButton().setEnabled(false);
			frame.update("Dealer");
		}
		
	}
	
	public void stay() {
		if (hand_player.totalScore() == 21) {
			hand_player.youWin();
			hand_player.youWin();
			frame.update("Player");
		}
		else if (hand_player.totalScore() > 21) {
			hand_player.youLose();
			frame.update("Dealer");
		}
		else {
			while (hand_dealer.totalScore() <= 16) {
				dealer.dealTo(hand_dealer);
			}
			if (hand_dealer.totalScore() > 21) {
				hand_player.youWin();
				frame.update("Player");
			}
			else if (hand_player.totalScore() > hand_dealer.totalScore()) {
				hand_player.youWin();
				frame.update("Player");
			}
			else if (hand_player.totalScore() == hand_dealer.totalScore()) {
				frame.update("Both");
			}
			else {
				hand_player.youLose();
				frame.update("Dealer");
			}
		}
		
		frame.getHitButton().setEnabled(false);
		frame.getStayButton().setEnabled(false);
	}
	
	public Card[] getCards(String s) {
		if (s.equals("player")) return hand_player.showCards();
		return hand_dealer.showCards();
	}
	
	public int playerChips() {
		return hand_player.countChips();
	}
	
	public String playerName() {
		return hand_player.name();
	}
	
}

