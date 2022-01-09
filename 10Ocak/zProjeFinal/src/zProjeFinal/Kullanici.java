package zProjeFinal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Kullanici {
	
	private int kullaniciNo;
	private String kullaniciAd, kullaniciSoyad, kullaniciEposta;
	private String kullaniciTelefon, kullaniciAdres;
	private double kullaniciAylikMaas, kullaniciPrim;
	protected int kullaniciSeviyesi;
	private Date kullaniciDogumTarih;
	private Timestamp kullaniciEklenmeTarih;
	private String kullaniciId, kullaniciKriptoSifre;
	//tarihler ve telefon kaldý telefon string olmasý mantýklý olur
	
	Kullanici(String idGiren) {
		if(kullaniciIdKontrol(idGiren)==true) {
			ResultSet dbVeri = null;
			String id_sorgu = "SELECT * FROM kullanicilar where kullanici_id = '" + idGiren + "'";
			dbVeri = DbBaglanti.sorgula(id_sorgu);
			
			
			try {
				while(dbVeri.next()) { //bu while yazmasa before start of result set hatasý alýnýr
					kullaniciNo = dbVeri.getInt("kullanici_no");
					kullaniciId = dbVeri.getString("kullanici_id");
					kullaniciKriptoSifre = dbVeri.getString("kullanici_kripto_sifre");
					kullaniciAd = dbVeri.getString("kullanici_ad");
					kullaniciSoyad = dbVeri.getString("kullanici_soyad");
					kullaniciDogumTarih = dbVeri.getDate("kullanici_dogum_tarih");
					kullaniciEklenmeTarih = dbVeri.getTimestamp("kullanici_eklenme_tarih");
					kullaniciTelefon = dbVeri.getString("kullanici_telefon");
					kullaniciEposta = dbVeri.getString("kullanici_eposta");
					kullaniciAdres = dbVeri.getString("kullanici_adres");
					kullaniciAylikMaas = dbVeri.getDouble("kullanici_aylik_maas");
					kullaniciPrim = dbVeri.getDouble("kullanici_prim");
					kullaniciSeviyesi = dbVeri.getInt("kullanici_seviyesi");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else calisanBulunamadiInfo();	
		
	}
	
	Kullanici (int kullaniciNo) { //kullanici no dan prime ulaþma
		if(kullaniciNoKontrol(kullaniciNo)==true) {
			ResultSet dbVeri = null;
			String id_sorgu = "SELECT * FROM kullanicilar where kullanici_no = '" + kullaniciNo + "'";
			dbVeri = DbBaglanti.sorgula(id_sorgu);
			
			try {
				while(dbVeri.next()) {
				/*	kullaniciId = dbVeri.getString("kullanici_id");
					kullaniciAd = dbVeri.getString("kullanici_ad");
					kullaniciSoyad = dbVeri.getString("kullanici_soyad");
					kullaniciPrim = dbVeri.getDouble("kullanici_prim");
				*/ //TÜM VERÝLERÝ ÇEKMEDEN ÖNCE SADECE BUNLAR VARDI
					this.kullaniciNo = dbVeri.getInt("kullanici_no");
					kullaniciId = dbVeri.getString("kullanici_id");
					kullaniciKriptoSifre = dbVeri.getString("kullanici_kripto_sifre");
					kullaniciAd = dbVeri.getString("kullanici_ad");
					kullaniciSoyad = dbVeri.getString("kullanici_soyad");
					kullaniciDogumTarih = dbVeri.getDate("kullanici_dogum_tarih");
					kullaniciEklenmeTarih = dbVeri.getTimestamp("kullanici_eklenme_tarih");
					kullaniciTelefon = dbVeri.getString("kullanici_telefon");
					kullaniciEposta = dbVeri.getString("kullanici_eposta");
					kullaniciAdres = dbVeri.getString("kullanici_adres");
					kullaniciAylikMaas = dbVeri.getDouble("kullanici_aylik_maas");
					kullaniciPrim = dbVeri.getDouble("kullanici_prim");
					kullaniciSeviyesi = dbVeri.getInt("kullanici_seviyesi");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else calisanBulunamadiInfo();	
	}
	
	public void setKullaniciKriptoSifre(String kullaniciKriptoSifre) {
		this.kullaniciKriptoSifre = kullaniciKriptoSifre;
		String sifre_update = "UPDATE `zprojefinal`.`kullanicilar` SET `kullanici_kripto_sifre` = '"+this.kullaniciKriptoSifre+"' WHERE (`kullanici_id` = '"+this.kullaniciId+"')";
		DbBaglanti.degistir(sifre_update);
	}
	
	public static void kullaniciSifreSifirla(int kullaniciNo) {
		String[] cevap = {"Evet","Hayýr"};
		int answer = JOptionPane.showOptionDialog(
				null, //component
				"Kullanýcýnýn parolasýný sýfýrlamak istediðinize emin misiniz?", //message 
				"Depom Sepette", //title
				JOptionPane.YES_NO_CANCEL_OPTION, //option type 
				JOptionPane.WARNING_MESSAGE, //message type
				null, // icon
				cevap, // option da yazacaklar 0-1-2 olarak deðiþtirir
				0);
		if(answer == 0 ) {
			SifreSifreliyici yeniSifre = new SifreSifreliyici();
			String sifirlanmisSifre = yeniSifre.randomSifre();
			String sifre_sifirla = "UPDATE `zprojefinal`.`kullanicilar` SET `kullanici_kripto_sifre` = '"+yeniSifre.kriptola(sifirlanmisSifre)+"' WHERE (`kullanici_no` = '"+kullaniciNo+"')";
			DbBaglanti.degistir(sifre_sifirla);
			String id_sorgu = "SELECT kullanici_kripto_sifre FROM kullanicilar where kullanici_no = '" + kullaniciNo + "'";
			ResultSet dbVeri = DbBaglanti.sorgula(id_sorgu);
			boolean basarili = false;
			try {
				while(dbVeri.next()) {
					if(yeniSifre.kriptola(sifirlanmisSifre).equals(dbVeri.getString("kullanici_kripto_sifre"))) {
						basarili = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(basarili == true) {
				String[] JOptionBut = {"Yeni Parola Göster"};
				JOptionPane.showOptionDialog(
						null, //component
						"Parola sýfýrlama baþarýlý.", //message 
						"Depom Sepette", //title
						JOptionPane.OK_OPTION, //option type 
						JOptionPane.INFORMATION_MESSAGE, //message type
						null, // icon
						JOptionBut, // option da yazacaklar 0-1-2 olarak deðiþtirir
						0);
				JOptionPane.showMessageDialog(null, kullaniciNo+"'nolu kullanýcý yeni parola: "+sifirlanmisSifre, "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
			}else JOptionPane.showMessageDialog(null, "Çalýþan þifre sýfýrlanamadý...!", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}//answer if kapama
	}

	public String getKullaniciKriptoSifre() {
		return kullaniciKriptoSifre;
	}

	
	
	public String getKullaniciId() {
		return kullaniciId;
	}

	private boolean kullaniciIdKontrol(String id) {
		ResultSet dbVeri = null;
		String id_sorgu = "SELECT * FROM kullanicilar";
		dbVeri = DbBaglanti.sorgula(id_sorgu);
		boolean eslesme = false;
		try {
			while(dbVeri.next()) {
				if(id.equals(dbVeri.getString("kullanici_id"))) eslesme = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eslesme;
	}
	
	private boolean kullaniciNoKontrol(int kullaniciNo) {
		ResultSet dbVeri = null;
		String no_sorgu = "SELECT * FROM kullanicilar";
		dbVeri = DbBaglanti.sorgula(no_sorgu);
		boolean eslesme = false;
		try {
			while(dbVeri.next()) {
				if(kullaniciNo==dbVeri.getInt("kullanici_no")) eslesme = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eslesme;
	}
	
	private void calisanBulunamadiInfo() { //int le -1 gönderip -1e göre iþlem devamlýlýðý kesilebilir yada tekrar bir boolean
		JOptionPane.showMessageDialog(null, "Çalýþan Bulunamadý", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
	}
	
	static int primEkle(String calisanSaptayici, double sepetToplamTutar) {  //PRÝM AYBAÞI SIFIRLANMALI LOGA KAYDEDÝLÝP //3000de1 genel prim
		int noDonusum = 0;    
		int select = 0;
		try {
			noDonusum = Integer.parseInt(calisanSaptayici); //burda int yada direk id
			select = 0;											
		} catch (Exception e2) {
			select = 1;
		}
		
		Kullanici primAlanCalisan;
		
		if(select == 0) {
			primAlanCalisan = new Kullanici(noDonusum);
		} else {
			primAlanCalisan = new Kullanici(calisanSaptayici);
		}
		
		double mevcutPrim = primAlanCalisan.getKullaniciPrim();
		double eklenecekPrim = (sepetToplamTutar/100);
		
		String update_sql = "UPDATE `zprojefinal`.`kullanicilar` SET `kullanici_prim` = '"+(mevcutPrim+eklenecekPrim)+"' WHERE (`kullanici_no` = '"+primAlanCalisan.kullaniciNo+"')";
		int basarili = DbBaglanti.degistir(update_sql);
		
		if(basarili != 1) {
			JOptionPane.showMessageDialog(null, "Prim Update HATA", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
		}
		return basarili;
	}
	
//	static boolean basarili;
	static int ekle(String id, String ad, String soyad, String telefon, String eposta, String adres, 
			String maas, int mevkiNo, String dgTarih) {
		int basarili = 0;
		if(id.replaceAll("\\W", "").equals("") || ad.replaceAll("\\W", "").equals("") ||
					soyad.replaceAll("\\W", "").equals("") || telefon.replaceAll("\\W", "").equals("") ||
					eposta.replaceAll("\\W", "").equals("") || adres.replaceAll("\\W", "").equals("") || 
					maas.replaceAll("\\W", "").equals("") || dgTarih.replaceAll("\\W", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Lütfen bütün boþluklarý uygun þekilde giriniz.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if(id.replaceAll("\\d", "").replaceAll("\\W", "").equals("")) {
				JOptionPane.showMessageDialog(null, "Kullanýcý adýnda harf olmasý zorunludur.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
			} else {
				ResultSet dbVeri = null;
				String max_no_sorgu = "SELECT Max(kullanici_no) from kullanicilar";
				dbVeri = DbBaglanti.sorgula(max_no_sorgu);
				int yeni_no = 0;
				final int ilkPrim=0;
				try {
					while(dbVeri.next()) {
						yeni_no = (dbVeri.getInt("Max(kullanici_no)")+1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				SifreSifreliyici yeniSifre = new SifreSifreliyici();
				String ilkSifre = yeniSifre.randomSifre();
				
				DateTimeFormatter sqlTipSaat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
				LocalDateTime eklenenSaat = LocalDateTime.now();
				
				String ekle_sql = "INSERT INTO `zprojefinal`.`kullanicilar` (`kullanici_no`, `kullanici_id`, `kullanici_kripto_sifre`, `kullanici_ad`, `kullanici_soyad`, `kullanici_dogum_tarih`, `kullanici_eklenme_tarih`, `kullanici_telefon`, `kullanici_eposta`, `kullanici_adres`, `kullanici_aylik_maas`, `kullanici_prim`, `kullanici_seviyesi`) VALUES ('"+
				yeni_no+"', '"+id+"', '"+yeniSifre.kriptola(ilkSifre)+"', '"+ad+"', '"+soyad+"', '"+dgTarih+"', '"+sqlTipSaat.format(eklenenSaat)+"', '"+telefon+"', '"+eposta+"', '"+adres+"', '"+maas+"', '"+ilkPrim+"', '"+mevkiNo+"');";
				basarili = DbBaglanti.ekle(ekle_sql);
				if(basarili == 1) {
					String[] JOptionBut = {"Þifre Göster"};
					JOptionPane.showOptionDialog(
							null, //component
							"Yeni çalýþan baþarýyla eklendi.", //message 
							"Depom Sepette", //title
							JOptionPane.OK_OPTION, //option type 
							JOptionPane.INFORMATION_MESSAGE, //message type
							null, // icon
							JOptionBut, // option da yazacaklar 0-1-2 olarak deðiþtirir
							0);
					JOptionPane.showMessageDialog(null, "Yeni Kullanýcý Ýlk Þifre: "+ilkSifre, "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
					return basarili;
				} else {
					JOptionPane.showMessageDialog(null, "Yeni çalýþan eklenemedi...!", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}
		return basarili;
			
			
//			int eklenme_sonra_no = 0;
//			ResultSet dbVeriEklemeSonrasi = DbBaglanti.sorgula(max_no_sorgu);
//			try {
//				while(dbVeriEklemeSonrasi.next()) {
//					eklenme_sonra_no = dbVeriEklemeSonrasi.getInt("Max(kullanici_no)");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			if(yeni_no==eklenme_sonra_no) {
//				String[] JOptionBut = {"Þifre Göster"};
//				JOptionPane.showOptionDialog(
//						null, //component
//						"Yeni çalýþan baþarýyla eklendi.", //message 
//						"Depom Sepette", //title
//						JOptionPane.OK_OPTION, //option type 
//						JOptionPane.INFORMATION_MESSAGE, //message type
//						null, // icon
//						JOptionBut, // option da yazacaklar 0-1-2 olarak deðiþtirir
//						0);
//				JOptionPane.showMessageDialog(null, "Yeni Kullanýcý Ýlk Þifre: "+ilkSifre, "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
//				basarili = true;
//			} else {
//				JOptionPane.showMessageDialog(null, "Yeni çalýþan eklenemedi...!", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
//				basarili=false;
//			}
	}
	// \\W 'u þ yi karakter olarak görmüyor
	static int degistir(int kullaniciNo, String id, String ad, String soyad, String dgTarih, String telefon, String eposta, String adres, String aylikmaas, String mevki) {
		int basarili = 0;
		if(id.replaceAll("\\W", "").equals("") || ad.replaceAll("\\W", "").equals("") ||
				soyad.replaceAll("\\W", "").equals("") || telefon.replaceAll("\\W", "").equals("") ||
				eposta.replaceAll("\\W", "").equals("") || adres.replaceAll("\\W", "").equals("") || 
				aylikmaas.replaceAll("\\W", "").equals("") || mevki.replaceAll("\\W", "").equals("") || 
				dgTarih.replaceAll("\\W", "").equals("")) {
		JOptionPane.showMessageDialog(null, "Lütfen bütün boþluklarý uygun þekilde giriniz.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if(id.replaceAll("\\d", "").replaceAll("\\W", "").equals("")) {
				JOptionPane.showMessageDialog(null, "Kullanýcý adýnda harf olmasý zorunludur.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String guncelle_sql = "UPDATE `zprojefinal`.`kullanicilar` SET `kullanici_id` = '"+id //guncelleGirdiler[0] //id
						+"', `kullanici_ad` = '"+ad //guncelleGirdiler[1] //ad
						+"', `kullanici_soyad` = '"+soyad //guncelleGirdiler[2] //soyad
						+"', `kullanici_dogum_tarih` = '"+dgTarih //guncelleGirdiler[8] //doðumgünü
						+"', `kullanici_telefon` = '"+telefon //guncelleGirdiler[3] //telefon
						+"', `kullanici_eposta` = '"+eposta //guncelleGirdiler[4]  //eposta
						+"', `kullanici_adres` = '"+adres //guncelleGirdiler[5]	  //adres
						+"', `kullanici_aylik_maas` = '"+aylikmaas //guncelleGirdiler[6]  //aylikmaas
						+"', `kullanici_seviyesi` = '2' WHERE (`kullanici_no` = '"+kullaniciNo+"')"; //kullanici seviyesi
				basarili = DbBaglanti.degistir(guncelle_sql);
				
				if(basarili==1) {
					JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
					return basarili;
				} else {
					JOptionPane.showMessageDialog(null, "Güncelleme Ýþlemi Baþarýsýz...", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		return basarili;
	}

	public Date getKullaniciDogumTarih() {
		return kullaniciDogumTarih;
	}

	public Timestamp getKullaniciEklenmeTarih() {
		return kullaniciEklenmeTarih;
	}

	public int getKullaniciNo() {
		return kullaniciNo;
	}
	
	public static int getKullaniciNo(String idGiren) {
		int kullaniciNo = 0;
		ResultSet kullanici_no_sorgu = DbBaglanti.sorgula("SELECT kullanici_no FROM zprojefinal.kullanicilar where kullanici_id = '"+idGiren+"'");
		try {
			while(kullanici_no_sorgu.next()) {
				kullaniciNo = kullanici_no_sorgu.getInt("kullanici_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kullaniciNo;
	}

	public String getKullaniciAd() {
		return kullaniciAd;
	}

	public String getKullaniciSoyad() {
		return kullaniciSoyad;
	}

	public String getKullaniciAdSoyad() {
		String a;
		if(kullaniciAd==null) {
			return kullaniciAd;
		} else {
			a = new String(kullaniciAd+" "+kullaniciSoyad);
		}
		return a;
	}
	
	public String getKullaniciEposta() {
		return kullaniciEposta;
	}

	public String getKullaniciTelefon() {
		return kullaniciTelefon;
	}

	public String getKullaniciAdres() {
		return kullaniciAdres;
	}

	public double getKullaniciAylikMaas() {
		return kullaniciAylikMaas;
	}

	public double getKullaniciPrim() {
		return kullaniciPrim;
	}

	public int getKullaniciSeviyesi() {
		return kullaniciSeviyesi;
	}
	
	
	
	
}
