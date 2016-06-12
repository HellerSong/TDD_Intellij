/**
 * Created by Heller Song on 6/9/2016.
 */
function initializeClueList() {
    $('.easyui-linkbutton').linkbutton();
}

function loadNewCluePage() {
    loadRightPanelContent('NewClue.html');
    var titlePathElement = $('div.index-main-title');
    var titlePath = titlePathElement.html() + '&nbsp;>&nbsp;<a href="#">新建窗口</a>';
    $('div.index-main-title').html(titlePath);
}