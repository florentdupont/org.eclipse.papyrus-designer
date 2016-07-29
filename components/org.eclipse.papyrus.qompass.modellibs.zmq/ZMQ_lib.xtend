package org.eclipse.papyrus.c.codegen..

class  {
	public static def public dispatch(Interface interface) '''
		switch (operationID)&#xD;
		{&#xD;
		«FOR (operation : Operation | interface.ownedOperation)»&#xD;
		  case ID_«operation.name»:&#xD;
			{&#xD;
			«FOR (p : Parameter | operation.parametersInInout()->reverse())»&#xD;
				«p.type.cppType()» «p.name»;&#xD;
				{&#xD;
					«p.type.cppType()» varName_ASN; &#xD;
					BDecAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
					«name» = varName_ASN;&#xD;
				}&#xD;
			«ENDFOR»&#xD;
				// delegate call to executor&#xD;
				rconn->«operation.cppCall()»;&#xD;
				break;&#xD;
			}&#xD;
		«ENDFOR»&#xD;
		}&#xD;
	'''

	public static def public marshall(Operation operation) '''
		// create buffer for ASN.1 data types&#xD;
		char buffer «'«'»MESSAGE_BUFFER_SIZE«'»'»;&#xD;
		char * pBuffer = &amp;buffer«'«'»MESSAGE_BUFFER_SIZE«'»'»;  // grows backwards&#xD;
		int encodedSize = 0;     // total size of encoded buffer&#xD;
		AsnLen itemSize;         // size of an encoded item&#xD;
		int operationID = ID_«operation.name»;&#xD;
			&#xD;
		// now marshall in and inout parameters via ASN.1&#xD;
		«FOR (parameter : Parameter | operation.parametersInInout())»&#xD;
		{&#xD;
			«parameter.type.cppType()» varName_ASN = «parameter.name»;&#xD;
			itemSize  = BEncAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
			encodedSize += itemSize;&#xD;
		}&#xD;
		«ENDFOR»&#xD;
		&#xD;
		encodedSize += encodeRequestMessage (&amp;pBuffer, operationID, m_SvIndex);&#xD;
		encodedSize += BEncDefLen (&amp;pBuffer, encodedSize);&#xD;
		&#xD;
		//create message&#xD;
		zmq.message_t message(encodedSize );&#xD;
		memcpy((void *) message.data (), pBuffer, encodedSize);&#xD;
		&#xD;
		// send message to its destination&#xD;
		if (!(ZMQ_lib.ZMQSocketRuntime.ZMQSocketRuntime.getSocket (m_NodeID).send (message) )){&#xD;
			// throw corba.SystemException ();&#xD;
		}&#xD;
		«IF not (operation.type = null)»&#xD;
		&#xD;
		/* recv function can be called here or client calls the function to receive data */&#xD;
		«operation.type.cppType()» return_value;  &#xD;
		return return_value;&#xD;
		«ENDIF»&#xD;
	'''

	public static def public marshall(Operation operation) '''
		// create buffer for ASN.1 data types&#xD;
		char buffer «'«'»MESSAGE_BUFFER_SIZE«'»'»;&#xD;
		char * pBuffer = &amp;buffer«'«'»MESSAGE_BUFFER_SIZE«'»'»;  // grows backwards&#xD;
		int encodedSize = 0;     // total size of encoded buffer&#xD;
		AsnLen itemSize;         // size of an encoded item&#xD;
		int operationID = ID_«operation.name»;&#xD;
			&#xD;
		// now marshall in and inout parameters via ASN.1&#xD;
		«FOR (parameter : Parameter | operation.parametersInInout())»&#xD;
		{&#xD;
			«parameter.type.cppType()» varName_ASN = «parameter.name»;&#xD;
			itemSize  = BEncAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
			encodedSize += itemSize;&#xD;
		}&#xD;
		«ENDFOR»&#xD;
		&#xD;
		encodedSize += encodeRequestMessage (&amp;pBuffer, operationID, m_SvIndex);&#xD;
		encodedSize += BEncDefLen (&amp;pBuffer, encodedSize);&#xD;
		&#xD;
		//create message&#xD;
		// wait for last message
		while (ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.length) {}
		//update data to buffer of sr, another thread will send it
		ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.length = encodedSize;
		memcpy(ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.BUFFER_SEND, pBuffer, encodedSize);
		&#xD;
		«IF not (operation.type = null)»&#xD;
		&#xD;
		/* recv function can be called here or client calls the function to receive data */&#xD;
		«operation.type.cppType()» return_value;  &#xD;
		return return_value;&#xD;
		«ENDIF»&#xD;
	'''

