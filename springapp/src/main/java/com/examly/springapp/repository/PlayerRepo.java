package com.examly.springapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

}
