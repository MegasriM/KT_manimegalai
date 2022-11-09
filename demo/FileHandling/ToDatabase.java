import FileHandling.DataBaseConnection;
import FileHandling.StudentDTO;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Iterator;

public class ToDatabase
{
    public static ToDatabase getInstance= new ToDatabase();
   public static   StudentDTO studentDTO=new StudentDTO();
    XSSFRow row;
    public static void main(String[] args) {
        try {
            File file = new File("E:\\excel\\StudentSpreadSheet.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                            System.out.print(cell.getStringCellValue() + "\t\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                            System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            break;
                        default:
                    }
                }

                System.out.println("");
                studentDTO.setName(row.getCell(0).getStringCellValue());
                studentDTO.setStream(row.getCell(1).getStringCellValue());
                studentDTO.setHeight(row.getCell(2).getStringCellValue());
                getInstance.InsertRowInDB(studentDTO.getName(), studentDTO.getStream(), studentDTO.getHeight());

            }
            System.out.println("Added to db successfully");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void InsertRowInDB(String name, String age, String height)
    {

        DataBaseConnection dataBaseConnection=new DataBaseConnection();
        try {
            Connection con;
            con = dataBaseConnection.getConnection();
            Statement stmt;
            PreparedStatement pstmt;
            pstmt = con.prepareStatement("insert into Student_filedb(student_name,stream,height) values(?,?,?);");

            pstmt.setString(1, studentDTO.getName());
            pstmt.setString(2, studentDTO.getStream());
            pstmt.setString(3, studentDTO.getHeight());

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
