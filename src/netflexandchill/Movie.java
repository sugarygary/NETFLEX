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
public class Movie extends Entertainment {

    private ArrayList<Integer> categoryIndex;
    private final ArrayList<Integer> rating = new ArrayList<>();

    public Movie(String title, ArrayList<Integer> categoryIndex, String desc) {
        this.title = title;
        this.categoryIndex = categoryIndex;
        this.desc = desc;
    }

    public ArrayList<Integer> getRating() {
        return rating;
    }

    public Movie(String title) {
        this.title = title;
    }

    @Override
    public double getAvgRating() {
        if (this.rating.isEmpty()) {
            return 0;
        }
        double ctr = 0.0;
        for (int i = 0; i < this.rating.size(); i++) {
            ctr += this.rating.get(i);
        }
        return ctr / this.rating.size();
    }

    @Override
    public void printCat(ArrayList<String> category) {
        System.out.print(category.get(this.categoryIndex.get(0)));
        for (int i = 1; i < this.categoryIndex.size(); i++) {
            System.out.print(", " + category.get(this.categoryIndex.get(i)));
        }
        System.out.println("");
    }

    public ArrayList<Integer> getCategoryIndex() {
        return categoryIndex;
    }

    @Override
    public void printDetails(ArrayList<String> category) {
        System.out.println(this.title);
        System.out.println("Description: " + this.desc);
        System.out.print("Category: ");
        this.printCat(category);
        System.out.println("Avg Rating: " + (Math.round(this.getAvgRating() * 100.0) / 100.0));
    }

}
