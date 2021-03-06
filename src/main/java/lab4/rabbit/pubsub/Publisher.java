package lab4.rabbit.pubsub;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import static java.lang.Thread.sleep;


public class Publisher {

    public static void main(String[] args) throws Exception {
        String HOST = "localhost";
        Integer PORT = 5672;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        try (Connection connection = new ConnectionFactory().newConnection();

             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("logs", "fanout");

            for (int i = 0; i < 10; i++) {
                String message = "Message#"+i;
                channel.basicPublish("logs", "", null, message.getBytes());
                System.out.println("Sent '" + message + "'");
                sleep(1000);
            }
        }
    }

}
