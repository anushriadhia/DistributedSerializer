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
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		// TODO Auto-generated method stub
		return null;
	}

}
