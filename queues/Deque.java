import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    
    private int size;

    
    private Node<Item> first;

    
    private Node<Item> last;

    
    private class Node<Item>
    {
        Item item;
        Node<Item> next;
        Node<Item> previous;
    }
   
    private class DequeIterator<Item> implements Iterator<Item>
    {
        
        private Node<Item> current;

        
        private DequeIterator(Node<Item> item)
        {
            current = item;
        }

        public boolean hasNext()
        {
            return (current != null);
        }

        public Item next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }

            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    
    public Deque()
    {
        size = 0;
        first = null;
        last = null;
    }

   
    public boolean isEmpty()
    {
        return (size == 0);
    }

    
    public int size()
    {
        return size;
    }

    
    public void addFirst(Item item)
    {
        if (item == null) {
            throw new NullPointerException("Can't add empty element to deque");
        }

        Node<Item> newItem = new Node<>();
        newItem.item = item;
        newItem.next = first;
        newItem.previous = null;

        if (isEmpty()) {
            last = newItem;
        } else {
            first.previous = newItem;
        }

        first = newItem;
        size++;
    }

    
    public void addLast(Item item)
    {
        if (item == null) {
            throw new NullPointerException("Can't add empty element to deque");
        }

        Node<Item> newItem = new Node<>();
        newItem.item = item;
        newItem.next = null;
        newItem.previous = last;

        if (isEmpty()) {
            first = newItem;
        } else {
            last.next = newItem;
        }

        last = newItem;
        size++;
    }

    
    public Item removeFirst()
    {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        } else {
            first.previous = null;
        }

        return item;
    }

   
    public Item removeLast()
    {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = last.item;
        last = last.previous;
        size--;

        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }

        return item;
    }

 
    public Iterator<Item> iterator()
    {
        return new DequeIterator<>(first);
    }

    
    public static void main(String[] args)
    {
        Deque<String> deque = new Deque<>();

        String text = "World";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = ", ";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "Hello";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "Meow, ";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "^^";
        deque.addLast(text);
        StdOut.println("addLast() with: '" + text + "'");

        deque.removeFirst();
        StdOut.println("removeFirst()");

        deque.removeLast();
        StdOut.println("removeLast()");

        text = "!";
        deque.addLast(text);
        StdOut.println("addLast() with: '" + text + "'");

        StdOut.println("Iterating deque...");
        for (String item: deque) {
            StdOut.println("Iterate element: " + item);
        }
    }
}
