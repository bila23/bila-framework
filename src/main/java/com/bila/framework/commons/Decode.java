/*----------------------------------------------------------
* FILE: Decode.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.commons;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.commons
 * <b>on </b> 3/12/2015 12:05:40 PM
 * <b>Purpose</b> 
 * <p>
 *  Clase que sirve para encriptar texto por medio de SHA
 * </p>
 */
public final class Decode {
    
    private static Decode decode;

    /**
     * Funcion que encripta el texto que se pasa como parametro
     * @param txt objeto String que se desea encriptar
     * @return objeto String con el texto encriptado
     * @throws java.lang.Exception
     */
    public synchronized String encrypt(String txt) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(txt.getBytes("UTF-8")); 
        byte raw[] = md.digest();
        byte[] base64Bytes = Base64.encodeBase64(raw);
        String hash = new String(base64Bytes);
        return hash;
    }

    /**
     * Funcion que me retorna una instancia de la clase Decode
     * por medio del patron Singleton
     * @return instancia del objeto Decode
     */
    public static synchronized Decode getInstance(){
        if(decode == null)
            decode = new Decode();
        return decode;
    }
    
}