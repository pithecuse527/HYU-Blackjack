package term_project;
import javax.swing.JOptionPane;

public class HumanPlayer extends CardPlayer {
	
	private String name;
	private int chips;
	
	public HumanPlayer(int max_cards, String n) {
		super(max_cards);
		name = n;
		chips = 0;
	}

	public boolean wantsACard() {
		String input = JOptionPane.showInputDialog("카드 드릴까요?");
		if (input.equals("Y") || input.equals("y"))
			return true;
		return false;
	}
	
	public void youWin() {
		chips += 1;
	}
	
	public void youWinBlackjack() {
		System.out.println("Black Jack! " + name + " wins!");
	}
	
	public void youLose() {
		chips -= 1;
	}
	
	public void youDraw() {
		
	}
	
	public int countChips() {
		return chips;
	}
	
	public String name() {
		return name;
	}
}
