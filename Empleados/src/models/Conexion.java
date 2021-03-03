package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Conexion {
	
	public Connection conexion;
	
	public Conexion(String user, String passwd) {
		openConnection(user,passwd);
	}
	

	
	//METODO QUE ABRE LA CONEXION CON SERVER MYSQL
	public void openConnection(String user, String passwd) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion=DriverManager.getConnection("jdbc:mysql://192.168.1.40:3306?useTimezone=true&serverTimezone=UTC",user,passwd);//credenciales temporales
			System.out.print("Server Connected");
			fecha();
			
		}catch(SQLException | ClassNotFoundException ex  ){
			System.out.print("No se ha podido conectar con mi base de datos");
			fecha();
			System.out.println(ex.getMessage());
			
		}
		
	}
		
	//METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
	public void closeConnection() {
		try {
	
			conexion.close();
			System.out.print("Server Disconnected");
			fecha();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.print("Error cerrando conexion");
			fecha();
		}
	}
	
	//METODO QUE CREA LA BASE DE DATOS
	public void createDB(String name) {
		try {
			String Query="CREATE DATABASE "+ name;
			Statement st= conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("DB creada con exito!");
			
		JOptionPane.showMessageDialog(null, "Se ha creado la DB " +name+ "de forma exitosa.");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la DB.");
		}	
	}

	//METODO QUE CREA TABLAS MYSQL
	public void createTable(String db,String q) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = q;
					//+ "Edad VARCHAR(3), Sexo VARCHAR(1))";
			Statement st= conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");
			
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
			System.out.println("Error creando tabla.");
			
		}
		
	}
	
	//METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public void insertData(String db, String table_name, int code, String name, int presupuesto) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "INSERT INTO " + table_name + " (Codigo,Nombre,Presupuesto) VALUE(" 
					+ "\"" + code + "\", "
					+ "\"" + name + "\", "
					+ "\"" + presupuesto + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("Datos almacenados correctamente");;
			
		} catch (SQLException ex ) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
					
	}
	public void insertData(String db, String table_name, String DNI, String name, String apellidos, int departamento) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "INSERT INTO " + table_name + " (DNI, Nombre, Apellidos, Departamento) VALUE(" 
					+ "\"" + DNI + "\", "
					+ "\"" + name + "\", "
					+ "\"" + apellidos + "\", "
					+ "\"" + departamento + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("Datos almacenados correctamente");;
			
		} catch (SQLException ex ) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
					
	}
	//METODO QUE OBTIENE VALORES MYSQL
	public void getValues(String db, String table_name, String table_name2) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "SELECT * FROM " + table_name;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			
			while (resultSet.next()) {
				System.out.println("Codigo: " +  resultSet.getString("Codigo") + " "
						+ "Nombre: " +  resultSet.getString("Nombre") + " "
						+ "Presupuesto: " +  resultSet.getString("Presupuesto") + " "
						);
			}
			
			String Query2 = "SELECT * FROM " + table_name2;
			Statement st2 = conexion.createStatement();
			java.sql.ResultSet resultSet2;
			resultSet2 = st2.executeQuery(Query2);
			
			while (resultSet2.next()) {
				System.out.println("DNI: " +  resultSet2.getString("DNI") + " "
						+ "Nombre: " +  resultSet2.getString("Nombre") + " "
						+ "Apellidos:" + resultSet2.getString("Apellidos") +  " "
						+ "Departamento: " +  resultSet2.getString("Departamento") + " "
						);
			}
			
			
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
	
	}
	 
	//METODO QUE LIMPIA TABLAS MYSQL
	/*public void deleteRecord(String db, String table_name, String ID) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("Registros de tabla ELIMINADOS con exito!");
						
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
	}	*/

	
	//METODO QUE ELIMINA TABLAS MYSQL
	/*public void deleteTAbla(String db, String table_name) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "DROP TABLE " + table_name + ";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("TABLA ELIMINADA con exito!");
						
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando la tabla");
		}
	}	*/
	
	
	//METODO QUE MUESTRA FECHA
	public void fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		System.out.println(" - " + hourdateFormat.format(date));
		}
	
	

}
