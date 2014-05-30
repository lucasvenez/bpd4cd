package br.ufscar.dc.gwm;

import java.util.HashMap;

public abstract class Edge<T1 extends Node, T2 extends Node> {

	protected T1 startNode;
	
	protected T2 endNode;

	protected HashMap<String,Object> attributes = new HashMap<String,Object>();

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(T1 startNode) {
		this.startNode = startNode;
	}

	public T2 getEndNode() {
		return endNode;
	}

	public void setEndNode(T2 endNode) {
		this.endNode = endNode;
		endNode.addIncomingEdge(this);
	}
	
	/**
	 * Associates the specified value with the specified key in this map. 
	 * If the map previously contained a mapping for the key, this method has no effect.
	 * 
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @return true whether the value no exists in the attributes and false otherwise 
	 */
	public boolean addAttribute(String key, Object value) {
		
		boolean result = false;
		
		if (!this.attributes.containsKey(key))
			this.attributes.put(key, value);
		
		return result;
	}
	
	/**
	 * Associates the specified value with the specified key in this map. 
	 * If the map previously contained a mapping for the key, the old value is replaced.
	 * 
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no mapping for key. 
	 * (A null return can also indicate that the map previously associated null with key.)
	 */
	public Object addOrUpdateAttribute(String key, Object value) {
		return this.attributes.put(key, value);
	}
	
	/**
	 * Removes the mapping for the specified key from this map if present.
	 * 
	 * @param key - key whose mapping is to be removed from the map
	 * @return the previous value associated with key, or null if there was no mapping for key. 
	 * (A null return can also indicate that the map previously associated null with key.)
	 */
	public Object removeAttribute(String key) {
		return this.attributes.remove(key);
	}
}
