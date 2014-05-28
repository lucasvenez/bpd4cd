package br.ufscar.dc.gwm;

import java.util.HashMap;

public abstract class Node {

	protected String name;

	protected boolean onPremise;

	protected HashMap<String,Object> attributes = new HashMap<String,Object>();

	protected Scope scope;
	
	public Node(String name) {
		this.name = name;
	}
	
	public Node(String name, boolean onPremise) {
		this(name);
		this.onPremise = onPremise;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnPremise() {
		return onPremise;
	}

	public void setOnPremise(boolean onPremise) {
		this.onPremise = onPremise;
	}

	public HashMap<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}
}
