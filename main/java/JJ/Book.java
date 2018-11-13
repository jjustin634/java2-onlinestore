package JJ;

public class Book extends Item {
    private String authors;
    private String edition;
    private String publisher;
    private int pubYear;

    public Book(String title, double price, String authors, String edition, String publisher, int pubYear) {
        super(title, price);
        this.authors = authors;
        this.edition = edition;
        this.publisher = publisher;
        this.pubYear = pubYear;
    }
}
