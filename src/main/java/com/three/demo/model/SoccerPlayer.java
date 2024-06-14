package com.three.demo.model;

import java.util.UUID;

public class SoccerPlayer {
    private String id;
    private String name;
    private int number;
    private String position;
    private String teamName;

    public SoccerPlayer(String name, int number, String position, String teamName) {
        // auto-generated id
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.number = number;
        this.position = position;
        this.teamName = teamName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
