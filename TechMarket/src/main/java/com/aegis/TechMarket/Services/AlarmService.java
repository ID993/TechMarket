package com.aegis.TechMarket.Services;

import com.aegis.TechMarket.DataTransferObjects.MailDto;
import com.aegis.TechMarket.Entities.Alarm;
import com.aegis.TechMarket.Entities.User;
import com.aegis.TechMarket.Enumerators.Enums;
import com.aegis.TechMarket.Exceptions.AppException;
import com.aegis.TechMarket.Repositories.IAlarmRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class AlarmService {

    private JavaMailSender mailSender;

    private IAlarmRepository alarmRepository;


    public Alarm create(Alarm alarm){
        return alarmRepository.save(alarm);
    }

    public List<Alarm> findAll(){
        return alarmRepository.findAll();
    }

    public Alarm findById(long id){
        return alarmRepository.findById(id).orElseThrow(() -> {
            throw new AppException("Not found alarm with id: " + id, HttpStatus.NOT_FOUND);
        });
    }

    public List<Alarm> findAllByCategory(Enums.Category category){
        return alarmRepository.findAllByCategory(category);
    }

    public List<Alarm> findAllByUser(User user){
        return alarmRepository.findAllByUser(user);
    }


    public Alarm update(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public void delete(Alarm alarm){
        alarmRepository.delete(alarm);
    }

    public void deleteById(long id){
        alarmRepository.deleteById(id);
    }

    public List<Alarm> findAllByCategoryAndPrice(Enums.Category category, float price){
        return alarmRepository.findAllByCategoryAndMinPriceAndMaxPrice(category, price);
    }

    public void sendRequest(MailDto mailDto) throws MessagingException {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            var user = (User)auth.getPrincipal();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setSubject(mailDto.getSubject());
            helper.setFrom("sniffer.tech.market@gmail.com");
            helper.setTo(mailDto.getTo());
            helper.setText("<b>Tech Market - no reply</b><br><br>" + mailDto.getMessage() + "<br><br><b>Full name: " + user.getFullName() + "</b><br><b>Email: " + user.getEmail(), true);
            mailSender.send(message);
        }
    }


}
