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


	node* findMin(node* t)
	{
		if (t == NULL)
			return NULL;

		if (t->left == NULL)
			return t;
		else
			return findMin(t->left);
	}

	node* deleteRecursivly(long long x, node* t)
    {
        node* temp;
        if(t == NULL)
            return NULL;
        if(x < t->data)
            t->left =deleteRecursivly(x, t->left);
        else if(x > t->data)
            t->right =deleteRecursivly(x, t->right);
        else if(t->left && t->right)
        {
            temp = findMin(t->right);
            t->data = temp->data;
            t->right =deleteRecursivly(t->data, t->right);
        }
        else
        {
            temp = t;
            if(t->left == NULL)
                t = t->right;
            else if(t->right == NULL)
                t = t->left;
            delete temp;
        }
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
	void deleteRecursivly(long long x) {
		root = deleteRecursivly(x, root);
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
	long long toDel = 0;
	cin >> toDel;
	while (cin >> x) {
		tree.insert(x);
	}
	tree.deleteRecursivly(toDel);
	tree.printToFile();

	return 0;
}