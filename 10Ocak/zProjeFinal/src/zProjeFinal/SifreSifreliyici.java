package zProjeFinal;

import java.util.Scanner;

public class SifreSifreliyici {
//	private String sifreliSifre;
	private char[] allChar = "!\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~������������".toCharArray(); //bunu internetten buldum bu sayede her karakteri elle tek tek string i�ine yazmad�m
	private char[] keyChar = "!\"9:;<=>?@A^_`abcCDEFG#$%&\\'()*+,-./012345678HIJBUVWXYZ[\\\\]KLMNOPnopjklmxqrstuvwQRSTdefghiyz{|}~������������".toCharArray();
	private String[] allCharString;
	private String[] keyCharString;
	private void charToString() {
		//allcharString = b�t�n charlar�n s�ral� �ekilde bulundu�u array
		//keycharString = b�t�n karkaterlerin kar���k �ekilde bulundu�u array
		allCharString = new String[allChar.length+1]; //charlar� aktaracak string boyutu belirledim ama +1 bo�luk karakteri i�in
		keyCharString = new String[keyChar.length+1]; //�steki ile ayn� �ekilde
		allCharString[allChar.length+1-1]=" ";        //string arraye bo�luk karakterini ekledim ama 0 bir kabul edildi�i i�in +1-1 yapt�m
		keyCharString[keyChar.length+1-1]=" ";		  //�steki ile ayn� �ekilde
		
		for(int i=0; i<allChar.length; i++) {
			allCharString[i]=String.valueOf(allChar[i]); //�steki ile ayn� �ekilde
		}
		
		for(int i=0; i<allChar.length; i++) {
			keyCharString[i]=String.valueOf(keyChar[i]); //chardaki her de�eri stringe ekledim
		}	
	}
	
	
	public void deneme() {
		Scanner kb = new Scanner(System.in);
		String gelenSifre = kb.nextLine();
		
		System.out.println(kriptola(gelenSifre));
		System.out.println(kriptoCoz(kriptola(gelenSifre)));
		if(gelenSifre.equals(kriptoCoz(kriptola(gelenSifre)))) {System.out.println("DENK");}
		
//		if(Arrays.equals(sifreIndex, cozSifre)) {System.out.println("Denk");} //iki arrayi kar��la�t�rma methodu bulunsun...	
		kb.close();
	}
	
	
	public String kriptola(String gelenSifre) {
		charToString(); //stringlerin i�lemesi i�in bu metod her zaman �al��mal� (static yap�labilirdi ama her �eye static istiyor uzuyor)
		int basamakGS = gelenSifre.length();        			//�nce gelen �ifrenin basamak say�s�n� bulduk
		String[] sifreKarakterleri = new String[basamakGS];		//gelen �ifreyi karakterlerine ay�r�p arrayde tutaca��z
		for(int i=0; i<basamakGS; i++) {
			sifreKarakterleri[i] = gelenSifre.substring(i, i+1);//for d�ng�s� i�inde substring ile �ifreyi karakter karakter array e aktar�yoruz
		}
		int[] allCharStringSaptayici = new int[basamakGS];      //allcharstringde karakter ka�inc� say�da haf�zada tutan array
		int allCharStringSayac=0;
		for(int i=0; i<basamakGS; i++) {
			for(int j=0; j<allCharString.length; j++) {			//her i gelen�ifrekarkateri i�in b�t�n allchar� �eviren j d�ng�s�
				if(sifreKarakterleri[i].equals(allCharString[j])) {   //�ifrenin hangi karakteri allchar�n ka��nc� karakteri ise onun sayisini alma
					allCharStringSaptayici[allCharStringSayac]=j;     //al�nan sayiyinin saptayiciya kayd� yap�ld�
					allCharStringSayac++;							  //sonraki karakter i�in saya� 1 art�r�ld�
				}
			}
		}
			
		String[] sifreliSifre = new String[basamakGS];				  //kriptolu �ifre i�in sifrelisifre arrayi olu�turuldu
		for(int i=0; i<basamakGS; i++) {
			sifreliSifre[i]=keyCharString[allCharStringSaptayici[i]]; //saptayicidaki say�lar� keychar i�inde hangi karaktere denk geliyorsa onlar� �ifreli�ifre arrayinde tutma
		}
		
		String sifreliSifreString = String.join("", sifreliSifre); //string arraydeekileri tek stringe b�t�n halde aktarma
		return sifreliSifreString;
	}//metod kapand�
	
	public String kriptoCoz(String sifreliSifre) {
		charToString(); //stringlerin i�lemesi i�in bu metod her zaman �al��mal� (static yap�labilirdi ama her �eye static istiyor uzuyor)
		int basamakSS = sifreliSifre.length(); 						//gelen�ifre ile �ifreli �ifre ayn� basamakta olaca�� i�in ama genel bir basamak say�s� belirlemek pek sa�l�kl� olmaz bu �ekil daha sa�l�kl�
		String[] sifreliSifreKarakterleri = new String[basamakSS];	//�ifreli�ifre string halde gelecek onu karakterlerine ay�r�p arrayde tutaca��z
		for(int i=0; i<basamakSS; i++) {
			sifreliSifreKarakterleri[i] = sifreliSifre.substring(i, i+1); //for d�ng�s� i�inde substring ile �ifreyi karakter karakter array e aktar�yoruz
		}
		int[] keyCharStringSaptayici = new int[basamakSS];			//keycharstringde karakter ka�inc� say�da haf�zada tutan array
		int keyCharStringSayac=0;
		for(int i=0; i<basamakSS; i++) {
			for(int j=0; j<keyCharString.length; j++) {						//her i sifreli�ifrekarkateri i�in b�t�n keychar� �eviren j d�ng�s�
				if(sifreliSifreKarakterleri[i].equals(keyCharString[j])) {
					keyCharStringSaptayici[keyCharStringSayac]=j;			//al�nan sayiyinin saptayiciya kayd� yap�ld�
					keyCharStringSayac++;									//sonraki karakter i�in saya� 1 art�r�ld�
				}
			}
		}
		
		String[] cozulmusSifre = new String[basamakSS];						//cozulen sifreyi bir arrayde birle�tirmek i�in olu�turulan array
		for(int i=0; i<basamakSS; i++) {									
			cozulmusSifre[i]=allCharString[keyCharStringSaptayici[i]];		//saptayicidaki sayilarin allchar i�inde hangi karaktere denk geldi�ini bulma
		}
		
		String cozulmusSifreString = String.join("", cozulmusSifre); //cozulmussifre arrayindeki sifreyi tek stringe birle�ik �ekilde yerle�tirme
		return cozulmusSifreString;
	}//metod kapand�
	
	public String randomSifre() { //26 aral�k ekleme tarihi //static yap�labilirdi haf�zada tutulmas�na gerek yok
		String[] randomSifreArray = new String[10];
		char[] basicChar = "0!\"#$%&123456789@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz+-*".toCharArray(); //0-72 toplam 73 karakter
		String[] basicCharString = new String[basicChar.length];
		for(int i=0; i<basicChar.length; i++) {
			basicCharString[i]=String.valueOf(basicChar[i]); //�steki ile ayn� �ekilde
		}
		for(int i=0; i<10; i++) {
			int randomSayi1 = (int) (Math.random()*10); //9 
			while(randomSayi1==0) {
				if(randomSayi1==0) { //-- 0 hari� tutulmal� sadece 1 s�f�r yeter
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
