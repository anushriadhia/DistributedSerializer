package serializer;

public class TextBuffer{

	StringBuffer buffer;
	int pos;
	
	public TextBuffer() {
		this.buffer = new StringBuffer(1000);
		this.pos = 0;
	}
	
	public void put(Object obj) {
		this.buffer.append(obj.toString().length());
		this.buffer.append(obj.toString());
		this.buffer.append('|');
	}
	
	private String getString(int len) {
		String str = buffer.substring(this.pos, len);
		pos += len+1;
		
		return str;
	}
	
	public String get() {
		int length = (int) buffer.charAt(pos);
		pos++;
		return getString(length);
	}
	
	public String toString() {
		return buffer.toString();
	}
}
