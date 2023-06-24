
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * The Playlist class represents a playlist of Songs objects.
 *
 * It provides methods to add and remove songs from the playlist, retrieve the
 * playlist size,
 *
 * get a song by index, set and get the playlist name, and retrieve all song
 * titles in the playlist.
 *
 * It also implements the Iterable interface to allow iteration over the songs
 * in the playlist.
 */
public class Playlist implements Iterable<Songs> {

    private final List<Songs> songs;
    private String name;

    /**
     *
     * Constructs a new Playlist object with an empty ArrayList of songs.
     */
    public Playlist() {
        songs = new ArrayList<>();
    }

    /**
     *
     * Adds a Songs object to the playlist.
     *
     * @param song The Songs object to add to the playlist
     */
    public void addSong(Songs song) {
        songs.add(song);
    }

    /**
     *
     * Removes a Songs object from the playlist.
     *
     * @param song The Songs object to remove from the playlist
     */
    public void removeSong(Songs song) {
        songs.remove(song);
    }

    /**
     *
     * Returns an iterator over the Songs objects in the playlist.
     *
     * @return An iterator over the Songs objects in the playlist
     */
    @Override
    public Iterator<Songs> iterator() {
        return songs.iterator();
    }

    /**
     *
     * Returns the number of Songs objects in the playlist.
     *
     * @return The number of Songs objects in the playlist
     */
    public int size() {
        return songs.size();
    }

    /**
     *
     * Returns the Songs object at the specified index in the playlist.
     *
     * @param index The index of the Songs object to retrieve
     * @return The Songs object at the specified index in the playlist
     */
    public Songs get(int index) {
        return songs.get(index);
    }

    /**
     *
     * Removes the specified Songs object from the playlist.
     *
     * @param song The Songs object to remove from the playlist
     */
    public void remove(Songs song) {
        songs.remove(song);
    }

    /**
     *
     * Sets the name of the playlist.
     *
     * @param name The name to set for the playlist
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * Returns the name of the playlist.
     *
     * @return The name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Retrieves all song titles from the collection of Songs objects in the
     * playlist.
     *
     * @return An Iterable of Strings, containing the titles of all the songs in
     *         the playlist
     */
    public Iterable<String> getAllSongTitles() {
        List<String> songTitles = new ArrayList<>();
        for (Songs song : songs) {
            songTitles.add(song.getTitle());
        }
        return songTitles;
    }
    /**
     *
     * Adds a song to a data structure. This method should be implemented by a
     * specific class. The method currently throws an
     * UnsupportedOperationException, indicating that it should be overridden.
     *
     * @param songToAdd The Songs object to be added
     * @throws UnsupportedOperationException If the method is not implemented by
     *                                       a specific class
     */
}
