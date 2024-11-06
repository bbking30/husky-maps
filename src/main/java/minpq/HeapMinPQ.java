package minpq;

import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * {@link PriorityQueue} implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class HeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link PriorityQueue} storing {@link PriorityNode} objects representing each element-priority pair.
     */
    private final PriorityQueue<PriorityNode<E>> pq;

    /**
     * Constructs an empty instance.
     */
    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::getPriority));
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public HeapMinPQ(Map<E, Double> elementsAndPriorities) {
        pq = new PriorityQueue<>(elementsAndPriorities.size(), Comparator.comparingDouble(PriorityNode::getPriority));
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        // TODO: Replace with your code
        pq.add(new PriorityNode<E> (element, priority));
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code
        for (PriorityNode<E> currNode : pq) {
            if (currNode.getElement() == element) {
                return true;
            }
        }

        return false;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public double getPriority(E element) {
        // TODO: Replace with your code
        for (PriorityNode<E> currNode : pq) {
            if (currNode.getElement() == element) {
                return currNode.getPriority();
            }
        }

        return 0;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        PriorityNode<E> minNode = pq.peek();
        for (PriorityNode<E> currElement : pq) {
            if (currElement.getPriority() < minNode.getPriority()) {
                minNode = currElement;
            }
        }

        return minNode.getElement();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        PriorityNode<E> minNode = pq.peek();
        int minNodeIndex = 0;
        for (PriorityNode<E> currNode : pq) {
            if (currNode.getPriority() < minNode.getPriority()) {
                minNode = currNode;
            }
        }
        E minElement = minNode.getElement();
        pq.remove(minNode);
        return minElement;

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code
        for (PriorityNode<E> currElement : pq) {
            if (currElement.getElement() == element) {
                currElement.setPriority(priority);
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return pq.size();
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
