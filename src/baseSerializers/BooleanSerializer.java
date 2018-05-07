package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;

import serializer.TextBuffer;
import serializer.ValueSerializer;

//TODO: figure this out

public class BooleanSerializer implements ValueSerializer{
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		int bool = (Boolean) anObject ? 1 : 0;		
		if(anOutputBuffer instanceof ByteBuffer) {			
			((ByteBuffer) anOutputBuffer).putInt(bool);
		}
		else if(anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(bool);
		}
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		if(anInputBuffer instanceof ByteBuffer) {
			int bool =  ((ByteBuffer) anInputBuffer).getInt();
			return (bool == 0) ? false : true;

		}
		else if(anInputBuffer instanceof TextBuffer) {
			int bool = Integer.parseInt(((TextBuffer) anInputBuffer).get());
			return (bool == 0) ? false : true;
		}
		
		return null;
		

	}


}
