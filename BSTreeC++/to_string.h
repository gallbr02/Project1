/*
 * Utility function that converts objects to a string representation.
 */

#ifndef TO_STRING_H_
#define TO_STRING_H_

#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <cctype>


/*
 * Returns a string representation of the given object.
 * The given object must overload the << operator.
 *
 * @param item   the object to convert to a string
 * @return       string representation of the given object
 */
template <typename T>
std::string to_string(const T& item)
{
	std::stringstream os;
	os << item;
	return os.str();
}


/*
 * Removes initial and trailing space from a string.
 *
 * @param str    the string from which to remove spaces
 * @return       the string without initial and trailing spaces
 */
/* dummy template to avoid having .cpp file and avoid linker errors */
/*
 * Removes initial and trailing space from a string.
 *
 * @param str    the string from which to remove spaces
 * @return       the string without initial and trailing spaces
 */
/* dummy template to avoid having .cpp file and avoid linker errors */
template <typename T=std::string>
std::string trim(std::string str)
{
	auto first = std::find_if(str.begin(), str.end(),
				  [&](char ch) { return !isspace(ch); });
	str.erase(str.begin(), first);
	auto last = std::find_if(str.rbegin(), str.rend(),
				 [&](char ch) { return !isspace(ch); });
	str.erase(last.base(), str.end());
	return str;
}


#endif /* TO_STRING_H_ */
