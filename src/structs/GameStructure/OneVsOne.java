package structs.GameStructure;

import java.util.List;
import java.util.Set;

import core.hibernate.HibernateUtil;
import structs.Game;
import structs.Player;
import structs.Score;

public class OneVsOne extends PartyStructure {
	public OneVsOne(Player player1, Score player1score, Player player2, Score player2score) {
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
	
	public static Object[][] getAll1Vs1Game() {
		List<Game> games;
		Object[][] result;
		
		//Load data from database
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		
		games = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Game.class).list();
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		
		//Convert data to 2d table
		result = new Object[games.size()][3];
		
		for(int i = 0; i < games.size(); i++) {
			result[i][0] = String.valueOf(games.get(i).getName());
			result[i][1] = String.valueOf(games.get(i).getBetterPlayer().getNickname());
			result[i][2] = String.valueOf(games.get(i).getBetterPlayer().getELO());
		}
		
		return result;
	}
}
