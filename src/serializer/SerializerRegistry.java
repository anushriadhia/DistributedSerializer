package serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import baseSerializers.ArrayListSerializer;
import baseSerializers.BooleanSerializer;
import baseSerializers.DoubleSerializer;
import baseSerializers.FloatSerializer;
import baseSerializers.HashMapSerializer;
import baseSerializers.HashSetSerializer;
import baseSerializers.HashtableSerializer;
import baseSerializers.IntegerSerializer;
import baseSerializers.LongSerializer;
import baseSerializers.ShortSerializer;
import baseSerializers.StringSerializer;
import baseSerializers.VectorSerializer;
import util.annotations.Tags;
import util.trace.port.serialization.extensible.ExtensibleSerializationTraceUtility;

import static util.annotations.Comp533Tags.SERIALIZER_REGISTRY;


@Tags({SERIALIZER_REGISTRY})
public class SerializerRegistry {
	
	static DispatchingSerializer dispatchSerializer;
	static HashMap<Class, ValueSerializer> serializerMap = new HashMap<Class, ValueSerializer>();

	
	public SerializerRegistry() {		
		registerValueSerializer(Boolean.class, new BooleanSerializer());
		registerValueSerializer(Double.class, new DoubleSerializer());
		registerValueSerializer(Float.class, new FloatSerializer());
		registerValueSerializer(Integer.class, new IntegerSerializer());
		registerValueSerializer(Long.class, new LongSerializer());
		registerValueSerializer(Short.class, new ShortSerializer());
		registerValueSerializer(HashSet.class, new HashSetSerializer());
		registerValueSerializer(ArrayList.class, new ArrayListSerializer());
		registerValueSerializer(Vector.class, new VectorSerializer());
		registerValueSerializer(HashMap.class, new HashMapSerializer());
		registerValueSerializer(Hashtable.class, new HashtableSerializer());
		
		ExtensibleSerializationTraceUtility.setTracing();

	}


	public static void registerValueSerializer (Class aClass, ValueSerializer serializer) {
		serializerMap.put(aClass, serializer);
	}

	public static ValueSerializer getValueSerializer(Class aClass) {
		return serializerMap.get(aClass);
	}
	
	public static void registerDispatchingSerializer(DispatchingSerializer newVal) {
		dispatchSerializer = newVal;
	}
	
	public static DispatchingSerializer getDispatchingSerializer() {
		return dispatchSerializer;
	}

	
	

}
