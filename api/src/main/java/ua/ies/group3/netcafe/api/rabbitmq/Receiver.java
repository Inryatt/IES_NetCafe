package ua.ies.group3.netcafe.api.rabbitmq;

import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(byte[] message) {
    String msg = new String(message, StandardCharsets.UTF_8);
    Gson g = new Gson();
    Teste s = g.fromJson(msg, Teste.class);
    // JSONPObject json = new JSONPObject(msg);
    System.out.println("Received <" + s.getName() + ">");
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}