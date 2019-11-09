package com.plash.configurator.utils.emailsender;

import com.plash.configurator.utils.StrUtil;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.Response;

import java.io.File;

public class SendGridMailSender {
    public static final String DOMAIN = "alore.io";
    public static final String API_KEY = "key-d8412bde85c935adb6e1a10b464dfeb9";

    public static Integer sendEmailUsingAPI(String to, String subject, String htmlbody, String filepath) {
        Response response = null;
        if (!StrUtil.nonNull(filepath).equalsIgnoreCase("")) {
            Configuration configuration = new Configuration()
                    .domain(DOMAIN)
                    .apiKey(API_KEY)
                    .from("Alore CRM", "accounts@alore.io");
            response = Mail.using(configuration)
                    .to(to)
                    .subject(subject)
                    .html(htmlbody)
                    .body()
                    .mail()
                    .multipart()
                    .attachment(new File(filepath))
                    .build()
                    .send();
        } else {
            Configuration configuration = new Configuration()
                    .domain(DOMAIN)
                    .apiKey(API_KEY)
                    .from("Alore CRM", "accounts@alore.io");
            response = Mail.using(configuration)
                    .to(to)
                    .subject(subject)
                    .html(htmlbody)
                    .body()
                    .mail()
                    .build()
                    .send();
        }
        return response.responseCode();
    }
}
