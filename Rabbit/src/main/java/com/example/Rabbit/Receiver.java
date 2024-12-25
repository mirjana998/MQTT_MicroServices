package com.example.Rabbit;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public Receiver() {
    }

    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(byte[] message) {
        System.out.println("*****************");
        String content = new String(message, StandardCharsets.UTF_8);
        System.out.println(content);
        String[] parts = content.split(":");
        String[] arr = parts[1].split("\n");
        try {

            String user = System.getProperty("user.dir");
            BufferedWriter bw = new BufferedWriter(new FileWriter(user + File.separator + "narudzbenica"+ File.separator + "narudzbenica" + arr[0]+".txt"));
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.latch.countDown();
    }

}
