import java.io.*;
import java.util.*;

public class TreeComparison {
    public static void main(String[] args) throws IOException {
        AVLTree avl = new AVLTree();
        RedBlackTree rb = new RedBlackTree();

        // Leitura dos dados do arquivo
        List<Integer> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/dados.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();  // Remove espaços em branco no início e no final
                if (!line.isEmpty()) {
                    try {
                        data.add(Integer.parseInt(line));
                    } catch (NumberFormatException e) {
                        System.out.println("Linha inválida: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inserção dos dados na AVL e medição do tempo
        long startTime = System.currentTimeMillis();
        for (int num : data) {
            avl.root = avl.insert(avl.root, num);
        }
        long endTime = System.currentTimeMillis();
        long timeAvlInsertion = endTime - startTime;

        // Inserção dos dados na Rubro-Negra e medição do tempo
        startTime = System.currentTimeMillis();
        for (int num : data) {
            rb.insert(num);
        }
        endTime = System.currentTimeMillis();
        long timeRbInsertion = endTime - startTime;

        // Sorteio dos números e execução das operações
        Random rand = new Random();
        int countAvl = 0, countRb = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            int num = rand.nextInt(19999) - 9999;
            if (num % 3 == 0) {
                avl.root = avl.insert(avl.root, num);
            } else if (num % 5 == 0) {
                // Remoção na AVL pode ser implementada
            } else {
                if (avl.find(avl.root, num)) {
                    countAvl++;
                }
            }
        }
        endTime = System.currentTimeMillis();
        long timeAvlOperations = endTime - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            int num = rand.nextInt(19999) - 9999;
            if (num % 3 == 0) {
                rb.insert(num);
            } else if (num % 5 == 0) {
                // Remoção na Rubro-Negra pode ser implementada
            } else {
                if (rb.find(num)) {
                    countRb++;
                }
            }
        }
        endTime = System.currentTimeMillis();
        long timeRbOperations = endTime - startTime;

        // Exibindo resultados
        System.out.println("Tempo de inserção na AVL: " + timeAvlInsertion + "ms");
        System.out.println("Tempo de inserção na Rubro-Negra: " + timeRbInsertion + "ms");
        System.out.println("Tempo de operações na AVL: " + timeAvlOperations + "ms");
        System.out.println("Tempo de operações na Rubro-Negra: " + timeRbOperations + "ms");
        System.out.println("Contagem de buscas na AVL: " + countAvl);
        System.out.println("Contagem de buscas na Rubro-Negra: " + countRb);
    }
}
