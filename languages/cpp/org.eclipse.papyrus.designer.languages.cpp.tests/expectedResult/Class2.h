// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#ifndef CPPCODEGENTEST_CLASS2_H
#define CPPCODEGENTEST_CLASS2_H

/************************************************************
 Class2 class header
 ************************************************************/

#include "CppCodegenTest/Pkg_CppCodegenTest.h"

#include "AnsiCLibrary/Pkg_AnsiCLibrary.h"

// Include from Include stereotype (header)
#include <iostream>
// End of Include stereotype (header)

namespace CppCodegenTest {
class Class1;
}

namespace CppCodegenTest {

/************************************************************/
/**
 * 
 */
class Class2 {
public:
	/**
	 * boolean  value with public visibility
	 */
	bool publicBVal;

	/**
	 * Default constructor
	 */
	Class2();

	/**
	 * Constructor with parameters
	 * @param newIVal1 
	 * @param newIVal2 
	 * @param newBVal 
	 */
	Class2(int /*in*/newIVal1, int /*in*/newIVal2, bool /*in*/newBVal);

	/**
	 * Virtual method
	 * @param a 
	 * @param b 
	 * @return  
	 */
	virtual double virtualOp(double /*in*/a, double /*in*/b);

	/**
	 * Static method
	 */
	static void staticOp();

	/**
	 * Inline method
	 * @param a 
	 * @param b 
	 * @return  
	 */
	inline int inlineOp(int /*in*/a, int /*in*/b);

	/**
	 * Friend method
	 */
	void friendOp();

	/**
	 * Non-static method
	 */
	void nonStaticOp();

	/**
	 * Method with in, out, inout, return parameters
	 * @param in 
	 * @param out 
	 * @param inout 
	 * @return ret 
	 */
	int paramsOp(int /*in*/in, int* /*out*/out, int* /*inout*/inout);

	/**
	 * Void Method
	 * @return  
	 */
	void voidOp();

	/**
	 * 
	 * @param newIVal1 
	 */
	void defaultValueOp(int /*in*/newIVal1 = 0);

	/**
	 * Virtual destructor
	 */
	virtual ~Class2();

	/**
	 * 
	 * @param c1 
	 * @return  
	 */
	Class1* classesOp(Class1 /*in*/c1);

	/**
	 * Const method
	 */
	void constOp() const;

protected:
	/**
	 * int value with protected visibility
	 */
	int protectedIVal1;

private:
	/**
	 * int value with private visibility
	 */
	int privateIVal2;
};
/************************************************************/
/* External declarations (package visibility)               */
/**
 * int value with package visibility
 */
int packageIVal3;
/************************************************************/

/* Inline functions                                         */
/**
 * Inline method
 * @param a 
 * @param b 
 * @return  
 */
inline int Class2::inlineOp(int /*in*/a, int /*in*/b) {
	return a + b;
}

} // of namespace CppCodegenTest

/************************************************************
 End of Class2 class header
 ************************************************************/

#endif
