package zProjeFinal;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;



public class Kasa {
	private double paraInKasa;
	
	Kasa() {
		String kasa_sorgu = "SELECT * FROM zprojefinal.kasa";
		ResultSet dbKasaVeri = DbBaglanti.sorgula(kasa_sorgu);
		
		try {
			while(dbKasaVeri.next()) {
				paraInKasa = dbKasaVeri.getDouble("kasa_para");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public double getParaInKasa() {
		return paraInKasa;
	}
	
	public void getParaDurumu() {
		JOptionPane.showMessageDialog(null, String.format("Kasada bulunan para: %,.2f \u20ba", paraInKasa), "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	static int setPara(int kasaNo, double gelenPara) {
		kasaNo = 1; //þimdlik sadece tek kasa
		double sonPara = (new Kasa().getParaInKasa()) + gelenPara;
		String update_sql = "UPDATE `zprojefinal`.`kasa` SET `kasa_para` = '"+sonPara+"' WHERE (`kasa_no` = '"+kasaNo+"')";
		int basarili = DbBaglanti.degistir(update_sql);
		if(basarili != 1) {
			JOptionPane.showMessageDialog(null, "Kasa Update HATA", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
		}
		return basarili;
	}
	
	
}
