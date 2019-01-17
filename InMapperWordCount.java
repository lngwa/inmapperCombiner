package w1d3_q1;

import java.util.ArrayList;
import java.util.List;

public class InMapperWordCount {
	private List<Mapper> mappers;
	private List<GroupByPair<Integer, Pair<String, Integer>>> reducers;
	private int m_nums;
	private int r_nums;
	public InMapperWordCount(int m_nums, int r_nums) {
		this.mappers = new ArrayList<>();
		this.reducers = new ArrayList<>();
		this.m_nums = m_nums;
		this.r_nums = r_nums;
		
		for(int i = 0; i < m_nums; i++) {
			mappers.add(new Mapper());
		}
		
		for(int i = 0; i < r_nums; i++) {
			reducers.add(new GroupByPair<Integer, Pair<String,Integer>>(i));
		}
	}
	
	public void execute() {
		//get mapper outputs
		List<Pair<String, Integer>> m_out = new ArrayList<>();
		for (int i = 0; i < mappers.size(); i++) {
			m_out = mappers.get(i).map("./input/m"+i+".txt");	
			System.out.println("\nMapper " + i + " Output");
			System.out.println("===============");
			m_out.forEach(p -> System.out.println("<" + p.getKey() + ", " + p.getValue() + ">"));
			
			//Channel the mapper outputs to the right reducers
			for (Pair<String, Integer> pair : m_out) {
				int r = getPartition(pair.getKey());
				reducers.get(r).getValue().add(pair);
			}
		}
		
		//output the reducer outputs
		Reducer reducer;
		for (int k = 0; k < reducers.size(); k++) {
			GroupByPair<Integer, Pair<String, Integer>> r = reducers.get(k);
			System.out.println("\nReducer " + k + " Input");
			System.out.println("===============");
			
			reducer = new Reducer();
			List<Pair<String, Integer>> r_out = reducer.reduce(r.getValue());
			System.out.println("\nReducer " + k + " Output");
			System.out.println("===============");
			r_out.forEach(p -> System.out.println("<" + p.getKey() + ", " + p.getValue() + ">"));
		}
		
	}
	
	public int getPartition(String key){
		return (int) key.hashCode() % r_nums;
	}

}
