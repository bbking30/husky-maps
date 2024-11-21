package minpq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Unsorted array (or {@link ArrayList}) implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class UnsortedArrayMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the element-priority pairs in no specific order.
     */
    private final List<PriorityNode<E>> elements;

    /**
     * Constructs an empty instance.
     */
    public UnsortedArrayMinPQ() {
        elements = new ArrayList<>();
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public UnsortedArrayMinPQ(Map<E, Double> elementsAndPriorities) {
        elements = new ArrayList<>(elementsAndPriorities.size());
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
        elements.add(new PriorityNode<>(element, priority));
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code
        for (PriorityNode<E> currElement : elements) {
            if (currElement.getElement() == element) {
                return true;
            }
        }

        return false;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public double getPriority(E element) {
        // TODO: Replace with your code
        for (PriorityNode<E> currElement : elements) {
            if (currElement.getElement() == element) {
                return currElement.getPriority();
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
        PriorityNode<E> minNode = elements.getFirst();
        for (PriorityNode<E> currElement : elements) {
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
        PriorityNode<E> minNode = elements.getFirst();
        int minNodeIndex = 0;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getPriority() < minNode.getPriority()) {
                minNode = elements.get(i);
                minNodeIndex = i;
            }
        }
        E minElement = minNode.getElement();
        elements.remove(minNodeIndex);
        return minElement;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code
        int index = 0;
        PriorityNode<E> currElement = elements.getFirst();
        while (currElement.getElement() != element && index < elements.size()) {
            index++;
            currElement = elements.get(index);
        }

        currElement.setPriority(priority);
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return elements.size();
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}


