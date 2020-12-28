package com.saassignment.subscriber;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class Reciever {
    private final static String QUEUE_NAME = "firstqueue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            //GetMovieDetails gmdtls = new GetMovieDetails();
            String delims = "[:]";
			String[] tokens = message.split(delims);
			String title = tokens[0];
			String year = tokens[1];
			
			System.out.println(title);
			System.out.println(year);
			
            String cast = GetMovieDetails.getCast(title, year);
            String dir = GetMovieDetails.getDir(title, year);
            InsertDetails indata = new InsertDetails();
            indata.connect();
            indata.createItem(message, cast, dir);
            System.out.println(" Cast of " + title + " is " + cast);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }
}