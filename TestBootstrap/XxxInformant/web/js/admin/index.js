window.dropdownJson = '';
window.searchDdropdownJson = '';


$(function () {
    //// Load all dropdown json string
    $.ajax({
        type: 'post',
        url: 'LoadDropdown',
        // data: values,
        async: false,
        success: function (result) {
            result = (new Function('return ' + result))();

            if (result.total > 0) {
                window.searchDdropdownJson = result.rows;
                //alert(result.rows.JBKJXSLY_LYFS[0].optionHtmlContent);
            }
        }
    });

    $.ajax({
        type: 'post',
        url: 'LoadSearchDropdown',
        // data: values,
        async: false,
        success: function (result) {
            result = (new Function('return ' + result))();

            if (result.total > 0) {
                window.searchDdropdownJson = result.rows;
            }
        }
    });

    setHeadDatetime();

    loadRightPanelContent('ClueList.html');
    //loadRightPanelContent('NewClue.html');

    //// Set click action for left panel item
    $('div.sdmenu a').click(function () {
        // Load exactly html page
        loadRightPanelContent($(this).attr('title'));

        // Switch left item focus
        $('div.sdmenu a').removeClass('focus');
        $(this).addClass('focus');

        // Change right main title path
        var rootName = $(this).parent().parent().attr('title');
        var subName = $(this).html();
        var titlePath = '<a href="#">' + rootName + '</a>&nbsp;>&nbsp;<a href="#">' + subName + '</a>';
        $('div.index-main-title').html(titlePath);
    });
});

function loadRightPanelContent(htmlFile) {
    var rightPanel = $('div.index-main-content');
    rightPanel.css('display', 'none');
    rightPanel.load(htmlFile + ' .main-content', null, function () {
        rightPanel.fadeIn('slow');
        var initMethodName = 'initialize' + htmlFile.substring(0, htmlFile.indexOf('.'));
        window[initMethodName]();
    });
}

function setHeadDatetime() {
    var headTimeElement = $('.index-head-time-value');
    headTimeElement.html('日期：' + (new Date()).toLocaleString());
    setInterval(function () {
        var now = (new Date()).toLocaleString();
        headTimeElement.html('日期：' + now);
    }, 1000);
}