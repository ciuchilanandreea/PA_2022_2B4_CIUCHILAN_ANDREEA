package Lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFS {
    private int n;
    private int m;
    private boolean [][] adjacency;
    String[] words;
    private List<String> subsetOfNeighbors = new ArrayList<>();
    private List<String> maxSubsetOfNeighbors = new ArrayList<>();
    private List<Integer> indexSubsetOfNeigbors = new ArrayList<>();
    private boolean [] visited;
    private boolean neighbor;
    //private MutableInt maxSize = new MutableInt();
    private int maxSize;

    public DFS(int n, int m, boolean[][] adjacency, String[] words) {
        this.n = n;
        this.m = m;
        this.adjacency = adjacency;
        this.words = words;
        this.visited = new boolean[n];
    }

    public void makeDFS(int current)
    {
        subsetOfNeighbors.clear();
        indexSubsetOfNeigbors.clear();
        maxSize = 0;
        Arrays.fill(visited, false);
        dfs(current);
    }

    private void dfs(int current)
    {
        visited[current] = true;
        subsetOfNeighbors.add(words[current]);
        indexSubsetOfNeigbors.add(current);
        neighbor = true;
        for(int index = 0; index < n; index++)
            if(adjacency[current][index] && !visited[index]) {
                    dfs(index);
                    if(subsetOfNeighbors.size() > maxSize)
                    {
                        maxSubsetOfNeighbors = new ArrayList<>(subsetOfNeighbors);
                        maxSize = maxSubsetOfNeighbors.size();
                    }
                    subsetOfNeighbors.remove(words[index]); //remove the word because on the tree, nodes at same level
                                                            //may be not be neighbors
                    visited[index] = false;
            }
    }

    public List<String> getSubsetOfNeighbors() {
        return maxSubsetOfNeighbors;
    }
}
