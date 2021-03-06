// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#ifndef CPPCODEGENTEST_CLASS7_H
#define CPPCODEGENTEST_CLASS7_H

/************************************************************
 Class7 class header
 ************************************************************/

#include "CppCodegenTest/Pkg_CppCodegenTest.h"

#include "AnsiCLibrary/Pkg_AnsiCLibrary.h"

// Include from Include stereotype (header)
#define helloworld 3
// End of Include stereotype (header)

namespace CppCodegenTest {

/************************************************************/
/**
 * 
 */
class Class7 {
public:
	Class7() :
			defaultProp(43) {
	}
	/**
	 * 
	 */
	static int staticProp;
	/**
	 * 
	 */
	int defaultProp;

};
/************************************************************/
/* External declarations (package visibility)               */
/**
 * 
 */
int externProp;
/************************************************************/

/* Inline functions                                         */

} // of namespace CppCodegenTest

/************************************************************
 End of Class7 class header
 ************************************************************/

#endif
