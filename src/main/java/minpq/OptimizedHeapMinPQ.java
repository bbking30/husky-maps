package minpq;

import java.util.*;

/**
 * Optimized binary heap implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class OptimizedHeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of element-priority pairs.
     */
    private final List<PriorityNode<E>> elements;
    /**
     * {@link Map} of each element to its associated index in the {@code elements} heap.
     */
    private final Map<E, Integer> elementsToIndex;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        elements = new ArrayList<>();
        elementsToIndex = new HashMap<>();
        elements.add(null);
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public OptimizedHeapMinPQ(Map<E, Double> elementsAndPriorities) {
        elements = new ArrayList<>(elementsAndPriorities.size());
        elementsToIndex = new HashMap<>(elementsAndPriorities.size());
        // TODO: Replace with your code
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        // TODO: Replace with your code
        elements.add(new PriorityNode<E> (element, priority));
        elementsToIndex.put(element, elements.size()-1);
        swim(elements.size()-1);
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code
        return elementsToIndex.containsKey(element);
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public double getPriority(E element) {
        // TODO: Replace with your code
        return elements.get(elementsToIndex.get(element)).getPriority();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        return elements.get(1).getElement();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        E minElement = elements.get(1).getElement();
        swap(1, elements.size() - 1);
        elements.remove(elements.size() - 1);
        elementsToIndex.remove(minElement);

        if (!isEmpty()) {
            sink(1);
        }
        return minElement;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code
        int index = elementsToIndex.get(element);
        double oldPriority = elements.get(index).getPriority();
        elements.get(index).setPriority(priority);

        if (priority < oldPriority) {
            swim(index);
        } else if (priority > oldPriority){
            sink(index);
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return elements.size()-1;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private void swim(int index) {
        while (index > 1 && elements.get(index).getPriority() <
                elements.get(index / 2).getPriority()) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    private void sink(int index) {
        int size = size();
        int childIndex = 2 * index;

        while (childIndex <= size) {
            if (childIndex < size && elements.get(childIndex).getPriority() >
                    elements.get(childIndex + 1).getPriority()) {
                childIndex++;
            }

            if (elements.get(index).getPriority() <=
                    elements.get(childIndex).getPriority()) {
                childIndex = size + 1;
            } else {
                swap(index, childIndex);
                index = childIndex;
                childIndex = 2 * index;
            }
        }
    }

    private void swap(int index1, int index2) {
        PriorityNode<E> tempNode = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, tempNode);

        elementsToIndex.put(elements.get(index1).getElement(), index1);
        elementsToIndex.put(elements.get(index2).getElement(), index2);
    }
}
