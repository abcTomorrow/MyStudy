package com.wojiushiwo.provider.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PointProvider {
    private static final String BROKER_URL="tcp://localhost:61616";
    private static final String QUEUE_NAME="myQueue";
    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("Hello ActiveMQ");
        System.out.println("send msg");
        producer.send(textMessage);
        session.close();
        connection.close();
    }
}
