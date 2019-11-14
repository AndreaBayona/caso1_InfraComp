package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	static Buffer buffer;
	static ArrayList<Cliente> clientes;
	static ArrayList<Servidor> servidor;
	static File archivo;
	static int numClientes;
	static int numServidores;
	static int tamBuffer;

	public static void main(String[] args) {

		//carga archivo
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenido, presione 1 para cargar el archivo con las configuraciones del programa");
		int option = sc.nextInt();
		if(option==1) {
			archivo=new File("."+File.separator+"data"+File.separator+"config.txt");

			Properties data = new Properties();
			FileInputStream in=null;
			try {
				in = new FileInputStream( archivo );
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			try
			{
				data.load( in );
				in.close( );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}

			numClientes = Integer.parseInt(data.getProperty("numClientes"));
			numServidores = Integer.parseInt(data.getProperty("numServidores"));
			tamBuffer = Integer.parseInt(data.getProperty("tamBuffer"));

			clientes = new ArrayList<Cliente>();
			servidor = new ArrayList<Servidor>();
			buffer = new Buffer(tamBuffer, numClientes);
			
			int numConsultas;

			for (int i = 0; i < numClientes; i++) {
				numConsultas = Integer.parseInt(data.getProperty("numConsultasCliente"+i));
				Cliente c = new Cliente(10000*i, numConsultas, buffer);
				c.start();
				clientes.add(c);
			}

			for (int i = 0; i < numServidores; i++) {
				Servidor s = new Servidor(1000+i, buffer);
				s.start();
				servidor.add(s);
			}
		}
	}


}
