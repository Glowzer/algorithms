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
	long long addBT(node* root) {
		if (root == NULL)
			return 0;
		return root->data + addBT(root->left) + addBT(root->right);
	}

public:
	BST()
	{
		root = NULL;
	}
	void insert(long long x) {
		root = insert(x, root);
	}
	long long sum() {
		return addBT(root);
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
	cout << tree.sum();

	return 0;
}