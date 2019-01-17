package w1d3_q1;

import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Mapper {
		private Scanner file;

		public List<Pair<String, Integer>> map(String filePath) {
			try {
				file = new Scanner(new FileReader(filePath));
			} catch (Exception e) {			
				e.printStackTrace();
			}

			List<Pair<String, Integer>> words = new LinkedList<>();
			
			while (file.hasNext()) {	
				String word = file.next();
				if(word.matches("[a-zA-Z-'\"]+.")) {
					word = word.replaceAll("['\".]", "");
					String[] keys = word.split("-");
					for(int i = 0; i < keys.length; i++) {
						if(!keys[i].trim().isEmpty()) {
							Pair<String, Integer> pair = new Pair<String, Integer>(keys[i].toLowerCase(), 1);
							words.add(pair);
						}
					}
				}			
			}
			
			file.close();
			
			List<Pair<String, Integer>> pairs = words; //.stream().sorted().collect(Collectors.toList());
			
			List<GroupByPair<String, Integer>> in_mapPairs = new LinkedList<>();
			
			Iterator<Pair<String, Integer>> it = pairs.iterator();
			
			//Creating list of GroupByPairs
			while(it.hasNext()) {
				//Check if it is in the GroupByPair list
				Pair<String, Integer> pair = it.next();
				GroupByPair<String, Integer> groupByPair = new GroupByPair<>(pair.getKey(), pair.getValue());
				if(in_mapPairs.contains(groupByPair)) {
					//get the groupbypair and add its value to key list
					in_mapPairs.get(in_mapPairs.indexOf(groupByPair)).getValue().add(pair.getValue());
					
				}else {
					in_mapPairs.add(groupByPair);
				}
			}
			
			Iterator<GroupByPair<String, Integer>> r = in_mapPairs.iterator();
			List<Pair<String, Integer>> m_out = new LinkedList<>();
			
			//Creating list of Pairs with key-value(total count) pair
			while(r.hasNext()) {
				//Check if it is in the GroupByPair list
				GroupByPair<String, Integer> pair = r.next();
				int count = pair.getValue().stream().mapToInt(i -> i.intValue()).sum();
				m_out.add(new Pair<String, Integer>(pair.getKey(), count));
			}
			return m_out;
		}
	}
