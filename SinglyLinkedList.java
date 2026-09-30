import java.util.*;
// push this again autograder thing wasnt clicked 
public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        if (size <= 1) {
            return;
        }

        //store in og order
        ArrayList<Node<E>> nodes = new ArrayList<>();

        Node<E> current = head;
        while (current != null) {
            nodes.add(current);
            current = current.getNext();
        }

        // make it sorted
        ArrayList<Node<E>> sorted = new ArrayList<>(nodes);

        sorted.sort((a, b) -> 
            a.getElement().compareTo(b.getElement())
        );

        // track 
        HashMap<Node<E>, Integer> positions = new HashMap<>();

        for (int i = 0; i < nodes.size(); i++) {
            positions.put(nodes.get(i), i);
        }

        // swap smallest with largest,
        // second smallest with second largest, etc.
        for (int i = 0; i < sorted.size() / 2; i++) {

            Node<E> smallest = sorted.get(i);
            Node<E> largest = sorted.get(sorted.size() - 1 - i);

            int smallestPosition = positions.get(smallest);
            int largestPosition = positions.get(largest);

            // swap their positions in the list
            nodes.set(smallestPosition, largest);
            nodes.set(largestPosition, smallest);

            // update their positions
            positions.put(smallest, largestPosition);
            positions.put(largest, smallestPosition);
        }

        // then reconnect w all nodes
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).setNext(nodes.get(i + 1));
        }

        // last node points to null
        nodes.get(nodes.size() - 1).setNext(null);

        // update head and tail
        head = nodes.get(0);
        tail = nodes.get(nodes.size() - 1);
    }
   
}

