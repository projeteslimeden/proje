package zProjeFinal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Tedarikci {
	
	private int tedarikciNo;
	private String tedarikciAd, tedarikciSorumlu;
	private String tedarikciMerkezAdres, tedarikciDagitimAdres;
	private String tedarikciTelefon, tedarikciEposta, tedarikciWebAdres;
	private String tedarikciKargo1, tedarikciKargo2, tedarikciKargo3, tedarikciKargo4;
	private int tedarikciTeslimatSaat;
	private double tedarikciToplamAlim;
	private Timestamp tedarikciEklemeTarih, tedarikciSonIslemTarih;
	
	Tedarikci(int tedarikciNo) {
		String no_sql = "SELECT * FROM zprojefinal.tedarikci where tedarikci_no = '"+tedarikciNo+"'";
		ResultSet dbVeri = DbBaglanti.sorgula(no_sql);
		
		try {
			while(dbVeri.next()) {
				this.tedarikciNo = dbVeri.getInt("tedarikci_no");
				tedarikciAd = dbVeri.getString("tedarikci_ad");
				tedarikciSorumlu = dbVeri.getString("tedarikci_sorumlu");
				tedarikciMerkezAdres = dbVeri.getString("tedarikci_merkez_adres");
				tedarikciDagitimAdres = dbVeri.getString("tedarikci_dagitim_adres");
				tedarikciTelefon = dbVeri.getString("tedarikci_telefon");
				tedarikciEposta = dbVeri.getString("tedarikci_eposta");
				tedarikciWebAdres = dbVeri.getString("tedarikci_web");
				tedarikciKargo1 = dbVeri.getString("tedarikci_kargo_1");
				tedarikciKargo2 = dbVeri.getString("tedarikci_kargo_2");
				tedarikciKargo3 = dbVeri.getString("tedarikci_kargo_3");
				tedarikciKargo4 = dbVeri.getString("tedarikci_kargo_4");
				tedarikciTeslimatSaat = dbVeri.getInt("tedarikci_teslimat_ortalama");
				tedarikciToplamAlim = dbVeri.getDouble("tedarikci_toplam_alim");
				tedarikciEklemeTarih = dbVeri.getTimestamp("tedarikci_eklenme_tarih");
				tedarikciSonIslemTarih = dbVeri.getTimestamp("tedarikci_son_islem_tarih");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	
	static int ekle(String ad, String sorumlu, String merkezAdres, String dagitimAdres, String telefon, String eposta,
					String webAdres, String kargo1, String kargo2, String kargo3, String kargo4) {
		int basarili = 0;
		if(ad.replaceAll("\\W", "").equals("") || sorumlu.replaceAll("\\W", "").equals("") ||
				merkezAdres.replaceAll("\\W", "").equals("") || dagitimAdres.replaceAll("\\W", "").equals("") ||
				telefon.replaceAll("\\W", "").equals("") || eposta.replaceAll("\\W", "").equals("") || webAdres.replaceAll("\\W", "").equals("") || 
				kargo1.replaceAll("\\W", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Kargo 2-3-4 haricinde bütün boþluklar zorunludur.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String max_no_sorgu = "SELECT Max(tedarikci_no) from tedarikci";
			ResultSet dbVeri = DbBaglanti.sorgula(max_no_sorgu);
			int yeni_no = 0;
			try {
				while(dbVeri.next()) {
					yeni_no = (dbVeri.getInt("Max(tedarikci_no)")+1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			final int ortalamaTeslimatSaati = 0;
			final double toplamAlimHarcamasi = 0;
			final String islemYokTarih = "0001-01-01 00:00:00";
			DateTimeFormatter sqlTipSaat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
			LocalDateTime eklenenSaat = LocalDateTime.now();
			
			String ekle_sql = "INSERT INTO `zprojefinal`.`tedarikci` (`tedarikci_no`, `tedarikci_ad`, `tedarikci_sorumlu`, `tedarikci_merkez_adres`, `tedarikci_dagitim_adres`, `tedarikci_telefon`, `tedarikci_eposta`, `tedarikci_web`, `tedarikci_kargo_1`, `tedarikci_kargo_2`, `tedarikci_kargo_3`, `tedarikci_kargo_4`, `tedarikci_teslimat_ortalama`, `tedarikci_toplam_alim`, `tedarikci_eklenme_tarih`, `tedarikci_son_islem_tarih`)"+
								" VALUES ('"+yeni_no+"', '"+ad+"', '"+sorumlu+"', '"+merkezAdres+"', '"+dagitimAdres
								+"', '"+telefon+"', '"+eposta+"', '"+webAdres+"', '"+kargo1+"', '"+kargo2+"', '"+kargo3+"', '"+kargo4
								+"', '"+ortalamaTeslimatSaati+"', '"+toplamAlimHarcamasi+"', '"+sqlTipSaat.format(eklenenSaat)
								+"', '"+islemYokTarih+"')";
			basarili = DbBaglanti.ekle(ekle_sql);
			if(basarili==1) {
				JOptionPane.showMessageDialog(null, "Tedarikci-"+ad+" baþarýyla eklendi.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
				return basarili;
			} else JOptionPane.showMessageDialog(null, "Yeni tedarikci eklenemedi.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}
		return basarili;
	}
	
	static int guncelle(int tedarikciNo, String ad, String sorumlu, String merkezAdres, String dagitimAdres, String telefon, String eposta,
			String webAdres, String kargo1, String kargo2, String kargo3, String kargo4) {
		int basarili = 0;
		if(ad.replaceAll("\\W", "").equals("") || sorumlu.replaceAll("\\W", "").equals("") ||
				merkezAdres.replaceAll("\\W", "").equals("") || dagitimAdres.replaceAll("\\W", "").equals("") ||
				telefon.replaceAll("\\W", "").equals("") || eposta.replaceAll("\\W", "").equals("") || webAdres.replaceAll("\\W", "").equals("") || 
				kargo1.replaceAll("\\W", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Kargo 2-3-4 haricinde bütün boþluklar zorunludur.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String update_sql = "UPDATE `zprojefinal`.`tedarikci` SET `tedarikci_ad` = '"+ad+
					"', `tedarikci_sorumlu` = '"+sorumlu+"', `tedarikci_merkez_adres` = '"+merkezAdres+
							"', `tedarikci_dagitim_adres` = '"+dagitimAdres+
							"', `tedarikci_telefon` = '"+telefon+"', `tedarikci_eposta` = '"+eposta+
							"', `tedarikci_web` = '"+webAdres+
							"', `tedarikci_kargo_1` = '"+kargo1+"', `tedarikci_kargo_2` = '"+kargo2+
							"', `tedarikci_kargo_3` = '"+kargo3+"', `tedarikci_kargo_4` = '"+kargo4+
							"' WHERE (`tedarikci_no` = '"+tedarikciNo+"')";
			basarili = DbBaglanti.degistir(update_sql);
			if(basarili==1) {
				JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
				return basarili;
			} else {
				JOptionPane.showMessageDialog(null, "Tedarikçi güncelleme baþarýsýz.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
			}
		}
		return basarili;
	}

	static ResultSet arama (String gelen) {
		ResultSet dbVeriArama = null;
		String arama_sql;
		String ayrac = "", kalan="";
		
		try {
			ayrac = gelen.substring(0, 1);
			kalan = gelen.substring(1);
		} catch (Exception e) {
			arama_sql = "SELECT * FROM zprojefinal.tedarikci where tedarikci_ad like '%"+gelen+
					"%' OR tedarikci_telefon like '%"+gelen+"%';";
		}
		
		if(ayrac.equals("@")) {
			arama_sql = "SELECT * FROM zprojefinal.tedarikci where tedarikci_eposta like '%"+kalan+"%';";
		} else if(ayrac.equals("#")) {
			arama_sql = "SELECT * FROM zprojefinal.tedarikci where tedarikci_no like '"+kalan+"%';";
		} else  {
			arama_sql = "SELECT * FROM zprojefinal.tedarikci where tedarikci_ad like '%"+gelen+
												"%' OR tedarikci_telefon like '%"+gelen+"%';";
		}
		dbVeriArama = DbBaglanti.sorgula(arama_sql);
		return dbVeriArama;
	}
	
	
	
	public int getTedarikciNo() {
		return tedarikciNo;
	}

	public String getTedarikciAd() {
		return tedarikciAd;
	}

	public String getTedarikciSorumlu() {
		return tedarikciSorumlu;
	}

	public String getTedarikciMerkezAdres() {
		return tedarikciMerkezAdres;
	}

	public String getTedarikciDagitimAdres() {
		return tedarikciDagitimAdres;
	}

	public String getTedarikciTelefon() {
		return tedarikciTelefon;
	}

	public String getTedarikciEposta() {
		return tedarikciEposta;
	}

	public String getTedarikciWebAdres() {
		return tedarikciWebAdres;
	}

	public String getTedarikciKargo1() {
		return tedarikciKargo1;
	}

	public String getTedarikciKargo2() {
		return tedarikciKargo2;
	}

	public String getTedarikciKargo3() {
		return tedarikciKargo3;
	}

	public String getTedarikciKargo4() {
		return tedarikciKargo4;
	}

	public int getTedarikciTeslimatSaat() {
		return tedarikciTeslimatSaat;
	}

	public double getTedarikciToplamAlim() {
		return tedarikciToplamAlim;
	}

	public Timestamp getTedarikciEklemeTarih() {
		return tedarikciEklemeTarih;
	}

	public Timestamp getTedarikciSonIslemTarih() {
		return tedarikciSonIslemTarih;
	}
	
	
	
	
	
}
