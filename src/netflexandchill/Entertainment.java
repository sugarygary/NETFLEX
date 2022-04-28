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
public abstract class Entertainment implements Cloneable, CategoryPrintable, Rateable {

    protected String title;
    protected String desc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void printCat(ArrayList<String> category) {
        //maap ko kalo interface nya jelek hehehe,ini di override di anaknya lagi :)
    }

    @Override
    public double getAvgRating() {
        //override di anaknya lagi :)
        return 0;
    }

    public abstract void printDetails(ArrayList<String> category);

}
