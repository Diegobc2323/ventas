package ventas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class IOData {

	
	
	public static void escribirDatos() {
		
		File f = new File("ventas.dat");
		
		Venta vVenta[] = new Venta[10];
		
		vVenta[0]= new Venta("Diego", 253, 1, 22.6);
		vVenta[1]= new Venta("Marcos", 471, 1, 129.99);
		vVenta[2]= new Venta("Jordi", 963, 5, 59.99);
		vVenta[3]= new Venta("Diego", 25, 1, 4.4);
		vVenta[4]= new Venta("Diego", 26, 1, 4);
		
		
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try (FileOutputStream fo = new FileOutputStream(f);
			DataOutputStream escribir = new DataOutputStream(fo);){
			
			for (int i = 0; i < vVenta.length; i++) {
				if (vVenta[i]!=null) {
					escribir.writeUTF(vVenta[i].getCliente());
					escribir.writeInt(vVenta[i].getProducto());
					escribir.writeInt(vVenta[i].getCantidad());
					escribir.writeDouble(vVenta[i].getPrecioUnitario());
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	
	public static Venta[] leerDatos() {
		
		File f = new File("ventas.dat");
		
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Venta vVenta[] = new Venta[10];
		
		String cliente="";
		int cont=0, producto=0, cantidad=0;
		double precioUnitario=0;
		
		
		try (FileInputStream fi = new FileInputStream(f);
			DataInputStream leer = new DataInputStream(fi)){
			
			while (true) {
				
				cliente=leer.readUTF();
				producto=leer.readInt();
				cantidad=leer.readInt();
				precioUnitario=leer.readDouble();
				
				vVenta[cont]= new Venta(cliente, producto, cantidad, precioUnitario);
				
				cont++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fin de lectura");
		}
		
		return vVenta;
		
	}

	
	public static double totalVentasCliente(String cli) {
		
		Venta vVenta[] = leerDatos();
		
		double deuda=0, aux=0;
		
		for (Venta v : vVenta) {
			if (v!=null && v.getCliente().equalsIgnoreCase(cli)) {
				aux = v.getCantidad() * v.getPrecioUnitario();
				deuda+=aux;
			}
		}
		
		return deuda;
			
		
		
	}
	
	
	public static int numClientes() {
		
		int cont=0;
		boolean repetido = false;
		Venta vVentas[] = leerDatos();
		
		
		for (int i = 0; i < vVentas.length; i++) {
			repetido=false;
			if (vVentas[i]!=null && i<(vVentas.length-1)) {
				for (int j = i+1; j < vVentas.length; j++) {
					if (vVentas[j]!=null && vVentas[i].getCliente().equalsIgnoreCase(vVentas[j].getCliente())) {
						repetido=true;
						
						break;
					}
				}
				
				if (repetido==false) {
					cont++;
				}
				
			}

		}
		
		
		return cont;
	}
}
