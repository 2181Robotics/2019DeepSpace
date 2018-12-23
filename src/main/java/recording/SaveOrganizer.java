package recording;

public class SaveOrganizer {
    private class BinaryNode {
        private String key;
        private ReplayAuto value;
        private BinaryNode left;
        private BinaryNode right;

        public BinaryNode(String key, ReplayAuto value) {
            this.key = key;
            this.value = value;
        }

        public void add(BinaryNode node) {
            int val = key.compareTo(node.key);
            if (val>0) {
                if (right==null) {
                    right = node;
                } else {
                    right.add(node);
                }
            } else {
                if (left==null) {
                    left = node;
                } else {
                    left.add(node);
                }
            }
        }

        public BinaryNode find(String name) {
            if (key.equals(name)) {
                return this;
            } else {
                int val = key.compareTo(name);
                if (val>0) {
                    if (right!=null) {
                        return right.find(name);
                    } else {
                        return null;
                    }
                } else {
                    if (left!=null) {
                        return left.find(name);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    private BinaryNode head;

    public SaveOrganizer() {
        head = new BinaryNode("", null);
    }

    public void add(String name, ReplayAuto value) {
        BinaryNode node = new BinaryNode(name, value);
        head.add(node);
    }

    public void updateReplay(String name, Saved start) {
        BinaryNode toUpdate = head.find(name);
        if (toUpdate!=null) {
            toUpdate.value.start.next = start;
        }
    }

    public ReplayAuto findReplay(String name) {
        return head.find(name).value;
    }
}