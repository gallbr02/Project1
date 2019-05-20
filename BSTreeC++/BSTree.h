/*
 * BSTree.h
 *
 *  Created on: Oct 30, 2017
 *      Author: gallbr02
 */

#ifndef BSTREE_H_
#define BSTREE_H_


#include <string>
#include<stack>
#include<queue>
#include <vector>
using namespace std;

#include "to_string.h"


template <typename E, typename Comparator>
class BSTree
{
private:
	class Node
	{
	public:
		E data;
		Node* left ;
		Node* right;
		Node* parent;

		Node(E d)
		{
			data = d;
			left = nullptr;
			right = nullptr;
			parent = nullptr;
		}
	public:
		E getData() {
			return data;
		}

		Node getLeft() {
			return left;
		}

		Node getRight() {
			return right;
		}

		Node getParent() {
			return parent;
		}
	};

private:
	Node* root;
	Comparator comp;

public:
	BSTree() {
		this->root = root;
		this->comp = comp;
	}

	BSTree(Comparator comp) {
		root = nullptr;
	}

	~BSTree()
	{
		// clear();
	}
	void clear()
	{
		Node* curr = root;
		//		while(curr->right != nullptr && curr->left != nullptr){
		//			Node* temp = curr->n;
		//			delete curr;
		//			curr = temp;
		//		}
		root = nullptr;

	}

public:
	bool isEmpty() const{
		return root  == nullptr;
	}

	E maxValueLoop() {
		if(root == nullptr){
			throw exception();
		}
		Node* max = findMaxValueLoop(root);
		E maxData = max->data;
		return maxData;
	}

private:
	Node* findMaxValueLoop (Node* curr) const{
		if(curr == nullptr){
			return nullptr;
		}
		while(curr->right != nullptr){
			curr = curr->right;
		}
		return curr;
	}

public:
	E minValueLoop() const{
		if(root == nullptr) {
			throw exception();
		}
		Node* min = findMinValueLoop(root);
		E minData = min->data;
		return minData;
	}

private:
	Node* findMinValueLoop(Node* curr)const {
		if(curr == nullptr) {
			return nullptr;
		}
		while (curr->left != nullptr) {
			curr = curr->left;
		}
		return curr;
	}

public:
	bool containsLoop(E item) {
		Node* curr = root;
		if(findNodeLoop(curr, item)== nullptr){
			return false;
		}
		return true;
	}

private:
	Node* findNodeLoop(Node* curr, E item) {
		while(curr != nullptr) {
			if(comp.compare(item, curr->data)==-1) {
				curr = curr->left;
			}
			else if(comp.compare(item,  curr->data)==1) {
				curr = curr->right;
			}else {
				return curr;
			}
		}
		return nullptr;
	}


public:
	void addLoop(E item) {
		if(root == nullptr) {
			root = new Node (item);
			return;
		}
		Node* curr = root;
		while(curr != nullptr) {
			if(comp.compare(item, curr->data)==-1) {
				if(curr->left == nullptr) {
					curr->left = new Node (item);
					curr->left->parent = curr;
					return;
				}
				curr = curr->left;
			}
			else if(comp.compare(item, curr->data)==1) {
				if(curr->right == nullptr) {
					curr->right = new Node (item);
					curr->right->parent = curr;
					return;
				}
				curr = curr->right;
			}
		}
	}

	void add(E item) {
		if(root == nullptr) {
			root = new Node(item);
		}else {
			add(root, item);
		}
	}

	void add(Node* curr, E item) {
		if(root == nullptr) {
			root = new Node (item);
			return;
		}
		if(comp.compare(item, curr->data)==-1) {
			if(curr->left == nullptr) {
				curr->left = new Node (item);
				curr->left->parent = curr;
				return;
			}else {
				add(curr->left, item);
			}
		}
		else if(comp.compare(item, curr->data)==1) {
			if(curr->right == nullptr) {
				curr->right = new Node (item);
				curr->right->parent = curr;

				return;
			}else {
				add(curr->right,item);
			}
		}
	}


