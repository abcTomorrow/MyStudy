package com.wojiushiwo.consumer.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PointConsumer {
    private static final String BROKER_URL="tcp://localhost:61616";
    private static final String QUEUE_NAME="myQueue";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        TextMessage receive = (TextMessage) consumer.receive();
        System.out.println(receive.getText());
        session.close();
        connection.close();
    }
}
