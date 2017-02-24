// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#ifndef BOOTLOADERCPP_BOOTLOADER_H
#define BOOTLOADERCPP_BOOTLOADER_H

/************************************************************
 BootLoader class header
 ************************************************************/

#include "BootLoaderCpp/Pkg_BootLoaderCpp.h"

#include "PrimitiveTypes/Pkg_PrimitiveTypes.h"
#include "ProducerConsumer/components/System/SystemPush_impl.h"

namespace BootLoaderCpp {

/************************************************************/
/**
 * 
 */
class BootLoader {
public:
	/**
	 * 
	 */
	static ::PrimitiveTypes::Integer argc;
	/**
	 * 
	 */
	static ::PrimitiveTypes::String* argv;
	/**
	 * 
	 */
	::ProducerConsumer::components::System::SystemPush_impl mainInstance;

	/**
	 * 
	 */
	void init();

};
/************************************************************/
/* External declarations (package visibility)               */
/************************************************************/

/* Inline functions                                         */

} // of namespace BootLoaderCpp

/************************************************************
 End of BootLoader class header
 ************************************************************/

#endif