package structs;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class Player implements Serializable {
	@Id @GeneratedValue
	private int idplayer;
	
	private String nickname;
	private int elo;
	private PlayerPrivilege privilege;
    
    @OneToMany(mappedBy="fk_player")
    Set<Score> scores = new HashSet<Score>();
    
    public int getID() {
    	return idplayer;
    }
	
    public String getNickname() {
    	return nickname;
    }
    public void setNickName(String nickname) {
    	this.nickname = nickname;
    }
    
    public PlayerPrivilege getPrivilege() {
    	return this.privilege;
    }
    private void setPrivilege(PlayerPrivilege privilege) {
    	this.privilege = privilege;
    }
    
    public int getELO() {
    	return this.elo;
    }
    public void setELO(int elo) {
    	this.elo = elo;
    }
   
	public Set<Score> getScores() {
		return scores;
	}
    
    public Player(String nickname) {
    	this.nickname = nickname;
    	this.privilege = PlayerPrivilege.PLAYER;
    	this.elo = 1000;
    }
    public Player(String nickname, PlayerPrivilege privilege) {
    	this.nickname = nickname;
    	this.privilege = privilege;
    	this.elo = 1000;
    }
    
    public void promoteAPlayer(Player player, PlayerPrivilege privilege) {
    	if(this.privilege == PlayerPrivilege.ADMINISTRATOR) {
    		player.setPrivilege(privilege);
    	}
    }
    public void checkAScore(Score score) {
    	if(this.privilege == PlayerPrivilege.MODERATOR) {
    		score.checkByModerator();
    	}
    }
    
    public enum PlayerPrivilege {
    	PLAYER,
    	MODERATOR,
    	ADMINISTRATOR
    }
}
