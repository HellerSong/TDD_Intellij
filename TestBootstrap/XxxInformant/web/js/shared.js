function setHeadDatetime() {
    var headTimeElement = $('.index-head-time-value');
    headTimeElement.html('日期：' + (new Date()).toLocaleString());
    setInterval(function () {
        var now = (new Date()).toLocaleString();
        headTimeElement.html('日期：' + now);
    }, 1000);
}


function resetSearchForm() {
    $(':input', '[class*="search-form"]').not(
        ':button, :submit, :reset, :hidden').val('').removeAttr('checked');
    $('select').combobox('clear');
    //$('.selectpicker').selectpicker('val', '0');
}
