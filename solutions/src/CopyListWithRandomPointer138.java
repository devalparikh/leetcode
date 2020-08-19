import java.util.*;

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }

    public Node(int _val) {
        val = _val;
    }
};

public class CopyListWithRandomPointer138 {

    public Node copyRandomList(Node head) {
        Node iter = head;
        Node prev = null;
        Node root = null;

        // List of clonned [original, clonned]
        Map<Node, Node> buffer = new HashMap<>();

        while(iter != null) {
            Node newNode = new Node(iter.val);

            // put a reference to original node for a while
            newNode.random = iter.random;

            // add new clonned node to the list
            buffer.put(iter, newNode);

            if(prev != null) {
                prev.next = newNode;
            } else {
                root = newNode;
            }

            prev = newNode;
            iter = iter.next;
        }

        // replace random reference to the reference of new node
        iter = root;
        while(iter != null) {
            iter.random = buffer.get(iter.random);

            iter = iter.next;
        }

        return root;
    }
}
