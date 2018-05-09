package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Hashtable;

import serializer.DispatchingSerializer;
import serializer.SerializerRegistry;
import serializer.TextBuffer;
import serializer.ValueSerializer;
import util.annotations.Tags;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationFinished;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationInitiated;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationFinished;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationInitiated;

import static util.annotations.Comp533Tags.MAP_SERIALIZER;

@Tags({MAP_SERIALIZER})
public class HashtableSerializer implements ValueSerializer {
	@SuppressWarnings("unchecked")
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		ExtensibleValueSerializationInitiated.newCase(this, anObject, anOutputBuffer);
		
		int size = ((Hashtable) anObject).size();
		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(size);
		} else if (anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(size);
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		
		((Hashtable) anObject).forEach((key, value)-> {
			try {
				dispatcher.ObjectToBuffer(anOutputBuffer, key, visitedObjects);
				dispatcher.ObjectToBuffer(anOutputBuffer, value, visitedObjects);
			} catch (NotSerializableException e) {
				e.printStackTrace();
			}
		});
		
		ExtensibleValueSerializationFinished.newCase(this, anObject, anOutputBuffer, visitedObjects);
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		ExtensibleBufferDeserializationInitiated.newCase(this, MAP_SERIALIZER, anInputBuffer, aClass);
		
		//find a better way to do this instantiation thing
		int size = 0;
		if(anInputBuffer instanceof ByteBuffer) {
			size = ((ByteBuffer) anInputBuffer).getInt();
		} else if(anInputBuffer instanceof TextBuffer) {
			size = Integer.parseInt(((TextBuffer) anInputBuffer).get());
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		Hashtable<Object, Object> newMap = new Hashtable<Object, Object>();
		
		for(int i = 0; i<size; i++) {
			Object key = dispatcher.objectFromBuffer(anInputBuffer, retrievedObjects);
			Object value = dispatcher.objectFromBuffer(anInputBuffer, retrievedObjects);
			
			newMap.put(key, value);		
		}
		
		ExtensibleBufferDeserializationFinished.newCase(this, MAP_SERIALIZER, anInputBuffer, aClass, retrievedObjects);
		
		return newMap;
	}


}
