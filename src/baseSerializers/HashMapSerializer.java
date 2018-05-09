package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;

import serializer.DispatchingSerializer;
import serializer.SerializerRegistry;
import serializer.TextBuffer;
import serializer.ValueSerializer;

public class HashMapSerializer implements ValueSerializer{
	
	@SuppressWarnings("unchecked")
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		int size = ((HashMap) anObject).size();
		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(size);
		} else if (anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(size);
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		
		((HashMap) anObject).forEach((key, value)-> {
			try {
				dispatcher.ObjectToBuffer(anOutputBuffer, key, visitedObjects);
				dispatcher.ObjectToBuffer(anOutputBuffer, value, visitedObjects);
			} catch (NotSerializableException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		//find a better way to do this instantiation thing
		int size = 0;
		if(anInputBuffer instanceof ByteBuffer) {
			size = ((ByteBuffer) anInputBuffer).getInt();
		} else if(anInputBuffer instanceof TextBuffer) {
			size = Integer.parseInt(((TextBuffer) anInputBuffer).get());
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		HashMap<Object, Object> newMap = new HashMap<Object, Object>();
		
		for(int i = 0; i<size; i++) {
			Object key = dispatcher.objectFromBuffer(anInputBuffer, retrievedObjects);
			Object value = dispatcher.objectFromBuffer(anInputBuffer, retrievedObjects);
			
			newMap.put(key, value);		
		}
		
		return newMap;
	}

}
