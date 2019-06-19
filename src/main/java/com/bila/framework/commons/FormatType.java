/*----------------------------------------------------------
* FILE: FormatType.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.commons
 * <b>on </b> 3/12/2015 11:46:58 AM
 * <b>Purpose</b> 
 * <p>
 *  Clase que posee metodos para conversion de datos
 * </p>
 */
public class FormatType {

    /**
     * Convierte un objeto de tipo String a Date con el formato DD/MM/YYYY
     * @param date objeto String con la fecha que se desea convertir
     * @return objeto de tipo Date
     * @throws Exception 
     */
    public static Date stringToDate(String date) throws Exception {
        if(date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(GeneralConstants.DATE_FORMAT);
        Date newDate = sdf.parse(date);
        return newDate;
    }
    
    /**
     * Convierte un objeto de tipo Date a String en formato DD/MM/YYYY
     * @param date objeto Date con la fecha que se desea convertir
     * @return objeto de tipo String
     * @throws Exception 
     */
    public static String dateToString(Date date)  throws Exception{
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(GeneralConstants.DATE_FORMAT);
        String fecha = sdf.format(date);
        return fecha;
    }
    
    /**
     * Convierte un objeto de tipo Date a String en formato DD/MM/YYYY hh:mm:ss
     * @param date objeto de tipo Date con la fecha que se desea convertir
     * @return objeto de tipo String
     * @throws Exception 
     */
    public static String datetoStringComplete(Date date) throws Exception {
        if(date == null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(GeneralConstants.DATE_FORMAT_COMPLETE);
        String now = formatter.format(date);
        return now;
    }

    /**
     * Convierte un objeto de tipo String a Date en formato DD/MM/YYYY hh:mm:ss
     * @param date objeto de tipo String con la fecha que se desea convertir
     * @return objeto de tipo Date
     * @throws Exception 
     */
    public static Date stringToDateComplete(String date) throws Exception {
        if(date == null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(GeneralConstants.DATE_FORMAT_COMPLETE);
        Date now = formatter.parse(date);
        return now;
    }
    
}
