/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConeBD;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JFileChooser;

 
public class ConexaoBD {
    Connection conn;
      public static Connection ConectarBD() {

        try {
            String DriverURL = "org.postgresql.Driver";
            Class.forName(DriverURL);            
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/agroindustria", "postgres", "123");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Status:Not Logged - Senha Errada ");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Assigment Status:Not Logged-Execution Failure 2");
        }
        return null;
    }
          public static void SalvarImagem() {
		JFileChooser fc = new JFileChooser();
		File img = null;
		int res = fc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			img = fc.getSelectedFile();
		}
		byte[] imagem = new byte[(int) img.length()];
		System.out.println("Lendo " + img.length() + " bytes...");
		try{
		DataInputStream is = new DataInputStream(new FileInputStream(img));
		is.readFully(imagem);
		is.close();
		PreparedStatement stmt = (PreparedStatement) ConectarBD()
				.prepareStatement(
						"INSERT INTO usuarios (foto) "
								+ "(?)");
		stmt.setString(1, img.getName());
		stmt.setObject(2, imagem);
		stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("Imagem " + img.getName() + " acrescentada ao banco");
	}
       public static byte[] ExibiImagem() {
		ResultSet rs = null;
		try {
			Statement stm = ConectarBD().createStatement();
			rs = stm.executeQuery("SELECT fotorosto FROM cfuncionario WHERE id = 200");
			while (rs.next()) {
				System.out.print(rs.getBytes("fotorosto").length);
				return rs.getBytes("fotorosto");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}   
}
