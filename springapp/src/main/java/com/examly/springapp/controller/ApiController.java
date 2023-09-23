package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Player;
import com.examly.springapp.model.Team;
import com.examly.springapp.service.ApiService;

@RestController
public class ApiController {

	@Autowired
	private ApiService apiService;
	
	@PostMapping("/team")
	public ResponseEntity<Boolean> save(@RequestBody Team team) {

		boolean s = apiService.saveTeam(team);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}
	
	@PostMapping("/team/{teamId}/player")
	public ResponseEntity<Boolean> savePlayer(@PathVariable Long teamId,@RequestBody Player player) {

		boolean s = apiService.savePlayer(player, teamId);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}
	
	@GetMapping("/team")
	public ResponseEntity<List<Team>> getAll() {

		List<Team> team = apiService.getAllTeam();
		return new ResponseEntity<>(team, HttpStatus.OK);
	}

	@GetMapping("/team/{teamId}")
	public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {

		Team Team = apiService.getTeamById(teamId);
		return new ResponseEntity<>(Team, HttpStatus.OK);
	}
	
	@GetMapping("team/{teamId}/player/{playerId}")
	public ResponseEntity<Player> getPlayerById(@PathVariable Long teamId,@PathVariable Long playerId) {

		Player player = apiService.getPlayerById(teamId,playerId);
		if(player!=null)
		{
			return new ResponseEntity<>(player, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/player")
	public ResponseEntity<List<Player>> getAllPlayer() {

		List<Player> player = apiService.getAllPlayer();
		return new ResponseEntity<>(player, HttpStatus.OK);
	}
}
