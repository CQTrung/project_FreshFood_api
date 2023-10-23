package com.example.freshfoodapi.email;

import com.example.freshfoodapi.entity.User;
import com.example.freshfoodapi.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailToUsersWithHighPoints(List<User> users) {
        for (User user : users) {
            if (user.getPoint() > 1000) {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Congratulation!");
                mailMessage.setText("Congratulations you have over 1000 points. You have become a VIP member!");
                javaMailSender.send(mailMessage);
            }
        }
    }

    public void sendEmailWithVoucher(User user, Voucher voucher) {
        ((JavaMailSenderImpl) javaMailSender).setHost("smtp.gmail.com");
        ((JavaMailSenderImpl) javaMailSender).setPort(587); // Use TLS port
        ((JavaMailSenderImpl) javaMailSender).setUsername("ctrung6123@gmail.com");
        ((JavaMailSenderImpl) javaMailSender).setPassword("tenraifximbghhnt"); // Use your Gmail password

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Your Voucher");
            helper.setText("Congratulations! You've earned a voucher. code : " + voucher.getVoucherCode());

            // Attach the voucher details or file
            // You can attach the voucher details or file here
            // Example: helper.addAttachment("voucher.pdf", voucher.getFile());

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
}
