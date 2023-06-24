
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * The PlaylistLibrary class represents a library of playlists, where each
 * playlist is a LinkedList of Songs objects.
 *
 * It provides methods to add and remove playlists, get a playlist by name,
 * check if a playlist is empty,
 *
 * add and remove songs from a playlist, and retrieve all playlist names.
 */
public class PlaylistLibrary {

    private final HashMap<String, LinkedList> playlists;

    /**
     *
     * Constructs a new PlaylistLibrary object with an empty HashMap of
     * playlists.
     */
    public PlaylistLibrary() {
        this.playlists = new HashMap<>();
    }

    /**
     *
     * Adds a new playlist to the library.
     *
     * @param playlistName The name of the playlist to add
     * @param playlist The LinkedList of Songs objects to add as the playlist
     */
    public void addPlaylist(String playlistName, LinkedList playlist) {
        playlists.put(playlistName, playlist);
    }

    /**
     *
     * Returns the playlist with the specified name from the library.
     *
     * @param playlistName The name of the playlist to retrieve
     * @return The LinkedList of Songs objects that represents the playlist with
     * the specified name
     */
    LinkedList<Songs> getPlaylist(String playlistName) {
        return playlists.get(playlistName);
    }

    /**
     *
     * Removes the playlist with the specified name from the library.
     *
     * @param playlistName The name of the playlist to remove
     */
    void removePlaylist(String playlistName) {
        playlists.remove(playlistName);
    }

    /**
     *
     * Checks if the playlist with the specified name is empty.
     *
     * @param playlistName The name of the playlist to check
     * @return true if the playlist is empty or does not exist, false otherwise
     */
    boolean isPlaylistEmpty(String playlistName) {
        LinkedList<Songs> playlist = playlists.get(playlistName);
        return playlist == null || playlist.isEmpty();
    }

    /**
     *
     * Adds a Songs object to the playlist with the specified name.
     *
     * @param playlistName The name of the playlist to add the song to
     * @param song The Songs object to add to the playlist
     */
    void addSongToPlaylist(String playlistName, Songs song) {
        LinkedList<Songs> playlist = playlists.get(playlistName);
        if (playlist != null) {
            playlist.add(song);
        }
    }

    /**
     *
     * Removes a Songs object from the playlist with the specified name.
     *
     * @param playlistName The name of the playlist to remove the song from
     * @param song The Songs object to remove from the playlist
     */
    void removeSongFromPlaylist(String playlistName, Songs song) {
        LinkedList<Songs> playlist = playlists.get(playlistName);
        if (playlist != null) {
            playlist.remove(song);
        }
    }

    /**
     *
     * Returns an Iterable of all playlist names in the library.
     *
     * @return An Iterable of Strings, containing the names of all playlists in
     * the library
     */
    Iterable<String> getAllPlaylistNames() {
        return playlists.keySet();
    }

    /**
     *
     * Adds a new playlist to the library. This method should be implemented by
     * a specific class. The method currently throws an
     * UnsupportedOperationException, indicating that it should be overridden.
     *
     * @param playlistName The name of the playlist to add
     * @param playlist The CustomLinkedList of Songs objects to add as the
     * playlist
     * @throws UnsupportedOperationException If the method is not implemented by
     * a specific class
     */
    void addPlaylist(String playlistName, CustomLinkedList playlist) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    Iterable<String> getPlaylistNames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
