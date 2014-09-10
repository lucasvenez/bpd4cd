package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.node.ActivityNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class EmptyParser extends ActivityParser<Element, ActivityNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7251376882312559471L;

	public EmptyParser(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActivityNode parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
