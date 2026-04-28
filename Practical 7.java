import java.util.Arrays;

public class PrimsMST {

    private static final int V = 6;

    int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    void primMST(int[][] graph) {
        int[] parent = new int[V]; 
        int[] key = new int[V]; 
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);

        key[0] = 0;
        parent[0] = -1; 

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v]!= 0 &&!mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        PrimsMST mst = new PrimsMST();

        int[][] graph = {
            {0, 4, 0, 0, 0, 2},
            {4, 0, 6, 0, 0, 3},
            {0, 6, 0, 3, 0, 1},
            {0, 0, 3, 0, 2, 5},
            {0, 0, 0, 2, 0, 4},
            {2, 3, 1, 5, 4, 0}
        };

        System.out.println("=== Prim's Minimum Spanning Tree ===");
        mst.primMST(graph);
    }
}
