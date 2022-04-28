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
public class SortByTitle implements Comparator<Entertainment> {

    @Override
    public int compare(Entertainment o1, Entertainment o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }

}
