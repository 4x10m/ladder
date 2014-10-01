package tests;

import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import structs.Player;
import structs.Game;
import structs.Score;
import core.hibernate.HibernateUtil;
import junit.framework.TestCase;

public class ScoreTest extends TestCase {
	static Session session;
	
	Player player;
	Game game;
	Score score;
	
	@Before
	public void setUp() throws Exception {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		player = new Player("testPlayer");
		game = new Game("testGame");
		score = new Score(player, game, 1);
		
		session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		session.getTransaction().rollback();
	}

	@Test (expected=ObjectNotFoundException.class)
	public void testAddAndDeleteAScore() {
		session.save(score);
		
		System.out.println(String.format("Score chargé depuis la base de données: %s", session.get(Score.class, score.getScoreid())));
		
		session.delete(score);
	}
	
	@Test
	public void testEloCalculatingSystem() throws Exception {
		/*Game game = new Game("testGame");
		Player player = new Player("testPlayer");
		Score score = new Score(player, game, 0);
		
		player.setELO(900);
		
		session.save(player);
		session.save(game);
		session.save(score);
		
		//player.setELO(1200);
		
		session.getTransaction().commit();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();*/
		
		//Calcul the elo average of player whose played to this game
		/*Set<Score> scores = ;
		
		if(scores.size() > 0) {
			for(Score score : scores) moy = Math.addExact(moy, score.getPlayer().getELO());
			
			moy = Math.floorDiv(moy, scores.size());
		}
		else moy = 1000;*/
		
		System.out.println(Score.getNewELO(1200, 900, 0));
	}
}
