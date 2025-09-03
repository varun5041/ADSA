#include <stdio.h>
#include <stdlib.h>

#define MAX 10   // Maximum size of queue and graph

// Global variables for Queue
int queue[MAX];
int front = -1, rear = -1;

// Array to mark visited vertices
int visited[MAX];

// Function to insert element in queue
void enqueue(int vertex) {
    if (rear == MAX - 1) {   // Check for overflow
        printf("Queue Overflow!\n");
        return;
    }
    if (front == -1) {   // First insertion
        front = 0;
    }
    rear++;
    queue[rear] = vertex;
}

// Function to remove element from queue
int dequeue() {
    if (front == -1) {   // Queue empty condition
        return -1;
    }

    int element = queue[front];

    // If only one element left
    if (front == rear) {
        front = -1;
        rear = -1;
    } else {
        front++;
    }

    return element;
}

// Breadth First Search function
void BFS(int graph[MAX][MAX], int n, int start) {
    // Mark all nodes as unvisited initially
    for (int i = 0; i < n; i++) {
        visited[i] = 0;
    }

    // Start BFS from the given start node
    enqueue(start);
    visited[start] = 1;

    printf("BFS Traversal: ");

    // Run until queue is empty
    while (front != -1) {
        int current = dequeue();
        printf("%d ", current);

        // Explore all adjacent vertices of current
        for (int j = 0; j < n; j++) {
            if (graph[current][j] == 1 && visited[j] == 0) {
                enqueue(j);
                visited[j] = 1;
            }
        }
    }

    printf("\n");
}

// Driver code
int main() {
    int vertices, start;
    int graph[MAX][MAX];

    printf("Enter number of vertices: ");
    scanf("%d", &vertices);

    printf("Enter the adjacency matrix (%d x %d):\n", vertices, vertices);
    for (int i = 0; i < vertices; i++) {
        for (int j = 0; j < vertices; j++) {
            scanf("%d", &graph[i][j]);
        }
    }

    printf("Enter starting vertex: ");
    scanf("%d", &start);

    BFS(graph, vertices, start);

    return 0;
}