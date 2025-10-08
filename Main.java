import java.util.*;

//NAME: Judi Cavender
//LAB PERIOD: 7:00 PM
class Main {
    public static void main(String[] args) {
        //create an ArrayList of <Show> type
        System.out.println("Complete the code for lab 6");
        ArrayList<Show> shows = new ArrayList<>();
        String inputFile = "titles.csv";
        String errorFile = "error.txt";
        String reportFile = "report.txt";

        //load the array list
        System.out.println("Correct the code in loadList ");
        Functions.loadList(inputFile, errorFile, shows);
        System.out.println("Total valid shows loaded: " + shows.size());

        //print the tvshows & movies as directed
        System.out.println("Correct the code in printReport");
        Functions.printReport(shows, reportFile);
        System.out.println("Report generated: " + reportFile);
    }
}