	E maxValue() {
		if(root == nullptr) {
			throw exception();
		}
		Node* max = findMaxNode(root);
		E maxData = max->data;
		return maxData;
	}

private:
	Node* findMaxNode (Node* curr)const {
		if(curr == nullptr) {
			return nullptr;
		}
		else if(curr->right == nullptr) {
			return curr;
		}else {
			return findMaxNode(curr->right);
		}
	}

public:
	E minValue() {
		if(root == nullptr) {
			throw exception();
		}
		Node* min = findMinNode(root);
		E minData = min->data;
		return minData;
	}

private:
	Node* findMinNode (Node* curr) const{
		if(curr == nullptr) {
			return nullptr;
		}
		else if(curr->left == nullptr) {
			return curr;
		}else {
			return findMinNode(curr->left);
		}
	}

public:
	bool contains (const E& item) {
		if(findNode(root,item)!=nullptr ) {
			return true;
		}else {
			return false;
		}
	}

private:
	Node* findNode (Node* curr, E item) {

		if(curr == nullptr) {
			return nullptr;
		}
		if(comp.compare(item, curr->data)==0) {
			return curr;
		}
		else if(comp.compare(item, curr->data)==-1) {
			Node*findleft = findNode(curr->left, item);
			return findleft;;
		}
		else if(comp.compare(item,  curr->data)==1) {
			Node*findRight = findNode(curr->right, item);
			return findRight;
		}
		return curr;
	}

public:
	bool remove(E item) {
		Node* remove = findNode(root, item);
		if(remove == nullptr) {
			return false;
		}
		else if(remove->right== nullptr || remove->left == nullptr) {
			removeMissing(remove);
			return true;
		}
		else if(remove->right != nullptr && remove->left != nullptr) {
			removeHasBoth(remove);
			return true;
		}else {
			return false;
		}
	}

	void removeMissing(Node* node) {
		if(node == root) {
			if (node->right != nullptr) {
				node = node->right;
				delete node;
				root = node;

			}
			else if(node->left != nullptr) {
				node = node->left;
				delete node;
				root = node;
			}else {
				root = nullptr;
			}
			return;
		}
		if(node->right == nullptr && node->left == nullptr) {
			if(node->parent->right == node) {
				node->parent->right = nullptr;
				delete node;
			}
			else {
				node->parent->left = nullptr;
			}
		}
		else if(node->right!= nullptr && node->left == nullptr) {
			if(node->parent->left == node) {
				node->parent->left = node->right;
				delete node;
			}else {
				node->parent->right = node->right;
				node->right->parent = node->parent;
				delete node;
			}
			return;
		}
		else if(node->right == nullptr && node->left != nullptr) {
			if(node->parent->right == node) {
				node->parent->right = node->left;
				delete node;
			}else {
				node->parent->left = node->left;
				node->left->parent = node->parent;
				delete node;
			}
			return;
		}
	}
	void removeHasBoth (Node* node) {
		Node* max = findMaxNode(node->left);
		node->data = max->data;
		removeMissing(max);
		delete node;
	}

	template <typename Visitor>
	void preorder(Visitor& visitor)const {
		preorder(visitor, root);
	}

	template <typename Visitor>
	void preorder(Visitor& visitor, Node* curr)const {
		if(curr == nullptr) {
			return;
		}else {
			visitor.visit(curr->data);
			preorder(visitor, curr->left);
			preorder(visitor, curr->right);
		}
	}

	template <typename Visitor>
	void inorder(Visitor&visitor) const{

		inorder(visitor,root);
	}

	template <typename Visitor>
	void inorder(Visitor& visitor, Node* curr)const {
		if(curr == nullptr) {
			return;
		}
		inorder(visitor,curr->left);
		visitor.visit(curr->data);
		inorder(visitor,curr->right);
	}

	template <typename Visitor>
	void postorder(Visitor&visitor)const {

		postorder(visitor,root);
	}

