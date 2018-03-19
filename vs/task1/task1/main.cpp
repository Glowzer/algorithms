#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstdio>

using namespace std;

class BST {
	struct node {
		int data;
		int height = -1;
		int countChilds = 0;
		bool isSolution = false;
		node* left = NULL;
		node* right = NULL;
	};
	node* root = NULL;
	node* searchNode;
	int numLeftChilds;
	int numRightChilds;
	int Lheight;
	int Rheight;
	int amount;

	node* insert(int x, node* t) {
		if (!t) {
			t = new node;
			t->data = x;
		}
		if (x < t->data) 
			t->left = insert(x, t->left);
		else if (x > t->data) 
			t->right = insert(x, t->right);
		return t;
	}
	node* findMin(node* t) {
		if (!t) return NULL;
		if (!t->left) return t;
		return findMin(t->left);
	}
	node* deleteRecursivly(int x, node* t) {
		node* temp;
		if (!t) return NULL;
		if (x < t->data) t->left = deleteRecursivly(x, t->left);
		else if (x > t->data) t->right = deleteRecursivly(x, t->right);
		else if (t->left && t->right)
		{
			temp = findMin(t->right);
			t->data = temp->data;
			t->right = deleteRecursivly(t->data, t->right);
		}
		else
		{
			temp = t;
			if (t->left == NULL)
				t = t->right;
			else if (t->right == NULL)
				t = t->left;
			delete temp;
		}
		return t;
	}
	void preOrder(node* t) {
		if (!t) return;
		cout << t->data << endl;
		preOrder(t->left);
		preOrder(t->right);
	}
	void postOrder(node* t) {
		if (!t)	return;
		postOrder(t->left);
		postOrder(t->right);
		if (t->left) {
			Lheight = t->left->height;
			numLeftChilds = t->left->countChilds;
		}
		else {
			Lheight = -1;
			numLeftChilds = 0;
		}
		if (t->right) {
			Rheight = t->right->height;
			numRightChilds = t->right->countChilds;
		}
		else {
			Rheight = -1;
			numRightChilds = 0;
		}
		if ((numLeftChilds == numRightChilds) && (Lheight != Rheight)) {
			t->isSolution = true;
			amount++;
		}
		else t->isSolution = false;
		t->countChilds = numLeftChilds + numRightChilds + 1;
		t->height = (Lheight > Rheight) ? Lheight + 1 : Rheight + 1;
	}
	void inOrder(node* t) {
		if (!t || amount < 0) return;
		inOrder(t->left);
		if (t->isSolution)
			amount--;
		if (!amount) {
			searchNode = t;
			amount--;
		}
		inOrder(t->right);
	}
public:
	BST() {
		root = NULL;
	}
	void insert(int x) {
		root = insert(x, root);
	}
	void printToFile() {
		this->preOrder(root);
	}
	void solution() {
		amount = 0;
		this->postOrder(root);
		if (amount % 2) {
			amount = amount / 2 + 1;
			this->inOrder(root);
			if (searchNode) root = deleteRecursivly(searchNode->data, root);
		}
	}
};

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	BST tree;
	int x = 0;
	while (cin >> x) {
		tree.insert(x);
	}
	tree.solution();
	tree.printToFile();

	return 0;
}