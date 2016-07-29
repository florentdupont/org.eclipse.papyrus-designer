// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#define ClientServerZMQ_components_Server_CServer_impl_BODY

/************************************************************
 CServer_impl class body
 ************************************************************/

// include associated header file
#include <ClientServerZMQ/components/Server/CServer_impl.h>

// Derived includes directives
#include <ClientServerZMQ/components/Interfaces/ICompute.h>

namespace components {
namespace Server {

// static attributes (if any)

/**
 * 
 * @param a @param b @param res 
 */
::corba::Long CServer_impl::mult(::corba::Long /*in*/a, ::corba::Long /*in*/b) {
	cout << "a*b=" << a * b * 5;
	return a * b;
}

/**
 * 
 * @param a @param b @param res 
 */
::corba::Long CServer_impl::add(::corba::Long /*in*/a, ::corba::Long /*in*/b) {
	cout << "a=" << a << " b=" << b << " a+b=" << a + b << endl;
	return a + b;
}

} // of namespace Server
} // of namespace components

/************************************************************
 End of CServer_impl class body
 ************************************************************/