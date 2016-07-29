package org.eclipse.papyrus.designer.components.modellibs.zmq.xtend

import static extension org.eclipse.papyrus.designer.components.transformation.core.UMLTool.*
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.CppUtils.*

import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Operation

class ZMQ {

	public static def marshall2(Operation operation) '''
		// create buffer for ASN.1 data types
		char buffer[MESSAGE_BUFFER_SIZE];
		char * pBuffer = &buffer[MESSAGE_BUFFER_SIZE];  // grows backwards
		int encodedSize = 0;     // total size of encoded buffer
		AsnLen itemSize;         // size of an encoded item
		int operationID = ID_«operation.name»;
			
		// now marshall in and inout parameters via ASN.1
		«FOR parameter : operation.parametersInInout»
		{
			«parameter.type.cppType()» varName_ASN = «parameter.name»;
			itemSize  = BEncAsnContent (&pBuffer, &varName_ASN);
			encodedSize += itemSize;
		}
		«ENDFOR»
		
		encodedSize += encodeRequestMessage (&pBuffer, operationID, m_SvIndex);
		encodedSize += BEncDefLen (&pBuffer, encodedSize);
		
		//create message
		zmq.message_t message(encodedSize );
		memcpy((void *) message.data (), pBuffer, encodedSize);
		
		// send message to its destination
		if (!(ZMQ_lib.ZMQSocketRuntime.ZMQSocketRuntime.getSocket (m_NodeID).send (message) )) {
			// throw corba.SystemException ();
		}
		«IF (operation.type != null)»
			/* recv function can be called here or client calls the function to receive data */
			«operation.type.cppType()» return_value;  
			return return_value;
		«ENDIF»
	'''


	public static def marshall5(Operation operation) '''
		// create buffer for ASN.1 data types
		char buffer[MESSAGE_BUFFER_SIZE];
		char * pBuffer = &buffer[MESSAGE_BUFFER_SIZE];  // grows backwards
		int encodedSize = 0;     // total size of encoded buffer
		AsnLen itemSize;         // size of an encoded item
		int operationID = ID_«operation.name»;
			
		// now marshall in and inout parameters via ASN.1
		«FOR parameter :  operation.parametersInInout»
		{
			«parameter.type.cppType()» varName_ASN = «parameter.name»;
			itemSize  = BEncAsnContent (&pBuffer, &varName_ASN);
			encodedSize += itemSize;
		}
		«ENDFOR»
		
		encodedSize += encodeRequestMessage (&pBuffer, operationID, 0);
		encodedSize += BEncDefLen (&pBuffer, encodedSize);
		
		//wait for last message sent
		while (ZMQ_lib.ZMQSocketRuntime.SRPubSub.length) {}
		
		ZMQ_lib.ZMQSocketRuntime.SRPubSub.length = encodedSize;
		memcpy(ZMQ_lib.ZMQSocketRuntime.SRPubSub.buffer, pBuffer, encodedSize);
		«IF (operation.type != null)»
		
			/* recv function can be called here or client calls the function to receive data */
			«operation.type.cppType()» return_value;  
			return return_value;
		«ENDIF»
	'''

	// differences of all marshall5 & 7 operations: m_SvIndex vs. 0, other criterion in while loop (wait for ...)
	// marshall3 is identical to marshall7, marshall2 has no wait
	public static def marshall7(Operation operation) '''
		// create buffer for ASN.1 data types
		char buffer[MESSAGE_BUFFER_SIZE];
		char * pBuffer = &buffer[MESSAGE_BUFFER_SIZE];  // grows backwards
		int encodedSize = 0;     // total size of encoded buffer
		AsnLen itemSize;         // size of an encoded item
		int operationID = ID_«operation.name»;
			
		// now marshall in and inout parameters via ASN.1
		«FOR parameter :  operation.parametersInInout»
		{
			«parameter.type.cppType()» varName_ASN = «parameter.name»;
			itemSize  = BEncAsnContent (&pBuffer, &varName_ASN);
			encodedSize += itemSize;
		}
		«ENDFOR»
		
		encodedSize += encodeRequestMessage (&pBuffer, operationID, m_SvIndex);
		encodedSize += BEncDefLen (&pBuffer, encodedSize);
		
		//create message
		// wait for last message
		while (ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.length) {}
		//update data to buffer of sr, another thread will send it
		ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.length = encodedSize;
		memcpy(ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.BUFFER_SEND, pBuffer, encodedSize);
		
		«IF (operation.type != null)»

			/* recv function can be called here or client calls the function to receive data */
			«operation.type.cppType()» return_value;  
			return return_value;
		«ENDIF»
	'''
	
	// dispatch 1, 4, 6 and 9 are identical
	public static def dispatch_(Interface intf) '''
		switch (operationID)
		{
		«FOR operation :  intf.ownedOperations»
			case ID_«operation.name»:
				{
				«FOR p :  operation.parametersInInout.reverse»
					«p.type.cppType()» «p.name»;
					{
						«p.type.cppType()» varName_ASN; 
						BDecAsnContent (&pBuffer, &varName_ASN);
						«p.name» = varName_ASN;
					}
				«ENDFOR»
				// delegate call to executor
				rconn->«operation.cppCall»;
				break;
			}
		«ENDFOR»
		}
	'''

	public static def dispatch8(Interface intf) '''
		AsnInt handler_id, operationID;
		BDecAsnContent(&pBuffer, &handler_id);
		BDecAsnContent(&pBuffer, &operationID);
		switch (operationID)
		{
		«FOR operation : intf.ownedOperations»
			case ID_«operation.name»:
			{
			«FOR p :  operation.parametersInInout.reverse»
				«p.type.cppType()» «p.name»;
				{
					«p.type.cppType()» varName_ASN; 
					BDecAsnContent (&pBuffer, &varName_ASN);
					«p.name» = varName_ASN;
				}
				«ENDFOR»
				// delegate call to executor
				handlers«operation».handler_id->«operation.cppCall»;
				break;
			}
		«ENDFOR»
		}
	'''
}
