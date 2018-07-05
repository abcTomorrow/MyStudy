package com.wojiushiwo.provider.pb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProvider {
    private static final String BROKER_URL="tcp://localhost:61616";
    private static final String TOPIC_NAME="myTopic";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        //持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("Topic Hello World");
        producer.send(textMessage);
        session.commit();
        connection.close();
    }
}
