package lab8;
import java.util.Objects;


    class Node {
        /**
         * Data for the node. It's final!
         */
        private final int value;
        /**
         * Next node to this node.
         */
        private Node next;
        private int access;
        public Node(int value) {
            this.value = value;
            this.next = null;
            access=0;
        }

        public int getAccess() {
            return access;
        }

        public void setAccess(int access) {
            this.access = access;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return getValue() == node.getValue();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }


