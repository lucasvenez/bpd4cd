package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.node.ActivityNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class WaitParser extends ActivityParser<Element, ActivityNode> {

	private static final long serialVersionUID = -8120938323199521078L;

	public WaitParser(Element activity) {
		super(activity);
	}

	@Override
	public ActivityNode parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
