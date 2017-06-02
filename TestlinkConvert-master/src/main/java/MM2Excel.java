
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MM2Excel {

    /**
     * FreeMind exchange to excel
     * .mm to .xls
     */

    public static void mm2excel(String filename) throws Exception {
        File freeMindFile = new File(filename);

        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(freeMindFile);// 读取.mm文件
        Element root = doc.getRootElement();

        String testCase = "";// 未读之前为空
        List<String> testCaseList = new ArrayList<String>();
        parseNode(testCaseList, testCase, root);

        File excelFile = new File(freeMindFile.getParentFile(),
                FilenameUtils.getBaseName(freeMindFile.getName()) + ".xls");
        writeTestCase2Excel(excelFile, testCaseList);

    }

    // 读取xml文件
    private static void parseNode(List<String> testCaseList, String testCase,
                                  Element node) {
        List<Element> subNodes = node.elements("node");
        for (Element subNode : subNodes) {
            String text = subNode.attributeValue("TEXT");//读取Text的值
            if (subNode.elements("node").size() > 0) {
                parseNode(testCaseList, testCase + "/" + text, subNode);//碰到一个node在testCase中添加它的值
            } else {
                testCaseList.add(StringUtils.substringAfter(testCase + "/"
                        + text, "/"));
            }
        }
    }

    private static String[] titles = { "模块","子模块", "测试名称", "摘要","用例等级","前提条件", "步骤编号", "步骤动作",
            "预期结果"};

    private static void writeTestCase2Excel(File excelFile,
                                            List<String> testCaseList) throws Exception {
        WritableWorkbook wwb = Workbook.createWorkbook(excelFile);
        WritableSheet ws = wwb.createSheet("Testcase", 0);

        for (int i = 0; i < titles.length; i++) {
            ws.addCell(new Label(i, 0, titles[i]));
        }

        for (int i = 0; i < testCaseList.size(); i++) {
            String testCase = testCaseList.get(i);
            String moduleName = StringUtils.substringBeforeLast(testCase, "/");
            ws.addCell(new Label(0, i + 1, StringUtils.substringBeforeLast(
                    moduleName, "/")));// 模块
            ws.addCell(new Label(1, i + 1, StringUtils.substringAfterLast(
                    moduleName, "/")));// 子模块
            ws.addCell(new Label(2, i + 1, StringUtils.substringAfterLast(
                    testCase, "/")));// 测试名称
            ws.addCell(new Label(3, i + 1, " "));// 摘要
            ws.addCell(new Label(4, i + 1, "2"));// 用例等级
            ws.addCell(new Label(5, i + 1, " "));// 前提条件
            ws.addCell(new Label(6, i + 1, " "));// 步骤编号
            ws.addCell(new Label(7, i + 1, " "));// 步骤动作
            ws.addCell(new Label(8, i + 1, " "));// 预期结果

        }

        wwb.write();
        wwb.close();
    }

}
