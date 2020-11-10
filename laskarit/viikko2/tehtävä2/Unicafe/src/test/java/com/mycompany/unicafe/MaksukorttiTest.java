package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10",kortti.toString());      
    }
    
    @Test
    public void saldonLisaysToimii() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10",kortti.toString());      
    }
    
    @Test
    public void saldonOttaminenToimiiJosRahaa() {
        kortti.otaRahaa(5);
        assertEquals(5,kortti.saldo());      
    }
    
    @Test
    public void saldonOttaminenEiVaikuta() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10",kortti.toString());      
    }
    
    @Test
    public void totuusarvoOtetaanLiikaa() {
        assertTrue(!kortti.otaRahaa(20));      
    }
    
    @Test
    public void totuusarvoOteteaanAlleSaldo() {
        assertTrue(kortti.otaRahaa(5));      
    }
    
    
    
    
    
    
}
