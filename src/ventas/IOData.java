package ventas;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOData {

	
	
	public static void guardarDatos() {
		
		File f = new File("ventas.dat");
		
		Venta vVenta[] = new Venta[3];
		
		vVenta[0]= new Venta("Diego", 253, 3, 22.6);
		vVenta[1]= new Venta("Marcos", 471, 1, 129.99);
		vVenta[2]= new Venta("Jordi", 963, 5, 59.99);
		
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
				escribir.writeUTF(vVenta[i].getCliente());
				escribir.writeInt(vVenta[i].getProducto());
				escribir.writeInt(vVenta[i].getCantidad());
				escribir.writeDouble(vVenta[i].getPrecioUnitario());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	
}
