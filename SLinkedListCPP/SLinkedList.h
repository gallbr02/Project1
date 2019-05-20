/*
 * SLinkedList.h
 *
 *  Created on: Oct 24, 2017
 *      Author: gallbr02
 */

#ifndef SLINKEDLIST_H_
#define SLINKEDLIST_H_

#include <exception>
#include <string>
using namespace std;

template <typename E>
//template <typename k, typename v>
class SLinkedList
{

private:
	class Node
	{
		E data;
		Node*  next;

		Node(E d)
		{
			data = d;
			next = nullptr;
		}
	};

	Node* head;


public:
	SLinkedList()
{
		head = nullptr;
}
public:
	~SLinkedList()
	{
		clear();

	}
	void clear()
	{
		Node* curr = head;
		while(curr != nullptr){
			Node* temp = curr->next;
			delete curr;
			curr = temp;
		}
		head = nullptr;
	}
	bool isEmpty() const
	{
		return head == nullptr;
	}


	void addFirst(const E data)
	{
		Node* node = new Node(data);
		node->next = head;
		head = node;
	}


	E getFirst() const
	{
		if (this->isEmpty()) {
			throw exception();
		}

		return head->data;
	}



	void addLast(const E data)
	{

		if (this->isEmpty()) {
			this->addFirst(data);
		}
		else {

			Node* curr = head;
			while (curr->next != nullptr) {
				curr = curr->next;
			}

			Node* node = new Node(data);
			curr->next = node;
		}
	}



	bool remove(const E data)
	{
		Node* prev = head;
		Node* curr = head;


		while (curr != nullptr) {
			if (curr->data.equals(data)) {

				if (head == curr) {
					head = curr->next;
					delete curr;
				}
				else {
					prev->next = curr->next;
					delete curr;
				}
				return true;
			}

			prev = curr;
			curr = curr->next;
		}

		return false;
	}


	string toString() const
	{
		string str = "";
		Node* curr = head;

		while (curr != nullptr) {
			str += curr->data + " ";
			curr = curr->next;
		}


		str = "[" + str() + "]";

		return str;
	}
};


#endif /* SLINKEDLIST_H_ */
