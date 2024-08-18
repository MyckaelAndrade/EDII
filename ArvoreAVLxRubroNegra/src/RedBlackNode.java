public class RedBlackNode {
    int data;
    RedBlackNode left, right, parent;
    boolean color;

    public RedBlackNode(int data) {
        this.data = data;
        left = right = parent = null;
        this.color = true; // Nodo novo é sempre vermelho
    }
}
