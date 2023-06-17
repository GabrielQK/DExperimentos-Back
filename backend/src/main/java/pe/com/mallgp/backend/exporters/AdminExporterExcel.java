package pe.com.mallgp.backend.exporters;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.com.mallgp.backend.entities.Admin;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AdminExporterExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Admin>admins;

    public AdminExporterExcel(List<Admin> admins){
        this.admins=admins;
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
        createCell(row, colCount,"Id",style);
        createCell(row, colCount+1,"Name",style);
        createCell(row, colCount+2,"Password",style);
    }

    public void writeDataLines(){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);

        for(Admin admin:admins){
            Row row = sheet.createRow(rowCount);
            int colCount=0;
            createCell(row,colCount,admin.getId(),style);
            createCell(row,colCount+1,admin.getName(),style);
            createCell(row,colCount+2,admin.getPassword(),style);
            rowCount++;
        }
    }

    public void writeFooterLine(){

    }

    public void export(HttpServletResponse response)throws IOException {
        sheet=workbook.createSheet("Reporte_Admin");

        writeHeaderLine();
        writeDataLines();
        writeFooterLine();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
