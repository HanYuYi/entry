/**
 * 封装ajax
 * @param url
 * @param type
 * @param async
 * @returns {Promise<unknown>}
 */
function ajax({ url, type, async = true, data }) {

    const pro = new Promise(function (resolve, reject) {
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
        xmlHttp.open(type, newUrl, async);
        if (typeStr === "post") {
            xmlHttp.setRequestHeader("Content-Type", "application/json");
            xmlHttp.send(JSON.stringify(data));
        }
        if (typeStr === "get") {
            xmlHttp.send();
        }

        xmlHttp.onload = function () {
            if (xmlHttp.readyState === 4) {
                if (xmlHttp.status === 200 || xmlHttp.status === 304) {
                    resolve(JSON.parse(xmlHttp.responseText));
                }
            } else {
                reject(xmlHttp.responseText)
            }
        }
    });

    return pro;
}