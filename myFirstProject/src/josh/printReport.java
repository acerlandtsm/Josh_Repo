package josh;

import java.sql.Connection;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.DBConnection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class printReport {

	public static void printJReport(String reportPath, Map<String, Object> parameters) {
		try {
			Connection conn = DBConnection.getConnection();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setTitle("sample");
			viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
			viewer.setLocationRelativeTo(null);
			viewer.setVisible(true);
            
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error printing report: " + e.getMessage());
		}
	}
}
