/*
 * BSTreeTest.h
 *
 *  Created on: Oct 30, 2017
 *      Author: gallbr02
 */

#ifndef BSTREETEST_H_
#define BSTREETEST_H_

#include "CppUnit.h"
#include "BSTree.h"
#include "IntComparator.h"
#include "StringVisitor.h"

#include <exception>
#include <iostream>
using namespace std;

class BSTreeTest
{
public:


	BSTree<int, IntComparator> empty;
	BSTree<int, IntComparator> single;
	BSTree<int, IntComparator> multi;


	IntComparator comp;
	StringVisitor<int>strVisitor;

public:
	void setUp()
	{
		comp = IntComparator();
		empty = BSTree<int, IntComparator>(comp);
		assertEquals( empty.toString(), "[]" );

		single = BSTree<int, IntComparator>(comp);
		single.addLoop(8);
		assertEquals( single.toString(), "[8]" );

		multi =  BSTree<int, IntComparator>(comp);
		multi.addLoop(6);
		multi.addLoop(4);
		multi.addLoop(7);
		multi.addLoop(3);
		multi.addLoop(9);
		multi.addLoop(1);

		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		cout << "setup done"<< endl;
	}
	void test()
	{
		cout<< "running test..." << endl;
		setUp();test_isEmpty(); cout<<"finish test_isEmpty()"<<endl;
		setUp();test_maxValueLoop(); cout<<"finish test_maxValueLoop()"<<endl;
		setUp();test_minValueLoop(); cout<<"finish test_minValueLoop()"<<endl;
		setUp();test_containsLoop(); cout<<"finish test_containsLoop()"<<endl;
		setUp();test_addLoop(); cout<<"finish test_addLoop()"<<endl;
		setUp();test_add(); cout<<"finish test_add()"<<endl;
		setUp();test_maxValue(); cout<<"finish test_maxValue()"<<endl;
		setUp();test_minValue(); cout<<"finish test_minValue()"<<endl;
		setUp();test_contains(); cout<<"finish test_contains()"<<endl;
		setUp();test_remove(); cout<<"finish test_remove()"<<endl;
		setUp();test_preorder(); cout<<"finish test_preorder()"<<endl;
		setUp();test_inorder(); cout<<"finish test_inorder()"<<endl;
		setUp();test_postorder(); cout<<"finish test_postorder()"<<endl;
		setUp();test_CountRangeVisitor(); cout<<"finish test_CountRangeVisitor()"<<endl;
		setUp();test_toString(); cout<<"finish test_toString()"<<endl;
		setUp();test_toStringPre(); cout<<"finish test_toStringPre()"<<endl;
		setUp();test_equals(); cout<<"finish test_equals()"<<endl;
		setUp();test_BSTree(); cout<<"finish test_BSTree()"<<endl;
		cout<<"done testing"<<endl;
	}


public:
	void test_isEmpty() {

		assertTrue( empty.isEmpty() );
		assertEquals( empty.toString(), "[]" );

		assertFalse( single.isEmpty() );
		assertEquals( single.toString(), "[8]" );

		assertFalse( multi.isEmpty() );
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_maxValueLoop() {
		try { empty.maxValueLoop(); fail();}
		catch (exception e) {}
		assertEquals( empty.toString(), "[]" );

		//assertTrue( single.maxValueLoop()==(8) );
		assertEquals( single.toString(), "[8]" );

		//assertTrue( multi.maxValueLoop()==(9) );
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}


	void test_minValueLoop() {
		try { empty.minValueLoop(); fail();}
		catch (exception e) {}
		assertEquals( empty.toString(), "[]" );

		assertTrue( single.minValueLoop()== 8);
		assertEquals( single.toString(), "[8]" );

		assertTrue( multi.minValueLoop()==(1) );
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_containsLoop() {
		empty.containsLoop(1);
		assertEquals( empty.toString(), "[]");

		assertTrue( single.containsLoop(8)) ;
		assertEquals( single.toString(), "[8]" );

		//assertFalse( multi.containsLoop(1)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		//assertFalse( multi.containsLoop(10)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertTrue( multi.containsLoop(3)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_addLoop() {
		empty.addLoop(1);
		assertEquals( empty.toString(), "[1]");

		single.addLoop(9) ;
		assertEquals( single.toString(), "[8 9]" );

		multi.addLoop(5) ;
		assertEquals( multi.toString(), "[6 4 7 3 5 9 1]" );
	}


	void test_add() {
		empty.add(1);
		assertEquals( empty.toString(), "[1]");

		single.add(9) ;
		assertEquals( single.toString(), "[8 9]" );

		multi.add(2) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1 2]" );
	}

	void test_maxValue() {
		try{ empty.maxValue(); fail();}
		catch (exception e) {}
		assertEquals( empty.toString(), "[]");

		assertTrue( single.maxValue()==(8));
		assertEquals( single.toString(), "[8]" );

		assertTrue ( multi.maxValue()==(9));
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_minValue() {
		try{ empty.minValue(); fail();}
		catch (exception e) {}
		assertEquals( empty.toString(), "[]");

		assertTrue( single.minValue()==(8));
		assertEquals( single.toString(), "[8]" );

		assertFalse( multi.minValue()==(4));
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertTrue( multi.minValue()==(1)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_contains() {
		empty.contains(1);
		assertEquals( empty.toString(), "[]");

		assertTrue( single.contains(8)) ;
		assertEquals( single.toString(), "[8]" );

		assertFalse (multi.contains(2)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertFalse( multi.contains(5)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertTrue (multi.contains(3)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

	}

	void test_remove() {
		empty.remove(0);
		assertEquals( empty.toString(), "[]");

		single.remove(8) ;
		assertEquals( single.toString(), "[]" );

		multi.remove(1) ;
		assertEquals( multi.toString(), "[6 4 7 3 9]" );
	}

	void test_preorder() {
		strVisitor = StringVisitor<int>();
		empty.preorder(strVisitor);
		assertEquals(strVisitor.getValue(), "[]");

		strVisitor = StringVisitor<int>();
		single.preorder(strVisitor) ;
		assertEquals( strVisitor.getValue(), "[8]" );

		strVisitor = StringVisitor<int>();
		multi.preorder(strVisitor) ;
		assertEquals( strVisitor.getValue(), "[6 4 3 1 7 9]" );
	}

	void test_inorder() {
		strVisitor =StringVisitor<int>();
		empty.inorder(strVisitor);
		assertEquals( strVisitor.getValue(), "[]");

		strVisitor = StringVisitor<int>();
		single.inorder(strVisitor) ;
		assertEquals( strVisitor.getValue(), "[8]" );

		strVisitor = StringVisitor<int>();
		multi.inorder(strVisitor) ;
		assertEquals(strVisitor.getValue(), "[1 3 4 6 7 9]");
	}

	void test_postorder() {
		strVisitor = StringVisitor<int>();
		empty.postorder(strVisitor);
		assertEquals( strVisitor.getValue(), "[]");

		strVisitor =StringVisitor<int>();
		single.postorder(strVisitor) ;
		assertEquals( strVisitor.getValue(), "[8]" );

		strVisitor = StringVisitor<int>();
		multi.postorder(strVisitor) ;
		assertEquals( strVisitor.getValue(), "[1 3 4 9 7 6]" );
	}

	void test_CountRangeVisitor() {
		//		empty.CountRangeVisitor();
		//		assertEquals( empty.toString(), "[]");
		//
		//		single.CountRangeVisitor(8) ;
		//		assertEquals( single.toString(), "[]" );
		//
		//		multi.CountRangeVisitor(1) ;
		//		assertEquals( multi.toString(), "[6 4 7 3 9]" );
	}

	void test_toString() {
		empty.toString();
		assertEquals( empty.toString(), "[]");

		single.toString() ;
		assertEquals( single.toString(), "[8]" );

		multi.toString() ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_toStringPre() {
		assertEquals( empty.toStringPre(), "[]");
		assertEquals( empty.toString(), "[]");

		assertEquals( single.toStringPre(), "[8]") ;
		assertEquals( single.toString(), "[8]" );

		assertEquals( multi.toStringPre(), "[6 4 3 1 7 9]") ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	void test_equals() {
		assertFalse(empty==(multi));
		assertTrue(empty==(empty));

		assertFalse(single==(empty)) ;
		assertTrue(single==(single));

		assertFalse(multi==(single)) ;
		assertTrue(multi==(multi));
	}

//	void test_clone() {
//		empty.clone();
//		assertEquals( empty.toString(), "[]");
//
//		single.clone() ;
//		assertEquals( single.toString(), "[]" );
//
//		multi.clone() ;
//		assertEquals( multi.toString(), "[6 4 7 3 9]" );
//	}

	void test_BSTree() {
		vector<int>numbers = {6, 4, 7, 3, 9};
		//BSTree<int, IntComparator> tree = BSTree<int, IntComparator>(numbers, comp);
		BSTree<int, IntComparator> tree(numbers, comp);
		assertEquals( tree.toStringPre(), "[6 4 7 3 9]");

	}

};


#endif /* BSTREETEST_H_ */
