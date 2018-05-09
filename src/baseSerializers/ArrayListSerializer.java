package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;

import serializer.DispatchingSerializer;
import serializer.SerializerRegistry;
import serializer.ValueSerializer;

public class ArrayListSerializer implements ValueSerializer{

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
			
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		((ArrayList<Object>) anObject).forEach((item)->{
			try {
				dispatcher.ObjectToBuffer(anOutputBuffer, item, visitedObjects);
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
