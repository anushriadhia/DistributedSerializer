package logicalSerializers;

import util.annotations.Tags;
import static util.annotations.Comp533Tags.LOGICAL_TEXTUAL_SERIALIZER;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;

import serialization.Serializer;

@Tags({LOGICAL_TEXTUAL_SERIALIZER})
public class TextualSerializer implements Serializer{

	@Override
	public Object objectFromInputBuffer(ByteBuffer inputBuffer) throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer outputBufferFromObject(Object object) throws NotSerializableException {
		// TODO Auto-generated method stub
		return null;
	}

}
