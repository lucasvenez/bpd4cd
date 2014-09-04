package br.ufscar.dc.gwm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public abstract class Attribute implements Serializable {

	private static final long serialVersionUID = 974556228010869488L;
	
	protected HashMap<String,Object> attributes = new HashMap<String,Object>();
	
	public HashMap<String, Object> getAttributes() {
		return this.attributes;
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
	 * Associates the specified value with the specified key in the attribute list. 
	 * If the attribute list previously contained a value for the key, the old value is replaced.
	 * 
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no value for key. 
	 * (A null return can also indicate that the attribute list previously associated null with key.)
	 */
	public Object addOrUpdateAttribute(String key, Object value) {
		return this.attributes.put(key, value);
	}
	
	/**
	 * Removes the value for the specified key from the attribute list if present.
	 * 
	 * @param key - key whose value is to be removed from the attribute list
	 * @return the previous value associated with key, or null if there was no value for key. 
	 * (A null return can also indicate that the attribute list previously associated null with key.)
	 */
	public Object removeAttribute(String key) {
		return this.attributes.remove(key);
	}
	
	/**
	 * Returns true if the attribute list contains the key.
	 * 
	 * @param key key whose presence is tested
	 * @return true if there is the key between the attribute keys.
	 */
	public boolean hasAttribute(String key) {
		return this.attributes.containsKey(key);
	}
	
	/**
	 * Returns true if the attribute list contains the value.
	 * 
	 * @param value value whose presence is tested
	 * @return true if there is the value between the attribute values.
	 */
	public boolean hasValue(Object value) {
		return this.attributes.containsValue(value);
	}

	/**
	 * Returns the value associated to the specified key
	 * 
	 * @param key - key whose the associated value is returned
	 * @return the value associated to the specified key
	 */
	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}
	
	/**
	 * Returns the value associated to the specified index
	 * 
	 * @param index - index whose associated value is returned
	 * @return the value associated to the specified index
	 * @throws IndexOutOfBoundsException
	 */
	public Object getAttribute(int index) throws IndexOutOfBoundsException {
		return this.attributes.values().toArray()[index];
	}
	
	public boolean hasKey(String key) {
		return attributes.containsKey(key);
	}
	
	public Set<String> getKeys() {
		return attributes.keySet();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
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
		Attribute other = (Attribute) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		return true;
	}
}
