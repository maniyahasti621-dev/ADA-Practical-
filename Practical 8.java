import java.util.Arrays;
import java.util.Comparator;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Subset {
    int parent, rank;
}

public class KruskalsMST {
    int V, E; 
    Edge[] edges; 

    KruskalsMST(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent!= i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
  
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
      
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void kruskalMST() {
        Edge[] result = new Edge[V - 1];
        int e = 0; 
        int i = 0; 

        Arrays.sort(edges, Comparator.comparingInt(o -> o.weight));

        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (e < V - 1 && i < E) {
            Edge nextEdge = edges[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x!= y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + " - " + result[i].dest + "\t" + result[i].weight);
            totalWeight += result[i].weight;
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 6; 
        int E = 9; 
        KruskalsMST graph = new KruskalsMST(V, E);

        graph.edges = new Edge[E];
        graph.edges[0] = new Edge(0, 1, 4);
        graph.edges[1] = new Edge(0, 5, 2);
        graph.edges[2] = new Edge(1, 2, 6);
        graph.edges[3] = new Edge(1, 5, 3);
        graph.edges[4] = new Edge(2, 3, 3);
        graph.edges[5] = new Edge(2, 5, 1);
        graph.edges[6] = new Edge(3, 4, 2);
        graph.edges[7] = new Edge(3, 5, 5);
        graph.edges[8] = new Edge(4, 5, 4);

        System.out.println("=== Kruskal's Minimum Spanning Tree ===");
        graph.kruskalMST();
    }
}
