package org.basex.rest;

/**
 * Olaksava implementaciju generickog data access object bean-a. 
 *
 */
public abstract class Identifiable {

	public abstract Long getId();
	
	public abstract void setId(Long id);
	
}
