/*
 * Collection of functions for a simple unit test framework modeled after JUnit.
 */

#ifndef CPPUNIT_H_
#define CPPUNIT_H_


#include <iostream>
#include <string>
class CppUnit;
using namespace std;


/*
 * Checks if the two expressions are equal (==).
 *
 * @param e1      the first  expression to compare
 * @param e2      the second expression to compare
 * @return        true if the two expressions are equal (==); false otherwise
 */
#define assertEquals(e1, e2) if (!CppUnit::__assertEquals( (e1), (e2) )) { cout << "CppUnit failure: assertEquals(" << #e1 << ", " << #e2 << ")" << endl; cout << "                 -->-->-->-->(" << e1 << ", " << e2 << ")" << endl; }


/*
 * Checks if the two expressions are different (!=).
 *
 * @param e1      the first  expression to compare
 * @param e2      the second expression to compare
 * @return        true if the two expressions are different (!=); false otherwise
 */
#define assertNotEquals(e1, e2) if (!CppUnit::__assertNotEquals( (e1), (e2) )) { cout << "CppUnit failure: assertNotEquals(" << #e1 << ", " << #e2 << ")" << endl; cout << "                 -->-->-->-->-->(" << e1 << ", " << e2 << ")" << endl; }


/*
 * Checks if the given condition is true.
 *
 * @param cond    the condition to check
 * @return        true if the given condition is true
 */
#define assertTrue(e) if (!CppUnit::__assertTrue( (e) )) { cout << "CppUnit failure: assertTrue(" << #e << ")" << endl; }


/*
 * Checks if the given condition is false.
 *
 * @param cond    the condition to check
 * @return        true if the given condition is false
 */
#define assertFalse(e) if (!CppUnit::__assertFalse( (e) )) { cout << "CppUnit failure: assertFalse(" << #e << ")" << endl; }


/*
 * Displays a message indicating failure.
 *
 * @param msg     the (optional) message to display
 */
template <typename T=string>
void fail(const T& msg = "") { 	cout << "CppUnit: fail called " << ((msg != "") ? "with message "+msg : ""); cout << endl; }


/*
 * Wrapper for selected methods modeled after JUnit -- do not use directly.
 */
class CppUnit
{
public:
	/*
	 * See macro assertEquals.
	 */
	template <typename T>
	static bool __assertEquals(const T& e1, const T& e2) { return e1 == e2; }
	static bool __assertEquals(const string& e1, const char* e2) 	{ return __assertEquals<string>(e1, string(e2)); }
  ;
	static bool __assertEquals(const char* e1, const string& e2) 	{ return __assertEquals<string>(string(e1), e2); }
	static bool __assertEquals(const char* e1, const char* e2) 	{ return __assertEquals<string>(string(e1), string(e2)); }
  

	/*
	 * See macro assertNotEquals.
	 */
	template <typename T>
	static bool __assertNotEquals(const T& e1, const T& e2) { return e1 != e2; }
	static bool __assertNotEquals(const string& e1, const char* e2) { return __assertNotEquals<string>(e1, string(e2)); }
	static bool __assertNotEquals(const char* e1, const string& e2) { return __assertNotEquals<string>(string(e1), e2); }
	static bool __assertNotEquals(const char* e1, const char* e2) { return __assertNotEquals<string>(string(e1), string(e2)); }


	/*
	 * See macro assertTrue.
	 */
	static bool __assertTrue(bool cond) { return cond; }


	/*
	 * See macro assertFalse.
	 */
	static bool __assertFalse(bool cond) { return !cond; };
};


#endif /* CPPUNIT_H_ */
