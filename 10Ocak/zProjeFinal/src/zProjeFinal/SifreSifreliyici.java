package zProjeFinal;

import java.util.Scanner;

public class SifreSifreliyici {
//	private String sifreliSifre;
	private char[] allChar = "!\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ŞşÜüĞğİıÇçÖö".toCharArray(); //bunu internetten buldum bu sayede her karakteri elle tek tek string içine yazmadım
	private char[] keyChar = "!\"9:;<=>?@A^_`abcCDEFG#$%&\\'()*+,-./012345678HIJBUVWXYZ[\\\\]KLMNOPnopjklmxqrstuvwQRSTdefghiyz{|}~ŞşÜüĞğİıÇçÖö".toCharArray();
	private String[] allCharString;
	private String[] keyCharString;
	private void charToString() {
		//allcharString = bütün charların sıralı şekilde bulunduğu array
		//keycharString = bütün karkaterlerin karışık şekilde bulunduğu array
		allCharString = new String[allChar.length+1]; //charları aktaracak string boyutu belirledim ama +1 boşluk karakteri için
		keyCharString = new String[keyChar.length+1]; //üsteki ile aynı şekilde
		allCharString[allChar.length+1-1]=" ";        //string arraye boşluk karakterini ekledim ama 0 bir kabul edildiği için +1-1 yaptım
		keyCharString[keyChar.length+1-1]=" ";		  //üsteki ile aynı şekilde
		
		for(int i=0; i<allChar.length; i++) {
			allCharString[i]=String.valueOf(allChar[i]); //üsteki ile aynı şekilde
		}
		
		for(int i=0; i<allChar.length; i++) {
			keyCharString[i]=String.valueOf(keyChar[i]); //chardaki her değeri stringe ekledim
		}	
	}
	
	
	public void deneme() {
		Scanner kb = new Scanner(System.in);
		String gelenSifre = kb.nextLine();
		
		System.out.println(kriptola(gelenSifre));
		System.out.println(kriptoCoz(kriptola(gelenSifre)));
		if(gelenSifre.equals(kriptoCoz(kriptola(gelenSifre)))) {System.out.println("DENK");}
		
//		if(Arrays.equals(sifreIndex, cozSifre)) {System.out.println("Denk");} //iki arrayi karşılaştırma methodu bulunsun...	
		kb.close();
	}
	
	
	public String kriptola(String gelenSifre) {
		charToString(); //stringlerin işlemesi için bu metod her zaman çalışmalı (static yapılabilirdi ama her şeye static istiyor uzuyor)
		int basamakGS = gelenSifre.length();        			//önce gelen şifrenin basamak sayısını bulduk
		String[] sifreKarakterleri = new String[basamakGS];		//gelen şifreyi karakterlerine ayırıp arrayde tutacağız
		for(int i=0; i<basamakGS; i++) {
			sifreKarakterleri[i] = gelenSifre.substring(i, i+1);//for döngüsü içinde substring ile şifreyi karakter karakter array e aktarıyoruz
		}
		int[] allCharStringSaptayici = new int[basamakGS];      //allcharstringde karakter kaçincı sayıda hafızada tutan array
		int allCharStringSayac=0;
		for(int i=0; i<basamakGS; i++) {
			for(int j=0; j<allCharString.length; j++) {			//her i gelenşifrekarkateri için bütün allcharı çeviren j döngüsü
				if(sifreKarakterleri[i].equals(allCharString[j])) {   //şifrenin hangi karakteri allcharın kaçıncı karakteri ise onun sayisini alma
					allCharStringSaptayici[allCharStringSayac]=j;     //alınan sayiyinin saptayiciya kaydı yapıldı
					allCharStringSayac++;							  //sonraki karakter için sayaç 1 artırıldı
				}
			}
		}
			
		String[] sifreliSifre = new String[basamakGS];				  //kriptolu şifre için sifrelisifre arrayi oluşturuldu
		for(int i=0; i<basamakGS; i++) {
			sifreliSifre[i]=keyCharString[allCharStringSaptayici[i]]; //saptayicidaki sayıları keychar içinde hangi karaktere denk geliyorsa onları şifrelişifre arrayinde tutma
		}
		
		String sifreliSifreString = String.join("", sifreliSifre); //string arraydeekileri tek stringe bütün halde aktarma
		return sifreliSifreString;
	}//metod kapandı
	
	public String kriptoCoz(String sifreliSifre) {
		charToString(); //stringlerin işlemesi için bu metod her zaman çalışmalı (static yapılabilirdi ama her şeye static istiyor uzuyor)
		int basamakSS = sifreliSifre.length(); 						//gelenşifre ile şifreli şifre aynı basamakta olacağı için ama genel bir basamak sayısı belirlemek pek sağlıklı olmaz bu şekil daha sağlıklı
		String[] sifreliSifreKarakterleri = new String[basamakSS];	//şifrelişifre string halde gelecek onu karakterlerine ayırıp arrayde tutacağız
		for(int i=0; i<basamakSS; i++) {
			sifreliSifreKarakterleri[i] = sifreliSifre.substring(i, i+1); //for döngüsü içinde substring ile şifreyi karakter karakter array e aktarıyoruz
		}
		int[] keyCharStringSaptayici = new int[basamakSS];			//keycharstringde karakter kaçincı sayıda hafızada tutan array
		int keyCharStringSayac=0;
		for(int i=0; i<basamakSS; i++) {
			for(int j=0; j<keyCharString.length; j++) {						//her i sifrelişifrekarkateri için bütün keycharı çeviren j döngüsü
				if(sifreliSifreKarakterleri[i].equals(keyCharString[j])) {
					keyCharStringSaptayici[keyCharStringSayac]=j;			//alınan sayiyinin saptayiciya kaydı yapıldı
					keyCharStringSayac++;									//sonraki karakter için sayaç 1 artırıldı
				}
			}
		}
		
		String[] cozulmusSifre = new String[basamakSS];						//cozulen sifreyi bir arrayde birleştirmek için oluşturulan array
		for(int i=0; i<basamakSS; i++) {									
			cozulmusSifre[i]=allCharString[keyCharStringSaptayici[i]];		//saptayicidaki sayilarin allchar içinde hangi karaktere denk geldiğini bulma
		}
		
		String cozulmusSifreString = String.join("", cozulmusSifre); //cozulmussifre arrayindeki sifreyi tek stringe birleşik şekilde yerleştirme
		return cozulmusSifreString;
	}//metod kapandı
	
	public String randomSifre() { //26 aralık ekleme tarihi //static yapılabilirdi hafızada tutulmasına gerek yok
		String[] randomSifreArray = new String[10];
		char[] basicChar = "0!\"#$%&123456789@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz+-*".toCharArray(); //0-72 toplam 73 karakter
		String[] basicCharString = new String[basicChar.length];
		for(int i=0; i<basicChar.length; i++) {
			basicCharString[i]=String.valueOf(basicChar[i]); //üsteki ile aynı şekilde
		}
		for(int i=0; i<10; i++) {
			int randomSayi1 = (int) (Math.random()*10); //9 
			while(randomSayi1==0) {
				if(randomSayi1==0) { //-- 0 hariç tutulmalı sadece 1 sıfır yeter
					randomSayi1 = (int) (Math.random()*10);
				}
			}
			int randomSayi2 = (int) ((Math.random()*10)-1); //8
			randomSifreArray[i]=basicCharString[randomSayi1*randomSayi2];
//			System.out.println(randomSayi1+"-"+randomSayi2+"-"+randomSayi1*randomSayi2);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String randomSifre = String.join("", randomSifreArray);
		return randomSifre;
	}
	
}
