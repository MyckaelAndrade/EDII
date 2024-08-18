public class AVLTree {
    AVLNode root;

    // Retorna a altura do nó
    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Rotação à direita
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotação à esquerda
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Obtém o fator de balanceamento do nó
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Método de inserção
    public AVLNode insert(AVLNode node, int key) {
        if (node == null)
            return (new AVLNode(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Método de busca
    public boolean find(AVLNode node, int key) {
        if (node == null) return false;
        if (node.key == key) return true;
        if (key < node.key)
            return find(node.left, key);
        else
            return find(node.right, key);
    }

    // Método de remoção (opcional, conforme a complexidade)
    // public AVLNode delete(AVLNode root, int key) {
    //     // Implementação do método de remoção
    // }
}
