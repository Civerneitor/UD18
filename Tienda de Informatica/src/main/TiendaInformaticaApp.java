package main;

import javax.swing.JOptionPane;

import models.Conexion;

public class TiendaInformaticaApp {

	private static final String BDname = "Tienda_Informatica";

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL
		String user = JOptionPane.showInputDialog("Introduce tu nombre de usuario");
		String passw = JOptionPane.showInputDialog("Introduce tu contraseña");
		Conexion c = new Conexion(user,passw);
		
		
		c.createDB(BDname);
		c.createTable(BDname,"Tabla_1");
		c.insertData(BDname,"Tabla_1","name", "lastname", "age", "H");
		c.getValues(BDname,"Tabla_1");
		//c.deleteRecord(BDname,"Tabla_1", "1");
		c.closeConnection();
	}

}
