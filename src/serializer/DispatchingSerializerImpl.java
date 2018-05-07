package serializer;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashSet;

import util.annotations.Tags;
import static util.annotations.Comp533Tags.DISPATCHING_SERIALIZER;

@Tags({DISPATCHING_SERIALIZER})
public class DispatchingSerializerImpl implements DispatchingSerializer{

	@Override
	public void ObjectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		if(anObject instanceof Boolean) {
			SerializerRegistry.getValueSerializer(Boolean.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		}  else if(anObject instanceof Double) {
			SerializerRegistry.getValueSerializer(Double.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		}  else if(anObject instanceof Float) {
			SerializerRegistry.getValueSerializer(Float.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		}  else if(anObject instanceof Integer) {
			SerializerRegistry.getValueSerializer(Integer.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		}  else if(anObject instanceof Long) {
			SerializerRegistry.getValueSerializer(Long.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		}  else if(anObject instanceof Short) {
			SerializerRegistry.getValueSerializer(Short.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		}  else if(anObject instanceof String) {
			SerializerRegistry.getValueSerializer(String.class).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
		} 
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		// TODO Auto-generated method stub
		return null;
	}

}
