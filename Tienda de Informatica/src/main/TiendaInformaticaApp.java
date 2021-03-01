package main;

import javax.swing.JOptionPane;

import models.Conexion;

public class TiendaInformaticaApp {

	private static final String BDname = "Tienda_Informatica";
	private static final String TABLA_1 = "Fabricantes";
	private static final String TABLA_2 = "Articulos";

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL
		String user = JOptionPane.showInputDialog("Introduce tu nombre de usuario");
		String passw = JOptionPane.showInputDialog("Introduce tu contraseña");
		Conexion c = new Conexion(user,passw);
		
		
		c.createDB(BDname);
		String Query = "CREATE TABLE Fabricantes "
				+ "(codigo serial not null PRIMARY KEY, Nombre VARCHAR(100))";
		c.createTable(BDname,Query);
		Query = "create table Articulos ("
				+ "codigo serial not null primary key,"
				+ "nombre varchar(100),"
				+ "precio int,"
				+ "fabricante bigint unsigned,"
				+ "foreign key (fabricante) "
				+ "references Fabricantes(codigo)"
				+ "on delete set null "
				+ "on update cascade);";
		c.createTable(BDname,Query);
		for(int i = 0; i<5; i++) {
			String name = JOptionPane.showInputDialog("Introduce el nombre del fabricante");
			c.insertData(BDname,TABLA_1,name);
		}
		for(int i = 0; i<5; i++) {
			String name = JOptionPane.showInputDialog("Introduce el nombre del articulo");
			int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduce el precio del articulo"));
			int fabricante = Integer.parseInt(JOptionPane.showInputDialog("Introduce el fabricante del articulo"));
			c.insertData(BDname,TABLA_2,name,precio,fabricante);
		}
		//c.insertData(BDname,"Tabla_1","name", "lastname", "age", "H");
		c.getValues(BDname,TABLA_1,TABLA_2);
		//c.deleteRecord(BDname,"Tabla_1", "1");
		c.closeConnection();
	}

}
