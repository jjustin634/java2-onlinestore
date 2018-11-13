package JJ;

import java.util.Date;

public class MusicCD extends Item {
    private String artists;
    private Date releaseDate;
    private String label;
    private String recordCompany;
    private int totalLength;
    private String genres;

    public MusicCD (
        String title,
        double price,
        String artists,
        Date releaseDate,
        String label,
        String recordCompany,
        int totalLength,
        String genres
    ) {
        super(title, price);
        this.artists = artists;
        this.releaseDate = releaseDate;
        this.label = label;
        this.recordCompany = recordCompany;
        this.totalLength = totalLength;
        this.genres = genres;
    }

}
