/*
 * IntComparator.h
 *
 *  Created on: Oct 30, 2017
 *      Author: gallbr02
 */


#ifndef INTCOMPARATOR_H_
#define INTCOMPARATOR_H_

class IntComparator
{
public:
	int compare(int i1, int i2) {
		if(i1<i2) {
			return -1;
		}
		else if(i1 > i2) {
			return 1;
		}
		else {
			return 0;
		}
	}
};

#endif

