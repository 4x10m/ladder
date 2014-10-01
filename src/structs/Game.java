package structs;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game {
	@Id @GeneratedValue
	int idgame;
	
	@JoinColumn(name="name")
	String name;
	
	@OneToMany(mappedBy="fk_game")
    Set<Score> scores = new HashSet<Score>();
	
	public int getID() {
		return idgame;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Score> getScores() {
		return scores;
	}
	
	private Game() { }
	
	public Game(String name) {
		this.name = name;
	}
}