	public static def public dispatch(Interface interface) '''
		switch (operationID)&#xD;
		{&#xD;
		«FOR (operation : Operation | interface.ownedOperation)»&#xD;
		  case ID_«operation.name»:&#xD;
			{&#xD;
			«FOR (p : Parameter | operation.parametersInInout()->reverse())»&#xD;
				«p.type.cppType()» «p.name»;&#xD;
				{&#xD;
					«p.type.cppType()» varName_ASN; &#xD;
					BDecAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
					«name» = varName_ASN;&#xD;
				}&#xD;
			«ENDFOR»&#xD;
				// delegate call to executor&#xD;
				rconn->«operation.cppCall()»;&#xD;
				break;&#xD;
			}&#xD;
		«ENDFOR»&#xD;
		}&#xD;
	'''

   public static def public marshall(Operation operation) '''
		// create buffer for ASN.1 data types&#xD;
		char buffer «'«'»MESSAGE_BUFFER_SIZE«'»'»;&#xD;
		char * pBuffer = &amp;buffer«'«'»MESSAGE_BUFFER_SIZE«'»'»;  // grows backwards&#xD;
		int encodedSize = 0;     // total size of encoded buffer&#xD;
		AsnLen itemSize;         // size of an encoded item&#xD;
		int operationID = ID_«operation.name»;&#xD;
			&#xD;
		// now marshall in and inout parameters via ASN.1&#xD;
		«FOR (parameter : Parameter | operation.parametersInInout())»&#xD;
		{&#xD;
			«parameter.type.cppType()» varName_ASN = «parameter.name»;&#xD;
			itemSize  = BEncAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
			encodedSize += itemSize;&#xD;
		}&#xD;
		«ENDFOR»&#xD;
		&#xD;
		encodedSize += encodeRequestMessage (&amp;pBuffer, operationID, 0);&#xD;
		encodedSize += BEncDefLen (&amp;pBuffer, encodedSize);&#xD;
		&#xD;
		//wait for last message sent&#xD;
		while (ZMQ_lib.ZMQSocketRuntime.SRPubSub.length) {}&#xD;
		&#xD;
		ZMQ_lib.ZMQSocketRuntime.SRPubSub.length = encodedSize;&#xD;
		memcpy(ZMQ_lib.ZMQSocketRuntime.SRPubSub.buffer, pBuffer, encodedSize);&#xD;
		«IF not (operation.type = null)»&#xD;
		&#xD;
		/* recv function can be called here or client calls the function to receive data */&#xD;
		«operation.type.cppType()» return_value;  &#xD;
		return return_value;&#xD;
		«ENDIF»&#xD;
	'''

	public static def public dispatch(Interface interface) '''
		switch (operationID)&#xD;
		{&#xD;
		«FOR (operation : Operation | interface.ownedOperation)»&#xD;
		  case ID_«operation.name»:&#xD;
			{&#xD;
			«FOR (p : Parameter | operation.parametersInInout()->reverse())»&#xD;
				«p.type.cppType()» «p.name»;&#xD;
				{&#xD;
					«p.type.cppType()» varName_ASN; &#xD;
					BDecAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
					«name» = varName_ASN;&#xD;
				}&#xD;
			«ENDFOR»&#xD;
				// delegate call to executor&#xD;
				rconn->«operation.cppCall()»;&#xD;
				break;&#xD;
			}&#xD;
		«ENDFOR»&#xD;
		}&#xD;
	'''
   
