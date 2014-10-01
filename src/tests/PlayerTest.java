package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import structs.Game;
import structs.Player;
import structs.Player.PlayerPrivilege;
import structs.Score;
import core.hibernate.HibernateUtil;

public class PlayerTest extends TestCase {
	static Session session;
	
	Player player;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {	
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		session.getTransaction().rollback();
	}

	
	@Test(expected = ObjectNotFoundException.class)
	public void testAddAndDeleteAPlayer() throws Exception {
		player = new Player("test");
		
		session.save(player);
			
		assertEquals(session.get(Player.class, player.getID()), player);
		
		session.delete(player);
		
		session.get(Player.class, player.getID());
	}
	
	@Test
	public void testChekingScoreSystem() {
		Player player = new Player("testPlayer"); //Create a simple user
		Player moderator = new Player("testModerator", PlayerPrivilege.MODERATOR); //Create a Moderator
		Game game = new Game("testGame"); //Create a game
		Score score = new Score(player, game, 1); //Create a score
		
		//Save all in database
		session.save(player);
		session.save(moderator);
		session.save(game);
		session.save(score);
		
		//Try to check the score with the player
		player.checkAScore(score);
		
		if(score.isCheckByModerator()) fail("The score should not be check by a simple player");
		
		//Try to check the score by the moderator
		moderator.checkAScore(score);
		
		if(!score.isCheckByModerator()) fail("The score shoul be check by moderator");
		
		//Try to save to database
		session.update(score);
		
		if(!((Score) session.get(Score.class, score.getScoreid())).isCheckByModerator()) fail("The value must be true");
	}
	
	@Test
	public void testPromotionSystem() {
		Player player = new Player("testPlayer"); //Create a simple player
		Player moderator = new Player("testModerator", PlayerPrivilege.MODERATOR); //Create a moderator
		Player administrator = new Player("testAdministrator", PlayerPrivilege.ADMINISTRATOR); //Create an administrator
		
		//Player try to promote himself
		player.promoteAPlayer(player, PlayerPrivilege.MODERATOR);
		
		if(player.getPrivilege() == PlayerPrivilege.MODERATOR) fail("Player couldn't promote an other player");
		
		//Moderator try to promote an other player
		moderator.promoteAPlayer(player, PlayerPrivilege.MODERATOR);
		
		if(player.getPrivilege() == PlayerPrivilege.MODERATOR) fail("Moderator couln't promote an other player");
		
		//Administrator try to promote an other player
		administrator.promoteAPlayer(player, PlayerPrivilege.MODERATOR);
		
		if(player.getPrivilege() != PlayerPrivilege.MODERATOR) fail("Administrator could promote an other player");
		
		//Try to save the player to database
		session.save(player);
		
		if(((Player) session.get(Player.class, player.getID())).getPrivilege() != PlayerPrivilege.MODERATOR) fail("Player privilege should be moderator");
	}
}
