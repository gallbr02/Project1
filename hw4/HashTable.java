

/**
 * HashTable.java
 * @author Brandon Gallagher
 * representation of a HashTable
 * @param <K> key
 * @param <V> value
 */
public class HashTable<K, V> {

	private class HashEntry<K, V> {
		K key;
		V value;
		public HashEntry (K k, V v){
			key = k;
			value = v;
		}
	}
	public HashTable() {
		this(SIZE, 0.5);
	}
	public HashTable(double lf) {
		table = (HashEntry<K, V>[]) new HashEntry[SIZE];
		maxLF = lf;
	}
	public HashTable(int cap) {
		this(cap, 0.5);
	}
	public HashTable(int cap, double lf) {
	cap = cap;
	count = 0;
	//table = (HashTable<K, V>.HashEntry<K, V>[];
	maxLF = lf;
	}
	/**
	 * computes the keys hash code method provided by java
	 * hashCode() returns a siggned integer which means that it may return a negative int
	 * returns the hashcode from modulus internal table size;
	 * write a smallloop to add all integer values of each symbol in the key
	 * @param key that holds each symbol to add all integers
	 * @return index must be in [0, internal__table_size -1] 
	 */
	public int hash(K key) {
		System.out.println(table.length);

		return (key.toString().length() % table.length);
	}
	/**
	 *1.  if this table contains key, this method replaces the value currently
	 * associated with key with the new value and returns the old.
	 * 
	 * 2. if this table does not contain key, this method adds a new[key, value]
	 * entry into the table and returns null
	 * @param key key that is being added or replaced.
	 * @param value they would be associated with the key
	 * @return the old value.
	 */
	public V put(K key, V value) {

		if(((count + 1.0)/ table.length) >= maxLF) {
			System.out.println("resizing");
			resize();
		}
		int hashI = hash(key);
		//int index = indexOf(key);
		HashEntry<K,V> space = new HashEntry<K,V> (key, value);
		/*
		if(indexOf(key)==-1) {
			table[hashI] = space;
			++ count;
			return null;
		}else {
			if(table[index].key.equals(key)) {
				V temp = table[index].value;
				table[index].value = value;
				return temp;
			}else {
				return null;
			}
		}
		 */
		// null entry index without DEL ---->put new entry
		// 					with first DEL index ----> put new entry
		// match: replace value
		// neither: rehash
		int q = 0;
		for(int i = hashI; i < table.length; i = hashI + (q*q)) {
			if(table[i] == null) {
				return null;
			}
			else if(table[i] == DEL) {
				table[hashI] = space;
				++count;
				return null;
			}
			else if(table[i].key.equals(key)) {
				V temp = table[i].value;
				table[i].value = value;
				return temp;
			}else {
				return null;
			}
			/*
			else {
				HashEntry<K, V> he = new HashEntry<K, V>(key, value);
				++count;
				return null;
			}
			 */
		}
		for(int i = 0; i < hashI; i = hashI + (q*q)) {
			if(table[i] == null) {
				return null;
			}
			else if(table[i] == DEL) {
				table[hashI] = space;
				++count;
				return null;
			}	
			else if(table[i].key.equals(key)) {
				V temp = table[i].value;
				table[i].value = value;
				return temp;
			}else {
				return null;
			}
		}
		++q;
		return value;
	}

	/** 
	 * returns true if there is an entry with matching key
	 * @param key that is being matched 
	 * @return true if there is an entry with matching key
	 */
	public boolean containsKey(K key) {
		/*
		for(int i = hashI; i < table.length; i = (int) (i * Math.pow(m, 2))) {
			if(table[i] == null ) {
				return false;
			}
			else if(table[i] != DEL) {
				if(key.equals(table[i].key)) {
					return true;
				}
			}
		}
		for(int i = 0; i < hashI; ++i) {
			if(table[i] == null ) {
				return false;
			}
			else if(table[i] != DEL) {
				if(key.equals(table[i].key)) {
					return true;
				}
			}
		}
		 */

		if(indexOf(key) != -1) {
			return true;
		}

		return false;
	}
	/**
	 * returns true if there is an entry with matching value
	 * @param value that is being matched 
	 * @return true if there is an entry with matching value
	 */
	public boolean containsValue(V value) {
		/*
		for(int i = hashI; i < table.length; i = (int) (i * Math.pow(m, 2))) {
			if(table[i] == null ) {
				return false;
			}
			else if(table[i] != DEL) {
				if(value.equals(table[i].key)) {
					return true;
				}
			}
		}
		 */
		for (int i = 0; i < table.length; ++i) {
			if(value.equals(table[i].value)) {
				return true;
			}else {
				return false;
			}
		}

		return false; 
	}
	/**
	 * returns true if there is an entry with matching key
	 * @param key that is being matched 
	 * @return true if there is an entry with matching key
	 */
	public V get(K key) {
		int hashI = hash(key);
		if(table[hashI] != null && table[hashI] != DEL) {
			if(key.equals(table[hashI].key)){
				return table[hashI].value;
			}
		}
		return null;
	}


