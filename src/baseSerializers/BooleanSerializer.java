package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;

import serializer.ValueSerializer;

//TODO: figure this out

public class BooleanSerializer implements ValueSerializer{
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		if(anOutputBuffer instanceof ByteBuffer) {			
			int bool = (Boolean) anObject ? 1 : 0;		
			((ByteBuffer) anOutputBuffer).putInt(bool);
		}
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		if(anInputBuffer instanceof ByteBuffer) {
			int bool =  ((ByteBuffer) anInputBuffer).getInt();
			
			return (bool == 0) ? false : true;
		}
		return null;
	}


}
