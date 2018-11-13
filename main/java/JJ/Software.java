package JJ;

public class Software extends Item {
    private String version;

    public Software(String title, double price, String version) {
        super(title, price);
        this.version = version;
    }
}
