package mx.edu.utez.JuventudxTemixco.service.Donation;

import jakarta.mail.internet.MimeMessage;
import mx.edu.utez.JuventudxTemixco.models.donations.BeanDonation;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarCorreoAgradecimiento(BeanDonation donation) throws Exception {

        MimeMessage mensaje = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setTo(donation.getCorreo());

        helper.setSubject("¡Gracias por tu donación!");

        helper.setText(generarHtml(donation), true);

        mailSender.send(mensaje);
    }

    private String generarHtml(BeanDonation donation){

        return """
                <!DOCTYPE html>
                <html>
                <body style="margin:0;background:#f4f4f4;font-family:Arial,sans-serif;">

                    <table width="100%%" cellpadding="0" cellspacing="0" style="padding:30px;">
                        <tr>
                            <td align="center">

                                <table width="600" cellpadding="0" cellspacing="0"
                                       style="background:white;border-radius:10px;overflow:hidden;">

                                    <tr style="background:#4A0042;">
                                        <td style="padding:25px;color:white;text-align:center;">
                                            <h1>¡Gracias por tu donación!</h1>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="padding:30px;">

                                            <p>Hola <strong style="color:#8E0073;">%s</strong>.</p>

                                            <p>
                                                Queremos agradecerte por apoyar a
                                                <strong style="color:#8E0073;">Juventud por Temixco A.C.</strong>
                                            </p>

                                            <p>
                                                Tu donación nos ayuda a seguir realizando
                                                programas y actividades en beneficio
                                                de la comunidad. 💜
                                            </p>

                                            <hr>

                                            <h3 style="color:#4A0042;">Resumen de tu donación</h3>

                                            <table cellpadding="8">

                                                <tr>
                                                    <td><strong style="color:#4A0042;">Nombre:</strong></td>
                                                    <td>%s %s %s</td>
                                                </tr>

                                                <tr>
                                                    <td><strong style="color:#4A0042;">Correo:</strong></td>
                                                    <td>%s</td>
                                                </tr>

                                                <tr>
                                                    <td><strong style="color:#4A0042;">Monto:</strong></td>
                                                    <td>$%s MXN</td>
                                                </tr>

                                                <tr>
                                                    <td><strong style="color:#4A0042;">ID Orden:</strong></td>
                                                    <td>%s</td>
                                                </tr>

                                                <tr>
                                                    <td><strong style="color:#4A0042;">ID Captura:</strong></td>
                                                    <td>%s</td>
                                                </tr>

                                            </table>

                                            <hr>

                                            <p>
                                                Gracias por confiar en nosotros. 💜
                                            </p>

                                            <br>

                                            <p>
                                                <strong style="color:#8E0073;">Juventud por Temixco A.C.</strong>
                                            </p>

                                        </td>
                                    </tr>

                                </table>

                            </td>
                        </tr>
                    </table>

                </body>
                </html>
                """
                .formatted(
                        donation.getNombre(),
                        donation.getNombre(),
                        donation.getApellidoP(),
                        donation.getApellidoM(),
                        donation.getCorreo(),
                        donation.getMonto(),
                        donation.getPaypal_order_id(),
                        donation.getPaypal_capture_id()
                );
    }
}