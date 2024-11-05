package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Const#ructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        elements = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Replace with your code
        elements.addAll(terms);
        elements.sort(CharSequence::compare);
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code
        List<CharSequence> terms = new ArrayList<>();
        if (prefix.isEmpty()) {
            return terms;
        }

        int index = Collections.binarySearch(elements, prefix, CharSequence::compare);
        if (index < 0) {
            index = -(index + 1);
        }

        while (index < elements.size() && Autocomplete.isPrefixOf(prefix, elements.get(index))) {
            terms.add(elements.get(index));
            index++;
        }

        return terms;
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
