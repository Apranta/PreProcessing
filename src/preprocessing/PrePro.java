/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import NLP_ITB.POSTagger.HMM.Decoder.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import jsastrawi.morphology.*;
/**
 *
 * @author Rezi Apriliansyah
 */
public class PrePro {
    private String[] wordsplit;

    private int length;
    char[] delimiter = {'!', '_', '.', ',', '(', ')', '?', ';', '"','-'};
    String[] stopWord = {"dan", "bahwa", "atau", "karena", "serta", "seandainya",
        "walaupun", "tetapi", "ketika", "apabila", "sekiranya",
        "sesudah", "melainkan", "kendatipun", "jika", "sedangkan",
        "semenjak", "agar", "hingga", "sebab", "andaikan", "hanya",
        "supaya", "sebelum", "sekalipun", "dengan", "lalu", "selama",
        "asal", "sungguhpun", "melainkan", "sampai", "tatkala",
        "seraya", "sambil", "setelah", "sehabis", "biar", "selesai",
        "sementara", "selagi", "sampai", "kalau", "jikalau", "bila",
        "seumpamanya", "sehingga", "namun", "sebaliknya", "kemudian",
        "selanjutnya", "yaitu", "yakni", "adalah", "ialah", "jadi",
        "sewaktu", "untuk", "guna", "seperti", "laksana", "sebagai",
        "tempat", "padahal", "sejak", "begitu", "demi", "seusai",
        "biarpun", "seakan-akan", "seolah-olah", "daripada",
        "oleh sebab itu", "tanpa", "oleh karena itu", "tuh"};

    public String CaseFolding(String word) {
          // System.out.println("case folding");
        String wordafter = "";
        word = word.toLowerCase();
        wordsplit = word.split(" ");
        int tanda = 0;
        char[] c;

        for (int i = 0; i < wordsplit.length; i++) {
            int x = 0;
            c = new char[wordsplit[i].length()];
            for (int j = 0; j < wordsplit[i].length(); j++) {
                for (int k = 0; k < delimiter.length; k++) {
                    if (wordsplit[i].charAt(j) == delimiter[k]) {
                        tanda++;
                    }
                }
                if (tanda == 0) {
                    c[x] = wordsplit[i].charAt(j);
                    x++;
                }
                tanda = 0;
            }
            wordsplit[i] = String.valueOf(c);
        }

            //---
        for (int i = 0; i < wordsplit.length; i++) {
            int hitung = 0;
             //   System.out.println("katanya : "+wordsplit[i]);
            //  System.out.println("panjangnya : "+wordsplit[i].length());
            for (int j = 0; j < wordsplit[i].length(); j++) {
                if (wordsplit[i].charAt(j) == 'a' || wordsplit[i].charAt(j) == 'b' || wordsplit[i].charAt(j) == 'c'
                        || wordsplit[i].charAt(j) == 'd' || wordsplit[i].charAt(j) == 'e' || wordsplit[i].charAt(j) == 'f'
                        || wordsplit[i].charAt(j) == 'g' || wordsplit[i].charAt(j) == 'h' || wordsplit[i].charAt(j) == 'i'
                        || wordsplit[i].charAt(j) == 'j' || wordsplit[i].charAt(j) == 'k' || wordsplit[i].charAt(j) == 'l'
                        || wordsplit[i].charAt(j) == 'm' || wordsplit[i].charAt(j) == 'n' || wordsplit[i].charAt(j) == 'o'
                        || wordsplit[i].charAt(j) == 'p' || wordsplit[i].charAt(j) == 'q' || wordsplit[i].charAt(j) == 'r'
                        || wordsplit[i].charAt(j) == 's' || wordsplit[i].charAt(j) == 't' || wordsplit[i].charAt(j) == 'u'
                        || wordsplit[i].charAt(j) == 'v' || wordsplit[i].charAt(j) == 'w' || wordsplit[i].charAt(j) == 'x'
                        || wordsplit[i].charAt(j) == 'y' || wordsplit[i].charAt(j) == 'z' || wordsplit[i].charAt(j) == '-') {
                } else {
                    hitung++;
                }
            }
            //  System.out.println("hitung : "+hitung);
            wordsplit[i] = wordsplit[i].substring(0, wordsplit[i].length() - hitung);

            //System.out.println("panjang Setelahnya : "+wordsplit[i].length());
        }

        //---
        int panjang = 0;
        for (int i = 0; i < wordsplit.length; i++) {
            if (!wordsplit[i].isEmpty()) {
                wordsplit[panjang] = wordsplit[i];
                panjang++;
            }
        }

        for (int i = 0; i < panjang; i++) {
            if (i != panjang - 1) {
                wordafter = wordafter + wordsplit[i] + " ";
            } else {
                wordafter = wordafter + wordsplit[i];
            }
        }
        return wordafter;
    }

    public String StopWordRemoval(String word) {
         //System.out.println("stop word");
        String wordafter = "";
        wordsplit = word.split(" ");

        String[] Temp = new String[wordsplit.length];
        int z = 0;
        int x = 0;

        for (String wordsplit1 : wordsplit) {
            for (String stopWord1 : stopWord) {
                if (wordsplit1.equals(stopWord1)) {
                    z++;
                }
            }
            if (z == 0) {
                Temp[x] = wordsplit1;
                x++;
            }
            z = 0;
        }

        for (int i = 0; i < x; i++) {
            if (i != x - 1) {
                wordafter = wordafter + Temp[i] + " ";
            } else {
                wordafter = wordafter + Temp[i];
            }
        }
        return wordafter;
    }
    
    public String[] Tokenizing(String Kata){
        return Kata.split(" ");
    }
    
    public String Stemming(String Kata) throws IOException{
        Set<String> dictionary = new HashSet<>();

        // Memuat file kata dasar dari distribusi JSastrawi
        // Jika perlu, anda dapat mengganti file ini dengan kamus anda sendiri
        InputStream in = Lemmatizer.class.getResourceAsStream("/root-words.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = br.readLine()) != null) {
            dictionary.add(line);
        }
        Lemmatizer lemmatizer = new DefaultLemmatizer(dictionary);
        return lemmatizer.lemmatize(Kata);
    }
    
    public ArrayList<String> POSTagging(String Kata){
        MainTagger mt = new MainTagger("./postagger/Lexicon.trn", "./postagger/Ngram.trn", 1);
        return mt.taggingStr(Kata);
    }
    
    public String[] run(String Kalimat) throws IOException{
       String[] hasil = null;
       String[] temp_stem;
       String temp;
       int i=0;
       temp = this.CaseFolding(Kalimat);
       temp = this.StopWordRemoval(temp);
       hasil = this.Tokenizing(temp);
       temp_stem = new String[hasil.length];
       for (String hasil1 : hasil) {
           temp_stem[i] = this.Stemming(hasil1);
           i++;
       }
       return temp_stem;
    }
}
