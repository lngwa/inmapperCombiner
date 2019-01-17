package w1d3_q1;	
import java.util.LinkedList;
import java.util.List;

public class GroupByPair<K extends Comparable<K>, V> implements Comparable<GroupByPair<K,V>>{
	
	private K key;
	private List<V> value;
	
	public GroupByPair(K key) {
		this(key, null);
	}
	
	public GroupByPair(K key, V value) {
		this.key = key;
		this.value = new LinkedList<>();
		if(value != null) {
			this.value.add(value);			
		}
	}
	
	public K getKey() {
		return key;
	}
	
	public List<V> getValue() {
		return value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupByPair<K,V> other = (GroupByPair<K, V>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		
		return true;
	}
	
	@Override
	public int compareTo(GroupByPair<K,V> pair) {
		return key.compareTo((K) pair.getKey());
	}
	
	
}
