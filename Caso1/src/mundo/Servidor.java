package mundo;

public class Servidor extends Thread {

	private long id;

	private Buffer buffer;

	public Servidor(long id, Buffer buffer) {
		this.setId(id);
		this.buffer = buffer;
	}

	public void run() {

		while (!buffer.terminar()) {
			buffer.responderMensajes();
			esperaAleatoria(2000, "Procesando por el servidor: " + id);
		}
	}
	
	private void esperaAleatoria(int tiempoMaximo, String mensaje) {
		long espera = (long) (Math.random() * tiempoMaximo);
		System.out.println("Esperando " + espera + " milisegundos " + mensaje);
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
