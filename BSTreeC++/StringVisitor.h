/*
 * StringVisitor.h
 *
 *  Created on: Oct 30, 2017
 *      Author: gallbr02
 */

#ifndef STRINGVISITOR_H_
#define STRINGVISITOR_H_

#include <string>
using namespace std;

template <typename E>
class StringVisitor
{
private:
	string str;

public:
	StringVisitor() {
		str = "";
	}

	void visit(E item) {
		str += to_string(item) + " ";

	}

	string getValue() {
		return "[" + trim(str) + "]";
	}
};


#endif
