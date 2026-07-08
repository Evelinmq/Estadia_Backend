package mx.edu.utez.JuventudxTemixco.service.CredencializacionService;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Service
public class Credencialización {

    @Autowired
    private DataSource dataSource;

    public byte[] generarReporteSQL(
            String nombreReporte,
            Map<String, Object> parametros) throws Exception {

        InputStream reporteStream = getClass()
                .getResourceAsStream("/credenciales/" + nombreReporte + ".jrxml");

        JasperReport reporte =
                JasperCompileManager.compileReport(reporteStream);

        try (Connection conexion = dataSource.getConnection()) {

            JasperPrint print = JasperFillManager.fillReport(
                    reporte,
                    parametros,
                    conexion);

            return JasperExportManager.exportReportToPdf(print);
        }
    }


}