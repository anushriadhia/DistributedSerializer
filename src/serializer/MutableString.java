package serializer;

import java.io.StringReader;

public class MutableString{
	
	int ptr;
	StringReader str;
	
	public MutableString(String str) {
		ptr = 0;
		this.str = new StringReader(str);
	}
	
	public int deserializeString() {
		return ptr++;
	}
	

}
