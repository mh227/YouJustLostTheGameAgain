package YouJustLostTheGameAgain.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Henderson
 *
 * @param <T> - Type you want returned, whether it's a superclass or subclass of the type you search for. As long as you make sure returnList never contains anything else, you're good.
 */
public class SearchUtil<T> {
	
	public SearchUtil() {}
	
	/**
	 * @param objList - List of objects to search.
	 * @param condition - A SelectorCondition, specified by a lambda expression.
	 * @return - List of objects of type T that match the condition. Can be empty, contain one record, or contain multiple, but will not be null.
	 */
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
	
	/**
	 * @author Mike Henderson
	 * 
	 * Just a simple functional interface for use with lambda expressions for searching the model.
	 */
	public interface SelectorCondition {
		public abstract boolean matchesCondition(Object obj);
	}

}
