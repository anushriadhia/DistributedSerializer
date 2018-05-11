package serializer;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main implements ClassTag{

	public static void main(String[] args) throws StreamCorruptedException, NotSerializableException {
//		
//		ArrayList<String> list = new ArrayList<String>();
//		HashSet<Object> set = new HashSet<Object>();
//		
//		TextBuffer buffer = new TextBuffer();
//		
//		SerializerRegistry registry = new SerializerRegistry();
//		registry.getDispatchingSerializer().ObjectToBuffer(buffer, "Hello", set);
//		System.out.println(registry.getDispatchingSerializer().objectFromBuffer(buffer, set));
//		
//		
//		
//		
		
		ArrayList<String> list = new ArrayList<String>();
		HashSet<Object> set = new HashSet<Object>();
		
//		TextBuffer buffer = new TextBuffer();
		ByteBuffer buffer = ByteBuffer.allocate(1000);
		
		SerializerRegistry.registerAll();
		SerializerRegistry.getDispatchingSerializer().ObjectToBuffer(buffer, null, set);
		
		buffer.flip();
		
		System.out.println(SerializerRegistry.getDispatchingSerializer().objectFromBuffer(buffer, set));
		
		
		
		
	}

}
