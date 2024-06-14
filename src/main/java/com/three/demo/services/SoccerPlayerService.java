package com.three.demo.services;

import com.three.demo.model.SoccerPlayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoccerPlayerService {
    private final List<SoccerPlayer> soccerPlayers = new ArrayList<>();

    public SoccerPlayerService() {
        soccerPlayers.add(new SoccerPlayer("Lionel Messi", 10, "Forward", "Paris Saint-Germain"));
        soccerPlayers.add(new SoccerPlayer("Cristiano Ronaldo", 7, "Forward", "Manchester United"));
        soccerPlayers.add(new SoccerPlayer("Neymar Jr.", 10, "Forward", "Paris Saint-Germain"));
        soccerPlayers.add(new SoccerPlayer("Robert Lewandowski", 9, "Forward", "Bayern Munich"));
    }

    public List<SoccerPlayer> findAll() {
        return soccerPlayers;
    }

    public Optional<SoccerPlayer> findById(String id) {
        return soccerPlayers.stream().filter(player -> player.getId().equals(id)).findFirst();
    }

    public void addPlayer(SoccerPlayer player) {
        soccerPlayers.add(player);
    }

    public void updatePlayer(SoccerPlayer updatedPlayer) {
        findById(updatedPlayer.getId()).ifPresent(player -> {
            player.setName(updatedPlayer.getName());
            player.setNumber(updatedPlayer.getNumber());
            player.setPosition(updatedPlayer.getPosition());
            player.setTeamName(updatedPlayer.getTeamName());
        });
    }

    public void deletePlayer(String id) {
        soccerPlayers.removeIf(player -> player.getId().equals(id));

    }
}
