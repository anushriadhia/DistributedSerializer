package serializer;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashSet;

import static util.annotations.Comp533Tags.VALUE_SERIALIZER;
import util.annotations.Tags;;

@Tags({VALUE_SERIALIZER})

public interface ValueSerializer {
	void objectToBuffer (Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException;
	Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException;
}
