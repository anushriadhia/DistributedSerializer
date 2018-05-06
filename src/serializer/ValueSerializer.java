package serializer;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.HashSet;

import static util.annotations.Comp533Tags.VALUE_SERIALIZER;
import util.annotations.Tags;;

@Tags({VALUE_SERIALIZER})
public interface ValueSerializer {
	void objectToBuffer(Object outputBuffer, 
			Object anObject, 
			HashSet<Object> visitedObjects) throws NotSerializableException;
	
	Object objectFromBuffer(Object inputBuffer, 
			Class aclass, 
			HashSet<Object> retrievedObjects) throws StreamCorruptedException, NotSerializableException;

}
