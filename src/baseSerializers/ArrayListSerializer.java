package baseSerializers;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;

import serializer.DispatchingSerializer;
import serializer.SerializerRegistry;
import serializer.TextBuffer;
import serializer.ValueSerializer;
import util.annotations.Tags;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationFinished;
import util.trace.port.serialization.extensible.ExtensibleBufferDeserializationInitiated;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationFinished;
import util.trace.port.serialization.extensible.ExtensibleValueSerializationInitiated;

import static util.annotations.Comp533Tags.COLLECTION_SERIALIZER;

@Tags({COLLECTION_SERIALIZER})
public class ArrayListSerializer implements ValueSerializer{
	@SuppressWarnings("unchecked")
	@Override
	public void objectToBuffer(Object anOutputBuffer, Object anObject, HashSet<Object> visitedObjects)
			throws NotSerializableException {
		
		ExtensibleValueSerializationInitiated.newCase(this, anObject, anOutputBuffer);
		
		int size = ((ArrayList) anObject).size();
		
		if(anOutputBuffer instanceof ByteBuffer) {
			((ByteBuffer) anOutputBuffer).putInt(size);
		} else if (anOutputBuffer instanceof TextBuffer) {
			((TextBuffer) anOutputBuffer).put(size);
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		
		((ArrayList) anObject).forEach((node)-> {
			try {
				dispatcher.ObjectToBuffer(anOutputBuffer, node, visitedObjects);
			} catch (NotSerializableException e) {
				e.printStackTrace();
			}
		});
		
		ExtensibleValueSerializationFinished.newCase(this, anObject, anOutputBuffer, visitedObjects);
		
	}

	@Override
	public Object objectFromBuffer(Object anInputBuffer, Class aClass, HashSet<Object> retrievedObjects)
			throws StreamCorruptedException, NotSerializableException {
		
		ExtensibleBufferDeserializationInitiated.newCase(this, COLLECTION_SERIALIZER , anInputBuffer, aClass);
		
		//find a better way to do this instantiation thing
		int size = 0;
		if(anInputBuffer instanceof ByteBuffer) {
			size = ((ByteBuffer) anInputBuffer).getInt();
		} else if(anInputBuffer instanceof TextBuffer) {
			size = Integer.parseInt(((TextBuffer) anInputBuffer).get());
		}
		
		DispatchingSerializer dispatcher = SerializerRegistry.getDispatchingSerializer();
		ArrayList<Object> newSet = new ArrayList<Object>();
		for(int i = 0; i<size; i++) {
			newSet.add(dispatcher.objectFromBuffer(anInputBuffer, retrievedObjects));
		}
		
		ExtensibleBufferDeserializationFinished.newCase(this, COLLECTION_SERIALIZER, anInputBuffer, aClass, retrievedObjects);

		return newSet;
		
	}



}
