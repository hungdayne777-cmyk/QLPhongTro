/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.io.InputStream;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author MSI
 */
public class InHoaDon {
   public static void inHoaDon(String maHoaDon) {
        try {
            // 1. Đọc file .jrxml từ resources
            InputStream jrxmlStream = InHoaDon.class.getResourceAsStream("/HoaDonPhongTro.jrxml");

            if (jrxmlStream == null) {
                JOptionPane.showMessageDialog(null, 
                    "Không tìm thấy file 'HoaDonPhongTro.jrxml' trong resources!", 
                    "Lỗi file mẫu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Biên dịch trực tiếp file .jrxml
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // 3. Truyền tham số
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("MaHoaDon", maHoaDon);

            // 4. Nạp dữ liệu và xuất báo cáo
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getConnection());
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("In Hóa Đơn - " + maHoaDon);
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi in hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
