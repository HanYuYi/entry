/**
 * 将js对象序列化为url拼接的字符串
 * @param jsonStr
 */
function serializeJson(jsonStr) {
    let serialize = "";
    Object.keys(jsonStr).forEach(key => serialize += key + "=" + jsonStr[key] + "&");
    if (serialize[serialize.length - 1] === "&") {
        serialize = serialize.substr(0, serialize.length - 1);
    }
    return serialize;
}