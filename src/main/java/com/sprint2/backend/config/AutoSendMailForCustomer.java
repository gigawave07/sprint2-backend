package com.sprint2.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.sprint2.backend.services.member_card.MemberCardService;
import com.sprint2.backend.entity.MemberCard;

@Configuration
@EnableScheduling
public class AutoSendMailForCustomer {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private MemberCardService memberCardService;

    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    private void scheduleFixedDelayTask() throws MessagingException, InterruptedException {
        System.out.println("System auto send mail start.");
        List<MemberCard> allMemberCard = this.memberCardService.findAll();
        List<MemberCard> listMemberCardNearExpired = new ArrayList<>();
        HashSet<String> mailList = new HashSet<>();
        LocalDateTime now = LocalDateTime.now();

        if (!allMemberCard.isEmpty()) {
            for (MemberCard memberCard : allMemberCard) {
                LocalDateTime endDate = memberCard.getEndDate();
                Duration between = Duration.between(now, endDate);
                if (between.toDays() <= 5) {
                    listMemberCardNearExpired.add(memberCard);
                    mailList.add(memberCard.getCar().getCustomer().getEmail());
                }
            }
            System.out.println(mailList);

            if (!mailList.isEmpty()) {
                try {
                    for (String mail : mailList) {
                        List<MemberCard> memberCardOfMail = new ArrayList<>();
                        for (MemberCard memberCard : listMemberCardNearExpired) {
                            if (memberCard.getCar().getCustomer().getEmail().equals(mail)) {
                                memberCardOfMail.add(memberCard);
                            }
                        }

                        MimeMessage message = this.emailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                        String nameCustomer = memberCardOfMail.get(0).getCar().getCustomer().getFullName();

                        helper.setTo(mail);
                        helper.setSubject("Thông báo gia hạn vé xe");
                        StringBuilder mailContent = new StringBuilder("<!DOCTYPE html>\n" +
                                "<html lang=\"en\">\n" +
                                "<head>\n" +
                                "  <meta charset=\"UTF-8\">\n" +
                                "  <title>Mail</title>\n" +
                                "  <style>\n" +
                                "    .bodyMail {\n" +
                                "      margin-top: 1%;\n" +
                                "    }\n" +
                                "\n" +
                                "    h5 {\n" +
                                "      color: white;\n" +
                                "      background-color: green;\n" +
                                "      text-align: center\n" +
                                "    }\n" +
                                "\n" +
                                "    p {\n" +
                                "      margin: 1% 0;\n" +
                                "    }\n" +
                                "\n" +
                                "    table {\n" +
                                "      border: 1px solid;\n" +
                                "      border-collapse: separate;\n" +
                                "      width: 90%\n" +
                                "    }\n" +
                                "\n" +
                                "    td, th {\n" +
                                "      border: 1px solid;\n" +
                                "      text-align: center;\n" +
                                "    }\n" +
                                "\n" +
                                "    span {\n" +
                                "      color: red;\n" +
                                "    }\n" +
                                "\n" +
                                "    .autoMail {\n" +
                                "      color: red;\n" +
                                "    }\n" +
                                "  </style>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "<div class=\"container-fluid\">\n" +
                                "  <div class=\"row\">\n" +
                                "    <div class=\"col-sm-3\"></div>\n" +
                                "    <div class=\"col-sm-6 bodyMail\">\n" +
                                "      <h5>\n" +
                                "        Công ty TNHH C06Parking thông báo vé xe sắp hết hạn\n" +
                                "      </h5>\n" +
                                "      <p>Kính gửi quý khách: <span>" + nameCustomer + "</span></p>\n" +
                                "      <p>Sau đây là danh sách vé xe sắp hết hạn của quý khách : </p>\n" +
                                "      <div class=\"row\">\n" +
                                "        <div class=\"container-xl\">\n" +
                                "          <div class=\"table-responsive\">\n" +
                                "            <div class=\"table-wrapper\">\n" +
                                "              <table>\n" +
                                "                <tr>\n" +
                                "                  <th>Hãng xe</th>\n" +
                                "                  <th>Biển số xe</th>\n" +
                                "                  <th>Loại vé</th>\n" +
                                "                  <th>Ngày hết hạn</th>\n" +
                                "                  <th>Giá (VNĐ)</th>\n" +
                                "                </tr>\n");
                        for (MemberCard memberCard : memberCardOfMail) {
                            mailContent.append("<tr>\n");
                            mailContent.append("<td>");
                            mailContent.append(memberCard.getCar().getBrandName());
                            mailContent.append("</td>");
                            mailContent.append("<td>");
                            mailContent.append(memberCard.getCar().getPlateNumber());
                            mailContent.append("</td>");
                            mailContent.append("<td>");
                            mailContent.append(memberCard.getMemberCardType().getMemberTypeName());
                            mailContent.append("</td>");
                            mailContent.append("<td>");
                            mailContent.append(memberCard.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            mailContent.append("</td>");
                            mailContent.append("<td>");
                            mailContent.append(memberCard.getPrice());
                            mailContent.append("</td>");
                            mailContent.append("</tr>");
                        }

                        mailContent.append("</table>\n" +
                                "            </div>\n" +
                                "          </div>\n" +
                                "        </div>\n" +
                                "      </div>\n" +
                                "      <p>Quý khách vui lòng thanh toán trước khi thời hạn vé kết thúc! " +
                                "Nếu không thanh toán vé sẽ được hủy\n" +
                                "        theo quy định của công ty.</p>\n" +
                                "      <p>Click vào <a style=\"color: green\" " +
                                "href=\"http://localhost:4200\">đây</a> để đăng nhập nhanh vào hệ thống" +
                                "của chúng tôi.</p>\n" +
                                "      <p class=\"autoMail\">P/s : Đây là thư thông báo tự động. " +
                                "Quý khách vui lòng không trả lời thư này!</p>\n" +
                                "    </div>\n" +
                                "    <div class=\"col-sm-3\"></div>\n" +
                                "  </div>\n" +
                                "</div>\n" +
                                "</body>\n" +
                                "</html>\n");

                        helper.setText(String.valueOf(mailContent), true);
                        this.emailSender.send(message);
                    }
                } catch (RuntimeException run) {
                    System.out.println("Internet connection is lost");
                    Thread.sleep(60000);
                    scheduleFixedDelayTask();
                }
            } else {
                System.out.println("List member card near expired is empty.");
            }
        } else {
            System.out.println("List member card is empty.");
        }
    }
}
