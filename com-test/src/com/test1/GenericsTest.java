package com.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gaurav Saini on 1/24/2018.
 */
public class GenericsTest {

    public static void main(String[] args) {

        Player joe = new FootballPlayer("Joe");
        Player beth = new TennisPlayer("Beth");
        Player pat = new SoccerPlayer("Pat");

        Team<FootballPlayer> footBallTeam = new Team<>("Football Team");
        footBallTeam.addPlayer((FootballPlayer) joe);
        //footBallTeam.addPlayer(beth);
        //footBallTeam.addPlayer(pat);

        System.out.println("Number of players : " + footBallTeam.getPlayerCount());

        Team<TennisPlayer> notwork = new Team<>("Doesn't work");
        notwork.addPlayer((TennisPlayer) beth);

        League<Football> footballLeague = new League<>();
        footballLeague.addTeam(new Football("fbTeam-1", 3));
        footballLeague.addTeam(new Football("fbTeam-2", 5));
        footballLeague.addTeam(new Football("fbTeam-3", 1));
        //footballLeague.addTeam(new Soccer("fbTeam-1", 3)); //Does not work

        footballLeague.primtTeams();
    }
}

abstract class Player {
    private String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class FootballPlayer extends Player {
    FootballPlayer(String name) {
        super(name);
    }
}

class TennisPlayer extends Player {
    TennisPlayer(String name) {
        super(name);
    }
}

class SoccerPlayer extends Player {
    SoccerPlayer(String name) {
        super(name);
    }
}

class Team<T extends Player> {
    private String name;
    private List<T> players;
    private int won;
    private int lost;

    Team(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    void addPlayer(T player) {
        players.add(player);
        System.out.println(player.getName() + " add to team " + this.name);
    }

    int getPlayerCount() {
        return players.size();
    }
}

class League<T extends Team2> {
    private List<T> teams;

    League() {
        teams = new ArrayList<T>();
    }

    void addTeam(T team) {
        teams.add(team);
    }

    void primtTeams() {
        Collections.sort(teams);
        for (Team2 t : teams) {
            System.out.println("Team " + t.getName() + " won matches " + t.getWon());
        }
    }
}

abstract class Team2 implements Comparable<Team2> {
    private String name;
    private int won;

    Team2(String name, int won) {
        this.won = won;
        this.name = name;
    }

    public int getWon() {
        return won;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Team2 o) {
        if (this.won < o.getWon()) {
            return -1;
        } else if (this.won > o.getWon()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class Football extends Team2 {
    Football(String name, int won) {
        super(name, won);
    }
}

class Soccer extends Team2 {
    Soccer(String name, int won) {
        super(name, won);
    }
}

class Dimension<T> {
    private T length;
    private T width;
    private T height;
    List<? extends T> list;

    //Generic constructor
    public Dimension(T length, T width, T height) {
        super();
        this.length = length;
        this.width = width;
        this.height = height;
    }
}