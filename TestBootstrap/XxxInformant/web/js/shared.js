function setDatetimeContent(element) {
    element.html(getCurrentTime());
    setInterval(function () {
        element.html(getCurrentTime());
    }, 1000);
}

function getCurrentTime() {
    var today = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
    var yearStr = new Date().format("yyyy年MM月dd日");
    var weekStr = today[new Date().getDay()]; // getDay() will return 0 ~ 6
    var timeStr = new Date().format("hh:mm:ss");

    return yearStr + '  ' + weekStr + '  ' + timeStr;
}


function resetSearchForm() {
    $(':input', '[class*="search-form"]').not(
        ':button, :submit, :reset, :hidden').val('').removeAttr('checked');
    $('select').combobox('clear');
    //$('.selectpicker').selectpicker('val', '0');
}

function isDigital(str) {
    for (var i = 0; i < 10; i++) {
        if (str.indexOf(i.toString()) != -1) {
            return true;
        }
    }

    return false;
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 *      月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符
 *      年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 *      (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 *      (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,              //月份
        "d+": this.getDate(),                   //日
        "h+": this.getHours(),                  //小时
        "m+": this.getMinutes(),                //分
        "s+": this.getSeconds(),                //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}

function checkRadioByValue(radioNameAttr, radioValue) {
    var checkedRadio = $('input[name="' + radioNameAttr + '"][checked]');

    if (radioValue != checkedRadio.val()) {
        checkedRadio.removeAttr('checked');
        $('input[name="' + radioNameAttr + '"][value="' + radioValue + '"]').attr('checked', true);
    }
}

function OpenNewWindowInCurrentPage(windowOption) {
    var windowId = windowOption.id;
    $('<div id="' + windowId + '" title="' + windowOption.title + '"></div>').appendTo('body');
    $('#' + windowId).dialog({
        width: windowOption.width ? windowOption.width : 400,
        height: windowOption.height ? windowOption.height : 400,
        left: windowOption.left ? windowOption.left : null,
        top: windowOption.top ? windowOption.top : null,
        modal: true,
        closed: true,
        collapsible: windowOption.collapsible ? windowOption.collapsible : false,// 可折叠
        minimizable: windowOption.minimizable ? windowOption.minimizable : false,// 最小化
        maximizable: windowOption.maximizable ? windowOption.maximizable : false,// 最大化
        resizable: windowOption.resizable ? windowOption.resizable : false,// 可缩放
        inline: true,
        param: windowOption.param,
        href: windowOption.url,
        cache: false,// 不进行缓存
        onClose: function () {
            $('#' + windowId).dialog('destroy');
        },
        onLoad: function () {
            window[windowOption.onLoadFunctionName]();
        }
    });
    $('#' + windowId).dialog('open');
}