package zProjeFinal;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbBaglanti {
	
	static Connection baglanti;
	static Statement ifade;
	
	static void kur() {
		try {
			baglanti = DriverManager.getConnection("jdbc:mysql://localhost:3306/zprojefinal","root","rootkit452234.ROOT");
			ifade = baglanti.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static ResultSet sorgula(String sql_sorgu) {
		ResultSet myRS = null;
		
		try {
			myRS=ifade.executeQuery(sql_sorgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myRS;
	}
	
	static int ekle(String sql_sorgu) {
		try {
			int basarili = ifade.executeUpdate(sql_sorgu);
			return basarili;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	static int degistir(String sql_sorgu) {
		try {
			int basarili = ifade.executeUpdate(sql_sorgu);
			return basarili;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	static void sil(String sql_sorgu) {
		try {
			ifade.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static int preparedEkle (String sql_sorgu, int siraNo, FileInputStream fInputStream) { //sadece blob deðer eklemek için urunler eklede kullanýldý
		try {
			PreparedStatement ifade = baglanti.prepareStatement(sql_sorgu);
			ifade.setBinaryStream(siraNo, fInputStream);
			int basarili = ifade.executeUpdate();
			return basarili;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
