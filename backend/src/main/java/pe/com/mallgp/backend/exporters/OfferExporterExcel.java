package pe.com.mallgp.backend.exporters;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.com.mallgp.backend.entities.Offer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OfferExporterExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Offer>offers;

    public OfferExporterExcel(List<Offer>offers){
        this.offers=offers;
        workbook=new XSSFWorkbook();
    }

    public void createCell(Row row, int column, Object value, CellStyle style){
        sheet.autoSizeColumn(column);
        Cell cell=row.createCell(column);

        if (value instanceof Integer){
            cell.setCellValue((Integer)value);
        }else if(value instanceof Double){
            cell.setCellValue((Double) value);
        }else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }else if(value instanceof Long){
            cell.setCellValue((Long) value);
        }else{
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    public void writeHeaderLine(){
        Row row=sheet.createRow(0);
        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        int colCount=0;

        createCell(row, colCount,"Id", style);
        createCell(row, colCount+1,"Name", style);
        createCell(row, colCount+2,"Name Product", style);
        createCell(row, colCount+3,"Gender Product", style);
        createCell(row, colCount+4,"Price S desc", style);
        createCell(row, colCount+5,"Price c desc", style);
        createCell(row, colCount+6,"Date On", style);
        createCell(row, colCount+7,"Date Off", style);
        createCell(row, colCount+8,"Image", style);
        createCell(row, colCount+9,"Store", style);
        createCell(row, colCount+10,"Product", style);
    }

    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);

        for(Offer offer:offers){
            Row row = sheet.createRow(rowCount);
            int colCount=0;

            createCell(row, colCount,offer.getId(), style);
            createCell(row, colCount+1,offer.getName(), style);
            createCell(row, colCount+2,offer.getName_product(), style);
            createCell(row, colCount+3,offer.getGender_product(), style);
            createCell(row, colCount+4,offer.getPrice_s_desc(), style);
            createCell(row, colCount+5,offer.getPrice_c_desc(), style);
            createCell(row, colCount+6,offer.getDate_on(), style);
            createCell(row, colCount+7,offer.getDate_of(), style);
            createCell(row, colCount+8,offer.getImg(), style);
            createCell(row, colCount+9,offer.getStore(), style);
            createCell(row, colCount+10,offer.getProduct(), style);
            rowCount++;
        }

    }

    public void writeFooterLine(){

    }

    public void export(HttpServletResponse response)throws IOException {
        sheet=workbook.createSheet("Reporte_Offers");

        writeHeaderLine();
        writeDataLines();
        writeFooterLine();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
