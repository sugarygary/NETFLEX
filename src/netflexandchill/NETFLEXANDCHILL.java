/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package netflexandchill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Gary
 */
public class NETFLEXANDCHILL {

    private final Scanner sc = new Scanner(System.in);

    private int returnIndex(String username, String password, ArrayList<Logger> loglist) {
        for (int i = 0; i < loglist.size(); i++) {
            if (loglist.get(i).getUsername().equals(username)) {
                if (loglist.get(i).getPassword().equals(password)) {
                    return i;
                } else {
                    return -2;
                }
            }
        }
        return -1;
    }

    public ArrayList<Integer> selectCategory(String exp, ArrayList<String> cat) {
        ArrayList<Integer> indexofcat = new ArrayList<>();
        String[] tmp = exp.split(",");
        for (String tmp1 : tmp) {
            int parser = Integer.parseInt(tmp1 + "");
            if (parser >= 1 && parser <= cat.size()) {
                indexofcat.add(parser - 1);
            } else {
                throw new ArrayIndexOutOfBoundsException("Selected index is out of bonds");
            }
        }
        return indexofcat;
    }

    public ArrayList<Integer> selectMovie(String exp, ArrayList<Entertainment> movieonly) {
        ArrayList<Integer> indexofcat = new ArrayList<>();
        String[] tmp = exp.split(",");
        for (String tmp1 : tmp) {
            int parser = Integer.parseInt(tmp1 + "");
            if (parser >= 1 && parser <= movieonly.size()) {
                indexofcat.add(parser - 1);
            } else {
                throw new ArrayIndexOutOfBoundsException("Selected index is out of bonds");
            }
        }
        return indexofcat;
    }

    private void printCategory(ArrayList<String> cat) {
        for (int i = 0; i < cat.size(); i++) {
            System.out.println((i + 1) + ". " + cat.get(i));
        }
    }