	public static def public marshall(Operation operation) '''
		// create buffer for ASN.1 data types&#xD;
		char buffer «'«'»MESSAGE_BUFFER_SIZE«'»'»;&#xD;
		char * pBuffer = &amp;buffer«'«'»MESSAGE_BUFFER_SIZE«'»'»;  // grows backwards&#xD;
		int encodedSize = 0;     // total size of encoded buffer&#xD;
		AsnLen itemSize;         // size of an encoded item&#xD;
		int operationID = ID_«operation.name»;&#xD;
			&#xD;
		// now marshall in and inout parameters via ASN.1&#xD;
		«FOR (parameter : Parameter | operation.parametersInInout())»&#xD;
		{&#xD;
			«parameter.type.cppType()» varName_ASN = «parameter.name»;&#xD;
			itemSize  = BEncAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
			encodedSize += itemSize;&#xD;
		}&#xD;
		«ENDFOR»&#xD;
		&#xD;
		encodedSize += encodeRequestMessage (&amp;pBuffer, operationID, m_SvIndex);&#xD;
		encodedSize += BEncDefLen (&amp;pBuffer, encodedSize);&#xD;
		&#xD;
		//create message&#xD;
		// wait for last message&#xD;
		while (ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.length) {}&#xD;
		//update data to buffer of sr, another thread will send it&#xD;
		ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.length = encodedSize;&#xD;
		memcpy(ZMQ_lib.ZMQSR_LB.ZMQSocketRuntime_LB.BUFFER_SEND, pBuffer, encodedSize);&#xD;
		&#xD;
		«IF not (operation.type = null)»&#xD;
		&#xD;
		/* recv function can be called here or client calls the function to receive data */&#xD;
		«operation.type.cppType()» return_value;  &#xD;
		return return_value;&#xD;
		«ENDIF»&#xD;
	'''

   public static def public dispatch(Interface interface) '''
		AsnInt handler_id, operationID;&#xD;
		BDecAsnContent(&amp;pBuffer, &amp;handler_id);&#xD;
		BDecAsnContent(&amp;pBuffer, &amp;operationID);&#xD;
		switch (operationID)&#xD;
		{&#xD;
		«FOR (operation : Operation | interface.ownedOperation)»&#xD;
		  case ID_«operation.name»:&#xD;
			{&#xD;
			«FOR (p : Parameter | operation.parametersInInout()->reverse())»&#xD;
				«p.type.cppType()» «p.name»;&#xD;
				{&#xD;
					«p.type.cppType()» varName_ASN; &#xD;
					BDecAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
					«name» = varName_ASN;&#xD;
				}&#xD;
			«ENDFOR»&#xD;
				// delegate call to executor&#xD;
				handlers«handler_id»->«operation.cppCall()»;&#xD;
				break;&#xD;
			}&#xD;
		«ENDFOR»&#xD;
		}&#xD;
	'''

	public static def public dispatch(Interface interface) '''
		switch (operationID)&#xD;
		{&#xD;
		«FOR (operation : Operation | interface.ownedOperation)»&#xD;
		  case ID_«operation.name»:&#xD;
			{&#xD;
			«FOR (p : Parameter | operation.parametersInInout()->reverse())»&#xD;
				«p.type.cppType()» «p.name»;&#xD;
				{&#xD;
					«p.type.cppType()» varName_ASN; &#xD;
					BDecAsnContent (&amp;pBuffer, &amp;varName_ASN);&#xD;
					«name» = varName_ASN;&#xD;
				}&#xD;
			«ENDFOR»&#xD;
				// delegate call to executor&#xD;
				rconn->«operation.cppCall()»;&#xD;
				break;&#xD;
			}&#xD;
		«ENDFOR»&#xD;
		}&#xD;
	'''
}
