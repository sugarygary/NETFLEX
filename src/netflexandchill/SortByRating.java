/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netflexandchill;

import java.util.Comparator;

/**
 *
 * @author Gary
 */
public class SortByRating implements Comparator<Entertainment> {

    @Override
    public int compare(Entertainment o1, Entertainment o2) {
        double avg1 = 0;
        double avg2 = 0;
        if (o1 instanceof Movie mov) {
            avg1 = mov.getAvgRating();
        } else if (o1 instanceof Series ser) {
            avg1 = ser.getAvgRating();
        }
        if (o2 instanceof Movie mov) {
            avg2 = mov.getAvgRating();
        } else if (o2 instanceof Series ser) {
            avg2 = ser.getAvgRating();
        }
        avg1 *= 10;
        avg2 *= 10;
        return (int) Math.round(avg2 - avg1);
    }

}
