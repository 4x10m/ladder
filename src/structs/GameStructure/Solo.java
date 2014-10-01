package structs.GameStructure;

import structs.Player;
import structs.Score;

public class Solo extends GameStructure {
	public Solo(Player player1, Score player1score, Player player2, Score player2score) {
		int newplayer1elo = calcul(player1.getELO(), player2.getELO(), player1score.getValue());
		int newplayer2elo = calcul(player2.getELO(), player1.getELO(), player2score.getValue());
		
		player1.setELO(newplayer1elo);
		player2.setELO(newplayer2elo);
	}
	
	private int calcul(int player1elo, int player2elo, int gamescore) {
		double l1 = player1elo - player2elo;
		double l2 = l1 / 400d;
		double l3 = Math.pow(10d, l2);
		double l4 = 1d +  l3;
		
		double exp = 1d / l4;
		
		double l5 = 15d * (Double.valueOf(gamescore) - exp);
		
		return Math.addExact(player1elo, (int) Math.round(l5));
	}
}
