function hehe(){


var xhr = new XMLHttpRequest();
xhr.open('GET', "http://hackjskn.tk/rests/32.985854/-96.747811/10.0", true);

xhr.send();
xhr.addEventListener("readystatechange", processRequest, false);
xhr.onreadystatechange = processRequest;
function processRequest(e) {
    if (xhr.readyState == 4 && xhr.status == 200) {
        var response = JSON.parse(xhr.responseText);
        alert(response);
    }
}

}