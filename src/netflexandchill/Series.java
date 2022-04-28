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
public class Series extends Entertainment {

    private final ArrayList<Movie> Episodes;

    public Series(ArrayList<Movie> Episodes, String title, String description) {
        this.Episodes = Episodes;
        this.title = title;
        this.desc = description;
    }

    public ArrayList<Movie> getEpisodes() {
        return Episodes;
    }

    @Override
    public double getAvgRating() {
        double ctr = 0.0;
        int ratingsizes = 0;
        for (int i = 0; i < Episodes.size(); i++) {
            ctr += Episodes.get(i).getAvgRating();
            ratingsizes += Episodes.get(i).getRating().size();
        }
        if (ratingsizes == 0) {
            return 0.0;
        }
        return ctr / ratingsizes;
    }

    @Override
    public void printCat(ArrayList<String> category) {
        ArrayList<Integer> catindex = new ArrayList<>();
        for (int i = 0; i < this.Episodes.size(); i++) {
            for (int j = 0; j < this.Episodes.get(i).getCategoryIndex().size(); j++) {
                if (!catindex.contains(this.Episodes.get(i).getCategoryIndex().get(j))) {
                    catindex.add(this.Episodes.get(i).getCategoryIndex().get(j));
                }
            }
        }
        System.out.print(category.get(catindex.get(0)));
        for (int i = 1; i < catindex.size(); i++) {
            System.out.print(", " + category.get(catindex.get(i)));
        }
        System.out.println("");
    }

    public ArrayList<Integer> getSummarizedCategory() {
        ArrayList<Integer> catindex = new ArrayList<>();
        for (int i = 0; i < this.Episodes.size(); i++) {
            for (int j = 0; j < this.Episodes.get(i).getCategoryIndex().size(); j++) {
                if (!catindex.contains(this.Episodes.get(i).getCategoryIndex().get(j))) {
                    catindex.add(this.Episodes.get(i).getCategoryIndex().get(j));
                }
            }
        }
        return catindex;
    }

    @Override
    public void printDetails(ArrayList<String> category) {
        System.out.println(this.title);
        System.out.println("Description: " + this.desc);
        System.out.print("Category: ");
        this.printCat(category);
        System.out.println("Avg Rating: " + (Math.round(this.getAvgRating() * 100.0) / 100.0));
        System.out.println("Episodes: ");
        NETFLEXANDCHILL.printMovies(this.Episodes);
    }

}
