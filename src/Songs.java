
/**
 * Represents a song with its attributes such as title, artist, genre, album,
 * track number, year, rating, and play count.
 * Implements the Comparable interface to compare two songs based on their title
 * and artist.
 */
public class Songs implements Comparable<Songs> {

    private String title;
    private String artist;
    private String genre;
    private String album;
    private int trackNumber;
    private int year;
    private double rating;
    private int playCount;

    /**
     *
     * Constructs a song with the given title, artist, genre, album, track
     * number, year, and rating. Initializes the play count to 0.
     *
     * @param title       the title of the song
     * @param artist      the artist of the song
     * @param genre       the genre of the song
     * @param album       the album of the song
     * @param trackNumber the track number of the song
     * @param year        the year of the song
     * @param rating      the rating of the song
     */
    public Songs(String title, String artist, String genre, String album, int trackNumber, int year, double rating) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.trackNumber = trackNumber;
        this.year = year;
        this.rating = rating;
        this.playCount = 0; // Initialize playCount to 0
    }

    /**
     *
     * Returns the title of the song.
     *
     * @return the title of the song
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * Sets the title of the song to the given title.
     *
     * @param title the title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * Returns the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * Sets the artist of the song to the given artist.
     *
     * @param artist the artist to be set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     *
     * Returns the genre of the song.
     *
     * @return the genre of the song
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * Sets the genre of the song to the given genre.
     *
     * @param genre the genre to be set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     *
     * Returns the album of the song.
     *
     * @return the album of the song
     */
    public String getAlbum() {
        return album;
    }

    /**
     *
     * Sets the album of the song to the given album.
     *
     * @param album the album to be set
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     *
     * Returns the track number of the song.
     *
     * @return the track number of the song
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     *
     * Sets the track number of the song to the given track number.
     *
     * @param trackNumber the track number to be set
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     *
     * Returns the year of the song.
     *
     * @return the year of the song
     */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    @Override
    public int compareTo(Songs other) {
        int titleComparison = this.title.compareTo(other.title);
        return titleComparison != 0 ? titleComparison : this.artist.compareTo(other.artist);
    }
}
