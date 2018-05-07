package serializer;

import java.util.HashMap;

import baseSerializers.BooleanSerializer;
import baseSerializers.DoubleSerializer;
import baseSerializers.FloatSerializer;
import baseSerializers.IntegerSerializer;
import baseSerializers.LongSerializer;
import baseSerializers.ShortSerializer;
import baseSerializers.StringSerializer;
import util.annotations.Tags;
import static util.annotations.Comp533Tags.SERIALIZER_REGISTRY;


@Tags({SERIALIZER_REGISTRY})
public class SerializerRegistry {
	
//	BooleanSerializer booleanSerializer;
//	DoubleSerializer doubleSerializer;
//	FloatSerializer floatSerializer;
//	IntegerSerializer integerSerializer;
//	LongSerializer longSerializer;
//	ShortSerializer shortSerializer;
//	StringSerializer stringSerializer;
	
	public SerializerRegistry() {
		registerValueSerializer(Boolean.class, new BooleanSerializer());
		registerValueSerializer(Double.class, new DoubleSerializer());
		registerValueSerializer(Float.class, new FloatSerializer());
		registerValueSerializer(Integer.class, new IntegerSerializer());
		registerValueSerializer(Long.class, new LongSerializer());
		registerValueSerializer(Short.class, new ShortSerializer());
		registerValueSerializer(String.class, new StringSerializer());
	}

	static HashMap<Class, ValueSerializer> serializerMap = new HashMap<Class, ValueSerializer>();

	public static void registerValueSerializer (Class aClass, ValueSerializer serializer) {
		serializerMap.put(aClass, serializer);
	}

	public static ValueSerializer getValueSerializer(Class aClass) {
		return serializerMap.get(aClass);
	}

	
	

}
