package w1d3_q1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Reducer {

		public List<Pair<String, Integer>> reduce(List<Pair<String, Integer>> m_output) {
			List<GroupByPair<String, Integer>> r_input = new LinkedList<>();
			
			Iterator<Pair<String, Integer>> it = m_output.iterator();
			
			//Creating list of GroupByPairs
			while(it.hasNext()) {
				//Check if it is in the GroupByPair list
				Pair<String, Integer> pair = it.next();
				GroupByPair<String, Integer> groupByPair = new GroupByPair<>(pair.getKey(), pair.getValue());
				if(r_input.contains(groupByPair)) {
					//get the groupbypair and add its value to key list
					r_input.get(r_input.indexOf(groupByPair)).getValue().add(pair.getValue());
					
				}else {
					r_input.add(groupByPair);
				}
			}
			
			Iterator<GroupByPair<String, Integer>> r = r_input.iterator();
			List<Pair<String, Integer>> r_output = new LinkedList<>();
			
			//Creating list of Pairs with key-value(total count) pair
			while(r.hasNext()) {
				//Check if it is in the GroupByPair list
				GroupByPair<String, Integer> pair = r.next();
				System.out.println("<" + pair.getKey() + ", " + pair.getValue().toString() + ">");		
				int count = pair.getValue().stream().mapToInt( i -> i.intValue()).sum();
				r_output.add(new Pair<String, Integer>(pair.getKey(), count));
			}
//			System.out.println("===========================");
			return r_output.stream().sorted().collect(Collectors.toList());
		}
	}
