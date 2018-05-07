package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashSet;

import serializer.SerializerRegistry;
import serializer.ValueSerializer;

public class HashSetSerializer implements ValueSerializer {

	@SuppressWarnings("unchecked")
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		((HashSet) anObject).forEach((node)-> {
			try {
				SerializerRegistry.getDispatchingSerializer().ObjectToBuffer(anOutputBuffer, node, visitedObjects);
			} catch (NotSerializableException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
	
		return null;
	}

}