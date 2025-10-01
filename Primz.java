package A_DSA;

import java.util.Scanner;

public class Prims {
    // Function to perform Prim's Minimum Spanning Tree algorithm
    static void PrimMST(int[][] graph, int V) {
        int[] key = new int[V];         // To store the minimum weight edge to each vertex
        boolean[] mstSet = new boolean[V]; // To represent the set of vertices included in MST
        int[] parent = new int[V];      // To store the constructed MST

        // Initialize all keys as infinite and mstSet[] as false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            parent[i] = -1;  // Initially, no parent for any node
        }

        key[0] = 0;  // Start from vertex 0 (arbitrary choice)

        // The MST will have V vertices
        for (int count = 0; count < V; count++) {
            // Pick the vertex u not in MST with the minimum key value
            int u = FindMinKey(key, mstSet, V);

            // Include u in the MST
            mstSet[u] = true;

            // Update key and parent for adjacent vertices of u
            for (int v = 0; v < V; v++) {
                // graph[u][v] != 0 --> there is an edge
                // !mstSet[v] --> vertex v is not yet included in MST
                // graph[u][v] < key[v] --> edge weight is less than current key
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the resulting MST
        PrintMST(parent, graph, V);
    }

    // Function to find the vertex with the minimum key value not yet included in MST
    static int FindMinKey(int[] key, boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // Function to print the MST stored in parent[]
    static void PrintMST(int[] parent, int[][] graph, int V) {
        int totalCost = 0;

        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++) {
            // Print edge from parent[i] to i and its weight
            System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]]; // Add weight to total cost
        }

        System.out.println("Total cost of MST: " + totalCost);
    }

    // Main function to drive the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        // Input adjacency matrix
        System.out.printf("Enter the adjacency matrix (%dx%d):\n", V, V);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();

                // Ensure no self-loop
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        // Run Prim's algorithm
        PrimMST(graph, V);

        sc.close();  // Close scanner
    }
}
