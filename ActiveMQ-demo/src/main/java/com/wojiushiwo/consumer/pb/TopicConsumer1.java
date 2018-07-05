package com.wojiushiwo.consumer.pb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer1 {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String TOPIC_NAME = "myTopic";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        TextMessage msg = (TextMessage) consumer.receive();
        System.out.println(msg.getText());
        session.close();
        connection.close();
    }
}
