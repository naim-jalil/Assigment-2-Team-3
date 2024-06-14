package com.three.demo.controller;

import com.three.demo.model.SoccerPlayer;
import com.three.demo.services.SoccerPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/soccerplayers")
public class SoccerPlayerController {

    @Autowired
    private SoccerPlayerService soccerPlayerService;

    @GetMapping
    public List<SoccerPlayer> getAllPlayers() {
        return soccerPlayerService.findAll();
    }

    @PostMapping
    public SoccerPlayer addPlayer(@RequestBody SoccerPlayer player) {
        soccerPlayerService.addPlayer(player);
        return player;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable String id) {
        if (soccerPlayerService.findById(id).isPresent()) {
            soccerPlayerService.deletePlayer(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "successfully deleted " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Player with id " + id + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlayerById(@PathVariable String id) {
        Optional<SoccerPlayer> player = soccerPlayerService.findById(id);

        if (player.isPresent()) {
            return new ResponseEntity<>(player.get(), HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Player with id " + id + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable String id,
            @RequestBody SoccerPlayer player) {

        if (soccerPlayerService.findById(id).isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "player not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (player.getId().equals(id)) {
            soccerPlayerService.updatePlayer(player);
            Map<String, String> response = new HashMap<>();
            response.put("message", "successfully updated " + player.getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "player name not match");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
