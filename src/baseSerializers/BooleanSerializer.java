package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;

import serializer.TextBuffer;
import serializer.ValueSerializer;

import util.annotations.Tags;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationFinished;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationInitiated;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationFinished;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationInitiated;

import static util.annotations.Comp533Tags.BOOLEAN_SERIALIZER;

@Tags({BOOLEAN_SERIALIZER})
public class BooleanSerializer implements ValueSerializer{
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		ExtensibleValueSerializationInitiated.newCase(this, anObject, anOutputBuffer);
						
		int bool = (Boolean) anObject ? 1 : 0;		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(bool);
		}
		else if(anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(bool);
		}
		
		ExtensibleValueSerializationFinished.newCase(this, anObject, anOutputBuffer, visitedObjects);
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		ExtensibleBufferDeserializationInitiated.newCase(this, BOOLEAN_SERIALIZER , anInputBuffer, aClass);
		
		int bool = -1;
		if(anInputBuffer instanceof ByteBuffer) {
			bool =  ((ByteBuffer) anInputBuffer).getInt();

		}
		else if(anInputBuffer instanceof TextBuffer) {
			bool = Integer.parseInt(((TextBuffer) anInputBuffer).get());
		}
		
		ExtensibleBufferDeserializationFinished.newCase(this, BOOLEAN_SERIALIZER, anInputBuffer, aClass, retrievedObjects);
		return (bool == 0) ? false : true;

		

	}


}
