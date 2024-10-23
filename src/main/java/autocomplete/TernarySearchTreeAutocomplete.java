package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    private Node put(Node currNode, CharSequence term, int index) {
        char currLetter = term.charAt(index);
        if (currNode == null) {
            currNode = new Node(currLetter);
        }

        if (currLetter < currNode.data) {
            currNode.left = put(currNode.left, term, index);
        } else if (currLetter > currNode.data) {
            currNode.right = put(currNode.right, term, index);
        } else if (index == term.length() - 1) {
            currNode.isTerm = true;
        } else {
            currNode.mid = put(currNode.mid, term, index + 1);
        }
        return currNode;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Replace with your code
        for (CharSequence term : terms) {
            overallRoot = put(overallRoot, term, 0);
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }


    private Node getPrefix(Node currNode, CharSequence prefix, int index) {
        if (currNode == null) {
            return null;
        }

        char letter = prefix.charAt(index);
        if (letter < currNode.data) {
            return getPrefix(currNode.left, prefix, index);
        } else if (letter > currNode.data) {
            return getPrefix(currNode.right, prefix, index);
        } else if (index < prefix.length() - 1) {
            return getPrefix(currNode.mid, prefix, index + 1);
        }

        return currNode;
    }

    private void collect(Node letter, StringBuilder prefix, List<CharSequence> terms) {
        if (letter != null) {
            if (letter.isTerm) {
                terms.add(prefix.toString() + letter.data);
            }
            collect(letter.left, prefix, terms);
            collect(letter.mid, new StringBuilder(prefix.toString() + letter.data), terms);
            collect(letter.right, prefix, terms);
        }
    }

    public List<CharSequence> keysWithPrefix(CharSequence prefix) {
        List<CharSequence> terms = new ArrayList<>();
        Node letter = getPrefix(overallRoot, prefix, 0);
        if (letter == null) {
            return terms;
        } else if (letter.isTerm) {
            terms.add(prefix);
        }

        collect(letter.mid, new StringBuilder(prefix), terms);

        return terms;
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code
        return keysWithPrefix(prefix);
    }


    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        public char val;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            // stores the letter
            this.data = data;
            // is the term in the tst or not (mark last letter as true)
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}

