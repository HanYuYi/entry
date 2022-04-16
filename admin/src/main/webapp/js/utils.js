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

/**
 * 格式化时间
 * @param fmt
 * @param date
 * @returns {*}
 */
function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "y+": date.getFullYear().toString(),        // 年
        "M+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "m+": date.getMinutes().toString(),         // 分
        "s+": date.getSeconds().toString()          // 秒
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        }
    }
    return fmt;
}