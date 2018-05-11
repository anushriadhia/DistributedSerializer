package serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public interface ClassTag {
	Class[] classMap = new Class[] {
			Boolean.class, 
			Double.class, 
			Float.class, 
			Integer.class, 
			Long.class, 
			Short.class, 
			String.class, 
			HashSet.class, 
			ArrayList.class, 
			Vector.class, 
			HashMap.class, 
			Hashtable.class,
			null,
			Enum.class
	};

}
