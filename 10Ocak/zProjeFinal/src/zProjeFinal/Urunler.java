package zProjeFinal;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Urunler{
	private int urunNo; 
	private BigInteger urunBarkod;
	private String urunAd;
	private double urunAlisFiyat, urunSatisFiyat;
	private int urunKalanAdet;
	private String urunTedarikci;
	private ImageIcon urunFoto;
	private Timestamp urunIlkEklemeTarih, urunSonAlisTarih, urunSonSatisTarih;
	private int kategoriNo;
	private String[] ozellik;
	private String[] ozellikKategorili;
	private String kategoriAd;

	Urunler (BigInteger barkod) {
			ResultSet dbVeri = null;
			String urun_sorgu = "SELECT * FROM urunler where urun_barkod = '" + barkod + "'";
			dbVeri = DbBaglanti.sorgula(urun_sorgu);
			Blob urunFotoBlob = null;
			BufferedImage urunFotoBuff = null;
			InputStream inFoto = null;
			ozellik = new String[8];
			
			try {
				while(dbVeri.next()) { //bu while yazmasa before start of result set hatasý alýnýr
					urunNo = dbVeri.getInt("urun_no");
					urunBarkod = BigInteger.valueOf(dbVeri.getLong("urun_barkod")); //barkodlar en az 13 haneli olduðu için biginteger ile alýndý
					urunAd = dbVeri.getString("urun_ad");
					urunAlisFiyat = dbVeri.getDouble("urun_alis_fiyat");
					urunSatisFiyat = dbVeri.getDouble("urun_satis_fiyat");
					urunKalanAdet = dbVeri.getInt("urun_kalan_adet");
					urunTedarikci = dbVeri.getString("urun_tedarikci");
					urunIlkEklemeTarih = dbVeri.getTimestamp("urun_ilk_eklenme_tarih");
					urunSonAlisTarih = dbVeri.getTimestamp("urun_son_alis_tarih");
					urunSonSatisTarih = dbVeri.getTimestamp("urun_son_satis_tarih");
					kategoriNo = dbVeri.getInt("kategori_no");
					
					for(int i=0; i<8; i++) {
						ozellik[i] = new String();
						String ic = "ozellik_"+(i+1);
						ozellik[i] = dbVeri.getString(ic);
					}
					
					urunFotoBlob = dbVeri.getBlob("urun_foto"); //(internet) blob olan resmi blob deðiþkene aktarmas
				}
				inFoto = urunFotoBlob.getBinaryStream(); //(internet) blob veriyi inputstreame aktarma
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
			
			try {
				urunFotoBuff = ImageIO.read(inFoto); //(internet) inputstream edilen blob u bufferedimage yapma
			} catch (IOException e) {
				e.printStackTrace();
			}
//			ImageIcon sImageIcon = new ImageIcon(urunFotoBuff);//(internet) bufferedimage i imageicon yapma
//			Image sImage = sImageIcon.getImage();//imageicon u image e kaydetti
//			Image yeniBoyutImage = sImage.getScaledInstance(200, 150, Image.SCALE_SMOOTH); //image yeniden boyutlandýrma ve yeni kaydetme
//			urunFoto = new ImageIcon(yeniBoyutImage);// yeniden boyutlandýrýlmýþ image i image icona kaydetti
			urunFoto = new ImageIcon(urunFotoBuff);
			urunOzellikCek();
	}
	
	
	private void urunOzellikCek() {
		Kategori cat = new Kategori(kategoriNo);
		String[] baslik = new String[8];
		ozellikKategorili = new String[8];
		kategoriAd = cat.getKategoriAd();
		baslik = cat.getBaslik();
		
		for(int i=0; i<8; i++) {
			if(ozellik[i]==null) {
				ozellik[i] = "Deðer Bulunamadý";
			}
			ozellikKategorili[i] = new String(baslik[i]+": "+ozellik[i]);
			if(baslik[i]==null || baslik[i].replaceAll("\\W", "").equals("")) {
				ozellikKategorili[i] = "";
			}
		}
	}
	
	public String getKategoriAd() {
		return kategoriAd;
	}


	public String[] getOzellikKategorili() {
		return ozellikKategorili;
	}


	static int ekle(String barkod, String urunAd, String alis, String satis, String tedarikci, FileInputStream foto, int kategoriNo,
			String ozellik1, String ozellik2, String ozellik3, String ozellik4, String ozellik5, String ozellik6, String ozellik7, String ozellik8) {
//		boolean girdiKontrol = false;
		int basarili = 0;
		if(barkod.replaceAll("\\W", "").equals("") || urunAd.replaceAll("\\W", "").equals("") ||
				alis.replaceAll("\\W", "").equals("") || satis.replaceAll("\\W", "").equals("") ||
				tedarikci.replaceAll("\\W", "").equals("") || kategoriNo==0) {
//			girdiKontrol = false;
			JOptionPane.showMessageDialog(null, "Lütfen bütün boþluklarý uygun þekilde giriniz.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
//			girdiKontrol = true;
			String max_no_sorgu = "SELECT Max(urun_no) from urunler";
			ResultSet dbVeri = DbBaglanti.sorgula(max_no_sorgu);
			int yeni_no = 0;
			try {
				while(dbVeri.next()) {
					yeni_no = (dbVeri.getInt("Max(urun_no)")+1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			final int ilkStok = 0;
			final String alimSatimYokTarih = "0001-01-01 00:00:00";
			DateTimeFormatter sqlTipSaat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
			LocalDateTime eklenenSaat = LocalDateTime.now();
			
			String ekle_sql = "INSERT INTO `zprojefinal`.`urunler` (`urun_no`, `urun_barkod`, `urun_ad`, "+
																	"`urun_alis_fiyat`, `urun_satis_fiyat`, `urun_kalan_adet`, "+
																	"`urun_ilk_eklenme_tarih`, `urun_son_alis_tarih`, `urun_son_satis_tarih`,"+
																	" `urun_tedarikci`, `urun_foto`, `kategori_no`, "+
																	"`ozellik_1`, `ozellik_2`, `ozellik_3`, `ozellik_4`, "+
																	"`ozellik_5`, `ozellik_6`, `ozellik_7`, `ozellik_8`)"+
			"VALUES ('"+yeni_no+"', '"+barkod+"', '"+urunAd+"', '"+alis+"', '"+satis+"', '"+ilkStok+
					 "', '"+sqlTipSaat.format(eklenenSaat)+"', '"+alimSatimYokTarih+"', '"+alimSatimYokTarih+"', '"+tedarikci+"', ?, '"+kategoriNo+
					 "', '"+ozellik1+"', '"+ozellik2+"', '"+ozellik3+"', '"+ozellik4+"', '"+ozellik5+"', '"+ozellik6+"', '"+ozellik7+"', '"+ozellik8+"')";
			basarili = DbBaglanti.preparedEkle(ekle_sql, 1, foto);
			if(basarili==1) {
				JOptionPane.showMessageDialog(null, "Yeni ürün baþarýyla eklendi.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
				return basarili;
			} else JOptionPane.showMessageDialog(null, "Yeni ürün eklenemedi.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}
		
		return basarili;
	}
	
	static int degistir(String barkod, String yeniBarkod, String ad, String alis, String satis, String tedarikci) {
		//UPDATE `zprojefinal`.`urunler` SET `urun_barkod` = '15', `urun_ad` = 'DGSTR', `urun_alis_fiyat` = '5', `urun_satis_fiyat` = '6', `urun_tedarikci` = 'HEHE' WHERE (`urun_no` = '11');
		int basarili = 0;
		if(barkod.replaceAll("\\W", "").equals("") || ad.replaceAll("\\W", "").equals("") ||
				alis.replaceAll("\\W", "").equals("") || satis.replaceAll("\\W", "").equals("") ||
				tedarikci.replaceAll("\\W", "").equals("") || yeniBarkod.replaceAll("\\W", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Lütfen bütün boþluklarý uygun þekilde giriniz.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String guncelle_sql = "UPDATE `zprojefinal`.`urunler` SET `urun_barkod` = '"+yeniBarkod+
																	  "', `urun_ad` = '"+ad+
																	  "', `urun_alis_fiyat` = '"+alis+
																	  "', `urun_satis_fiyat` = '"+satis+
																	  "', `urun_tedarikci` = '"+tedarikci+
																	  "' WHERE (`urun_barkod` = '"+barkod+"')";
			basarili = DbBaglanti.degistir(guncelle_sql);
			if(basarili==1) {
				JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
				return basarili;
			} else {
				JOptionPane.showMessageDialog(null, "Ürün güncelleme baþarýsýz.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
				//HATA SAPTAMA
//				System.out.println("Eski barkod: "+barkod+"\nYeni Barkod: "+yeniBarkod+"\rAd: "+ad+"\rAlis-Satis: "+alis+"-"+satis+"\rTedarikci: "+tedarikci);
			}
		}
		return basarili;
	}
	
	
	static boolean barkodKontrol(BigInteger barkod) {
		boolean eslesme = false;
		String urun_sorgu = "SELECT * FROM urunler";
		ResultSet dbVeriKontrol = DbBaglanti.sorgula(urun_sorgu);
		try {
			while(dbVeriKontrol.next()) {
				if(barkod.equals(BigInteger.valueOf(dbVeriKontrol.getLong("urun_barkod"))))  {
					return eslesme=true;
				} else eslesme=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eslesme;
	}
	
	static ResultSet arama(String urunAd) {
		ResultSet dbVeriArama = null;
		String arama_sql = "SELECT * FROM zprojefinal.urunler where urun_ad like '%"+urunAd+"%'"; //isim içinde olan charlara göre çeker
		dbVeriArama = DbBaglanti.sorgula(arama_sql);
		return dbVeriArama;
	}
	static ResultSet arama(BigInteger barkod) {
		ResultSet dbVeriArama = null;
		String arama_sql = "SELECT * FROM zprojefinal.urunler where urun_barkod like '"+barkod+"%'"; //barkodunda 4 olan tüm barkodlar gelir // deðiþti barkod baþtan baþlar
		dbVeriArama = DbBaglanti.sorgula(arama_sql);
		return dbVeriArama;
	}

	public int getUrunNo() {
		return urunNo;
	}

	public BigInteger getUrunBarkod() {
		return urunBarkod;
	}

	public String getUrunAd() {
		return urunAd;
	}

	public double getUrunAlisFiyat() {
		return urunAlisFiyat;
	}

	public double getUrunSatisFiyat() {
		return urunSatisFiyat;
	}

	public int getUrunKalanAdet() {
		return urunKalanAdet;
	}

	public String getUrunTedarikci() {
		return urunTedarikci;
	}

	public ImageIcon getUrunFoto() {
		return urunFoto;
	}
	public int setUrunFoto(FileInputStream foto) {
		String update_sql = "UPDATE `zprojefinal`.`urunler` SET `urun_foto` = ? WHERE (`urun_barkod` = '"+this.urunBarkod+"');";
		int basarili = DbBaglanti.preparedEkle(update_sql, 1, foto);
		return basarili;
	}
	
	public Timestamp getUrunIlkEklemeTarih() {
		return urunIlkEklemeTarih;
	}

	public Timestamp getUrunSonAlisTarih() {
		return urunSonAlisTarih;
	}

	public Timestamp getUrunSonSatisTarih() {
		return urunSonSatisTarih;
	}
	
	public void setUrunKalanAdet(int urunKalanAdet) {
		this.urunKalanAdet = urunKalanAdet;
		String urun_update = "UPDATE `zprojefinal`.`urunler` SET `urun_kalan_adet` = '"+urunKalanAdet+"' WHERE (`urun_no` = '"+this.urunNo+"')";
		DbBaglanti.degistir(urun_update);
	}
	
	static void setUrunKalanAdet(String barkod, int eklenecekAdet) {
		BigInteger barkod_int= new BigInteger(barkod);
		new Urunler(barkod_int).getUrunKalanAdet(); // bu satýra gerek olmayabilir ama þimdi bozmayalým çalýþýyor
		String urun_update = "UPDATE `zprojefinal`.`urunler` SET `urun_kalan_adet` = '"+(new Urunler(barkod_int).getUrunKalanAdet()+eklenecekAdet)+"' WHERE (`urun_barkod` = '"+barkod+"')";
//		System.out.println(urun_update);
		DbBaglanti.degistir(urun_update);
	}
}
