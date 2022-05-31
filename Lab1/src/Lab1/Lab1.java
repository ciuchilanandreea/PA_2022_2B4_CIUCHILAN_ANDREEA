package Lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab1 {
    public static void main(String args[]) {
        int index;
        int indexj;
        char [] c;
        int n, p;
        int m;
        String[][] neighbors;
        boolean[][] adjacency;
        String word;
        String[] words;
        List<String> subsetOfNeighbors = new ArrayList<>();

        long startTime = System.nanoTime();
        if (args.length < 3) {
            System.out.println("3 parametri!\n");
            return;
        }
        m = args.length - 2;
        n = Integer.parseInt(args[0]);
        p = Integer.parseInt(args[1]);

        c = new char[args.length - 2];
        for (index = 0; index < m; index++) {
            c[index] = args[index + 2].charAt(0);
            if (!Character.isLetter(c[index])) {
                System.out.println("Parametrul " + (index + 3) + " trebuie sa fie litera");
                return;
            }
        }

        words = new String[n];
        for (index = 0; index < n; index++) {
            word = generateRandomWord(p, c);
            words[index] = word;
        }


        //generating adjacency matrix
        adjacency = new boolean[n][n];
        for (index = 0; index < n - 1; index++)
            for (indexj = index + 1; indexj < n; indexj++)
                if (commonLetter(words[index], words[indexj])) {
                    adjacency[index][indexj] = true;
                    adjacency[indexj][index] = true;
                }

        //generating neighbors using adjacency matrix
        neighbors = generateNeighbors(adjacency, words, n, p);

        System.out.println("Generated words are:");
        for (index = 0; index < n; index++)
            System.out.println(words[index]);

        System.out.println("\nWords neighbors are:");
        for (index = 0; index < n; index++) {
            int count = Integer.parseInt(neighbors[index][0]);
            for (indexj = 1; indexj < count; indexj++)
                System.out.print(neighbors[index][indexj] + " ");

            System.out.println();
        }

        //print the neighbors only if n is max 500
        if(n <= 3000)
        {
            System.out.println("Generated words are:");
            for (index = 0; index < n; index++)
                System.out.println(words[index]);

            System.out.println("\nWords neighbors are:");
            for (index = 0; index < n; index++) {
                int count = Integer.parseInt(neighbors[index][0]);
                for (indexj = 1; indexj < count; indexj++)
                    System.out.print(neighbors[index][indexj] + " ");

                System.out.println();
            }
        }
        else
        {
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        }

        //generating the max subset
        DFS dfs = new DFS(n, m, adjacency, words);
        int maxCount = 0;
        for(index = 0; index < n; index++)
        {
            dfs.makeDFS(index);
            if(dfs.getSubsetOfNeighbors().size() > maxCount) {
                maxCount = dfs.getSubsetOfNeighbors().size();
                subsetOfNeighbors = dfs.getSubsetOfNeighbors();
            }
        }

        if(maxCount > 2)
        {
            System.out.println("Subset have length " + maxCount);
            for(index = 0; index < subsetOfNeighbors.size(); index++) {
                System.out.println(subsetOfNeighbors.get(index));
            }
        }
        else{
            System.out.println("We don't have a subset of length minimum 3");
        }
    }

    public static String generateRandomWord(int len, char[] alphabet) {
        StringBuilder word = new StringBuilder();
        Random rand = new Random();
        for (int index = 0; index < len; index++) {
            int k = rand.nextInt(alphabet.length);
            word.append(alphabet[k]);
        }
        return word.toString();
    }

    public static Boolean commonLetter(String first, String second) {
        int indexI, indexJ;
        for (indexI = 0; indexI < first.length(); indexI++)
            for (indexJ = 0; indexJ < second.length(); indexJ++)
                if (first.toCharArray()[indexI] == second.toCharArray()[indexJ])
                    return true;
        return false;
    }

    public static String[][] generateNeighbors(boolean[][] adj, String[] str, int n, int p)
    {
        int indexI;
        int indexJ;
        int count;
        String [][] aux = new String[n+1][n+1];

        for(indexI = 0; indexI < n; indexI++) {
            count = 1;
            aux[indexI][1] = str[indexI];
            for(indexJ = 0; indexJ < n; indexJ++)
                if(adj[indexI][indexJ])
                {
                    aux[indexI][++count] = str[indexJ];
                }
            aux[indexI][0] = String.valueOf(count+1);
        }

        return aux;
    }

}