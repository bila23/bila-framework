/*----------------------------------------------------------
* FILE: IMessages.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.commons;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.commons
 * <b>on </b> 21/08/2017 08:12:08 AM
 * <b>Purpose</b> 
 * <p>
 *      Interfaz que posee metodos para presentación de mensajes
 *      en la pagina web
 * </p>
 */
public interface IMessages {
    
    /**
     * Muestra un mensaje de exito pasando como parametro
     * el titulo del mensaje y su texto complementario
     * @param title objeto de tipo String con el titulo del mensaje
     * @param msg objeto de tipo String con el texto del mensaje
     */
    public static void success(String title, String msg){
    }
    
    /**
     * Muestra un mensaje de exito definiendo el ID del objeto mensaje
     * pasando como parametro el titulo del mensaje, su texto y el ID
     * @param title objeto de tipo String con el titulo del mensaje
     * @param msg objeto de tipo String con el texto del mensaje
     * @param id objeto de tipo String con el ID del objeto mensaje
     */
    public static void success(String title, String msg, String id){
    }

    public static void successId(String msg, String id){
    }
    
    public static void success(String msg){
    }
    
    public static void warning(String title, String msg){
    }
    
    public static void warning(String msg){
    }
    
    public static void warning(String title, String msg, String id){
    }
    
    public static void warningId(String msg, String id){
    }
    
    public static void error(String title, String msg){
    }
    
    public static void error(String title, String msg, String id){
    }

    public static void errorId(String msg, String id){
    }
    
    public static void error(String msg){
    }    

}