	template <typename Visitor>
	void postorder(Visitor& visitor, Node* curr) const{
		if(curr == nullptr) {
			return;
		}
		postorder(visitor, curr->left);
		postorder(visitor, curr->right);
		visitor.visit(curr->data);
	}

	class PreIterator
	{
	private:
		stack<Node*> myStack;
	public:
		PreIterator(Node* root) {
			myStack = stack<Node*>();
			if(root == nullptr) {
				return;
			}else {
				myStack.push(root);
			}
		}
		bool hasNext() {
			if(!(myStack.empty())) {
				return true;
			}else {
				return false;
			}
		}
		E next() {
			if(this->hasNext()==true) {
				Node* curr = myStack.top();
				myStack.pop();
				if(curr->right != nullptr) {
					myStack.push(curr->right);
				}
				if(curr->left != nullptr) {
					myStack.push(curr->left);
				}
				return curr->data;
			}else{
				throw exception();
			}

		}
	};
	class LevelIterator
	{

	private:
		queue<Node*> myQ;
	public:
		LevelIterator(Node* root) {
			myQ = queue<Node*>();
			if(root == nullptr) {
				return;
			}else {
				myQ.push(root);
			}
		}
		bool hasNext() {
			if(!(myQ.empty())) {
				return true;
			}else {
				return false;
			}
		}
		E next()
		{
			if(this->hasNext()==true) {
				Node*curr = myQ.front();
				myQ.pop();
				if(curr->left != nullptr) {
					myQ.push(curr->left);
				}
				if(curr->right != nullptr) {
					myQ.push(curr->right);
				}
				return curr->data;
			}else{
				throw exception ();
			}
		}
	};


	string toString()const {
		LevelIterator iterator = LevelIterator(root);
		string str ="";
		while(iterator.hasNext()) {
			str += " " + to_string(iterator.next());
		}
		str = "[" + trim(str) + "]";
		return str;
	}

	string toStringPre()const{
		PreIterator preIterator =  PreIterator(root);
		string str= "";
		while(preIterator.hasNext()) {
			str+= " " +to_string(preIterator.next());
		}
		str = "[" + trim(str) + "]";
		return str;

	}

	bool operator==(BSTree<E,Comparator >& other) {
		if(this == &other) {
			return true;
		}else{
			return equals(this->root, other.root);
		}

	}

	bool equals(Node* root1, Node* root2) {
		if(root1 == nullptr || root2 == nullptr) {
			return false;
		}else if(root1->data==(root2->data)) {
			if(equals(root1->right, root2->right)== true && equals(root1->left, root2->left)== true){
				return true;
			}
			return true;
		}else {
			return false;
		}
	}
public:
	BSTree(BSTree<E, Comparator>& orig)
{
		BSTree<E, Comparator> copy = BSTree<E, Comparator>();
		this->comp = comp->comp;
		copyTree(this->root) = copy->root;
		return copy;
}

private:

	// I copy triangles
	Node copyTree(Node* curr)const {
		Node* leftTri;
		Node* rightTri;
		if(curr == nullptr) {
			return nullptr;
		}
		Node* copy = new Node(curr->data);
		leftTri = copyTree(copy->left);
		rightTri = copyTree(copy->right);
		leftTri->parent = copy;
		copy->left = leftTri;
		rightTri->parent = copy;
		copy->right = rightTri;
		return copy;

	}


public:
	BSTree(vector<E> items, Comparator comp)
{
		this->comp = comp;
		this->root = rebuildPreorder(items, 0, items.size());
}
private:
	Node* rebuildPreorder(vector<E> items, int i, int j) {
		if(i == j) {
			return nullptr;
		}else {
			Node* curr = new Node(items[i]);
			int m = i +1;
			while(comp.compare(items[i], items[m])== 1) {
				m++;
				rebuildPreorder(items, i, j);
			}
		}
		return root;

	}

};


#endif /* BSTREE_H_ */
