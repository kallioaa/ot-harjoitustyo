/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aatukallio
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }    
    @Before
    public void setUp() {
        this.kassa = new Kassapaate();
        this.kortti = new Maksukortti(500);
    }
    
    @Test
    public void luodussaKassassaOikea() {
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void luodussaKassassaEiMyyty() {
        assertEquals(0,kassa.maukkaitaLounaitaMyyty()+kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittavaMaksuKateisostoEdullinen() {
        assertEquals(60,kassa.syoEdullisesti(300));
    }
     @Test
    public void riittavaMaksuKateisostoMaukas() {
        assertEquals(60,kassa.syoMaukkaasti(460));
    }
    
    @Test
    public void kassaKasvaaKateisestaEdullinen() {
        kassa.syoEdullisesti(300);
        assertEquals(100240,kassa.kassassaRahaa());
    }
    @Test
    public void kassaKasvaaKateisestaMaukas() {
        kassa.syoMaukkaasti(460);
        assertEquals(100400,kassa.kassassaRahaa());
    }
    
    @Test
    public void myytymaaraKasvaaEdullinen() {
        kassa.syoEdullisesti(300);
        assertEquals(1,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytymaaraKasvaaMaukas() {
        kassa.syoMaukkaasti(400);
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void riittavaMaksuKateisostoEdullinenEiTarpeeksi() {
        assertEquals(100,kassa.syoEdullisesti(100));
    }
     @Test
    public void riittavaMaksuKateisostoMaukasEiTarpeeksi() {
        assertEquals(100,kassa.syoMaukkaasti(100));
    }
    
    @Test
    public void kassaKasvaaKateisestaEdullinenEiTarpeeksi() {
        kassa.syoEdullisesti(100);
        assertEquals(100000,kassa.kassassaRahaa());
    }
    @Test
    public void kassaKasvaaKateisestaMaukasEiTarpeeksi() {
        kassa.syoEdullisesti(100);
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void myytymaaraKasvaaEdullinenEiTarpeeksi() {
        kassa.syoEdullisesti(100);
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytymaaraKasvaaMaukasEiTarpeeksi() {
        kassa.syoMaukkaasti(100);
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void riittavaKorttimaksuKateisostoEdullinen() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    @Test
    public void riittavaKorttimaksuKateisostoMaukas() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void riittmatonKorttimaksuKateisostoEdullinen() {
        kortti.otaRahaa(400);
        assertTrue(!kassa.syoEdullisesti(kortti));
    }
    @Test
    public void riittamatonKorttimaksuKateisostoMaukas() {
        kortti.otaRahaa(400);
        assertTrue(!kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void riittavaKorttimaksuKateisostoEdullinenSaldo() {
        kassa.syoEdullisesti(kortti);
        assertEquals(260,kortti.saldo());
    }
    
    @Test
    public void riittavaKorttimaksuKateisostoMaukasSaldo() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100,kortti.saldo());
    }
    
    @Test
    public void eiRiittavaKorttimaksuKateisostoEdullinenSaldo() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        assertEquals(100,kortti.saldo());
    }
    
    @Test
    public void eiRiittavaKorttimaksuKateisostoMaukasSaldo() {
        kortti.otaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100,kortti.saldo());
    }
    
    @Test
    public void myytymaaraKorttiKasvaaEdullinen() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytymaaraKorttiKasvaaMaukas() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void myytymaaraKasvaaEdullinenKortillaEiTarpeeksi() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytymaaraKasvaaMaukasKortillaEiTarpeeksi() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiKasvaKortistaEdullinen() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
    }
    @Test
    public void kassaEiKasvaKortistaMaukas() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanSaldoMuuttuuKortilleLadatessa() {
        kassa.lataaRahaaKortille(kortti, 200);
        assertEquals(100200,kassa.kassassaRahaa());
    }
    
    @Test
    public void kortinSaldoMuuttuuladatessa() {
        kassa.lataaRahaaKortille(kortti, 200);
        assertEquals(700,kortti.saldo());
    }
    
    @Test
    public void kortinSaldoEiMuuttuuladatessaNeg() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(500,kortti.saldo());
    }
    
    @Test
    public void kassanSaldoMuuttuuEiKortilleLadatessaNegh() {
        kassa.lataaRahaaKortille(kortti, -200);
        assertEquals(100000,kassa.kassassaRahaa());
    }
    
    
  
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
