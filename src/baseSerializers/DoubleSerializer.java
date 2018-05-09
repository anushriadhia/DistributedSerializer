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

import static util.annotations.Comp533Tags.DOUBLE_SERIALIZER;

@Tags({DOUBLE_SERIALIZER})

public class DoubleSerializer implements ValueSerializer {
	
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		ExtensibleValueSerializationInitiated.newCase(this, anObject, anOutputBuffer);
				
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putDouble((Double) anObject);
		}
		else if(anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(anObject);
		}
		
		ExtensibleValueSerializationFinished.newCase(this, anObject, anOutputBuffer, visitedObjects);
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		ExtensibleBufferDeserializationInitiated.newCase(this, DOUBLE_SERIALIZER, anInputBuffer, aClass);
		
		double val = 0;
		if(anInputBuffer instanceof ByteBuffer) {
			val = ((ByteBuffer) anInputBuffer).getDouble();
		} 
		else if (anInputBuffer instanceof TextBuffer) {
			val =  Double.parseDouble(((TextBuffer) anInputBuffer).get());
		}
		
		ExtensibleBufferDeserializationFinished.newCase(this, DOUBLE_SERIALIZER, anInputBuffer, aClass, retrievedObjects);
		
		return val;
	}


}
