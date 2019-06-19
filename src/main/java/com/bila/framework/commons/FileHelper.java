/*----------------------------------------------------------
* FILE: FileHelper.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.commons
 * <b>on </b> 29/03/2016 03:12:31 PM
 * <b>Purpose</b> 
 * <p>
 *      Facilita el manejo y gestión de archivos
 * </p>
 */
public class FileHelper {

    /**
     * Verifica si el archivo existe
     * @param path ruta del archivo
     * @param fileName nombre del archivo
     * @return true | false
     * @throws Exception 
     */
    public boolean existFile(String path, String fileName) throws Exception {
        File file = new File(path + fileName);
        if (file.exists())
            return true;
        return false;
    }
    
    /**
     * Copia el contenido de un archivo a una nueva ubicacion
     * @param in objeto de tipo InputStream con el contenido del archivo
     * @param path ruta donde se ubicara el archivo
     * @param fileName nombre del archivo
     * @throws Exception 
     */
    public static void copyFile(InputStream in, String path, String fileName) throws Exception {
        int fbyte;
        byte[] byteArray = new byte[1024];
        FileOutputStream out = null;
        try {
             out = new FileOutputStream(new File( path.concat(fileName) ));
            while (true) {
                fbyte = in.read(byteArray);
                if (fbyte < 0) {
                    break;
                }
                out.write(byteArray, 0, fbyte);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            throw e;
        }finally{
            in.close();
            if(out != null)
                out.close();
        }
    }
    
    /**
     * Elimina un archivo por su ubicacion y nombre
     * @param path ubicacion del archivo
     * @param fileName nombre del archivo
     * @throws Exception 
     */
    public static void deleteFile(String path, String fileName) throws Exception {
        File file = new File(path.concat(fileName));
        if(file.exists()){
            if(file.isDirectory())
                deleteFolder(file);
            else
                file.delete();            
        }
    }
    
    /**
     * Elimina una carpeta y todo su contenido
     * @param file archivo con la ubicación de la carpeta
     * @throws Exception
     */
    public static void deleteFolder(File file) throws Exception{
        if(!file.exists()) return;
        if(file.isDirectory()){
            for(File f : file.listFiles())
                deleteFolder(f);
        }
        file.delete();
    }
    
    /**
     * Descomprime un archivo de extensión zip
     * @param fileName ruta y nombre del archivo
     * @throws ZipException
     * @throws IOException 
     */
    public static void unzipFile(String fileName) throws Exception {
        File file = new File(fileName);
        ZipFile zip = new ZipFile(file);
        Enumeration e = zip.entries();
        ZipEntry zen = null;
        int size = 0;
        InputStream zis = null;
        try{
            while (e.hasMoreElements()) {
                zen = (ZipEntry) e.nextElement();
                if (zen.isDirectory()) {
                    continue;
                }
                size = (int) zen.getSize();
                zis = zip.getInputStream(zen);
                writeFile(zis, new File(file.getParentFile().getAbsolutePath().concat(File.separator).concat(zen.getName())), size);
                zis.close();
            }
        }finally{
            if(zip != null)
                zip.close();
        }
    }

    private static void writeFile(InputStream zis, File file, int size) throws Exception {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();// this is important
        }
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
            byte[] byteStream = new byte[size];
            int buf = -1;
            int rb = 0;
            while (((size - rb) > 0)) {
                buf = zis.read(byteStream, rb, size - rb);

                if (buf == -1) {
                    break;
                }
                rb += buf;
            }
            fos.write(byteStream);
        }finally {
            if(fos != null)
                fos.close();
        }
    }
    
    /**
     * Verifico si la ruta es un directorio
     * @param path ruta donde esta ubicado
     * @param nameFile nombre del directorio
     * @return true | false
     * @throws Exception 
     */
    public boolean isADirectory(String path, String nameFile) throws Exception {
        File file = null;
        file = new File(path + nameFile);
        return file.isDirectory();
    }
    
    /**
     * Funcion para copiar el contenido de una carpeta a otra
     * @param src objeto de tipo File con la carpeta origen
     * @param dest objeto de tipo File con la carpeta destino
     * @throws Exception 
     */
    public void copyFolder(File src, File dest) throws Exception {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }

            String files[] = src.list();
            
            File srcFile = null;
            File destFile = null;
            for (String file : files) {
                srcFile = new File(src, file);
                destFile = new File(dest, file);
                copyFolder(srcFile, destFile);
            }

        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }
    
    /**
     * Copio un archivo a otro destino o carpeta
     * @param source objeto de tipo String con la ruta del archivo original
     * @param dest objeto de tipo String con la nueva ruta
     * @param delete variable tipo boolean que define si se elimina o no el archivo original
     * @throws Exception 
     */
    public static void copyFile(String source, String dest, boolean delete) throws Exception{
        File fsource = new File(source);
        if(fsource.exists()){
            File fdest = new File(dest);
            if(fdest.exists())
                fdest.delete();
            boolean status = fsource.renameTo(fdest);
            if(!status)
                throw new Exception();
            else
                if(delete)
                    fsource.delete();
        }
    }
    
}
