/*----------------------------------------------------------
* FILE: JasperService.java
* PRODUCT: bila-framework
*----------------------------------------------------------
*/
package com.bila.framework.reports;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * <b>Created by </b>WJuarez
 * <b>for </b>bila-framework
 * <b>package </b>com.bila.framework.reports
 * <b>on </b> 09-19-2017 02:17:11 PM
 * <b>Purpose</b> 
 * <p>
 *        Servicio que ayuda la generacion de reporte por medio de JasperReports
 * </p>
 */
public class JasperService {
    
    /**
     * Genera un objeto de tipo JasperPrint tomando como data source
     * un objeto de tipo List, utiliza el JRBeanCollectionDataSource
     * @param reportStream objeto de tipo InputStream con los datos binarios del objeto jasper
     * @param param objeto de tipo Map con los parametros del reporte
     * @param data objeto de tipo List con los datos que tendra el reporte
     * @return objeto de tipo JasperPrint
     * @throws Exception 
     */
    private static JasperPrint generateJasperPrintFromCollection(InputStream reportStream, Map param, List<?> data) throws Exception{
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, param, ds);        
        return jasperPrint;
    }

    /**
     * Genera en PDF un reporte hecho con JasperReports
     * @param reportStream objeto de tipo InputStream con los datos binarios del objeto jasper
     * @param param objeto de tipo Map con los parametros del reporte
     * @param data objeto de tipo List con los datos que tendra el reporte
     * @param response objeto de tipo HttpServletResponse para la generacion y presentacion del PDF
     * @param fileName objeto de tipo String con el nombre que tendra el PDF
     * @throws Exception 
     */
    public static void generatePDF(InputStream reportStream, Map param, List<?> data, HttpServletResponse response, String fileName) throws Exception{
        if (reportStream == null || data == null || response == null) return;
        
        JasperPrint jasperPrint = generateJasperPrintFromCollection(reportStream, param, data);
        
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;filename=".concat(fileName).concat(".pdf"));
        
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(pdfReportStream.toByteArray());
        responseOutputStream.flush();
        responseOutputStream.close();
        
        pdfReportStream.close();
    }

    /**
     * Genera en XLS un reporte hecho con JasperReports
     * @param reportStream objeto de tipo InputStream con los datos binarios del objeto jasper
     * @param param objeto de tipo Map con los parametros del reporte
     * @param data objeto de tipo List con los datos que tendra el reporte
     * @param response objeto de tipo HttpServletResponse para la generacion y presentacion del PDF
     * @param fileName objeto de tipo String con el nombre que tendra el PDF
     * @throws Exception 
     */    
    public static void generateXls(InputStream reportStream, Map param, List<?> data, HttpServletResponse response, String fileName) throws Exception{
        if (reportStream == null || data == null || response == null) return;
        
        JasperPrint jasperPrint = generateJasperPrintFromCollection(reportStream, param, data);
        
        JRXlsExporter xlsExporter = new JRXlsExporter();
        SimpleXlsReportConfiguration repConfig = new SimpleXlsReportConfiguration();
        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName.concat(".xls")));
        repConfig.setDetectCellType(Boolean.TRUE);
        xlsExporter.setConfiguration(repConfig);
        xlsExporter.exportReport();
        
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(fileName).concat(".xls"));
    }
    
    /**
     * Genera en XLSX un reporte hecho con JasperReports
     * @param reportStream objeto de tipo InputStream con los datos binarios del objeto jasper
     * @param param objeto de tipo Map con los parametros del reporte
     * @param data objeto de tipo List con los datos que tendra el reporte
     * @param response objeto de tipo HttpServletResponse para la generacion y presentacion del PDF
     * @param fileName objeto de tipo String con el nombre que tendra el PDF
     * @throws Exception 
     */
    public static void generateXlsx(InputStream reportStream, Map param, List<?> data, HttpServletResponse response, String fileName) throws Exception{
        if (reportStream == null || data == null || response == null) return;
        
        JasperPrint jasperPrint = generateJasperPrintFromCollection(reportStream, param, data);
        
        JRXlsxExporter xlsExporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration repConfig = new SimpleXlsxReportConfiguration();
        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName.concat(".xls")));
        repConfig.setDetectCellType(Boolean.TRUE);
        xlsExporter.setConfiguration(repConfig);
        xlsExporter.exportReport();
        
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(fileName).concat(".xlsx"));
    }
    
}