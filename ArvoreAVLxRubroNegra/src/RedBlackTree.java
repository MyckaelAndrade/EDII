public class RedBlackTree {
    private RedBlackNode root;
    private RedBlackNode TNULL;

    public RedBlackTree() {
        TNULL = new RedBlackNode(0);
        TNULL.color = false;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    // Rotação à esquerda
    private void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Rotação à direita
    private void rightRotate(RedBlackNode x) {
        RedBlackNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Inserção de nós na árvore
    public void insert(int key) {
        RedBlackNode node = new RedBlackNode(key);
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = true; // Nodo novo é sempre vermelho

        RedBlackNode y = null;
        RedBlackNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(RedBlackNode k) {
        RedBlackNode u;
        while (k.parent.color == true) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == true) {
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == true) {
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = false;
    }

    // Método de busca
    public boolean find(int key) {
        return searchTreeHelper(this.root, key);
    }

    private boolean searchTreeHelper(RedBlackNode node, int key) {
        if (node == TNULL || key == node.data) {
            return node != TNULL;
        }
        if (key < node.data) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }
}
