package util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InHopDong {

    public static void inHopDong(String maHD) {
        try {
            // 1. Đọc file HopDongPhongTro.jrxml từ folder resources
            InputStream jrxmlStream = InHopDong.class.getResourceAsStream("/HopDongPhongTro.jrxml");

            if (jrxmlStream == null) {
                JOptionPane.showMessageDialog(null, 
                    "Không tìm thấy file 'HopDongPhongTro.jrxml' trong resources!", 
                    "Lỗi file mẫu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Biên dịch trực tiếp file .jrxml
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // 3. Truyền tham số MaHD khớp với SQL
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("MaHD", maHD);

            // 4. Nạp dữ liệu từ CSDL và hiển thị xem trước hợp đồng
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getConnection());
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("In Hợp Đồng - " + maHD);
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi in hợp đồng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}