	/**
	 * removes the key at the associated value
	 * @param key that is being removed
	 * @return the key that is being removed and replace it with DEL
	 */
	public V remove(K key) {
		int hashI = hash(key);
		HashEntry<K,V> delete = null;
		for(int i = hashI; i < table.length; ++i) {
			if(table[i] == null) {
				return null;
			}
			else if(key.equals(table[i].key)) {
				table[i] = delete;
			}
		}
		--count;
		return delete.value;
	}
	/**
	 * returns the number of key value pairs stored in the table
	 * @return the number of key vallue pairs in the table
	 */
	public int size() {
		return count;
	}
	/**
	 * return true if the table is empty and false otherwise
	 * @return true if the table is empty and false otherwise
	 */
	public boolean empty() {
		return (count == 0);
	}
	/**
	 * clears the table
	 */
	public void clear() {
		//for(int i = 0; i < table.length; ++i) {
		//table[i] = null;
		//}
		//return;
		//make a new table that is empty
		//HashEntry<K, V>[] nTable = new HashEntry<K,V>(null, null);
		//table = nTable;
		HashEntry<K,V>[] nTable =(HashEntry<K, V>[]) new HashEntry[0]; 
		nTable= table;
	}
	/**
	 * returns a print friendly string with entire context of this hashtable
	 * load factor:
	 * max load factor:
	 * current size:
	 * return "" for empty hash table
	 */
	public String toString() {
		String str = "";
		str += "loadFactor = " + lf + "\n";
		str += "max loadFactor = " + maxLF + "\n";
		str += "count = " + count + "\n\n";

		int i = 0;
		for(HashEntry<K,V> entry: table) {
			if(entry != null) {
				str += i + ": ";
				if(entry.equals(DEL)) {
					str += "DELETED\n";
				}
				else {
					//str += entry.toString() + "\n";
					str+= entry + "\n";
				}
			}
		}
		return str;
	}
	/**
	 * 
	 * @param key of the appropriate index called
	 * @return the index of the key
	 */
	private int indexOf(K key) {
		int hashI = hash(key);
		int i = hashI;
		int num = 0;
		while(i < table.length) {
			if(table[i] == null && table[i].key == null) {
				if(table[i] == DEL) {
					return -1;
				}
				else if(key.equals(table[i].key)) {
					return i;
				}

				i += num*num;
				++num;
			}

		}
		int yup = i % table.length;
		while(yup< i) {
			if(table[i] == null && table[i].key == null) {
				if(table[i] == DEL) {
					return -1;
				}
				else if(table[yup].key.equals(key)) {
					return yup;
				}
				i += num*num;
				yup = i % table.length;
				++num;
			}
		}
		return yup;
	}
	/**
	 * increases the internal storage capacity by
	 * 		finding the smallest prime number greater than the double of current table size
	 * 		correctly copy all existing entries to the new table
	 * automatically called from the put method
	 * 		fill rate of the internal table reaches maxLF
	 * 		OR the quadratic probing is not successful
	 */
	public static int getPrime(int prime) {
		++prime;
		for(int i = 2; i < prime; ++i) {
			if(prime % i == 0) {
				++prime;
				i = 2;
			}else {
				continue;
			}
		}
		return prime;
	}
	private void resize() {
		cap = cap * 2;
		HashEntry<K,V>[] nTable =(HashEntry<K, V>[]) new HashEntry[cap];
		cap = getPrime(cap);
		int i = 0;
		for(HashEntry<K,V> input: table) {
			if(input != null) {
				nTable = table;
			}
			++i;
		}
		table = nTable;
	}
	public static void main(String[] args) {
		HashTable<String, Integer> table = new HashTable<String, Integer>();
//		//printTable(table);
//		String[] keys = {"Alex", "Tony", "Brandon", "Ogden", "Hannah", "Mateus", "Orrin", "Andeulazia",
//
//				"Wil", "Liam", "Alex", "Isaac", "Jenna", "Chase", "Nate"};
//		Integer[] vals = new Integer[keys.length];
//		Integer[] hash = new Integer[keys.length];
//		for (int i = 0; i < keys.length; ++i) {
//			vals[i] = i + 1;
//			hash[i] = table.hash(keys[i]);
//			System.out.printf("%2d: put(\"%s\") primary index = %d\n", (i+1), keys[i], hash[i]);
//			table.put(keys[i], vals[i]);
//			System.out.println(table);
//		}
		// try to delete some keys
		// try to add the deleted keys back
		System.out.println(table.put("Brandon", 1));
		System.out.println(table.put("ogden", 2));
		System.out.println(table.put("mateus", 3));
		System.out.println(table.put("nate", 4));
		System.out.println(table.put("jenna", 2));
		System.out.println(table.put("hannah", 5));
		System.out.println(table.put("joe", 3));
		
		System.out.println();

		System.out.println(table.remove("nate"));
		System.out.println(table.remove("jenna"));
		System.out.println(table.remove("hannah"));
		System.out.println(table.remove("joe"));
		
		System.out.println();
		
		System.out.println(table.get("nate"));
		System.out.println(table.get("jenna"));
		System.out.println(table.get("hannah"));
		System.out.println(table.get("joe"));
		
		System.out.println();
		
		System.out.println(table.containsKey("nate"));
		System.out.println(table.containsKey("jenna"));
		System.out.println(table.containsKey("hannah"));
		System.out.println(table.containsKey("joe"));
		
		System.out.println();
		
		System.out.println(table.containsValue(1));
		System.out.println(table.containsValue(3));
		System.out.println(table.containsValue(4));
		System.out.println(table.containsValue(5));
	}

	private HashEntry<K, V>[] table;
	private double lf;
	private double maxLF;
	private int count;
	private int cap;
	public final HashEntry<K, V> DEL = new HashEntry<K, V> (null, null);
	public static final int SIZE = 10;
}
