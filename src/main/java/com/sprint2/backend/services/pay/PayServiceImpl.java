package com.sprint2.backend.services.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.sprint2.backend.entity.Customer;
import com.sprint2.backend.entity.Invoice;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.repository.InvoiceRepository;
import com.sprint2.backend.repository.MemberCardRepository;

@Service
public class PayServiceImpl implements PaySerVice {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    /*
     * get all member card of customer currently logged in database
     * @param idCustomer
     * @return ResponseEntity<List<MemberCard>>
     * */
    @Override
    public List<MemberCard> findByCustomerID(Long id) {
        return this.memberCardRepository.findByCustomerId(id);
    }

    /*
     * update member card after customer pay and send mail notification for customer
     * @param money, memberCardList
     * void
     * */
    @Override
    public void updateMemberCardAndSendMailAfterCustomerPay(Double money, List<Long> listIDMemberCard) {

        // update member card and create list member card for send email :
        List<MemberCard> memberCardListAfterPay = updateMemberCard(money, listIDMemberCard);

        // create invoice :
        createInvoice(money, listIDMemberCard);

        // send mail notification for customer after pay complete :
        sendMailNotificationForCustomer(memberCardListAfterPay);
    }

    private List<MemberCard> updateMemberCard(Double money, List<Long> listIDMemberCard) {
        List<MemberCard> memberCardListAfterPay = new ArrayList<>();

        for (Long element : listIDMemberCard) {
            MemberCard memberCard = this.memberCardRepository.findById(element).orElse(null);
            if (memberCard != null) {
                memberCardListAfterPay.add(memberCard);
                if (memberCard.getMemberCardType().getMemberTypeName().equals("Tuần")) {
                    memberCard.setEndDate(memberCard.getEndDate().plusDays(7));
                } else if (memberCard.getMemberCardType().getMemberTypeName().equals("Tháng")) {
                    memberCard.setEndDate(memberCard.getEndDate().plusMonths(1));
                } else {
                    memberCard.setEndDate(memberCard.getEndDate().plusYears(1));
                }
                this.memberCardRepository.save(memberCard);
            }
        }

        return memberCardListAfterPay;
    }

    private void createInvoice(Double money, List<Long> listIDMemberCard) {
        MemberCard memberCardUseCreatInvoice =
                this.memberCardRepository.findById(listIDMemberCard.get(0)).orElse(null);
        if (memberCardUseCreatInvoice != null) {
            Invoice invoice = new Invoice();
            Customer customer = memberCardUseCreatInvoice.getCar().getCustomer();
            invoice.setTotalAmount(money);
            invoice.setCustomer(customer);
            invoice.setPayDate(LocalDateTime.now());
            this.invoiceRepository.save(invoice);
        }
    }

    private void sendMailNotificationForCustomer(List<MemberCard> memberCardListAfterPay) {
        try {
            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String nameCustomer = memberCardListAfterPay.get(0).getCar().getCustomer().getFullName();

            helper.setTo(memberCardListAfterPay.get(0).getCar().getCustomer().getEmail());
            helper.setSubject("Thông báo từ C06Parking");
            StringBuilder mailContent = new StringBuilder(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "  <meta charset=\"UTF-8\">\n" +
                            "  <title>Mail</title>\n" +
                            "  <style>\n" +
                            "    * {\n" +
                            "      font-family: \"Varela Round\";\n" +
                            "    }" +
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
                            "      width: 100%\n" +
                            "    }\n" +
                            "\n" +
                            "    td, th {\n" +
                            "      border: 1px solid;\n" +
                            "      text-align: center;\n" +
                            "    }\n" +
                            "\n" +
                            "    span {\n" +
                            "      color: blue;\n" +
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
                            "      <h5 style=\"width: 100%\">\n" +
                            "        Công ty TNHH C06Parking thông báo gia hạn vé xe thành công\n" +
                            "      </h5>\n" +
                            "<div>\n" +
                            "        <img src=\"https://bkaii.com.vn/images/bai-do-xe-tai-duc.jpg" +
                            "\" style=\"width: 100%; height: 425px\">\n" +
                            "      </div>" +
                            "      <p>Kính gửi quý khách: <span>" + nameCustomer + "</span></p>\n" +
                            "      <p>Sau đây là danh sách vé xe đã gia hạn thành công của quý khách : </p>\n" +
                            "      <div class=\"row\">\n" +
                            "        <div class=\"container-xl\">\n" +
                            "          <div class=\"table-responsive\">\n" +
                            "            <div class=\"table-wrapper\">\n" +
                            "              <table>\n" +
                            "                <tr>\n" +
                            "                  <th>Biển số xe</th>\n" +
                            "                  <th>Loại vé</th>\n" +
                            "                  <th>Ngày hết hạn sau khi cập nhật</th>\n" +
                            "                </tr>\n");
            for (MemberCard memberCardAfterPay : memberCardListAfterPay) {
                mailContent.append("<tr>\n");
                mailContent.append("<td>");
                mailContent.append(memberCardAfterPay.getCar().getPlateNumber());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCardAfterPay.getMemberCardType().getMemberTypeName());
                mailContent.append("</td>");
                mailContent.append("<td>");
                mailContent.append(memberCardAfterPay.getEndDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                mailContent.append("</td>");
                mailContent.append("</tr>");
            }

            mailContent.append("</table>\n" +
                    "            </div>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "      </div>\n" +
                    "      <p class=\"autoMail\">P/s : Đây là thư thông báo tự động. " +
                    "Quý khách vui lòng không trả lời thư này!</p>\n" +
                    "      <div class=\"navbar-light bg-light mt-5 rounded\">\n" +
                    "        <div class=\"row p-4  mx-0 p-0\">\n" +
                    "          <div class=\"col-sm-3 col-xl-3\">\n" +
                    "            <h4 class=\"justify-content-center\">LIÊN HỆ</h4>\n" +
                    "            <ul class=\"list-unstyled\">\n" +
                    "              <li>295 Nguyễn Tất Thành, DN</li>\n" +
                    "              <li>0236 6517 021</li>\n" +
                    "            </ul>\n" +
                    "          </div>\n" +
                    "          <div class=\"col-sm-5 col-xl-5 \">\n" +
                    "            <h4 class=\"justify-content-center\">GIỚI THIỆU</h4>\n" +
                    "            <p>Công ty chúng tôi hân hạnh mang đến những dịch vụ đậu xe hiện đại " +
                    "và an toàn nhất.\n" +
                    "              Chúng tôi tự hào là 1 trong những hệ thống đặt chỗ đậu xe</p>" +
                    "<p>hàng đầu Việt Nam.</p>\n" +
                    "          </div>\n" +
                    "        </div>\n" +
                    "      </div>" +
                    "    </div>\n" +
                    "    <div class=\"col-sm-3\"></div>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n");

            helper.setText(String.valueOf(mailContent), true);
            this.emailSender.send(message);
        } catch (MessagingException messaging) {
            messaging.printStackTrace();
        }
    }
}
