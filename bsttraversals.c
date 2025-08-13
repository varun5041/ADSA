#include <stdio.h>
#include <stdlib.h>

// Structure for a node of the binary search tree
struct node {
    int data;
    struct node* left;
    struct node* right;
};

// Function to create a new node with the given value
struct node* create_node(int value) {
    struct node* new_node = (struct node*)malloc(sizeof(struct node));
    if (!new_node) {
        printf("Memory allocation failed!\n");
        exit(1);
    }
    new_node->data = value;
    new_node->left = new_node->right = NULL;
    return new_node;
}

// Function to insert a new value into the BST
struct node* insert(struct node* root, int value) {
    if (root == NULL) {
        return create_node(value);
    }
    if (value < root->data) {
        root->left = insert(root->left, value);
    } 
    else if (value > root->data) { // Prevent duplicates
        root->right = insert(root->right, value);
    }
    else {
        printf("Duplicate value %d ignored.\n", value);
    }
    return root;
}

// Function to perform in-order traversal
void inorder(struct node* root) {
    if (root != NULL) {
        inorder(root->left);
        printf("%d ", root->data);
        inorder(root->right);
    }
}

// Function to perform pre-order traversal
void preorder(struct node* root) {
    if (root != NULL) {
        printf("%d ", root->data);
        preorder(root->left);
        preorder(root->right);
    }
}

// Function to perform post-order traversal
void postorder(struct node* root) {
    if (root != NULL) {
        postorder(root->left);
        postorder(root->right);
        printf("%d ", root->data);
    }
}

// Function to search for a value in the BST
struct node* search(struct node* root, int value) {
    if (root == NULL || root->data == value) {
        return root;
    }
    if (value < root->data) {
        return search(root->left, value);
    }
    return search(root->right, value);
}

// Function to free all nodes in the tree
void free_tree(struct node* root) {
    if (root != NULL) {
        free_tree(root->left);
        free_tree(root->right);
        free(root);
    }
}

int main() {
    struct node* root = NULL;
    int choice, value, num_elements;

    while (1) {
        printf("\nEnter your choice:\n");
        printf("1. Insert multiple nodes\n");
        printf("2. In-order traversal\n");
        printf("3. Pre-order traversal\n");
        printf("4. Post-order traversal\n");
        printf("5. Search for a value\n");
        printf("6. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);

        switch(choice) {
            case 1:
                
                printf("How many elements do you want to insert? ");
                scanf("%d", &num_elements);
                int i;
                for (i = 0; i < num_elements; i++) {
                    printf("Enter value to insert: ");
                    scanf("%d", &value);
                    root = insert(root, value);
                }
                printf("%d nodes inserted.\n", num_elements);
                break;

            case 2:
                printf("In-order traversal: ");
                inorder(root);
                printf("\n");
                break;

            case 3:
                printf("Pre-order traversal: ");
                preorder(root);
                printf("\n");
                break;

            case 4:
                printf("Post-order traversal: ");
                postorder(root);
                printf("\n");
                break;

            case 5:
                printf("Enter value to search: ");
                scanf("%d", &value);
                if (search(root, value) != NULL) {
                    printf("Value %d found in the tree.\n", value);
                } else {
                    printf("Value %d not found in the tree.\n", value);
                }
                break;

            case 6:
                printf("Exiting program.\n");
                free_tree(root);
                return 0;

            default:
                printf("Invalid choice! Please select a valid option.\n");
        }
    }
    return 0;
}
