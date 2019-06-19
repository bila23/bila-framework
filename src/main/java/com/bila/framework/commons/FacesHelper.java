/*----------------------------------------------------------
 * FILE: FacesHelper.java
 * PRODUCT: bila-framework
 *----------------------------------------------------------
 */
package com.bila.framework.commons;

import java.security.Principal;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.commons
 * <b>on </b> 4/04/2016 03:27:23 PM
 * <b>Purpose</b>
 * <p>
 *      Clase que posee metodos comunes para el desarrollo de JSF
 * </p>
 */
public class FacesHelper {

    private static ResourceBundle rb;
    
    /**
     * Presenta un mensaje de error al usuario
     * @param title titulo del mensaje
     * @param msg texto del mensaje
     */
    public static void errorMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * Presenta un mensaje de error al usuario definiendo el componente donde debe mostrarse
     * @param title titulo del mensaje
     * @param msg texto del mensaje
     * @param id id del componente donde debe mostrarse el mensaje
     */
    public static void errorMessage(String title, String msg, String id) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(id, facesMsg);
    }
    
    /**
     * Presenta un mensaje de exito al usuario
     * @param title titulo del mensaje
     * @param msg texto del mensaje
     */
    public static void successMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * Presenta un mensaje de exito al usuario definiendo el componente donde debe mostrarse
     * @param title titulo del mensaje
     * @param msg texto del mensaje
     * @param id id del componente donde debe mostrarse el mensaje
     */
    public static void successMessage(String title, String msg, String id) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage(id, facesMsg);
    }
    
    /**
     * Presenta un mensaje de advertencia al usuario
     * @param title titulo del mensaje
     * @param msg texto del mensaje
     */
    public static void warningMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    /**
     * Presenta un mensaje de advertencia al usuario definiendo el componente donde debe mostrarse
     * @param title titulo del mensaje
     * @param msg texto del mensaje
     * @param id id del componente donde debe mostrarse el mensaje
     */
    public static void warningMessage(String title, String msg, String id) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, title, msg);
        FacesContext.getCurrentInstance().addMessage(id, facesMsg);
    }
    
    /**
     * Recupero un mensaje del archivo bundle por medio de su llave
     * @param bundle paquete y nombre del archivo bundle
     * @param key llave que deseo recuperar su valor
     * @return objeto de tipo String con el valor de deseado
     */
    public static String getResourceMessage(String bundle, String key){
        if(key == null) return "";
        if(key.isEmpty()) return "";
        if(rb == null){
            Locale locale = FacesContext.getCurrentInstance() .getViewRoot().getLocale();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            rb = ResourceBundle.getBundle(bundle, locale, loader);
        }
        return rb.getString(key);
    }
    
    /**
     * Muestra un mensaje de exito por medio de mensajes que estan ubicados en archivo properties
     * @param bundle nombre de la ubicacion del archivo properties
     * @param title titulo del mensaje
     * @param msg llave del mensaje que esta ubicado en el archivo properties
     */
    public static void success(String bundle, String title, String msg){
        successMessage(getResourceMessage(bundle, title), getResourceMessage(bundle, msg));
    }

    /**
     * Muestra un mensaje de exito para presentarlo en un componente especifico.Los mensajes los recupera de un archivo properties
     * @param bundle nombre de la ubicacion del archivo properties
     * @param title titulo del mensaje
     * @param msg llave del mensaje que esta ubicado en el archivo properties
     * @param id id del componente donde se debe mostrar el mensaje
     */
    public static void success(String bundle, String title, String msg, String id){
        successMessage(getResourceMessage(bundle, title), getResourceMessage(bundle, msg), id);
    }

    /**
     * Muestra un mensaje de advertencia por medio de mensajes que estan ubicados en archivo properties
     * @param bundle nombre de la ubicacion del archivo properties
     * @param title titulo del mensaje
     * @param msg llave del mensaje que esta ubicado en el archivo properties
     */
    public static void warning(String bundle, String title, String msg){
        warningMessage(getResourceMessage(bundle, title), getResourceMessage(bundle, msg));
    }

    /**
     * Muestra un mensaje de advertencia para presentarlo en un componente especifico.Los mensajes los recupera de un archivo properties
     * @param bundle nombre de la ubicacion del archivo properties
     * @param title titulo del mensaje
     * @param msg llave del mensaje que esta ubicado en el archivo properties
     * @param id id del componente donde se debe mostrar el mensaje
     */
    public static void warning(String bundle, String title, String msg, String id){
        warningMessage(getResourceMessage(bundle, title), getResourceMessage(bundle, msg), id);
    }

    /**
     * Muestra un mensaje de error por medio de mensajes que estan ubicados en archivo properties
     * @param bundle nombre de la ubicacion del archivo properties
     * @param title titulo del mensaje
     * @param msg llave del mensaje que esta ubicado en el archivo properties
     */
    public static void error(String bundle, String title, String msg){
        errorMessage(getResourceMessage(bundle, title), getResourceMessage(bundle, msg));
    }
    
    /**
     * Muestra un mensaje de error para presentarlo en un componente especifico.Los mensajes los recupera de un archivo properties
     * @param bundle nombre de la ubicacion del archivo properties
     * @param title titulo del mensaje
     * @param msg llave del mensaje que esta ubicado en el archivo properties
     * @param id id del componente donde se debe mostrar el mensaje
     */
    public static void error(String bundle, String title, String msg, String id){
        errorMessage(getResourceMessage(bundle, title), getResourceMessage(bundle, msg), id);
    }
    
    /**
     * Recupero el nombre del usuario que ha iniciado sesion en el sistema
     * @return  objeto de tipo String con el nombre del usuario
     */
    public static String getUserLogin(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        if(principal == null) return null;
        return principal.getName().toUpperCase();
    }
    
    /**
     * Recupero el objeto Servlet Request de la instancia actual del FacesContext
     * @return objeto de tipo HttpServletRequest
     */
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    /**
     * Recupero el objeto Servlet Response de la instancia actual del FacesContext
     * @return objeto de tipo HttpServletResponse
     */
    public static HttpServletResponse getResponse(){
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
    
    /**
     * Recupero un parametro que se envia por Request por medio de su nombre
     * @param name objeto de tipo String con el nombre del parametro que quiero recuperar
     * @return objeto de tipo String con el valor del parametro
     */
    public static String getParameter(String name){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
    
}
