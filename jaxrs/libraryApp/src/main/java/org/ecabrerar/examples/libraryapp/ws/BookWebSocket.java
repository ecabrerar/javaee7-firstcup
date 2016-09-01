/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.libraryapp.ws;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author ecabrerar
 * @date Sep 1, 2016
 */
@ServerEndpoint(value = "/app/websockets")
public class BookWebSocket {

    @OnMessage
    public String searchBook(String name) {
        return "Found book " + name;
    }
}
