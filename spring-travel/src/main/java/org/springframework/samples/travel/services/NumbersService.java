package org.springframework.samples.travel.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NumbersService {
    List<Integer> findPositives(Collection<Integer> numbers) {
        if (numbers == null) return Collections.EMPTY_LIST;

        List<Integer> result = new ArrayList<Integer>(numbers.size());
        
        for (Integer i : numbers) {
            if (i >= 0) result.add(i);
        }
        return result;
    }
}
