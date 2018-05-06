package serializer;

import java.io.StreamCorruptedException;
import java.io.NotSerializableException;

public interface ValueSerializer {
	void objectToBuffer (Object anOutputBuffer, Object anObject, MutableString visitedObjects)
			throws NotSerializableException;
	Object objectFromBuffer(Object anInputBuffer, Class aClass, MutableString retrievedObjects)
			throws StreamCorruptedException, NotSerializableException;
}
