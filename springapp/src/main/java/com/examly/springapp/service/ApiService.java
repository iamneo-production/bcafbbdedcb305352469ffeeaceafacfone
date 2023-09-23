package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Player;
import com.examly.springapp.model.Team;
import com.examly.springapp.repository.PlayerRepo;
import com.examly.springapp.repository.TeamRepo;


@Service
public class ApiService {
	
	@Autowired
	private PlayerRepo playerRepo;
	
	@Autowired
	private TeamRepo teamRepo;
	
	public boolean savePlayer(Player p, Long teamId) {
		if(teamRepo.existsById(teamId))
		{
			p.setTeam(teamRepo.findById(teamId).get());
			return playerRepo.save(p) != null ? true : false;
		}
		return false;
	}
	
	public boolean saveTeam(Team t) {
		return teamRepo.save(t) != null ? true : false;
	}
	
	public List<Team> getAllTeam() {

		List<Team> list = teamRepo.findAll();
		return list;
	}

	public Team getTeamById(Long teamId) {

		if (teamRepo.existsById(teamId)) {
			Team team = teamRepo.findById(teamId).get();
			return team;
		}
		return null;
	}
	
	public Player getPlayerById(Long teamId, Long playerId) {
		Team t= getTeamById(teamId);
		Player p= playerRepo.findById(playerId).get();
		if (t.getPlayers().contains(p)) {
			return p;
		}
		return null;
	}
	
	public List<Player> getAllPlayer() {

		List<Player> list = playerRepo.findAll();
		return list;
	}
}
