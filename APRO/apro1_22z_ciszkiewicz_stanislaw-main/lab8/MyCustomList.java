package lab8;

    public class MyCustomList {
        private Node root;
        private int size;

        public MyCustomList() {
            root = null;
            size = 0;
        }

        /**
         * Adds the new node to the list
         * @param value added to the list
         */
        public void add(int value) {
            Node node = root;
            Node newNode = new Node(value);
            // we have the empty list, so add the new node as the root
            if (root == null) {
                root = newNode;
            }
            else {
                // traverse through the list to find the last node
                while (node.getNext() != null) {
                    node = node.getNext();
                }
                // add the new node after the last one
                node.setNext(new Node(value));
            }
            // increase the size of the list
            ++size;
        }

        /**
         * Removes all items from the list with a given value
         * @param value - value present in nodes
         * @return number of removed elements
         */
        public int remove(int value) {
            Node node = root;
            int rmv =0;
            if (node.getValue()==value) {
                root = root.getNext();
                size--;
                rmv++;
            }
            Node nextt = node.getNext();
            while (nextt.getNext()!=null) {
                if (nextt.getValue()==value) {
                    node.setNext(nextt.getNext());
                    size --;
                    rmv++;
                }
                node = nextt;
                nextt = node.getNext();
            }
            nextt = node.getNext();
            if (nextt.getValue()==value){
                node.setNext(null);
                size--;
                rmv++;
            }
            return rmv;
        }

        /**
         * Removes the head from the list.
         * @return true if the operations was done correctly, false otherwise
         */
        public boolean pop() {
            Node node = root;
            root = root.getNext();
            size--;
            return false;
        }

        /**
         * Returns the size of the list
         * @return the integer saying, how many elements the list contains
         */
        public int length(){
            return size;
        }
        private void removeExact(Node wanted){
            Node node = root;
            if (node==wanted) {
                root = root.getNext();
                size--;
                return;
            }
            Node nextt = node.getNext();
            while (nextt.getNext()!=null) {
                if (nextt==wanted) {
                    node.setNext(nextt.getNext());
                    size --;
                    return;
                }
                node = nextt;
                nextt = node.getNext();
            }
            nextt = node.getNext();
            if (nextt==wanted){
                node.setNext(null);
                size--;
            }
        }
        private void findNewPlace(Node wanted){
            removeExact(wanted);
            Node node = root;
            Node prev = null;
            if (wanted.getAccess() >= node.getAccess()){
                wanted.setNext(node);
                root = wanted;
                ++size;
            }
            else {
                while (node.getNext() != null) {
                    prev = node;
                    node = node.getNext();
                    if (wanted.getAccess() >= node.getAccess()){
                        wanted.setNext(node);
                        prev.setNext(wanted);
                        ++size;
                        return;
                    }
                }
            }
        }
        /**
         * Finds a node in the list with the given value
         * @param value what value should be found
         * @return first node with a given value or null if none is present in the list
         */
        public Node getNode(int value) {
            Node result = root;
            while (result != null) {
                if (result.getValue() == value) {
                    result.setAccess(result.getAccess()+1);
                    findNewPlace(result);
                    return result;
                }
                result = result.getNext();
            }
            return null;
        }

        public Node getRoot() {
            root.setAccess(root.getAccess()+1);
            return root;
        }


        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node node = root;
            while (node != null) {
                s.append(node.getValue());
                s.append(" ");
                node = node.getNext();
            }
            return s.toString();
        }

        public static void main(String[] args) {
            MyCustomList customList = new MyCustomList();
            customList.add(5);
            customList.add(7);
            customList.add(9);
            customList.add(5);
            customList.add(1);
            customList.add(3);
            customList.add(9);
            System.out.println("List: " + customList);
            System.out.println("Size: " + customList.length());
            Node node = customList.getNode(7);
            System.out.println("Found node: " + node);
            customList.remove(5);
            System.out.println("List: " + customList);
            System.out.println("Size: " + customList.length());
            node = customList.getNode(9);
            System.out.println("Found node: " + node);
            node = customList.getNode(3);
            System.out.println("Found node: " + node);
            node = customList.getNode(3);
            System.out.println("Found node: " + node);
            System.out.println("List: " + customList);
            System.out.println("Size: " + customList.length());
            node = customList.getNode(1);
            System.out.println("Found node: " + node);
            System.out.println("List: " + customList);
            System.out.println("Size: " + customList.length());
        }
    }


