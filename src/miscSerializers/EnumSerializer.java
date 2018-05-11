package miscSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Arrays;
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
		Object[] eConstants = ((Enum) anOutputBuffer).getClass().getEnumConstants();
		
		SerializerRegistry.getValueSerializer(String.class).objectToBuffer(anOutputBuffer, className, visitedObjects);
		SerializerRegistry.getValueSerializer(Integer.class).objectToBuffer(anOutputBuffer, eConstants.length, visitedObjects);
		
		for(int i = 0; i < eConstants.length; i++) {
			SerializerRegistry.getValueSerializer(String.class).objectToBuffer(anOutputBuffer, eConstants[i].toString(), visitedObjects);
		}		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		
		
		
		
		
		return null;
	}

}
