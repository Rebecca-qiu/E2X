/**
 * Created by catty on 17/3/21.
 */
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
public class GuiPage {
    private JFrame frmMm;
    private JTextField textField;
    /**
     * GUI page.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiPage window = new GuiPage();
                    window.frmMm.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the application.
     */
    public GuiPage() {
        initialize();
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmMm = new JFrame();
        frmMm.setTitle("Testlink用例导入转换器");
        frmMm.setBounds(100, 100, 400, 150);
        frmMm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMm.getContentPane().setLayout(null);
        final JButton m2eButton = new JButton("mm to excel");
        m2eButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String filename = textField.getText();
                if(StringUtils.isBlank(filename)||!filename.endsWith(".mm")){
                    JOptionPane.showMessageDialog(frmMm, "请选择Freemind文件！");
                    return;
                }

                try {
                    MM2Excel.mm2excel(filename);
                    JOptionPane.showMessageDialog(frmMm, "文件转换成功！请到原文件目录下查看");
                    textField.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frmMm, "文件转换失败：" + e.getMessage());
                }
            }
        });
        m2eButton.setBounds(74, 80, 117, 29);
        frmMm.getContentPane().add(m2eButton);
        textField = new JTextField();
        textField.setBounds(110, 36, 194, 25);
        frmMm.getContentPane().add(textField);
        textField.setColumns(10);
        JLabel lblNewLabel = new JLabel("源文件：");
        lblNewLabel.setBounds(17, 37, 105, 25);
        frmMm.getContentPane().add(lblNewLabel);
        final JButton e2xButton = new JButton("excel to xml");
        e2xButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String filename = textField.getText();
                if(StringUtils.isBlank(filename)||!filename.endsWith(".xls")) {
                    JOptionPane.showMessageDialog(frmMm, "请选择Excel文件！");
                    return;
                }

                try {
                   // ExcelToXml.transferExcelToXml(filename);
                    ExcelToXml.Excel2XMl(filename);
                    JOptionPane.showMessageDialog(frmMm, "文件转换成功！请到原文件目录下查看");
                    textField.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frmMm, "文件转换失败：" + e.getMessage());
                }
            }
        });
        e2xButton.setBounds(245, 80, 117, 29);
        frmMm.getContentPane().add(e2xButton);

        JButton button = new JButton("选择");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FileDialog fd = new FileDialog(frmMm);
                fd.setFilenameFilter(new FilenameFilter() {
                    @Override
                    public boolean accept(File paramFile, String paramString) {
                        return paramString.endsWith(".mm")||paramString.endsWith(".xls");
                    }
                });
                fd.setVisible(true);
                if (fd.getFile() != null) {
                    textField.setText(fd.getDirectory() + fd.getFile());
                    m2eButton.setEnabled(fd.getFile().endsWith(".mm"));
                    e2xButton.setEnabled(fd.getFile().endsWith(".xls"));
                }
            }
        });
        button.setBounds(302, 38, 81, 25);
        frmMm.getContentPane().add(button);
    }
}
