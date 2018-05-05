package serializer;

import java.util.HashMap;

import util.annotations.Tags;
//@Tags({SERIALIZER_REGISTRY})

public class SerializerRegistry {
	
	static HashMap<Class, ValueSerializer> serializerMap;
	
	public SerializerRegistry() {
		
		serializerMap = new HashMap<Class, ValueSerializer>();
		
	}
	
	
	public static void registerValueSerializer (Class aClass, ValueSerializer serializer) {
		serializerMap.put(aClass, serializer);
	}
	
	public static ValueSerializer getValueSerializer(Class aClass) {
		return serializerMap.get(aClass);
	}

}
