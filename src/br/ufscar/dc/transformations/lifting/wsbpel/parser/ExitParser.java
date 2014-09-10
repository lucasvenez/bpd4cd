package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.node.control.ExitNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class ExitParser extends ActivityParser<Element, ExitNode> {

	private static final long serialVersionUID = -291378980066834758L;

	public ExitParser(Element activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ExitNode parse() throws UnsupportedDataTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
