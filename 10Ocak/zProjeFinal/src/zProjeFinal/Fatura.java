package zProjeFinal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.table.DefaultTableModel;

public class Fatura {
	//TEMSÝLÝ FATURA ZAMAN AYIRILIRSA DAHA GÜZEL OLABÝLÝR
	static int gunFatura = 1;
	static void yaz(DefaultTableModel sepet, double sepetToplamTutar) { //, Musteri alan
		
		DateTimeFormatter TRTipSaat = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss"); 
		DateTimeFormatter tempFatura = DateTimeFormatter.ofPattern("yyyyMMdd"); 
		LocalDateTime eklenenSaat = LocalDateTime.now();
		
		File fatura = null;
		try {
			fatura = new File("Faturalar\\fatura"+tempFatura.format(eklenenSaat)+"-"+gunFatura+".txt");
			fatura.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("DOSTA OLUÞTURMADA HATA");
		}
		
		
		
		try {
			FileWriter yaz = new FileWriter(fatura);
			yaz.write("Depom Sepette\r\r");
			yaz.write("Fatura No:   "+tempFatura.format(eklenenSaat)+"-"+gunFatura+"\r");
			yaz.write("Tarih-Saat:  "+TRTipSaat.format(eklenenSaat)+"\r\r");
			//kayýtlý müþteriye kesilen özel fatura
			for(int i=0; i<sepet.getRowCount(); i++) {
				yaz.write(sepet.getValueAt(i, 0)+" ---- "+ sepet.getValueAt(i, 1)+" ---- "+sepet.getValueAt(i, 2)+" ---- "+sepet.getValueAt(i, 3)+
						" Adet  B: "+sepet.getValueAt(i, 4).toString().replaceAll("\\u20ba", "")+"TL"
						+"    T: "+ sepet.getValueAt(i, 5).toString().replaceAll("\\u20ba", "")+"TL"+"\r");
			}
			yaz.write("\r------------------------------------\r");
			yaz.write("Toplam Tutar: "+sepetToplamTutar+" TL\r");
			//KDV felan eklenebilir
			yaz.close();
			gunFatura++;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("DOSTA YAZMADA HATA");
		}
	}
	
	static void yaz(DefaultTableModel sepet, double sepetToplamTutar, Musteri alanMusteri) { //
		
		DateTimeFormatter TRTipSaat = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss"); 
		DateTimeFormatter tempFatura = DateTimeFormatter.ofPattern("yyyyMMdd"); 
		LocalDateTime eklenenSaat = LocalDateTime.now();
		
		File fatura = null;
		try {
			fatura = new File("Faturalar\\fatura"+tempFatura.format(eklenenSaat)+"-"+gunFatura+".txt");
			fatura.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("DOSTA OLUÞTURMADA HATA");
		}
		
		
		
		try {
			FileWriter yaz = new FileWriter(fatura);
			yaz.write("Depom Sepette\r\r");
			yaz.write("Fatura No:   "+tempFatura.format(eklenenSaat)+"-"+gunFatura+"\r");
			yaz.write("Tarih-Saat:  "+TRTipSaat.format(eklenenSaat)+"\r\r");
			//kayýtlý müþteriye kesilen özel fatura
			yaz.write("Müþteri: "+alanMusteri.getMusteriAdSoyad()+"\r");
			yaz.write("Adres: "+alanMusteri.getMusteriAdres()+"\r\r");
//			System.out.println(alanMusteri.getMusteriAdSoyad()); //test
			
			
			for(int i=0; i<sepet.getRowCount(); i++) {
				yaz.write(sepet.getValueAt(i, 0)+" ---- "+ sepet.getValueAt(i, 1)+" ---- "+sepet.getValueAt(i, 2)+" ---- "+sepet.getValueAt(i, 3)+
						" Adet  B: "+sepet.getValueAt(i, 4).toString().replaceAll("\\u20ba", "")+"TL"
						+"    T: "+ sepet.getValueAt(i, 5).toString().replaceAll("\\u20ba", "")+"TL"+"\r");
			}
			yaz.write("\r------------------------------------\r");
			yaz.write("Toplam Tutar: "+sepetToplamTutar+" TL\r");
			//KDV felan eklenebilir
			yaz.close();
			gunFatura++;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("DOSTA YAZMADA HATA");
		}
	}
}
