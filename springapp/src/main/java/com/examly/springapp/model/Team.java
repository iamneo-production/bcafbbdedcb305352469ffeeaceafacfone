package com.examly.springapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    private Long teamId;

    private String teamName;
    private String sportName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Team(Long teamId, String teamName, String sportName) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.sportName = sportName;
		}

	public Team() {
	}
    
    
 
}
