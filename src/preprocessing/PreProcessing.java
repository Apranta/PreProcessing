/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;
import NLP_ITB.POSTagger.HMM.Decoder.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rezi Apriliansyah
 */
public class PreProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        PrePro pp = new PrePro();
        System.out.println(Arrays.toString(pp.run("Manusia ini hanya bisa memakan dan mencintai kamu,-")));
//        MainTagger mt = new MainTagger("./postagger/Lexicon.trn", "./postagger/Ngram.trn", 1);
//        ArrayList<String> a = new ArrayList<>();
//        String[] k = new String[2];
//        a = mt.taggingStr("Kita Memakan Nasi");
//        int i;
//        for(String b : a){
//            i=0;
//            for (String ka : b.split("/")) {
//                k[i] = ka;
//                System.out.print( k[i] + " ->");
//                i++;
//            }
//            System.out.println("");
//        }
    }
    
}
