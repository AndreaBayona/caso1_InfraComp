package mundo;

import java.util.ArrayList;

public class Buffer {

	private int tamanio;
	private int cantidadClientes;
	private ArrayList<Mensaje> mensajes;

	public Buffer(int tamanio, int cantidadClientes) {

		this.tamanio = tamanio;
		this.cantidadClientes = cantidadClientes;
		mensajes = new ArrayList<Mensaje>();

	}

	public void almacenarMensaje(Mensaje m) {

		synchronized(this) {
			while(mensajes.size() == tamanio) {
				
				try {
					System.out.println("El buffer está lleno: ESPERE");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		synchronized (this) {
			mensajes.add(m);
			System.out.println("////////////TAMANIO COLA DE MENSAJES: " + mensajes.size());
		}
		synchronized(m) {
			try {
				m.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void responderMensajes() {

		while(mensajes.isEmpty() && cantidadClientes > 0) {
			//System.out.println("loop");
			//NO HAGA NADA
			Thread.yield();
		}

		synchronized(this) {
			if(!mensajes.isEmpty()) {
				Mensaje retirado;
				retirado = mensajes.remove(0);
				retirado.setContenido(retirado.getContenido() +1); 
				System.out.println("Modifica el contenido del mensaje");
				notify();
				synchronized(retirado) {
					retirado.notifyAll();
				}
			}
		}


	}

	public synchronized boolean terminar(){
		return cantidadClientes == 0 && mensajes.isEmpty();
	}

	public synchronized void retirarCliente() {
		cantidadClientes--;
		System.out.println("*********************CANTIDAD CLIENTES " + cantidadClientes);
	}


	public synchronized boolean vacio() {
		return mensajes.isEmpty();
	}


	public synchronized boolean hayClientes() {
		//System.out.println("Hay clientes " + cantidadClientes);
		return cantidadClientes > 0;
	}

	

	public int clientes() {
		return cantidadClientes;
	}

}
