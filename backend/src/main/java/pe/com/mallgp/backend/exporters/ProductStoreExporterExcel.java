package pe.com.mallgp.backend.exporters;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.com.mallgp.backend.entities.ProductStore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductStoreExporterExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<ProductStore>productStores;

    public ProductStoreExporterExcel(List<ProductStore>productStores){
        this.productStores=productStores;
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
        createCell(row, colCount+1,"Product", style);
        createCell(row, colCount+2,"Store", style);
        createCell(row, colCount+3,"Price", style);
        createCell(row, colCount+4,"Restock", style);
    }

    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);

        for(ProductStore productStore:productStores){
            Row row = sheet.createRow(rowCount);
            int colCount=0;
            createCell(row, colCount,productStore.getId(), style);
            createCell(row, colCount+1,productStore.getProduct(), style);
            createCell(row, colCount+2,productStore.getStore(), style);
            createCell(row, colCount+3,productStore.getPrice(), style);
            createCell(row, colCount+4,productStore.getRestock(), style);
            rowCount++;

        }
    }

    public void writeFooterLine(){

    }

    public void export(HttpServletResponse response)throws IOException {
        sheet=workbook.createSheet("Reporte_ProductStore");

        writeHeaderLine();
        writeDataLines();
        writeFooterLine();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
