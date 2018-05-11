package miscSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import serializer.SerializerRegistry;
import serializer.ValueSerializer;
import util.annotations.Tags;
import static util.annotations.Comp533Tags.ARRAY_SERIALIZER;

@Tags({ARRAY_SERIALIZER})
public class ArraySerializer implements ValueSerializer{

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		SerializerRegistry.getValueSerializer(ArrayList.class).objectToBuffer(anOutputBuffer, Arrays.asList(anObject), visitedObjects);
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		ArrayList result =(ArrayList) SerializerRegistry.getValueSerializer(ArrayList.class).objectFromBuffer(anInputBuffer, aClass, retrievedObjects);
		return result.toArray();
	}

}
