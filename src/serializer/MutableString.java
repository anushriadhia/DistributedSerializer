package serializer;

public class MutableString{
	
	StringBuffer buffer;
	int pos;
	
	public MutableString() {
		this.buffer = new StringBuffer(1000);
		this.pos = 0;
	}
	
	public void append(String obj) {
		this.buffer.append(obj);
		this.buffer.append('|');
	}
	
	public String read(int len) {
		String s = buffer.substring(this.pos, len);
		pos += len+1;
		
		return s;
	}
	
	public String toString() {
		return buffer.toString();
	}
}
