package tests;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import structs.Score;
import core.hibernate.HibernateUtil;

@RunWith(
	Suite.class
)
@SuiteClasses({
	PlayerTest.class,
	GameTest.class,
	ScoreTest.class
})
public class AllTest {
	static Session session;
	
	@BeforeClass
	public static void setUp() {
		HibernateUtil.getSessionWithTransaction().getTransaction().commit();
	}
	
	@After
	public void tearDown() {
		HibernateUtil.closeSessionFactory();
	}
}
