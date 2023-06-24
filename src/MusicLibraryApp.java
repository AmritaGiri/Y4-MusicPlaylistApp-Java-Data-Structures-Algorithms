
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * This class represents a MusicLibraryApp, which is a simple console-based
 * application for managing a music library and playlists.
 *
 * It allows users to display all songs, add a song, delete a song, search for
 * songs, manage playlists, and undo the last delete operation.
 *
 * @author S05ad1
 */
public class MusicLibraryApp {

    /**
     *
     * The main method that drives the application.
     *
     * @param args Command-line arguments, not used in this application.
     */
    public static void main(String[] args) {
        CustomLinkedList songList = new CustomLinkedList();
        HashMap<String, Songs> songMap = new HashMap<>();
        PlaylistLibrary playlistLibrary = new PlaylistLibrary();
        Stack<Songs> deletedSongs = new Stack<>(); // Create the deletedSongs stack

        // Read music library data from file
        String musicLibraryFile = "music_library.txt";
        readMusicLibraryDataFromFile(musicLibraryFile, songList, songMap);

        // Read playlist data from file
        String playlistsFile = "playlists.txt";
        // readPlaylistDataFromFile(playlistsFile, playlistLibrary, songMap);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Display all songs");
            System.out.println("2. Add a song");
            System.out.println("3. Delete a song");
            System.out.println("4. Search for songs");
            System.out.println("5. Manage playlists");
            System.out.println("6. Undo last delete");
            System.out.println("0. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Display all songs
                    displaySongs(songList, songMap);
                    break;
                case 2:
                    // Add a song
                    addSong(scanner, songList, songMap);
                    break;
                case 3:
                    // Delete a song
                    deleteSong(scanner, songList, songMap, deletedSongs); // Pass the deletedSongs stack to the
                                                                          // deleteSong method
                    break;
                case 4:
                    // Search for songs
                    searchSongs(scanner, songList, songMap);
                    break;
                case 5:
                    // Manage playlists
                    managePlaylists(scanner, playlistLibrary, songMap);
                    break;
                case 6:
                    // Undo last delete
                    undoDelete(deletedSongs, songList, songMap);
                    break;
                case 0:
                    // Exit
                    System.out.println("Saving and exiting...");
                    saveMusicLibraryDataToFile(musicLibraryFile, songList);
                    savePlaylistDataToFile(playlistsFile, playlistLibrary);
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }

    }

    /**
     *
     * Reads the music library data from a file and populates the song list and
     * song map.
     *
     * @param musicLibraryFile The path of the music library file.
     * @param songList         The custom linked list where song data will be
     *                         stored.
     * @param songMap          The hash map where song data will be stored with the
     *                         song
     *                         title as the key.
     */
    private static void readMusicLibraryDataFromFile(String musicLibraryFile, CustomLinkedList songList,
            HashMap<String, Songs> songMap) {
        // Implement logic to read music library data from the file
        try (BufferedReader br = new BufferedReader(new FileReader(musicLibraryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] songData = line.split(",");
                Songs song = new Songs(songData[0], songData[1], songData[2], songData[3],
                        Integer.parseInt(songData[4]), Integer.parseInt(songData[5]), Double.parseDouble(songData[6]));
                songList.add(song);
                songMap.put(song.getTitle(), song);
            }
        } catch (IOException e) {
        }
    }

    /**
     *
     * Reads the playlist data from a file and populates the playlist library
     * and updates the song map.
     *
     * @param playlistsFile   The path of the playlist data file.
     * @param playlistLibrary The PlaylistLibrary object where the playlist data
     *                        will be stored.
     * @param songMap         The hash map where song data will be updated with the
     *                        songs in the playlists.
     */
    private static void readPlaylistDataFromFile(String playlistsFile, PlaylistLibrary playlistLibrary,
            HashMap<String, Songs> songMap) {
        // Implement logic to read playlist data from the file
        try (BufferedReader br = new BufferedReader(new FileReader(playlistsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] playlistData = line.split(",");
                String playlistName = playlistData[0];
                CustomLinkedList playlist = new CustomLinkedList();
                for (int i = 1; i < playlistData.length; i++) {
                    Songs song = songMap.get(playlistData[i]);
                    if (song != null) {
                        playlist.add(song);
                    }
                }
                playlistLibrary.addPlaylist(playlistName, playlist);
            }
        } catch (IOException e) {
        }
    }

