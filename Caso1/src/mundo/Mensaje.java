package mundo;

public class Mensaje {
	
	private int id;
	
	private double contenido;
	
	public Mensaje(int id, double contenido) {
		
		this.setId(id);
		this.contenido = contenido;
	}

	public double getContenido() {
		return contenido;
	}

	public void setContenido(double contenido) {
		this.contenido = contenido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
