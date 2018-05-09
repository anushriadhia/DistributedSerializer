package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;

import serializer.TextBuffer;
import serializer.ValueSerializer;
import util.annotations.Tags;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationFinished;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationInitiated;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationFinished;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationInitiated;

import static util.annotations.Comp533Tags.STRING_SERIALIZER;

@Tags({STRING_SERIALIZER})

public class StringSerializer implements ValueSerializer{

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		ExtensibleValueSerializationInitiated.newCase(this, anObject, anOutputBuffer);
				
		if(anOutputBuffer instanceof ByteBuffer) {			
			String str = (String) anObject;
			((ByteBuffer) anOutputBuffer).put(str.getBytes());
		}
		else if (anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(anObject);
		}
		
		ExtensibleValueSerializationFinished.newCase(this, anObject, anOutputBuffer, visitedObjects);
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		ExtensibleBufferDeserializationInitiated.newCase(this, STRING_SERIALIZER, anInputBuffer, aClass);
		
		String val = "";
		if(anInputBuffer instanceof ByteBuffer) {
			byte[] strArr = new byte[((ByteBuffer) anInputBuffer).getInt()];
			((ByteBuffer) anInputBuffer).get(strArr);
			val = new String(strArr);
		}
		else if (anInputBuffer instanceof TextBuffer) {
			val = ((TextBuffer) anInputBuffer).get();
		}
		
		ExtensibleBufferDeserializationFinished.newCase(this, STRING_SERIALIZER, anInputBuffer, aClass, retrievedObjects);
		
		return val;
	}
	

}
