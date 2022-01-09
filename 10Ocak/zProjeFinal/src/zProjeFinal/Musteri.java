package zProjeFinal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Musteri {
	
	private int musteriNo;
	private String musteriAdSoyad;
	private String musteriTelefon, musteriAdres, musteriEposta;
	private Timestamp musteriEklenmeTarih, musteriSonIslemTarih;
	private double musteriToplamHarcama;
	
	Musteri (int musteriNo) {
		if(musteriNoKontrol(musteriNo)==true) {
			ResultSet dbVeri = null;
			String musteri_sorgu = "SELECT * FROM musteri where musteri_no = '" + musteriNo + "'";
			dbVeri = DbBaglanti.sorgula(musteri_sorgu);
			
			try {
				while(dbVeri.next()) {
					this.musteriNo = dbVeri.getInt("musteri_no");
					musteriAdSoyad = dbVeri.getString("musteri_ad_soyad");
					musteriTelefon = dbVeri.getString("musteri_telefon");
					musteriAdres = dbVeri.getString("musteri_adres");
					musteriEklenmeTarih = dbVeri.getTimestamp("musteri_eklenme_tarih");
					musteriEposta = dbVeri.getString("musteri_eposta");
					musteriSonIslemTarih = dbVeri.getTimestamp("musteri_son_islem_tarih");
					musteriToplamHarcama = dbVeri.getDouble("musteri_toplam_harcama");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else musteriBulunamadiInfo();
		
	}
	
	Musteri (String musteriTel) {
		if(musteriTelKontrol(musteriTel)==true) {
			ResultSet dbVeri = null;
			String musteri_sorgu = "SELECT * FROM musteri where musteri_telefon = '" + musteriTel + "'";
			dbVeri = DbBaglanti.sorgula(musteri_sorgu);
			
			try {
				while(dbVeri.next()) {
					this.musteriNo = dbVeri.getInt("musteri_no");
					musteriAdSoyad = dbVeri.getString("musteri_ad_soyad");
					musteriTelefon = dbVeri.getString("musteri_telefon");
					musteriAdres = dbVeri.getString("musteri_adres");
					musteriEklenmeTarih = dbVeri.getTimestamp("musteri_eklenme_tarih");
					musteriEposta = dbVeri.getString("musteri_eposta");
					musteriSonIslemTarih = dbVeri.getTimestamp("musteri_son_islem_tarih");
					musteriToplamHarcama = dbVeri.getDouble("musteri_toplam_harcama");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else musteriBulunamadiInfo();
	}
	
	public double getMusteriToplamHarcama() {
		return musteriToplamHarcama;
	}
	
	public void setMusteriToplamHarcama(double sepetToplamTutar) {
		this.musteriToplamHarcama = this.musteriToplamHarcama + sepetToplamTutar;
		String update_sql = "UPDATE `zprojefinal`.`musteri` SET `musteri_toplam_harcama` = '"+this.musteriToplamHarcama+"' WHERE (`musteri_no` = '"+this.musteriNo+"');";
		int basarili = DbBaglanti.degistir(update_sql);
		
		if(basarili != 1) {
			JOptionPane.showMessageDialog(null, "Müþteri Harcama Update HATA", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	static int ekle(String isim, String tel, String eposta, String adres) {
		String max_no_sorgu = "SELECT Max(musteri_no) from musteri";
		ResultSet dbVeri = DbBaglanti.sorgula(max_no_sorgu);
		int yeni_no = 0;
		try {
			while(dbVeri.next()) {
				yeni_no = (dbVeri.getInt("Max(musteri_no)")+1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final String islemYokTarih = "0001-01-01 00:00:00";
		DateTimeFormatter sqlTipSaat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime eklenenSaat = LocalDateTime.now();
		
		String ekle_sql = "INSERT INTO `zprojefinal`.`musteri` (`musteri_no`, `musteri_ad_soyad`, `musteri_eklenme_tarih`, `musteri_telefon`, `musteri_adres`, `musteri_eposta`, `musteri_son_islem_tarih`)"+
													" VALUES ('"+yeni_no+"', '"+isim+"', '"+sqlTipSaat.format(eklenenSaat)+"', '"+tel+"', '"+adres+"', '"+eposta+"', '"+islemYokTarih+"')";
		
		int basarili = DbBaglanti.ekle(ekle_sql);
		if(basarili==1) {
			JOptionPane.showMessageDialog(null, "Müþteri-"+isim+" baþarýyla eklendi.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else JOptionPane.showMessageDialog(null, "Yeni müþteri eklenemedi.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		
		return basarili;
	}
	
	static int guncelle(int musteriNo, String isim, String tel, String eposta, String adres) {
		String guncelle_sql = "UPDATE `zprojefinal`.`musteri` SET `musteri_ad_soyad` = '"+isim+
																"', `musteri_telefon` = '"+tel+
																"', `musteri_adres` = '"+adres+
																"', `musteri_eposta` = '"+eposta+
																"' WHERE (`musteri_no` = '"+musteriNo+"')";
		int basarili = DbBaglanti.degistir(guncelle_sql);
		if(basarili==1) {
			JOptionPane.showMessageDialog(null, "Güncelleme Baþarýlý", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
			return basarili;
		} else {
			JOptionPane.showMessageDialog(null, "Güncelleme Baþarýsýz...", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}
		return 0;
	}
	
	static ResultSet arama(String gelen) {
		ResultSet dbVeriArama = null;
		String arama_sql = "SELECT * FROM zprojefinal.musteri where musteri_ad_soyad like '%"+gelen
															+"%' OR musteri_telefon like '%"+gelen+"%';";
		dbVeriArama = DbBaglanti.sorgula(arama_sql);
		return dbVeriArama;
	}

	
	
	
	public Timestamp getMusteriSonIslemTarih() {
		return musteriSonIslemTarih;
	}
	
	public void setMusteriSonIslemTarih() {
		DateTimeFormatter sqlTipSaat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime eklenenSaat = LocalDateTime.now();
		String update_sql = "UPDATE `zprojefinal`.`musteri` SET `musteri_son_islem_tarih` = '"+sqlTipSaat.format(eklenenSaat)+"' WHERE (`musteri_no` = '"+musteriNo+"')";
		int basarili = DbBaglanti.degistir(update_sql);
		if(basarili!=1) {
			System.out.println("Müþteri tarih update problem.");
		}
	}

	public int getMusteriNo() {
		return musteriNo;
	}

	public String getMusteriAdSoyad() {
		return musteriAdSoyad;
	}

	public String getMusteriTelefon() {
		return musteriTelefon;
	}

	public String getMusteriAdres() {
		return musteriAdres;
	}

	public String getMusteriEposta() {
		return musteriEposta;
	}

	public Timestamp getMusteriEklenmeTarih() {
		return musteriEklenmeTarih;
	}

	private boolean musteriNoKontrol(int musteriNo) {
		ResultSet dbVeri = null;
		String musteri_sorgu = "SELECT * FROM musteri";
		dbVeri = DbBaglanti.sorgula(musteri_sorgu);
		boolean eslesme = false;
		try {
			while(dbVeri.next()) {
				if(musteriNo==dbVeri.getInt("musteri_no")) eslesme = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eslesme;
	}
	
	private boolean musteriTelKontrol(String musteriTel) {
		ResultSet dbVeri = null;
		String musteri_sorgu = "SELECT * FROM musteri";
		dbVeri = DbBaglanti.sorgula(musteri_sorgu);
		boolean eslesme = false;
		try {
			while(dbVeri.next()) {
				if(musteriTel.equals(dbVeri.getString("musteri_telefon"))) eslesme = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eslesme;
	}
	
	private void musteriBulunamadiInfo() { //int le -1 gönderip -1e göre iþlem devamlýlýðý kesilebilir yada tekrar bir boolean
		JOptionPane.showMessageDialog(null, "Müþteri Bulunamadý", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void deneme() {
		System.out.println(musteriNo);
		System.out.println(musteriAdres+"-"+musteriAdSoyad+"-"+musteriTelefon);
		System.out.println(musteriEklenmeTarih+"-"+musteriEposta);
		System.out.println("---------------------------------------");
	}
	
	
	
}
