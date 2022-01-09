package zProjeFinal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategori {
	
	private String kategoriAd, baslik1, baslik2, baslik3, baslik4, baslik5, baslik6, baslik7, baslik8;
	private int kategoriNoAlt, kategoriNoUst;
	private String[] baslik;
	
	
	Kategori(int kategoriNo) {
		int kategoriNoAlt = kategoriNo % 1000;
		int kategoriNoUst = ((kategoriNo % 1000000)-kategoriNoAlt)/1000;
//		System.out.println("Alt kategori no = "+kategoriNoAlt);
//		System.out.println("Üst kategori No = "+kategoriNoUst);
		
		baslik = new String[8];
		
		String ustKategori_sql = "SELECT * FROM ust_kategori_basliklari where ust_kategori_basliklari_no = '"+ kategoriNoUst +"'";
		ResultSet dbVeriUst = DbBaglanti.sorgula(ustKategori_sql);
		
		try {
			while(dbVeriUst.next()) {
				this.kategoriNoUst = dbVeriUst.getInt("ust_kategori_basliklari_no");
				kategoriAd = dbVeriUst.getString("ust_kategori_ad");
				for(int i=0; i<8; i++) {
					String ic = new String("ust_kategori_baslik"+(i+1));
					baslik[i] = dbVeriUst.getString(ic);
				}
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int baslikBos = 0;
		for(int i=0; i<8; i++) {
			if(baslik[i]!=null) {if(!baslik[i].replaceAll("\\W", "").equals("")) {baslikBos += 10000000/(Math.pow(10, i));}else{baslikBos += 0;}} else {baslikBos += 0;}
		}
			
//		System.out.println(baslikBos); //TEST
		
		if(kategoriNoAlt != 0) {
			String altKategori_sql = "SELECT * FROM kategori_basliklari where kategori_no = '" + kategoriNoAlt + "'";
			ResultSet dbVeriAlt = DbBaglanti.sorgula(altKategori_sql);
			try {
				while(dbVeriAlt.next()) {
					this.kategoriNoAlt = dbVeriAlt.getInt("kategori_no");
					kategoriAd = new String(kategoriAd+"> "+dbVeriAlt.getString("kategori_adi"));//dbVeriAlt.getString("kategori_adi");
					
					switch(baslikBos) {
						case 0:	
							for(int i=0; i<8; i++) {
								String ic = new String("baslik_"+(i+1));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 10000000:
							for(int i=1; i<8; i++) {
								String ic = new String("baslik_"+(i));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11000000:
							for(int i=2; i<8; i++) {
								String ic = new String("baslik_"+(i-1));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11100000:
							for(int i=3; i<8; i++) {
								String ic = new String("baslik_"+(i-2));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11110000:
							for(int i=4; i<8; i++) {
								String ic = new String("baslik_"+(i-3));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11111000:
							for(int i=5; i<8; i++) {
								String ic = new String("baslik_"+(i-4));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11111100:
							for(int i=6; i<8; i++) {
								String ic = new String("baslik_"+(i-5));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11111110:
							for(int i=7; i<8; i++) {
								String ic = new String("baslik_"+(i-6));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static String[] altInfo(String[] baslik, int altKategoriNo) {
		
		int baslikBos = 0;
		for(int i=0; i<8; i++) {
//			System.out.println("Baslik-"+i+" = "+baslik[i]); //TEST
			if(baslik[i]!=null) {if(!baslik[i].replaceAll("\\W", "").equals("")) {baslikBos += 10000000/(Math.pow(10, i));}else{baslikBos += 0;}} else {baslikBos += 0;}
		}
			
//		System.out.println("altinfo þifre baslikbos = "+baslikBos); //TEST
		
//		if(kategoriNoAlt != 0) {
			String altKategori_sql = "SELECT * FROM zprojefinal.kategori_basliklari where kategori_no = '"+altKategoriNo+"'";
			ResultSet dbVeriAlt = DbBaglanti.sorgula(altKategori_sql);
			try {
				while(dbVeriAlt.next()) {
//					this.kategoriNoAlt = dbVeriAlt.getInt("kategori_no");
//					kategoriAd = new String(kategoriAd+"> "+dbVeriAlt.getString("kategori_adi"));//dbVeriAlt.getString("kategori_adi");
					
					switch(baslikBos) {
						case 0:	
							for(int i=0; i<8; i++) {
								String ic = new String("baslik_"+(i+1));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 10000000:
							for(int i=1; i<8; i++) {
								String ic = new String("baslik_"+(i));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11000000:
							for(int i=2; i<8; i++) {
								String ic = new String("baslik_"+(i-1));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11100000:
							for(int i=3; i<8; i++) {
								String ic = new String("baslik_"+(i-2));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11110000:
							for(int i=4; i<8; i++) {
								String ic = new String("baslik_"+(i-3));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11111000:
							for(int i=5; i<8; i++) {
								String ic = new String("baslik_"+(i-4));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11111100:
							for(int i=6; i<8; i++) {
								String ic = new String("baslik_"+(i-5));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
						case 11111110:
							for(int i=7; i<8; i++) {
								String ic = new String("baslik_"+(i-6));
								baslik[i] = dbVeriAlt.getString(ic);
							}
							break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//		}
		return baslik;
	}

	public String[] getBaslik() {
		return baslik;
	}


	public String getKategoriAd() {
		return kategoriAd;
	}


	public String getBaslik1() {
		return baslik1;
	}


	public String getBaslik2() {
		return baslik2;
	}


	public String getBaslik3() {
		return baslik3;
	}


	public String getBaslik4() {
		return baslik4;
	}


	public String getBaslik5() {
		return baslik5;
	}


	public String getBaslik6() {
		return baslik6;
	}


	public String getBaslik7() {
		return baslik7;
	}


	public String getBaslik8() {
		return baslik8;
	}


	public int getKategoriNo() {
		return kategoriNoAlt;
	}
	
	
	
	
}
