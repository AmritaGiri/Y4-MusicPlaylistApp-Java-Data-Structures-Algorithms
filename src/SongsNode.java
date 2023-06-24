
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * This class represents a node in a singly-linked list of songs. Each node
 * contains a reference
 *
 * to a Songs object and a reference to the next node in the list.
 */
class SongsNode {

    Songs data;
    SongsNode next;

    /**
     *
     * Constructs a new SongsNode object with the specified Songs object and a
     * null reference to the next node in the list.
     *
     * @param data the Songs object to be stored in this node.
     */
    public SongsNode(Songs data) {
        this.data = data;
        this.next = null;
    }
}

/**
 *
 * This class represents a custom implementation of a singly-linked list of
 * songs.
 *
 * It provides methods for adding and removing Songs objects, as well as
 * iterating over the list.
 */
class CustomLinkedList implements Iterable<Songs> {

    private SongsNode head;

    /**
     *
     * Constructs a new empty CustomLinkedList object with a null reference to
     * the head node.
     */
    public CustomLinkedList() {
        head = null;
    }

    /**
     *
     * Adds a new Songs object to the end of the list.
     *
     * @param songs the Songs object to be added to the list.
     */
    public void add(Songs songs) {
        SongsNode newNode = new SongsNode(songs);
        if (head == null) {
            head = newNode;
        } else {
            SongsNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    /**
     *
     * Removes a specified Songs object from the list, if it exists.
     *
     * @param songToRemove the Songs object to be removed from the list.
     *
     * @return true if the Songs object was successfully removed from the list,
     *         false otherwise.
     */
    public boolean remove(Songs songToRemove) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(songToRemove)) {
            head = head.next;
            return true;
        }

        SongsNode current = head;
        while (current.next != null) {
            if (current.next.data.equals(songToRemove)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     *
     * Returns an iterator over the Songs objects in the list.
     *
     * @return an Iterator object that can be used to iterate over the list.
     */
    @Override
    public Iterator<Songs> iterator() {
        return new Iterator<Songs>() {
            private SongsNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Songs next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Songs song = current.data;
                current = current.next;
                return song;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove() is not supported");
            }
        };
    }

    /**
     *
     * Returns true if the list is empty, false otherwise.
     *
     * @return true if the list is empty, false otherwise.
     */
    boolean isEmpty() {
        return head == null;
    }

    /**
     *
     * Returns the number of Songs objects in the list.
     *
     * @return the number of Songs objects in the list.
     */
    int size() {
        int count = 0;
        SongsNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
/**
 * Returns the Songs object at the specified index in the list.
 *
 */
