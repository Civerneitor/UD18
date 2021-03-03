package main;

import javax.swing.JOptionPane;

import models.Conexion;

public class EmpleadosApp {

	private static final String BDname = "EMPLEA2";
	private static final String TABLA_1 = "Departamentos";
	private static final String TABLA_2 = "Empleados";

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL
		String user = JOptionPane.showInputDialog("Introduce tu nombre de usuario");
		String passw = JOptionPane.showInputDialog("Introduce tu contraseña");
		Conexion c = new Conexion(user, passw);

		c.createDB(BDname);
		String Query = "CREATE TABLE Departamentos "
				+ "(Codigo int not null PRIMARY KEY, Nombre VARCHAR(100), Presupuesto int)";
		c.createTable(BDname, Query);
		Query = "create table Empleados (DNI varchar(8) not null primary key,Nombre varchar(100),Apellidos varchar(255),Departamento int, foreign key (Departamento) references Departamentos(Codigo));";
		c.createTable(BDname, Query);
		for (int i = 0; i < 5; i++) {
			int code = Integer.parseInt(JOptionPane.showInputDialog("Introduce el codigo del departamento"));
			String name = JOptionPane.showInputDialog("Introduce el nombre del departamento");
			int presupuesto = Integer.parseInt(JOptionPane.showInputDialog("Introduce el presupuesto del departamento"));
			c.insertData(BDname, TABLA_1, code, name, presupuesto);
		}
		for (int i = 0; i < 5; i++) {
			String DNI = JOptionPane.showInputDialog("Introduce el DNI del empleado");
			String name = JOptionPane.showInputDialog("Introduce el nombre del empleado");
			String apellidos = JOptionPane.showInputDialog("Introduce los apellidos del empleado");
			int departamento = Integer.parseInt(JOptionPane.showInputDialog("Introduce el codigo del departamento"));
			c.insertData(BDname, TABLA_2, DNI, name, apellidos, departamento);
		}
		// c.insertData(BDname,"Tabla_1","name", "lastname", "age", "H");
		c.getValues(BDname, TABLA_1, TABLA_2);
		// c.deleteRecord(BDname,"Tabla_1", "1");
		c.closeConnection();
	}

}
