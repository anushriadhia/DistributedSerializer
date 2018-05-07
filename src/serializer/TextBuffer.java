package serializer;

import java.io.StreamCorruptedException;

public class TextBuffer{

	StringBuffer buffer;
	int pos;
	
	public TextBuffer() {
		this.buffer = new StringBuffer(1000);
		this.pos = 0;
	}
	
	public void put(Object obj) {
		String s = obj.toString();
		writeString(String.format("%010d", s.length()));
		writeString(s);
	}
	
	private String readString(int len) {
		String s = buffer.substring(this.pos, this.pos+len);
		this.pos += len+1; // skip past the eye-catcher character
		
		return s;
	}

	private void writeString(String s) {
		buffer.append(s);
		buffer.append('|'); // add an eye-catcher character as a visual aid
	}
	
	public String get() throws StreamCorruptedException {
		try {
			int len = Integer.parseInt(readString(10));
			return readString(len);
		}
		catch (NumberFormatException ex) {
			throw new StreamCorruptedException();
		}
	}
	
	public String toString() {
		return buffer.toString();
	}
}
