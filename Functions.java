import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Functions {

    //NOTE: some basic code has been provided to help process the input file
    //PRE:
    //POST:
    public static void loadList(String inputFile, String errorFile, ArrayList<Show> shows) {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile))) {
            String header = reader.readLine(); // Skip header
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    ArrayList<String> tokens = parseCSVLine(line);

                    if (tokens.size() < 15) throw new IllegalArgumentException("Not enough tokens");

                    //trim all fields 0 id has been done for you
                    String id = tokens.get(0).trim();
                    String title = "";
                    String type = "";
                    String description = "";

                    //verify that releaseYear is an integer
                    int releaseYear = parseInt(tokens.get(4).trim(), "release_year");
                    String rating = "";

                    //runtime is not necessary
                    int runtime = parseOptionalInt(tokens.get(6).trim(), "runtime");

                    //parseList is provided here for genres. 
                    //Add necessary call for countries as well
                    ArrayList<String> genres = parseList(tokens.get(7));
                    ArrayList<String> countries = new ArrayList<>();

                    //these functions need to be written
                    double seasons = parseOptionalDouble(tokens.get(9).trim(), "seasons");
                    double score = parseDouble(tokens.get(11).trim(), "imdb_score");

                    //throw error for missing genres or countries

                    shows.add(new Show(id, title, type, description, releaseYear, rating, runtime, genres, countries, seasons, score));

                } catch (Exception e) {
                    errorWriter.write("Error: " + e.getMessage() + " — Line: " + line + "\n");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    //NOTE: some basic code has been provided 
    //PRE:
    //POST:
    public static void printReport(ArrayList<Show> shows, String reportFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {

            writer.write("Top 10 Longest Running TV Shows:\n\n");
            
            //create a list of only TV Shows & only movies
            List<Show> tvShows  = new ArrayList<>();
            List<Show> movies  = new ArrayList<>();

            //add logic to create the movies list (tvShows has been provided)
            for (Show s: shows){
                if ("SHOW".equalsIgnoreCase(s.type))
                    tvShows.add(s);
            }

            //sort list in descending order
            tvShows.sort(Comparator.comparingDouble(s -> -s.seasons));

            //create a sublist of the top 10 shows
            if (tvShows.size() > 10)
                tvShows = tvShows.subList(0,10);

            //print the tvShows


            
            writer.write("\n\nTop 10 Movies by IMDB Score:\n\n");

            //add logic to handle the  movies & print top 10 best scores
 

        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
    }

    //PRE:
    //POST:
    private static int parseInt(String s, String field) throws Exception {
        if (s == null || s.isEmpty() || s.equalsIgnoreCase("NaN"))
            throw new NumberFormatException("Invalid " + field);
        return Integer.parseInt(s);
    }

    //PRE:
    //POST:
    private static int parseOptionalInt(String s, String field) {
        try {
            return parseInt(s, field);
        } catch (Exception e) {
            return 0; // fallback if optional
        }
    }

    //PRE:
    //POST:
    private static double parseDouble(String s, String field) throws Exception {
        return 0.0;
    }

    //PRE:
    //POST:
    private static double parseOptionalDouble(String s, String field) {
        return 0.0;
    }

    //parseList has been provided
    //PRE: take a field that contains a sublist
    //POST return an arraylist of values from this list
    private static ArrayList<String> parseList(String s) {
        
        String cleaned = s.replaceAll("\\[|\\]|'", "").trim();
        ArrayList<String> result = new ArrayList<>();

        if (cleaned.isEmpty()) 
            return result;

        String[] items = cleaned.split(",");
        for (String item : items) {
                String trimmed = item.trim();
                if (!trimmed.isEmpty())
                   result.add(trimmed);
        }
        return result;
    }

    //parseCSVLine has been provided
    //PRE:  input current input line
    //POST: return the list of tokens (some fields may have a sublist(sb)) 
    private static ArrayList<String> parseCSVLine(String line) {
        ArrayList<String> tokens = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens;
    }
}
