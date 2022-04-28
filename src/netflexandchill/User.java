/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netflexandchill;

import java.util.ArrayList;

/**
 *
 * @author Gary
 */
public class User extends Logger {

    private final ArrayList<Entertainment> Watchlist = new ArrayList<>();
    private final ArrayList<Entertainment> Watched = new ArrayList<>();
    private final ArrayList<Entertainment> History = new ArrayList<>();

    public ArrayList<Entertainment> getHistory() {
        return History;
    }

    public User(String username, String password) {
        super(username, password);
    }

    public ArrayList<Entertainment> getWatchlist() {
        return Watchlist;
    }

    public ArrayList<Entertainment> getWatched() {
        return Watched;
    }

}
