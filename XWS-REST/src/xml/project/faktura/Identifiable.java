package xml.project.faktura;

/**
 * Olaksava implementaciju generickog data access object bean-a. 
 *
 */
public abstract class Identifiable {

	public abstract Long procitajId();
	
	public abstract void postaviID(Long id);
	
}
