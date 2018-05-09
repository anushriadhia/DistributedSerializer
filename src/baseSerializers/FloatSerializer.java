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

import static util.annotations.Comp533Tags.FLOAT_SERIALIZER;

@Tags({FLOAT_SERIALIZER})

public class FloatSerializer implements ValueSerializer {

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		ExtensibleValueSerializationInitiated.newCase(this, anObject, anOutputBuffer);
		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putFloat((Float)anObject);
		}
		else if(anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(anObject);
		}
		
		ExtensibleValueSerializationFinished.newCase(this, anObject, anOutputBuffer, visitedObjects);
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		ExtensibleBufferDeserializationInitiated.newCase(this, FLOAT_SERIALIZER, anInputBuffer, aClass);
		
		Float val = (float) 0;
		if(anInputBuffer instanceof ByteBuffer) {
			val = ((ByteBuffer) anInputBuffer).getFloat();
		}
		else if(anInputBuffer instanceof TextBuffer) {
			val = Float.parseFloat(((TextBuffer) anInputBuffer).get());
		}
		
		ExtensibleBufferDeserializationFinished.newCase(this, FLOAT_SERIALIZER, anInputBuffer, aClass, retrievedObjects);
		
		return val;
	}

}
