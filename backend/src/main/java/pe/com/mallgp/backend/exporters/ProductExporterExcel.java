package pe.com.mallgp.backend.exporters;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.com.mallgp.backend.entities.Offer;
import pe.com.mallgp.backend.entities.Product;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductExporterExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Product>products;

    public ProductExporterExcel(List<Product>products){
        this.products=products;
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
        createCell(row, colCount+2,"Categoria", style);
        createCell(row, colCount+3,"Descripcion", style);
        createCell(row, colCount+4,"Precio", style);
        createCell(row, colCount+5,"Genero", style);
        createCell(row, colCount+6,"Image", style);
    }

    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);

        for(Product product:products){
            Row row = sheet.createRow(rowCount);
            int colCount=0;

            createCell(row, colCount,product.getId(), style);
            createCell(row, colCount+1,product.getName(), style);
            createCell(row, colCount+2,product.getCategory(), style);
            createCell(row, colCount+3,product.getDescription(), style);
            createCell(row, colCount+4,product.getPrice(), style);
            createCell(row, colCount+5,product.getGender(), style);
            createCell(row, colCount+6,product.getImg(), style);
            rowCount++;
        }
    }

    public void writeFooterLine(){

    }

    public void export(HttpServletResponse response)throws IOException {
        sheet=workbook.createSheet("Reporte_Productos");

        writeHeaderLine();
        writeDataLines();
        writeFooterLine();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }

}
