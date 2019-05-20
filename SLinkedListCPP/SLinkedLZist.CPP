/*
 * SLinkedLZist.CPP
 *
 *  Created on: Oct 26, 2017
 *      Author: gallbr02
 */


#include <iostream>
using namespace std;

#include "SLinkedList.h"

int main(){
	SLinkedList<int> list = SLinkedList<int>();
	list.addLast(5);
	list.addLast(6);
	list.addLast(3);

	int value1 = list.getFirst();
	list.remove(value1);

	int value2 = list.getFirst();
	list.remove(value2);

	int value3 = list.getFirst();
	list.remove(value3);



}
#endif