    /**
     *
     * Adds a new song to the song list and song map.
     *
     * @param scanner  The Scanner object used for reading user input.
     * @param songList The custom linked list where the new song will be added.
     * @param songMap  The hash map where the new song will be added with the
     *                 song title as the key.
     */
    private static void addSong(Scanner scanner, CustomLinkedList songList, HashMap<String, Songs> songMap) {
        System.out.println("Enter song title:");
        String title = scanner.next();

        System.out.println("Enter song artist:");
        String artist = scanner.next();

        System.out.println("Enter song genre:");
        String genre = scanner.next();

        System.out.println("Enter song album:");
        String album = scanner.next();

        System.out.println("Enter song year:");
        int year = scanner.nextInt();

        System.out.println("Enter song play count:");
        int playCount = scanner.nextInt();

        System.out.println("Enter song rating:");
        double rating = scanner.nextDouble();

        Songs song = new Songs(title, artist, genre, album, year, playCount, rating);
        songList.add(song);
        songMap.put(title, song);
    }

    /**
     *
     * Deletes a song from the song list and song map, and pushes the deleted
     * song onto the deletedSongs stack.
     *
     * @param scanner      The Scanner object used for reading user input.
     * @param songList     The custom linked list from which the song will be
     *                     removed.
     * @param songMap      The hash map from which the song will be removed.
     * @param deletedSongs The stack where the deleted song will be pushed for
     *                     potential undo operation.
     */
    private static void deleteSong(Scanner scanner, CustomLinkedList songList, HashMap<String, Songs> songMap,
            Stack<Songs> deletedSongs) {
        System.out.println("Enter song title to delete:");
        scanner.nextLine(); // Consume newline character
        String titleToDelete = scanner.nextLine();

        if (!songMap.containsKey(titleToDelete)) {
            System.out.println("Could not find song to delete.");
            return;
        }

        try {
            // Find song in LinkedList and remove it
            Songs deletedSong = songMap.get(titleToDelete);
            boolean removedFromLinkedList = songList.remove(deletedSong);

            // Remove song from HashMap
            Songs removedSongFromHashMap = songMap.remove(titleToDelete);

            if (removedFromLinkedList && removedSongFromHashMap != null) {
                // Push the deleted song onto the stack
                deletedSongs.push(deletedSong);
                System.out.println("Song successfully deleted.");
            } else {
                System.out.println("Could not delete song.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the song.");
        }
    }

    /**
     *
     * This method adds the last deleted song back to the songList and songMap
     *
     * @param deletedSongs a stack containing deleted songs
     *
     * @param songList     a linked list of all songs
     *
     * @param songMap      a hashmap of all songs
     */
    private static void undoDelete(Stack<Songs> deletedSongs, CustomLinkedList songList,
            HashMap<String, Songs> songMap) {

        /**
         *
         * This method first checks if the deletedSongs stack is empty. If there
         * are no songs in the stack, it prints a message and returns from the
         * method. Otherwise, it retrieves the last deleted song from the stack
         * using the pop method. It then adds the song back to the songList
         * using the add method and to the songMap using the put method.
         * Finally, it prints a message to indicate that the song has been
         * restored.
         */
        if (deletedSongs.empty()) {
            System.out.println("No songs have been deleted yet!");
            return;
        }

        // Retrieve the last deleted song from the stack
        Songs lastDeleted = deletedSongs.pop();

        // Add the song back to the songList and songMap data structures
        songList.add(lastDeleted);
        songMap.put(lastDeleted.getTitle(), lastDeleted);

        System.out.println("Song " + lastDeleted.getTitle() + " has been restored!");
    }

    /**
     *
     * This method searches for songs in the songList and songMap that match the
     * search query.
     *
     * @param scanner  a Scanner object to read input from the user
     *
     * @param songList a linked list of all songs
     *
     * @param songMap  a hashmap of all songs
     */
    private static void searchSongs(Scanner scanner, CustomLinkedList songList, HashMap<String, Songs> songMap) {
        System.out.println("Enter search query:");
        String searchQuery = scanner.next();

        boolean foundSong = false;

        // Search in LinkedList
        System.out.println("\nResults from LinkedList:");
        for (Songs song : songList) {
            if (song.getTitle().contains(searchQuery) || song.getArtist().contains(searchQuery)
                    || song.getGenre().contains(searchQuery) || song.getAlbum().contains(searchQuery)) {
                System.out.println(song.getTitle() + " by " + song.getArtist());
                foundSong = true;
            }
        }

        // Search in HashMap
        System.out.println("\nResults from HashMap:");
        for (String key : songMap.keySet()) {
            Songs song = songMap.get(key);
            if (song.getTitle().contains(searchQuery) || song.getArtist().contains(searchQuery)
                    || song.getGenre().contains(searchQuery) || song.getAlbum().contains(searchQuery)) {
                System.out.println(song.getTitle() + " by " + song.getArtist());
                foundSong = true;
            }
        }

        if (!foundSong) {
            System.out.println("No matching songs found.");
        }
    }

    /**
     *
     * Manages playlists by providing options to create a new playlist, add
     * songs to an existing playlist,
     *
     * remove songs from an existing playlist, display all playlists, or play a
     * playlist.
     *
     * @param scanner         the scanner object to read user input
     *
     * @param playlistLibrary the playlist library object containing all the
     *                        playlists
     *
     * @param songMap         the hashmap object containing all the songs
     */
    private static void managePlaylists(Scanner scanner, PlaylistLibrary playlistLibrary,
            HashMap<String, Songs> songMap) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a new playlist");
            System.out.println("2. Add songs to a playlist");
            System.out.println("3. Remove songs from a playlist");
            System.out.println("4. Display all playlists");
            System.out.println("5. Play a playlist");
            System.out.println("0. Back");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Create a new playlist
                    System.out.println("Enter playlist name:");
                    scanner.nextLine(); // Consume newline character
                    String newPlaylistName = scanner.nextLine();
                    playlistLibrary.addPlaylist(newPlaylistName, new LinkedList<>());
                    System.out.println("Playlist created.");
                    break;
                case 2:
                    // Add songs to a playlist
                    System.out.println("Enter playlist name:");
                    scanner.nextLine(); // Consume newline character
                    String playlistNameToAddSongs = scanner.nextLine();
                    LinkedList<Songs> playlistToAddSongs = playlistLibrary.getPlaylist(playlistNameToAddSongs);
                    if (playlistToAddSongs != null) {
                        while (true) {
                            System.out.println("Enter song title to add to playlist (0 to stop):");
                            String songTitle = scanner.nextLine();
                            if (songTitle.equals("0")) {
                                break;
                            }
                            Songs songToAdd = songMap.get(songTitle);
                            if (songToAdd != null) {
                                playlistToAddSongs.add(songToAdd);
                                System.out.println("Song added to playlist.");
                            } else {
                                System.out.println("Song not found.");
                            }
                        }
                    } else {
                        System.out.println("Playlist not found.");
                    }
                    break;

                case 3:
                    // Remove songs from a playlist
                    System.out.println("Enter playlist name:");
                    scanner.nextLine(); // Consume newline character
                    String playlistNameToRemoveSongs = scanner.nextLine();
                    LinkedList<Songs> playlistToRemoveSongs = playlistLibrary.getPlaylist(playlistNameToRemoveSongs);
                    if (playlistToRemoveSongs != null) {
                        while (true) {
                            System.out.println("Enter song title to remove from playlist (0 to stop):");
                            String songTitle = scanner.nextLine();
                            if (songTitle.equals("0")) {
                                break;
                            }
                            Songs songToRemove = songMap.get(songTitle);
                            if (songToRemove != null) {
                                playlistToRemoveSongs.remove(songToRemove);
                                System.out.println("Song removed from playlist.");
                            } else {
                                System.out.println("Song not found.");
                            }
                        }
                    } else {
                        System.out.println("Playlist not found.");
                    }
                    break;

                case 4:
                    // Display all playlists
                    System.out.println("Displaying all playlists:");
                    for (String playlistName : playlistLibrary.getAllPlaylistNames()) {
                        LinkedList<Songs> playlist = playlistLibrary.getPlaylist(playlistName);
                        System.out.println("Playlist: " + playlistName);
                        for (Songs songs : playlist) {
                            System.out.println("- " + songs.getTitle() + " by " + songs.getArtist());
                        }
                    }
                    break;
                case 5:
                    // Play a playlist
                    System.out.println("Enter playlist name:");
                    scanner.nextLine(); // Consume newline character
                    String playlistNameToPlay = scanner.nextLine();
                    LinkedList<Songs> playlistToPlay = playlistLibrary.getPlaylist(playlistNameToPlay);
                    if (playlistToPlay != null) {
                        System.out.println("Playing playlist: " + playlistNameToPlay);
                        for (Songs songs : playlistToPlay) {
                            System.out.println("- " + songs.getTitle() + " by " + songs.getArtist());
                        }
                    } else {
                        System.out.println("Playlist not found.");
                    }
                    break;
                case 0:
                    // Back
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    /**
     * Display all the songs present in the music library.
     *
     * @param songList a CustomLinkedList object representing the list of songs
     *                 in the library.
     * @param songMap  a HashMap object representing the mapping of song titles
     *                 to song objects in the library.
     */
    private static void displaySongs(CustomLinkedList songList, HashMap<String, Songs> songMap) {
        System.out.println("Displaying songs from LinkedList:");
        for (Songs song : songList) {
            System.out.println(song.getTitle() + " by " + song.getArtist());
        }

        System.out.println("\nDisplaying songs from HashMap:");
        songMap.keySet().stream().map((key) -> songMap.get(key)).forEach((songs) -> {
            System.out.println(songs.getTitle() + " by " + songs.getArtist());
        });
    }

    /**
     * Save the music library data to a file.
     *
     * @param musicLibraryFile a String object representing the path of the file
     *                         where the data is to be saved.
     * @param songList         a CustomLinkedList object representing the list of
     *                         songs
     *                         in the library.
     */
    private static void saveMusicLibraryDataToFile(String musicLibraryFile, CustomLinkedList songList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(musicLibraryFile))) {
            for (Songs song : songList) {
                writer.println(song.getTitle() + "," + song.getArtist() + "," + song.getGenre() + "," + song.getAlbum()
                        + "," + song.getYear() + "," + song.getPlayCount() + "," + song.getRating());
            }
            System.out.println("Music library data saved to file.");
        } catch (IOException e) {
        }
    }

    /**
     * Save the playlist data to a file.
     *
     * @param playlistsFile   a String object representing the path of the file
     *                        where the data is to be saved.
     * @param playlistLibrary a PlaylistLibrary object representing the list of
     *                        playlists in the library.
     */
    private static void savePlaylistDataToFile(String playlistsFile, PlaylistLibrary playlistLibrary) {
        try (PrintWriter writer = new PrintWriter(new File(playlistsFile))) {
            for (String playlistName : playlistLibrary.getPlaylistNames()) {
                LinkedList<Songs> playlist = playlistLibrary.getPlaylist(playlistName);
                if (!playlist.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(playlistName).append(",");
                    for (int i = 0; i < playlist.size(); i++) {
                        Songs song = playlist.get(i);
                        sb.append(song.getTitle());
                        if (i < playlist.size() - 1) {
                            sb.append(",");
                        }
                    }
                    writer.println(sb.toString());
                }
            }
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Delete a song from the music library.
     *
     * @param scanner  a Scanner object representing the input stream to get user
     *                 input.
     * @param songList a CustomLinkedList object representing the list of songs
     *                 in the library.
     * @param songMap  a HashMap object representing the mapping of song titles
     *                 to song objects in the library.
     */
    private static void deleteSong(Scanner scanner, CustomLinkedList songList, HashMap<String, Songs> songMap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
