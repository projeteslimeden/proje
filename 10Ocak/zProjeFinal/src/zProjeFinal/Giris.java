package zProjeFinal;

import java.sql.ResultSet;   //DB library
import java.sql.SQLException;//DB library

public class Giris {
	
	public static String idGiren;
	private String gelenSifre;
	
	Giris (String id, String sifre) {
		idGiren=id;
		this.gelenSifre=sifre;
	}
	
	public boolean kontrol() {
		SifreSifreliyici kripto = new SifreSifreliyici();
		String kriptoluSifre = kripto.kriptola(this.gelenSifre);
		
		
		ResultSet dbVeri = null;
		String id_sorgu = "SELECT kullanici_id, kullanici_kripto_sifre FROM kullanicilar where kullanici_id = '" + idGiren + "'";
		dbVeri = DbBaglanti.sorgula(id_sorgu);
		
		boolean eslesme=false;
		try {
			while(dbVeri.next()) { 		//bu while yazmasa before start of result set hatasý alýnýr
//				System.out.println(dbVeri.getString("kullanici_id") + ":" + dbVeri.getString("kullanici_kripto_sifre")); //DENEME TEST SATIRI
				if(kriptoluSifre.equals(dbVeri.getString("kullanici_kripto_sifre")) && idGiren.equals(dbVeri.getString("kullanici_id")) ) {
					eslesme=true;
				} 	
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
//		System.out.println(idGirdi.getText()+":"+kriptoluSifre); //DENEME TEST SATIRI
		return eslesme; 
	}
	
}