    private boolean isThereAnyMovie(ArrayList<Entertainment> netflix) {
        for (Entertainment entertainment : netflix) {
            if (entertainment instanceof Movie) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Entertainment> getMovieOnly(ArrayList<Entertainment> netflix) {
        ArrayList<Entertainment> movieonly = new ArrayList<>();
        for (int i = 0; i < netflix.size(); i++) {
            if (netflix.get(i) instanceof Movie) {
                movieonly.add(netflix.get(i));
            }
        }
        return movieonly;
    }

    private ArrayList<Movie> addEp(ArrayList<Entertainment> movieonly, ArrayList<Entertainment> netflix, ArrayList<Integer> selectremove) {
        for (int i = 0; i < selectremove.size(); i++) {
            for (int j = 0; j < netflix.size(); j++) {
                if (movieonly.get(i).equals(netflix.get(j))) {
                    netflix.remove(j);
                    break;
                }
            }
        }
        ArrayList<Movie> episode = new ArrayList<>();
        for (int i = 0; i < selectremove.size(); i++) {
            episode.add((Movie) movieonly.get(i));
        }
        return episode;
    }

    private Movie generateMovie(ArrayList<String> category) {
        System.out.print("Movie name : ");
        String moviename = sc.nextLine();
        System.out.print("Description : ");
        String moviedesc = sc.nextLine();
        System.out.println("Category (separate with a comma)");
        printCategory(category);
        System.out.print(">> ");
        String categoryselect = sc.nextLine();
        ArrayList<Integer> c = selectCategory(categoryselect, category);
        return new Movie(moviename, c, moviedesc);
    }

    public static void printEnt(ArrayList<Entertainment> netflix) {
        for (int i = 0; i < netflix.size(); i++) {
            if (netflix.get(i) instanceof Series) {
                System.out.println((i + 1) + ". " + netflix.get(i).getTitle() + " (Series)");
            } else if (netflix.get(i) != null) {
                System.out.println((i + 1) + ". " + netflix.get(i).getTitle());
            }
        }
    }

    public static void printSeries(ArrayList<Series> netflix) {
        for (int i = 0; i < netflix.size(); i++) {
            System.out.println((i + 1) + ". " + netflix.get(i).getTitle());
        }
    }

    public static void printMovies(ArrayList<Movie> netflix) {
        for (int i = 0; i < netflix.size(); i++) {
            System.out.println((i + 1) + ". " + netflix.get(i).getTitle());
        }
    }

    public NETFLEXANDCHILL() throws CloneNotSupportedException {
        int log;
        ArrayList<Logger> loglist = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<Entertainment> netflix = new ArrayList<>();
        if (loglist.isEmpty()) {
            loglist.add(new Admin("admin", "admin"));
        }
        do {
            System.out.println("""
                               ## NETFLEX ##
                               1. Login
                               2. Register
                               3. Exit""");
            System.out.print(">> ");
            log = Integer.parseInt(sc.nextLine());
            if (log == 1) {
                System.out.print("Username : ");
                String tempusername = sc.nextLine();
                System.out.print("Password : ");
                String temppass = sc.nextLine();
                int currentindex = returnIndex(tempusername, temppass, loglist);
                if (currentindex == -1) {
                    System.out.println("Username not found.");
                } else if (currentindex == -2) {
                    System.out.println("Wrong password!");
                } else if (currentindex == 0) {
                    //MENU ADMIN
                    int inputadmin;
                    do {
                        System.out.println("""
                                           ## NETFLEX ##
                                           Welcome, Admin
                                           1. Input New Category
                                           2. Input New Movie
                                           3. Input New Series
                                           4. Edit Series
                                           5. List Catalogue
                                           0. Logout""");
                        System.out.print(">> ");
                        inputadmin = Integer.parseInt(sc.nextLine());
                        if (inputadmin == 1) {
                            String tempcategory;
                            do {
                                System.out.print("Input category name : ");
                                tempcategory = sc.nextLine();
                                if (category.contains(tempcategory)) {
                                    System.out.println("Category already exists!");
                                } else {
                                    category.add(tempcategory);
                                    break;
                                }
                            } while (category.contains(tempcategory));
                        } else if (inputadmin == 2) {
                            if (!category.isEmpty()) {
                                netflix.add(generateMovie(category));
                            } else {
                                System.out.println("No categories are registered.");
                            }
                        } else if (inputadmin == 3) {
                            if (!isThereAnyMovie(netflix)) {
                                System.out.println("Add movies for premiere episodes!");
                            } else if (category.isEmpty()) {
                                System.out.println("No categories are registered.");
                            } else {
                                System.out.print("Series name: ");
                                String seriesname = sc.nextLine();
                                System.out.print("Description: ");
                                String seriesdesc = sc.nextLine();
                                System.out.println("Episodes:");
                                ArrayList<Entertainment> movieonly = getMovieOnly(netflix);
                                printEnt(movieonly);
                                System.out.print(">> ");
                                String chooseep = sc.nextLine();
                                ArrayList<Integer> selectedindex = selectMovie(chooseep, movieonly);
                                ArrayList<Movie> tempep = addEp(movieonly, netflix, selectedindex);
                                netflix.add(new Series(tempep, seriesname, seriesdesc));
                            }
                        } else if (inputadmin == 4) {
                            ArrayList<Series> seriesonly = new ArrayList<>();
                            for (int i = 0; i < netflix.size(); i++) {
                                if (netflix.get(i) instanceof Series tmp) {
                                    seriesonly.add(tmp);
                                }
                            }
                            if (seriesonly.isEmpty()) {
                                System.out.println("No series available!");
                            } else {
                                int inputseries;
                                do {
                                    printSeries(seriesonly);
                                    System.out.println("0. Back");
                                    System.out.print(">> ");
                                    inputseries = Integer.parseInt(sc.nextLine());
                                    if (inputseries >= 1 && inputseries <= seriesonly.size()) {
                                        inputseries -= 1;
                                        int editseries;
                                        do {
                                            seriesonly.get(inputseries).printDetails(category);
                                            System.out.println("""
                                                               99. Add episode
                                                               0. Back""");
                                            System.out.print(">> ");
                                            editseries = Integer.parseInt(sc.nextLine());
                                            if (editseries == 99) {
                                                seriesonly.get(inputseries).getEpisodes().add(generateMovie(category));
                                                for (int i = 1; i < loglist.size(); i++) {
                                                    if (loglist.get(i) instanceof User tempuser) {
                                                        if (tempuser.getHistory().contains(seriesonly.get(inputseries))) {
                                                            tempuser.getHistory().remove(seriesonly.get(inputseries));
                                                            tempuser.getWatchlist().add(seriesonly.get(inputseries));
                                                        }
                                                    }
                                                }
                                            }
                                        } while (editseries != 0);
                                        inputseries += 1;
                                    } else if (inputseries != 0) {
                                        System.out.println("Invalid input!");
                                    }
                                } while (inputseries != 0);
                            }
                        } else if (inputadmin == 5) {
                            ArrayList<Entertainment> sorted = new ArrayList<>();
                            for (int i = 0; i < netflix.size(); i++) {
                                sorted.add((Entertainment) netflix.get(i).clone());
                            }
                            Collections.sort(sorted, new SortByTitle());
                            if (!sorted.isEmpty()) {
                                System.out.println("Catalogue");
                                printEnt(sorted);
                            } else {
                                System.out.println("Catalogue is empty!");
                            }
                        }
                    } while (inputadmin != 0);
                } else {
                    //MENU USER
                    User currentuser = (User) loglist.get(currentindex);
                    int usermenu;
                    do {
                        System.out.println("## NETFLEX ##");
                        System.out.println("Welcome, " + currentuser.getUsername());
                        System.out.println("""
                                           1. Browse category
                                           2. Search
                                           3. Watchlist
                                           4. View watch history
                                           0. Logout""");
                        System.out.print(">> ");
                        usermenu = Integer.parseInt(sc.nextLine());
                        switch (usermenu) {
                            case 1 -> {
                                if (category.isEmpty()) {
                                    System.out.println("Netflex is under maintenance!");
                                } else {
                                    System.out.println("Browse Category");
                                    printCategory(category);
                                    System.out.print(">> ");
                                    String exp = sc.nextLine();
                                    ArrayList<Integer> selectedcat = selectCategory(exp, category);
                                    ArrayList<Entertainment> catquery = new ArrayList<>();
                                    for (int i = 0; i < netflix.size(); i++) {
                                        if (netflix.get(i) instanceof Series tmp) {
                                            if (tmp.getSummarizedCategory().containsAll(selectedcat)) {
                                                catquery.add(netflix.get(i));
                                            }
                                        } else if (netflix.get(i) instanceof Movie tmp) {
                                            if (tmp.getCategoryIndex().containsAll(selectedcat)) {
                                                catquery.add(netflix.get(i));
                                            }
                                        }
                                    }
                                    if (catquery.isEmpty()) {
                                        System.out.println("No results found!");
                                    } else {
                                        Collections.sort(catquery, new SortByTitle());
                                        int chooseresult;
                                        System.out.println("Result:");
                                        printEnt(catquery);
                                        do {
                                            System.out.print(">> ");
                                            chooseresult = Integer.parseInt(sc.nextLine());
                                            if (chooseresult >= 1 && chooseresult <= catquery.size()) {
                                                int todo;
                                                chooseresult -= 1;
                                                do {
                                                    catquery.get(chooseresult).printDetails(category);
                                                    System.out.println("""
                                                                                                                           -------------------------
                                                                                                                           99. Insert into watchlist
                                                                                                                           0. Back""");
                                                    System.out.print(">> ");
                                                    todo = Integer.parseInt(sc.nextLine());
                                                    if (todo == 99) {
                                                        if (catquery.get(chooseresult) instanceof Movie) {
                                                            if (currentuser.getWatched().contains(catquery.get(chooseresult))) {
                                                                currentuser.getWatched().remove(currentuser.getWatched().indexOf(catquery.get(chooseresult)));
                                                            }
                                                        } else if (catquery.get(chooseresult) instanceof Series ser) {
                                                            if (currentuser.getWatched().containsAll(ser.getEpisodes())) {
                                                                currentuser.getWatched().removeAll(ser.getEpisodes());
                                                            }
                                                        }
                                                        if (!currentuser.getWatchlist().contains(catquery.get(chooseresult))) {
                                                            currentuser.getWatchlist().add(catquery.get(chooseresult));
                                                            System.out.println("Succesfully added to watchlist!");
                                                        } else {
                                                            System.out.println("This movie/serie is already in your watchlist!");
                                                        }
                                                    }
                                                } while (todo != 0 && todo != 99);
                                                chooseresult += 1;
                                            }
                                        } while (chooseresult < 1 && chooseresult > catquery.size());
                                    }
                                }
                            }
                            case 2 -> {
                                System.out.println("Search");
                                System.out.print("Keyword: ");
                                ArrayList<Entertainment> query = new ArrayList<>();
                                String query2 = sc.nextLine();
                                for (int i = 0; i < netflix.size(); i++) {
                                    if (netflix.get(i).getTitle().toLowerCase().contains(query2.toLowerCase())) {
                                        query.add(netflix.get(i));
                                    }
                                }
                                if (query.isEmpty()) {
                                    System.out.println("No results found!");
                                } else {
                                    System.out.println("""
                                                                                           Sort:
                                                                                           1. Title
                                                                                           2. Rating""");
                                    int sorter;
                                    do {
                                        System.out.print(">> ");
                                        sorter = Integer.parseInt(sc.nextLine());
                                        if (sorter == 1) {
                                            Collections.sort(query, new SortByTitle());
                                        } else if (sorter == 2) {
                                            Collections.sort(query, new SortByRating());
                                        }
                                    } while (sorter != 1 && sorter != 2);
                                    System.out.println("Result:");
                                    printEnt(query);
                                    int chooseresult;
                                    do {
                                        System.out.print(">> ");
                                        chooseresult = Integer.parseInt(sc.nextLine());
                                        if (chooseresult >= 1 && chooseresult <= query.size()) {
                                            int todo;
                                            chooseresult -= 1;
                                            do {
                                                query.get(chooseresult).printDetails(category);
                                                System.out.println("""
                                                                                                                   -------------------------
                                                                                                                   99. Insert into watchlist
                                                                                                                   0. Back""");
                                                System.out.print(">> ");
                                                todo = Integer.parseInt(sc.nextLine());
                                                if (todo == 99) {
                                                    if (query.get(chooseresult) instanceof Movie) {
                                                        if (currentuser.getWatched().contains(query.get(chooseresult))) {
                                                            currentuser.getWatched().remove(currentuser.getWatched().indexOf(query.get(chooseresult)));
                                                        }
                                                    } else if (query.get(chooseresult) instanceof Series ser) {
                                                        if (currentuser.getWatched().containsAll(ser.getEpisodes())) {
                                                            currentuser.getWatched().removeAll(ser.getEpisodes());
                                                        }
                                                    }
                                                    if (!currentuser.getWatchlist().contains(query.get(chooseresult))) {
                                                        currentuser.getWatchlist().add(query.get(chooseresult));
                                                        System.out.println("Succesfully added to watchlist!");
                                                    } else {
                                                        System.out.println(query.get(chooseresult).getTitle() + " is already in your watchlist!");
                                                    }
                                                }
                                            } while (todo != 0 && todo != 99);
                                            chooseresult += 1;
                                        }
                                    } while (chooseresult < 1 || chooseresult > query.size());
                                }
                            }
                            case 3 -> {
                                ArrayList<Entertainment> unwatched = new ArrayList<>();
                                for (int i = 0; i < currentuser.getWatchlist().size(); i++) {
                                    if (currentuser.getWatchlist().get(i) instanceof Movie mov) {
                                        unwatched.add(mov);
                                    } else if (currentuser.getWatchlist().get(i) instanceof Series ser) {
                                        unwatched.add(ser);
                                    }
                                }
                                if (unwatched.isEmpty()) {
                                    System.out.println("Watch List is currently empty!");
                                } else {
                                    System.out.println("Watch List");
                                    printEnt(unwatched);
                                    int choosewatch;
                                    do {
                                        System.out.print(">> ");
                                        choosewatch = Integer.parseInt(sc.nextLine());
                                    } while (choosewatch < 1 || choosewatch > unwatched.size());
                                    if (choosewatch >= 1 && choosewatch <= unwatched.size()) {
                                        choosewatch -= 1;
                                        if (unwatched.get(choosewatch) instanceof Movie mov) {
                                            System.out.println("You have watched " + mov.getTitle());
                                            if (!currentuser.getHistory().contains(mov)) {
                                                int rating;
                                                do {
                                                    System.out.print("Please give your rating (0-5):");
                                                    rating = Integer.parseInt(sc.nextLine());
                                                } while (rating > 5 || rating < 0);
                                                mov.getRating().add(rating);
                                                currentuser.getHistory().add(mov);
                                                System.out.println("Thank you for your Rating");
                                            }
                                            currentuser.getWatchlist().remove(mov);
                                        } else if (unwatched.get(choosewatch) instanceof Series ser) {
                                            System.out.println("Episodes:");
                                            for (int i = 0; i < ser.getEpisodes().size(); i++) {
                                                System.out.print((i + 1) + ". " + ser.getEpisodes().get(i).getTitle());
                                                if (currentuser.getWatched().contains(ser.getEpisodes().get(i))) {
                                                    System.out.println(" (Already Watched)");
                                                } else {
                                                    System.out.println();
                                                }
                                            }
                                            int selectep;
                                            do {
                                                System.out.print(">> ");
                                                selectep = Integer.parseInt(sc.nextLine());
                                            } while (selectep < 1 || selectep > ser.getEpisodes().size());
                                            selectep -= 1;
                                            System.out.println("You have watched " + ser.getTitle() + " Ep " + (selectep + 1) + ": " + ser.getEpisodes().get(selectep).getTitle());
                                            if (!currentuser.getWatched().contains(ser.getEpisodes().get(selectep)) && !currentuser.getHistory().contains(ser)) {
                                                int rating;
                                                do {
                                                    System.out.print("Please give your rating (0-5):");
                                                    rating = Integer.parseInt(sc.nextLine());
                                                } while (rating > 5 || rating < 0);
                                                ser.getEpisodes().get(selectep).getRating().add(rating);
                                            }
                                            if (!currentuser.getWatched().contains(ser.getEpisodes().get(selectep))) {
                                                currentuser.getWatched().add(ser.getEpisodes().get(selectep));
                                            }
                                            if (currentuser.getWatched().containsAll(ser.getEpisodes())) {
                                                currentuser.getHistory().add(ser);
                                                currentuser.getWatchlist().remove(ser);
                                            }
                                        }
                                    }
                                }
                            }
                            case 4 -> {
                                if (!currentuser.getHistory().isEmpty()) {
                                    System.out.println("Watch History");
                                    printEnt(currentuser.getHistory());
                                } else {
                                    System.out.println("You haven't watch any movie/serie!");
                                }
                            }
                        }
                    } while (usermenu != 0);
                }
            } else if (log == 2) {
                System.out.print("Username : ");
                String tempusername = sc.nextLine();
                System.out.print("Password : ");
                String temppass = sc.nextLine();
                System.out.print("Confirm Password : ");
                String temppasscon = sc.nextLine();
                int uniquechecker = returnIndex(tempusername, temppass, loglist);
                if (uniquechecker == -1) {
                    if (temppasscon.equals(temppass)) {
                        loglist.add(new User(tempusername, temppass));
                        System.out.println("Register success!");
                    } else {
                        System.out.println("Password confirmation doesn't match!");
                    }
                } else {
                    System.out.println("Username already exists!");
                }

            }
        } while (log != 3);
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        new NETFLEXANDCHILL();
    }
}
