/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import java.util.Arrays;

/**
 *
 * @author Rezi Apriliansyah
 */
public class PreProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PrePro pp = new PrePro();
        System.out.println(pp.StopWordRemoval(pp.CaseFolding("Manusia ini hanya bisa dan kamu,-")));
    }
    
}
