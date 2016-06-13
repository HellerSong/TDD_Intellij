/**
 * Created by Heller Song on 6/9/2016.
 */
$(function () {
    setInterval(function () {
        var now = (new Date()).toLocaleString();
        $('.index-head-datetime').html('日期：' + now);
    }, 1000);

    loadRightPanelContent('ClueList.html');

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