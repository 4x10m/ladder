package structs;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="scores")
public class Score {
	@Id @GeneratedValue
	int scoreid;
	
	@ManyToOne
	private Player fk_player;
	
	@ManyToOne
	private Game fk_game;
	
	private Timestamp timestamp;
	private int value;
	private boolean checkedByModerator;
	
	public Player getPlayer() {
		return fk_player;
	}
	public void setPlayer(Player player) {
		this.fk_player = player;
	}
	public Game getGame() {
		return fk_game;
	}
	public void setGame(Game game) {
		this.fk_game = game;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getScoreid() {
		return scoreid;
	}
	
	public void checkByModerator() {
		checkedByModerator = true;
	}
	
	public boolean isCheckByModerator() {
		return checkedByModerator;
	}
	
	public Score(Player player, Game game, int value) {
		this.fk_player = player;
		this.fk_game = game;
		this.timestamp = Timestamp.valueOf(LocalDateTime.now());
		this.value = value;
		this.checkedByModerator = false;
	}
	
	public static long getNewELO(int playerAelo, int playerBelo, int gamescore) {
		//calcul expected value
		double l1 = playerBelo - playerAelo;
		double l2 = l1 / 400d;
		double l3 = Math.pow(10d, l2);
		double l4 = 1d +  l3;
		
		double exp = 1d / l4;
		
		double l5 = 15d * (Double.valueOf(gamescore) - exp);
		
		return Math.addExact(playerAelo, Math.round(l5));
	}
}
