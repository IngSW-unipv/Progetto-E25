package it.unipv.ingsw.model.spedizione;

public class Coordinate {

	private double latitudine, longitudine;

	public Coordinate(double longitudine, double latitudine) {
		this.longitudine = longitudine;
		this.latitudine = latitudine;
	}

	public double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}

	public double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
	
	//distanza punto-punto
	public double distanza(Coordinate x) {
		
		return Math.sqrt(Math.pow(this.getLatitudine()-x.getLatitudine(), 2)+Math.pow(this.getLongitudine()-x.getLongitudine(), 2));
	}
	
	//distanza punto-segmento (segmento ab, punto this)
	//restituisce una distanza positiva se punto dentro segmento, distanza negativa se punto fuori da segmento
	public double distanza(Coordinate ra, Coordinate rb) {
		double ax = ra.getLongitudine();
		double ay = ra.getLatitudine();
		double bx = rb.getLongitudine();
		double by = rb.getLatitudine();
		double px = this.getLongitudine();
		double py = this.getLatitudine();
		
		//evito divisione per zero se il segmento è un punto singolo
	    if (ax == bx && ay == by) {
	        return this.distanza(ra);
	    }
	    
		//vettore AB
	    double ABx = bx - ax;
	    double ABy = by - ay;

	    //vettore AP
	    double APx = px - ax;
	    double APy = py - ay;

	    //prodotto scalare
	    double dotProduct = APx * ABx + APy * ABy;

	    //modulo quadro di AB
	    double AB2 = ABx * ABx + ABy * ABy;

	    //fattore di proiezione normale sulla retta
	    double t = dotProduct / AB2;

	    if (t < 0) {
	        //ho la proiezione di p fuori dal segmento dal lato di a
	        return - this.distanza(ra);
	    } else if (t > 1) {
	        ///ho la proiezione di p fuori dal segmento dal lato di b
	        return - this.distanza(rb);
	    } else {
	        //la proiezione di p è interna al segmento, quindi uso formula distanza punto retta
	    	double a = by - ay;
			double b = ax - bx;
			double c = bx * ay - ax * by;

			return Math.abs(a * px + b * py + c) / ra.distanza(rb);
	    }
	
	}

}
