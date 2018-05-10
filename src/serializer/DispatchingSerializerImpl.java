package serializer;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;

import util.annotations.Tags;
import static util.annotations.Comp533Tags.DISPATCHING_SERIALIZER;

@Tags({DISPATCHING_SERIALIZER})
public class DispatchingSerializerImpl implements DispatchingSerializer{
	
	@Override
	public void ObjectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		Class objClass = (anObject==null) ? null : anObject.getClass();
	
		int classTag = Arrays.asList(classMap).indexOf(objClass);
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(classTag);
		} else if(anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(classTag);
		}
		
		SerializerRegistry.getValueSerializer(objClass).objectToBuffer(anOutputBuffer, anObject, visitedObjects);
	
				
		
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		if (anInputBuffer instanceof ByteBuffer) {
			Class objClass = classMap[((ByteBuffer) anInputBuffer).getInt()];
			return SerializerRegistry
					.getValueSerializer(objClass)
					.objectFromBuffer(anInputBuffer, objClass, retrievedObjects);
		}
		else if (anInputBuffer instanceof TextBuffer) {
			Class objClass = classMap[Integer.parseInt(((TextBuffer) anInputBuffer).get())];
			return SerializerRegistry
					.getValueSerializer(objClass)
					.objectFromBuffer(anInputBuffer, objClass, retrievedObjects);
		}
		
		return null;
	}

}
