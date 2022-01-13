package ua.ies.group3.netcafe.api.rabbitmq;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(byte[] message) {
    String msg = new String(message, StandardCharsets.UTF_8);
    System.out.println("Received <" + msg + ">");
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}