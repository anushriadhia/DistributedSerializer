package serializer;

public class Main {

	public static void main(String[] args) {
		MutableString ms = new MutableString();
		
		ms.append(String.format("%010d", 5));
		ms.append("hello world");
		System.out.println(ms);
	}
}
