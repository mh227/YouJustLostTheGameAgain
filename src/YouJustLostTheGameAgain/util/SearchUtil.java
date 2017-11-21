package YouJustLostTheGameAgain.util;

import java.util.ArrayList;
import java.util.List;

public class SearchUtil<T> {
	
	public SearchUtil() {}
	
	//Ensure that ONLY Objects of type T make it into returnList! We don't need any ClassCastExceptions!
	public List<T> select(List<Object> objList, SelectorCondition condition) {
		List<T> returnList = new ArrayList<T>();
		for(Object obj : objList) {
			if(condition.matchesCondition(obj)) {
				returnList.add((T)obj);
			}
		}
		return returnList;
	}
	
	public interface SelectorCondition {
		public abstract boolean matchesCondition(Object obj);
	}

}
