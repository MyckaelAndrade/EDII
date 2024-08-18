import java.util.Random;

public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random rand = new Random();

        // Inserindo 100 números aleatórios na árvore AVL
        for (int i = 0; i < 100; i++) {
            int number = rand.nextInt(1001) - 500;
            tree.insert(number);
        }

        // Imprimindo a árvore após a inserção
        System.out.println("Tree after inserting 100 numbers:");
        tree.printTree();

        // Removendo 20 números aleatórios da árvore AVL
        for (int i = 0; i < 20; i++) {
            int number = rand.nextInt(1001) - 500;
            tree.delete(number);
        }

        // Imprimindo a árvore após a remoção
        System.out.println("\nTree after deleting 20 numbers:");
        tree.printTree();
    }
}

