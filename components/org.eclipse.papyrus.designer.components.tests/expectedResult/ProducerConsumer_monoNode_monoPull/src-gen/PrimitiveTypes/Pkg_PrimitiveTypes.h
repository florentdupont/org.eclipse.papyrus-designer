#ifndef PKG_PRIMITIVETYPES
#define PKG_PRIMITIVETYPES

/************************************************************
 Pkg_PrimitiveTypes package header
 ************************************************************/

#ifndef _IN_
#define _IN_
#endif
#ifndef _OUT_
#define _OUT_
#endif
#ifndef _INOUT_
#define _INOUT_
#endif

/* Package dependency header include                        */

namespace PrimitiveTypes {

// Types defined within the package
/**
 * Boolean is used for logical expressions, consisting of the predefined values true and false.
 */
typedef bool Boolean;

/**
 * Integer is a primitive type representing integer values.
 */
typedef int Integer;

/**
 * String is a sequence of characters in some suitable character set used to display information about the model. Character sets may include non-Roman alphabets and characters.
 */
typedef const char * String;

} // of namespace PrimitiveTypes

/************************************************************
 End of Pkg_PrimitiveTypes package header
 ************************************************************/

#endif
