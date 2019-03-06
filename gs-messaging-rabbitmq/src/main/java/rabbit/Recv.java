package rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Recv {
	
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost( "localhost" );
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
		    String message = new String(delivery.getBody(), "UTF-8");
		    System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}

}
