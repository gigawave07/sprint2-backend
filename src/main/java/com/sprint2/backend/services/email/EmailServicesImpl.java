package com.sprint2.backend.services.email;

import com.sprint2.backend.entity.AppAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServicesImpl implements EmailServices {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * nguyen quoc khanh
     *
     * @param appAccount
     * @param siteURL
     * @throws UnsupportedEncodingException
     * @throws MessagingException           this function set up the content of email and send email for customer when they change their password
     */
    @Override
    public void sendVerificationEmail(AppAccount appAccount, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String subject = "Email xác nhận";
        String senderName = "C06 Parking team";
        String mailContent = "<div style=\"background-color: orange; width: 80%;color: white;height: 50px;\">";
        mailContent += " <h2 style=\"position: relative;top:10px;left: 2px\">C06 Parking team</h2>";
        mailContent += "</div>";
        mailContent += "</div>";
        mailContent += " <h1 style=\"color: #26bb84\"> Chào " + appAccount.getUsername() + "</h1>";
        mailContent += "<p style=\"font-weight: bolder\">Bạn đã yêu cầu đổi mật khẩu trên hệ thống quản lý bãi đỗ xe của C06 Parking team. Nếu\n" +
                "        bạn không có yêu cầu hoặc\n" +
                "        có sự nhầm lẫn nào thì bạn có thể bỏ qua email này.</p>";
        mailContent += "  <p style=\"color: #03A9F4\"> Sử dụng mã phía dưới để xác nhận đổi mật khẩu</p>";
        mailContent += "<div  style=\"color: white; background-color: blue;width: 20%;height: 35px; font-weight: bolder;text-align: center\">";
        mailContent += " <p style=\"position: relative;top: 5px;font-size: 20px\">" + appAccount.getVerificationCode() + "</p>";
        mailContent += "</div>";
        mailContent += "</div>";
        mailContent += "<p> Nếu có nhầm lẫn hoặc sai sót gì bạn có thể liên hệ với chúng tôi qua email c0620g1@gmail.com</p>";
        mailContent += "<hr>";
        mailContent += "<h2 style=\"color: lightpink\">Trân trọng, C06 Parking team</h2>";
        mailContent += "<img style=\"width: 200px\"\n" +
                "     src=\"https://jobsgo.vn/blog/wp-content/uploads/2019/05/59871131_6119243920752_2111385517323976704_n.png\">";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("pkhanh2994@gmail.com", senderName);
        helper.setTo(appAccount.getUsername());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }
}
