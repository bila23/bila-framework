/*----------------------------------------------------------
 * FILE: GeneralFunction.java
 * PRODUCT: bila-framework
 *----------------------------------------------------------
 */
package com.bila.framework.commons;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.commons
 * <b>on </b> 3/12/2015 11:58:44 AM
 * <b>Purpose</b>
 * <p>
 * Metodos generales para el desarrollo de una aplicacion
 * </p>
 */
public class GeneralFunction {

    /**
     * Verifico si el objeto que paso como parametro es nulo o esta vacio
     *
     * @param obj objeto String que deseo verificar
     * @return true | false
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null || obj.toString().length() < 1 || obj.toString().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Verifico si el objeto que paso como parametro es nulo, vacio o cero
     *
     * @param obj objeto que deseo verificar
     * @return true | false
     */
    public static boolean isNullOrEmptyOrZero(Object obj) {
        if (obj == null
                || obj.toString().length() < 1
                || obj.toString().equals("")
                || ((obj instanceof Long && obj.equals(0L)) || (obj instanceof Integer && obj.equals(0)) || (obj instanceof Float && obj.equals(0.00)) || (obj instanceof Double && obj
                .equals(0.00)))) {
            return true;
        }
        return false;
    }

    /**
     * Verifico si el objeto que paso como primer parametro es nulo de ser asi
     * retorno el segundo parametro y sino el primer parametro
     *
     * @param obj objeto que deseo verificar si es nulo
     * @param val objeto que retorno en el caso que el primer parametro sea nulo
     * @return el primero o segundo parametro segun sea la condicion
     */
    public static Object nvl(Object obj, Object val) {
        if (isNullOrEmpty(obj)) {
            return val;
        }
        return obj;
    }

    /**
     * Formatea un numero en el numero de decimales que se defina en el segundo
     * parametro
     *
     * @param d numero que se desea formatear
     * @param decimalPlace numero de decimales que se desean
     * @return numero con el formato establecido
     */
    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Redondea un numero a dos decimales
     *
     * @param d numero que se desea redondear
     * @return numero con dos decimales
     */
    public static double roundTwoDecimals(double d) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Recupera la fecha actual
     *
     * @return objeto de tipo Date con la fecha actual
     */
    public Date getDate() {
        return Calendar.getInstance().getTime();
    }
    
    public static Date today(){
        return Calendar.getInstance().getTime();
    }

