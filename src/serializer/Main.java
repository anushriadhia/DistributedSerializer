package serializer;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main implements ClassTag{

	public static void main(String[] args) throws StreamCorruptedException, NotSerializableException {
		
		ArrayList<String> list = new ArrayList<String>();
		HashSet<Object> set = new HashSet<Object>();
		
//		TextBuffer buffer = new TextBuffer();
		ByteBuffer buffer = ByteBuffer.allocate(1000);
		
		SerializerRegistry.registerAll();
		SerializerRegistry.getDispatchingSerializer().ObjectToBuffer(buffer, "Hello", set);
		
		buffer.flip();
		
		System.out.println(SerializerRegistry.getDispatchingSerializer().objectFromBuffer(buffer, set));
		
		
		
		
//		TextBuffer buf = new TextBuffer();
//
//		buf.put("Hello World");  // append a string
//		buf.put(1234);           // append an integer
//		System.out.println(buf); // print current buffer
//
//		String s;                
//
//		s = buf.get();           // retrieve first object => should be "Hello World"
//		System.out.println(s);
//
//		s = buf.get();           // retrieve second object => should be toString() of 1234
//		System.out.println(s);   
//		System.out.println(Integer.valueOf(s));
//		
//		System.out.println(Arrays.asList(classMap).indexOf(null));
		
	}

}
