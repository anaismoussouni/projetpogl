package Modele;



public class Objet {

    protected Element element;
    protected Type type;
	
    
    public enum Element {
    	AIR,
    	EAU,
    	FEU,
    	TERRE,
    };
    
    public enum Type {
    	CLE,
    	ARTEFACT,
    };
    
    public Objet(Type type, Element element){
    	
    	this.type = type;
    	this.element = element;	
    }


	public Type getType() {
    	return this.type;
    }
    
    public Element getElement() {
    	return this.element;
    }
    

	
    public boolean equals(Object o) {
		
    	if ( ! (o instanceof Objet) )
			return false;
		
		Objet c = (Objet) o;
		return (c.element == element) && (c.type == type);
	}
    
}