    /**
     * Recupera el año actual
     *
     * @return int con el año actual
     * @throws Exception
     */
    public static int getYear() throws Exception {
        Calendar cal = Calendar.getInstance();
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * Recupero la hora actual en formato 0 - 23
     *
     * @return dato primitivo int con la hora actual
     * @throws Exception
     */
    public static int getHour() throws Exception {
        Calendar cal = Calendar.getInstance();
        Integer hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * Recupera el mes actual
     *
     * @return int con el mes actual
     * @throws Exception
     */
    public static int getMonth() throws Exception {
        Calendar cal = Calendar.getInstance();
        Integer month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int getMonth(Date fecha) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (fecha == null) {
            return 0;
        }
        cal.setTime(fecha);
        Integer month = cal.get(Calendar.MONTH) + 1;
        return month;
    }


    /**
     * Recupera el mes actual en formato String
     *
     * @return Objeto String con el mes actual
     * @throws Exception
     */
    public static String getMonthString() throws Exception {
        Calendar cal = Calendar.getInstance();
        Integer month = cal.get(Calendar.MONTH) + 1;
        return month.toString();
    }

    /**
     * Recupero el dia actual
     *
     * @return int con el dia acutal
     * @throws Exception
     */
    public static int getDay() throws Exception {
        Calendar cal = Calendar.getInstance();
        Integer day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * Recupero el dia actual en formato String
     *
     * @return Objeto String con el dia acutal
     * @throws Exception
     */
    public static String getDayString() throws Exception {
        Calendar cal = Calendar.getInstance();
        Integer day = cal.get(Calendar.DAY_OF_MONTH);
        return day.toString();
    }

    /**
     * Recupero la extension del nombre de archivo que paso como parametro
     *
     * @param file nombre del archivo que quiero recuperar su extension
     * @return objeto de tipo String
     */
    public static String getExtension(String file) {
        try {
            if (file == null) {
                return null;
            }
            if (!file.contains(".")) {
                return null;
            }
            return FilenameUtils.getExtension(file);
        } catch (Exception e) {
            //Si se genera una excepcion es por que no posee extension el documento
        }
        return null;
    }

    /**
     * Me retorna el nombre del mes que paso como parametro, el parametro es un
     * string
     *
     * @param month String con el numero de mes
     * @return objeto de tipo String
     */
    public static String getShortMonth(String month) {
        int m = Integer.valueOf(month);
        switch (m) {
            case 1:
                return "Ene";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dic";
        }
        return "";
    }

    /**
     * Me retorna el nombre del mes que paso como parametro, el parametro es un
     * int
     *
     * @param month String con el numero de mes
     * @return objeto de tipo String
     */
    public static String getShortMonth(int month) {
        switch (month) {
            case 1:
                return "Ene";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dic";
        }
        return "";
    }

    private static String initcapOneWord(String param) {
        switch (param) {
            case "":
                return "";
            case " ":
                return "";
            default:
                switch (param.toLowerCase()) {
                    case "de":
                        return "de";
                    case "c.h.":
                        return param.toUpperCase();
                    case "del":
                        return "del";
                    case "y":
                        return "y";
                    case "en":
                        return "en";
                    case "el":
                        return "el";
                    case "la":
                        return "la";
                    case "e":
                        return "e";
                    case "para":
                        return "para";
                }
                char[] charArray = param.toLowerCase().toCharArray();
                charArray[0] = Character.toUpperCase(charArray[0]);
                return new String(charArray);
        }
    }

    /**
     * Convierte la primera letra del String en mayuscula
     *
     * @param text objeto de tipo String con la palabra que se desea transformar
     * @return objeto de tipo String
     */
    public static String initcap(String text) {
        if (text == null || text.length() <= 0) {
            return "";
        }
        String[] split;
        if (text.contains(" ")) {
            split = text.split(" ");
        } else {
            split = new String[1];
            split[0] = text;
        }
        StringBuilder str = new StringBuilder();
        int i = 0, size = split.length;
        for (i = 0; i < size; i++) {
            str.append(initcapOneWord(split[i])).append(" ");
        }
        return str.toString().substring(0, str.length() - 1);
    }

    /**
     * Recupero el primer dia del mes actual
     *
     * @return objeto de tipo Date con el primer dia del mes actual
     * @throws Exception
     */
    public static Date getFirsDateOfActualMonth() throws Exception {
        int year = getYear();
        int month = getMonth();
        StringBuilder day = new StringBuilder();
        day.append("01/").append(month).append("/").append(year);
        return FormatType.stringToDate(day.toString());
    }

    /**
     * Convierto una lista de entidades a una lista de objetos VO. Servira
     * unicamente para el caso que el vo posea los mismos campos de la entidad
     *
     * @param <M> objeto generic asociado a la entidad
     * @param <V> objeto generic asociado al VO
     * @param list objeto de tipo List<M> relacionado con la lista de entidades
     * @param clazz objeto de tipo Class asociado al VO
     * @return objeto de tipo List<V>
     * @throws Exception
     */
    public static <M, V> List<V> modelToVOList(List<M> list, Class<V> clazz) throws Exception {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return null;
        }
        int i = 0, size = list.size();
        M model = null;
        V vo = null;
        List<V> lst = new ArrayList();
        for (i = 0; i < size; i++) {
            model = list.get(i);
            vo = clazz.newInstance();
            BeanUtils.copyProperties(vo, model);
            lst.add(vo);
        }
        model = null;
        vo = null;
        return lst;
    }

    /**
     * Recupero el año de la fecha que paso como parametro
     *
     * @param date objeto de tipo Date
     * @return dato primitivo int con el año de la fecha que paso como parametro
     * @throws Exception
     */
    public static int getYear(Date date) throws Exception {
        if (date == null) {
            return 0;
        }
        DateFormat format = new SimpleDateFormat("yyyy");
        return Integer.valueOf(format.format(date));
    }
    
    /**
     * Genera un string aleatorio
     * @param count dato de tipo int con el tamaño del string aleatorio
     * @return objeto de tipo String con las letras mayusculas
     * @throws Exception 
     */
    public static String randomString(int count) throws Exception{
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int character = 0;
        while (count -- != 0) {
            character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Verifica si el string que se pasa como parametro es numero o no
     * @param str objeto de tipo String que se desea verificar
     * @return true | false
     */
    public static boolean isStringNumeric(String str){
        return StringUtils.isNumeric(str);
    }
    
    /**
     * Verifica si las fechas que se pasan como parametro son las mismas
     * @param d1 fecha 1
     * @param d2 fecha 2
     * @return true | false si las fechas son las mismas
     * @throws Exception 
     */
    public static boolean isSameDate(Date d1, Date d2) throws Exception{
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }
    
}
