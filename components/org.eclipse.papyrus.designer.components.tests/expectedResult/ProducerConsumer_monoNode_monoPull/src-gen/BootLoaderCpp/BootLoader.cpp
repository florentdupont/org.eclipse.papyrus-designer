// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#define BootLoaderCpp_BootLoader_BODY

/************************************************************
 BootLoader class body
 ************************************************************/

// include associated header file
#include "BootLoaderCpp/BootLoader.h"

// Derived includes directives

// Include from Include declaration (body)
BootLoaderCpp::BootLoader bootloader;

int main(int argc, const char* argv[]) {
	// store arguments for future access
	BootLoaderCpp::BootLoader::argc = argc;
	BootLoaderCpp::BootLoader::argv = argv;

	bootloader.init();
	return 0;
}

// End of Include declaration (body)

namespace BootLoaderCpp {

// static attributes (if any)
/**
 * 
 */
::PrimitiveTypes::Integer BootLoader::argc;
/**
 * 
 */
::PrimitiveTypes::String* BootLoader::argv;

/**
 * 
 */
void BootLoader::init() {
	mainInstance.pullConThread.priority = 5;
	mainInstance.createConnections();
	mainInstance.prod.run();
}

} // of namespace BootLoaderCpp

/************************************************************
 End of BootLoader class body
 ************************************************************/