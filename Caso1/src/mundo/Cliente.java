package mundo;

public class Cliente extends Thread{
	
	private int cedula;
	private Mensaje[] mensajesEnviar;
	
	private Buffer buffer;
	
	
	
	public Cliente (int cedula, int limiteM, Buffer buffer) {
		
		this.cedula = cedula;
		mensajesEnviar = new Mensaje[limiteM];
		this.buffer = buffer;
		
	}

	
	public void run() {
		
		int contador = 0;
		
		for(Mensaje m : mensajesEnviar) {
			m = new Mensaje(cedula + contador, Math.random()*100);
			contador++;
			buffer.almacenarMensaje(m);
			esperaAleatoria(1000, "CLIENTE: Mensaje enviado #" + contador + " Cliente: " + cedula);
		}
		buffer.retirarCliente();
	
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
}
