/**
 * 封装ajax
 * @param url
 * @param type
 * @param async
 * @returns {Promise<unknown>}
 */
function ajax({ url, type, async = true, data }) {
    const typeStr = type.toLocaleString();

    let xmlHttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlHttp = new XMLHttpRequest();
    } else {
        // IE6, IE5 浏览器执行代码
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    let newUrl = url;

    // get序列化参数
    if (typeStr === "get") {
        let serialize = "";
        Object.keys(data).forEach(key => serialize += key + "=" + data[key] + "&");
        if (serialize[serialize.length - 1] === "&") {
            serialize = serialize.substr(0, serialize.length - 1);
        }
        newUrl = newUrl + "?" + serialize;
    }

    return new Promise(function (resolve, reject) {
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                resolve(xmlHttp.responseText);
            } else {
                reject(xmlHttp.responseText)
            }
        }
        xmlHttp.open(type, url, async);
        if (typeStr === "post") {
            // xmlHttp.setRequestHeader("Content-Type", "application/json");
        }
        // xmlHttp.setRequestHeader("Content-Type", "application/json");
        if (typeStr === "post") {
            xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        // xmlHttp.setRequestHeader('Accept', 'application/json');
        // typeStr === "get" ? xmlHttp.send() : xmlHttp.send(JSON.stringify(data));
        typeStr === "get" ? xmlHttp.send() : xmlHttp.send("username=三体&password=刘慈欣&confirmPassword=北京人民出版社");
    });
}