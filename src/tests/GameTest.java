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
import core.hibernate.HibernateUtil;

public class GameTest extends TestCase {
	static Session session;
	
	Game game = new Game("testGame");
	
	@BeforeClass
	public void setUp() throws Exception {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
	}

	@AfterClass
	public void tearDown() throws Exception {
		session.getTransaction().rollback();
	}

	@Test (expected = ObjectNotFoundException.class)
	public void testAddAndDeleteAGame() {
		session.save(game);
		
		System.out.println(String.format("Jeu chargé depuis la base de donnée: %s", (Game) session.get(Game.class, game.getID())));
		
		session.delete(game);
	}

}
