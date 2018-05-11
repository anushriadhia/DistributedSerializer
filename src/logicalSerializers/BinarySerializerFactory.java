package logicalSerializers;

import util.annotations.Tags;
import static util.annotations.Comp533Tags.LOGICAL_BINARY_SERIALIZER_FACTORY;

import serialization.Serializer;
import serialization.SerializerFactory;

@Tags({LOGICAL_BINARY_SERIALIZER_FACTORY})
public class BinarySerializerFactory implements SerializerFactory{

	@Override
	public Serializer createSerializer() {
		return (Serializer) new BinarySerializer();
	}

}
