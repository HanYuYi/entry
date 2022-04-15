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
            let serialize = serializeJson(data);
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
                console.log(111)
                if (xmlHttp.status === 200 || xmlHttp.status === 304) {
                    resolve(JSON.parse(xmlHttp.responseText));
                    console.log(222)
                } else {
                    console.log(xmlHttp.responseText)
                    reject(xmlHttp.responseText)
                }
            } else {
                console.log(333)
                reject(xmlHttp.responseText)
            }
        }
    });

    return pro;
}