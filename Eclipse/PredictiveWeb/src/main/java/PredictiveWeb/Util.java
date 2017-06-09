package PredictiveWeb;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

	/**
	 * Split a string; the default usual StringTokenizer won't recognize fields
	 * when 2 separators follow.
	 * 
	 * @param iString
	 *            String containing different elements to split
	 * @param iSeparator
	 *            Separator char use to build the String
	 * @return an array of String objects.
	 */
	public static String[] splitString(String iString, char iSeparator) {
		// first count the number of separator
		int i;
		int lCount = 0;
		for (i = 0; i < iString.length(); i++) {
			if (iString.charAt(i) == iSeparator) {
				lCount = lCount + 1;
			}
		}
		lCount = lCount + 1; // nb fields = nb sep + 1
		String[] lResult = new String[lCount];
		int lStart = 0;
		int lWord = 0;
		for (i = 0; i < iString.length(); i++) {
			if (iString.charAt(i) == iSeparator) {
				// found a new one
				lResult[lWord] = iString.substring(lStart, i);
				lWord = lWord + 1;
				lStart = i + 1;
			}
		}
		// last one
		lResult[lWord] = iString.substring(lStart);
		return lResult;
	}

	/**
	 * Find the position of an object contained in an array of objects.
	 * 
	 * @param iArray
	 *            Collection of Objects
	 * @param iObject
	 *            Object to find in the array
	 * @return index of the found object, -1 if not found
	 */
	public static int findIndexInArray(Object[] iArray, Object iObject) {
		for (int i = 0; i < iArray.length; i++) {
			if ((iArray != null) && (iArray[i].equals(iObject))) {
				return i;
			}
		}
		return -1;
	}

	public static Map<String, String> parseString(String input) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String keyValue : input.split(" *& *")) {
			String[] pairs = keyValue.split(" *= *", 2);
			map.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
		}
		System.out.println(map);
		return map;
	}
}
