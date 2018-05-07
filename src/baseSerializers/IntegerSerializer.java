package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;

import serializer.TextBuffer;
import serializer.ValueSerializer;

public class IntegerSerializer implements ValueSerializer {

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		int classTag = Arrays.asList(classMap).indexOf(Integer.class);
		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(classTag);
			((ByteBuffer) anOutputBuffer).putInt((Integer) anObject);
		}
		else if (anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(classTag);
			((TextBuffer) anOutputBuffer).put(anObject);
		}
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		if(anInputBuffer instanceof ByteBuffer) {
			return ((ByteBuffer) anInputBuffer).getInt();
		}
		else if (anInputBuffer instanceof TextBuffer) {
			return Integer.parseInt(anInputBuffer.toString());
		}
		return null;
	}

}
