package br.ufscar.dc.gwm.interfaces;

import br.ufscar.dc.gwm.construction.Construction;

public interface INestedNode<T1> {

	public void setParentConstruction(Construction<T1> construction);
}
