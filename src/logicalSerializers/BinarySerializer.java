package logicalSerializers;

import util.annotations.Tags;
import static util.annotations.Comp533Tags.LOGICAL_BINARY_SERIALIZER;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;

import serialization.Serializer;

@Tags({LOGICAL_BINARY_SERIALIZER})
public class BinarySerializer implements Serializer{
	
	ByteBuffer buffer;
	
	public BinarySerializer() {
		buffer = ByteBuffer.allocate(1000);
	}

	@Override
	public ByteBuffer outputBufferFromObject(Object object) throws NotSerializableException {
		return null;
	}
	
	@Override
	public Object objectFromInputBuffer(ByteBuffer inputBuffer) throws StreamCorruptedException {
		return null;
	}

	

}
