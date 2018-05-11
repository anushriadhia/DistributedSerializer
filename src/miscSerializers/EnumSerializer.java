package miscSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;

import serializer.SerializerRegistry;
import serializer.ValueSerializer;
import util.annotations.Tags;
import static util.annotations.Comp533Tags.ENUM_SERIALIZER;

@Tags({ENUM_SERIALIZER})
public class EnumSerializer implements ValueSerializer {
	
	public enum Hello{
		HI,
		Hey
		
	}

	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		String className = ((Enum) anOutputBuffer).getClass().getName();
		
		SerializerRegistry.getValueSerializer(String.class).objectToBuffer(anOutputBuffer, className, visitedObjects);
		SerializerRegistry
		
//		SerializerRegistry.getValueSerializer(aClass)
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		return null;
	}

}
