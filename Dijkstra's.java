package A_DSA;

import java.util.Scanner;

public class Dijkstra {

    // Function to find the vertex with minimum distance not yet visited
    static int FindMinDistance(int[] dist, boolean[] visited, int n) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // Function to perform dijkstraAlgo's algorithm
    static void dijkstraAlgo(int[][] graph, int src, int n) {
        int[] dist = new int[n];         // To store shortest distances from src
        boolean[] visited = new boolean[n]; // visited[i] is true if vertex I is processed

        // Initialize all distances to infinity and visited[] to false
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0; // Distance to source is 0

        // Process all vertices
        for (int count = 0; count < n - 1; count++) {
            // Pick the unvisited vertex with the minimum distance
            int u = FindMinDistance(dist, visited, n);
            visited[u] = true;

            // Update distance of adjacent vertices of the picked vertex
            for (int v = 0; v < n; v++) {
                // Conditions:
                // 1. Not yet visited
                // 2. There is an edge from u to v
                // 3. A shorter path to v through u is found
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Output the distances
        PrintDistances(dist, src, n);
    }

    // Function to print the shortest distances from the source
    static void PrintDistances(int[] dist, int src, int n) {
        System.out.println("Vertex\tDistance from Source " + src);
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();

        // Input adjacency matrix
        int[][] graph = new int[n][n];
        System.out.printf("Enter the adjacency matrix (%dx%d):\n", n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Input source vertex
        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();

        // Run dijkstraAlgo's algorithm
        dijkstraAlgo(graph, src, n);

        sc.close();
    }
}
