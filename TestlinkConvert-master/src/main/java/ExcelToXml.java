/**
 * Created by catty on 17/3/21.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class ExcelToXml {

 public static void Excel2XMl(String oldFileName) throws Exception {

     Testsuite ts = new Testsuite();
     FileInputStream in = null;
     HSSFWorkbook wb = null;
     Testcase tc = null;
     List<Testcase> list = new ArrayList<Testcase>();
     List<Step> steplist = null;
     String TEST_WORKBOOK_NAME = oldFileName;
     long time = System.currentTimeMillis();
     String newfilename = getXmlName(oldFileName, time);

     try {
         in = new FileInputStream(TEST_WORKBOOK_NAME);
         POIFSFileSystem fs = new POIFSFileSystem(in);
         wb = new HSSFWorkbook(fs);
     } catch (IOException e) {
         System.out.println(e.toString());
     } finally {
         try {
             in.close();
         } catch (IOException e) {
             //  System.out.println(e.toString());
         }
     }
     HSSFSheet sheet = wb.getSheet("Testcase");
     int rowNum = sheet.getLastRowNum();
     HSSFRow row = sheet.getRow(1);
     HSSFCell module = row.getCell(0);
     for (int i = 1; i <= rowNum; i++) {
         row = sheet.getRow(i);
         HSSFCell testcaseName = row.getCell(1);
         HSSFCell summary = row.getCell(2);

         HSSFCell importance = row.getCell(3);
         HSSFCell precondtion = row.getCell(4);
         Step step = new Step();
         HSSFCell stepnum = row.getCell(5);
         HSSFCell action = row.getCell(6);
         HSSFCell expectedResult = row.getCell(7);

         if(testcaseName ==null || getValue(testcaseName).equals(""))
         {
             Step rs1 = new Step();
             step.setStep_number( getValue(stepnum));
             step.setActions(getValue(action));
             step.setExpectedresults(getValue(expectedResult));
             step.setExecution_type("");
             steplist.add(step);
         }
         else
         {
             tc = new Testcase();
             tc.setNode_order("");
             tc.setName(getValue(testcaseName));
             tc.setSummary(getValue(summary));
             tc.setExternalid("");
             tc.setInternalid("");
             tc.setVersion("");
             tc.setPreconditions(getValue(precondtion));
             tc.setExecution_type("");
             tc.setImportance(getValue(importance));
             steplist = new ArrayList<Step>();
             step.setStep_number(getValue(stepnum));
             step.setActions(getValue(action));
             step.setExpectedresults(getValue(expectedResult));
             step.setExecution_type("");
             steplist.add(step);
             tc.setSteps(steplist);
             list.add(tc);
         }

     }

     ts.setDetails("");
     ts.setName(getValue(module));
     ts.setNode_order("");
     ts.setCaseList(list);

     Serializer serializer = new Persister();
     File result = new File(newfilename);
     serializer.write(ts,result);

 }
    public static String getValue(HSSFCell hssfCell) {
        if (hssfCell ==null)
        {
            return  "";
        }else{
            switch(hssfCell.getCellType()){
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    return hssfCell.getBooleanCellValue() ? "TRUE":"FALSE";
                case HSSFCell.CELL_TYPE_FORMULA:
                    return hssfCell.getCellFormula();
                case HSSFCell.CELL_TYPE_NUMERIC:
                    return String.valueOf(hssfCell.getNumericCellValue());
                case HSSFCell.CELL_TYPE_STRING:
                    return hssfCell.getStringCellValue();
                default:
                    return "";
            }
        }
    }

    private static String getXmlName(String oldfilename, long time) {
        File oldFile = new File(oldfilename);
        String name = FilenameUtils.getBaseName(oldFile.getName());
        name = name.replaceAll("[0-9]*", "");
        String newFileName = null;
        if (name.endsWith("_"))
            newFileName = "TestCase_" + name + time + ".xml";
        else {
            newFileName = "TestCase_" + name + "_" + time + ".xml";
        }
        return new File(oldFile.getParentFile(), newFileName).getPath();
    }


}