package org.daragon.miscellany.pattern;

public class KeyGenerator {

    private static KeyGenerator keygen = new KeyGenerator();
    private static final int POOL_SIZE=20;
    private KeyInfo key;
    private KeyGenerator() {
        key = new KeyInfo(POOL_SIZE);
    }

    public static KeyGenerator getInstance() {
        return keygen;
    }

    public synchronized int getNextKey() {
        return key.getNextKey();
    }
    
   
    
    
    
    
    
}
