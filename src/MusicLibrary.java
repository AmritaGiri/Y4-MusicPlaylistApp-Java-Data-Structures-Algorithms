
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * The MusicLibrary class creates a LinkedList and a HashMap of songs, adds
 * songs to both structures,
 *
 * and displays them.
 *
 * It also includes a main method to demonstrate the functionalities of the
 * MusicLibrary class.
 */
public class MusicLibrary {

    /**
     *
     * The main method that demonstrates the functionalities of the MusicLibrary
     * class.
     *
     * It creates a LinkedList and a HashMap of songs, adds songs to both
     * structures,
     *
     * and displays them.
     *
     * @param args arguments passed to the main method.
     */
    public static void main(String[] args) {
        // create a LinkedList and a HashMap of songs
        List<Songs> songsList = new LinkedList<>();
        HashMap<String, Songs> songsMap = new HashMap<>();
        Map<Integer, String> map = new HashMap<>();

        // populate the map with key-value pairs
        for (int i = 0; i < 1000000; i++) {
            map.put(i, "value " + i);
        }

        // retrieve the values from the map and measure the time
        long startTime = System.nanoTime();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
        }
        long endTime = System.nanoTime();

        // calculate the elapsed time and print the result
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds");
        // calculate and print the elapsed time long duration = (endTime - startTime) /
        // 1000000; // convert nanoseconds to milliseconds
        // System.out.println("Retrieving all entries took " + duration + "
        // milliseconds"); }

        // add songs to both structures
        Songs song1 = new Songs("Song Title 1", "Artist 1", "Genre 1", "Album 1", 1, 0, 5.0);
        Songs song2 = new Songs("Song Title 2", "Artist 2", "Genre 2", "Album 2", 2, 0, 4.0);
        songsList.add(song1);
        songsList.add(song2);
        songsMap.put(song1.getTitle(), song1);
        songsMap.put(song2.getTitle(), song2);

        // display all songs in the LinkedList
        System.out.println("Songs in LinkedList:");
        for (Songs songs : songsList) {
            System.out.println(songs.getTitle() + " by " + songs.getArtist());
        }

        // find a song in the HashMap
        String titleToFind = "Song Title 1";
        String artistToFind = "Artist 1";
        Songs foundSong = songsMap.get(titleToFind);

        if (foundSong != null && foundSong.getArtist().equals(artistToFind)) {
            System.out.println("Found song in HashMap: " + foundSong.getTitle() + " by " + foundSong.getArtist());
        } else {
            System.out.println("Song not found in HashMap.");
        }
    }
}
