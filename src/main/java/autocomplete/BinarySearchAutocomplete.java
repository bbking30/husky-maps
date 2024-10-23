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
     * Constructs an empty instance.
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

        int start = Collections.binarySearch(elements, prefix, CharSequence::compare);
        if (start < 0) {
            start = -(start + 1);
        }

        for (int index = start; index < elements.size(); index++) {
            CharSequence term = elements.get(index);
            if (Autocomplete.isPrefixOf(prefix, term)) {
                terms.add(term);
            }
        }

        return terms;
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
