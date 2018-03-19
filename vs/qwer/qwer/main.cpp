#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstdio>

using namespace std;

class BST
{
	struct node
	{
		long long data;
		node* left = NULL;
		node* right = NULL;
	};
	node* root = NULL;
	node* insert(long long x, node* t)
	{
		if (t == NULL) {
			t = new node;
			t->data = x;
			t->left = t->right = NULL;
		}
		if (x < t->data)
			t->left = insert(x, t->left);
		else if (x > t->data)
			t->right = insert(x, t->right);
		return t;
	}
	void preorder(node* t)
	{
		if (t == NULL) return;
		cout << t->data << endl;
		preorder(t->left);
		preorder(t->right);
	}
public:
	BST()
	{
		root = NULL;
	}
	void insert(long long x) {
		root = insert(x, root);
	}
	void printToFile() {
		this->preorder(root);
	}
};

int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	BST tree;
	long long x = 0;
	while (cin >> x) {
		tree.insert(x);
	}
	tree.printToFile();
	
	return 0;
}