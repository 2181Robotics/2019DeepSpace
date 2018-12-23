package recording;

public class SaveOrganizer {
    private class BinaryNode {
        private String key;
        private Saved value;
        private BinaryNode left;
        private BinaryNode right;

        public BinaryNode(String key, Saved value) {
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

    public void add(String name, Saved value) {
        BinaryNode node = new BinaryNode(name, value);
        head.add(node);
    }

    public void updateReplay(String name, Saved start) {
        BinaryNode toUpdate = head.find(name);
        if (toUpdate!=null) {
            toUpdate.value.next = start;
        }
    }

    public boolean exists(String name) {
        return (head.find(name)!=null);
    }

    public void set(String name, RecordedJoystick joystick) {
        BinaryNode thing = head.find(name);
        if (thing!=null) {
            joystick.start = thing.value.next;
        }
    }
}