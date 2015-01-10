package org.javaee7.movieplex7.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author ecabrerar
 */
@Named
@Dependent
public class SalesReader extends AbstractItemReader {

    private BufferedReader reader;

    @Override
    public Object readItem() throws Exception {
        String string = null;
        try {
            string = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return string;
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
        reader = new BufferedReader(
                new InputStreamReader(
                        Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("META-INF/sales.csv")));
    }

}
