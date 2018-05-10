package serializer;

import java.io.StreamCorruptedException;
import java.util.Arrays;

public class Main implements ClassTag{

	public static void main(String[] args) throws StreamCorruptedException {
		TextBuffer buf = new TextBuffer();

		buf.put("Hello World");  // append a string
		buf.put(1234);           // append an integer
		System.out.println(buf); // print current buffer

		String s;                

		s = buf.get();           // retrieve first object => should be "Hello World"
		System.out.println(s);

		s = buf.get();           // retrieve second object => should be toString() of 1234
		System.out.println(s);   
		System.out.println(Integer.valueOf(s));
		
		System.out.println(Arrays.asList(classMap).indexOf(null));
		System.out.println(classMap[12]);
		
	}
	
	public static void printClass(Object o) {
		System.out.println(o.getClass());
	}
}
