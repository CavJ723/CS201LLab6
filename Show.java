import java.util.ArrayList;

//This is provided for you as we have not covered classes at this point
class Show {
    String id;
    String title;
    String type;
    String description;
    int releaseYear;
    String rating;
    int runtime;
    ArrayList<String> genres;
    ArrayList<String> countries;
    double seasons;
    double score;

    public Show(String id, String title, String type, String description, int releaseYear, String rating,
                int runtime, ArrayList<String> genres, ArrayList<String> countries, double seasons, double score) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.runtime = runtime;
        this.genres = genres;
        this.countries = countries;
        this.seasons = seasons;
        this.score = score;
    }
}
