package org.javaee7.movieplex7.points;

import java.io.Serializable;
import java.util.Enumeration;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;

/**
 *
 * @author ecabrerar
 */
@JMSDestinationDefinition(name = "java:global/jms/pointsQueue",
        interfaceName = "javax.jms.Queue")
@Named
@SessionScoped
public class ReceivePointsBean implements Serializable {

    @Inject
    JMSContext context;
    @Resource(mappedName = "java:global/jms/pointsQueue")
    Queue pointsQueue;

    public String receiveMessage() {
        String message
                = context.createConsumer(pointsQueue).receiveBody(String.class);
        System.out.println("Received message: " + message);
        return message;
    }

    public int getQueueSize() {
        int count = 0;
        try {
            QueueBrowser browser = context.createBrowser(pointsQueue);
            Enumeration elems = browser.getEnumeration();
            while (elems.hasMoreElements()) {
                elems.nextElement();
                count++;
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
        
        return count;
    }
}
