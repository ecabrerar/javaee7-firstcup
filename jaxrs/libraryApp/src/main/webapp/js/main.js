function createRequest() {
    var xmlhttp = null;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
        if (typeof xmlhttp.overrideMimeType != 'undefined') {
            xmlhttp.overrideMimeType('text/xml');
        }
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        alert('Perhaps your browser does not support xmlhttprequests?');
    }
    
    return xmlhttp;
}

function sendBrowseRequest() {
    var req = createRequest(); // defined above
// Create the callback:
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            document.getElementById("query").innerHTML = "GET app/library/books";
            document.getElementById("output").innerHTML =
                    req.responseText;
        }
    };

    req.open("GET", "app/library/books", true);
    req.send(null);
}


var wsUri = "ws://localhost:8080/libraryApp/app/websockets";

var websocket = new WebSocket(wsUri, 'echo-protocol');

function sendSearchWSRequest(book) {
    
    websocket.onopen = function () {
     console.log('socket connection opened properly');
     websocket.send(book); // send a message
     console.log('message sent');
   };
   
    console.log("Searching for: " + book);
}


function sendCheckoutRequest(book) {
    var req = createRequest(); // defined above

// Create the callback:
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            document.getElementById("query").innerHTML =
                    "DELETE app/library/books/" + encodeURI(book.trim());
            document.getElementById("output").innerHTML
                    = req.responseText;
        }
    };

    req.open("DELETE", "app/library/books/" + book, true);
    req.send(null);
}


function sendReturnRequest(book) {
    var req = createRequest(); // defined above

// Create the callback:
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            document.getElementById("query").innerHTML =
                    "POST app/library/books/" + encodeURI(book.trim());
            document.getElementById("output").innerHTML =
                    req.responseText;
        }
    };

    req.open("POST", "app/library/books/" + book, true);
    req.send(null);
}

function sendHoldRequest(book) {
    var req = createRequest(); // defined above

// Create the callback:
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            document.getElementById("query").innerHTML =
                    "POST app/library/books/hold/" + encodeURI(book.trim());
            document.getElementById("output").innerHTML =
                    req.responseText;
        }
    };

    req.open("POST", "app/library/books/hold/" + book, true);
    req.send(null);
}


function browse_onclick() {
    return sendBrowseRequest();
}

function search_onclick(){
    return sendSearchWSRequest(getValueFromTextField());
}

function checkout_onclick(){
    return sendCheckoutRequest(getValueFromTextField());
}

function return_onclick(){
    return sendReturnRequest(getValueFromTextField());
}

function hold_onclick(){
    return sendHoldRequest(getValueFromTextField()); 
}

function getValueFromTextField(){
    return document.getElementById("bookName").value;
}