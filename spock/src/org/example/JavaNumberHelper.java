package org.example;

import java.util.*;

public class JavaNumberHelper {
	public int[] findPositives(int[] numbers) {
		List positivesList = new ArrayList();
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > 0) {
				positivesList.add(new Integer(numbers[i]));
			}
		}
		
		int[] positivesArray = new int[positivesList.size()];
		for (int i = 0; i < positivesArray.length; i++) {
			positivesArray[i] = ((Integer) positivesList.get(i)).intValue();
		}
		return positivesArray;
	}
}
