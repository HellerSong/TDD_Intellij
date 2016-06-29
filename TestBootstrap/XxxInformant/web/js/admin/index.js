//// Global variables
window.dropdownJson = '';
window.searchDropdownJson = '';
window.treeDropdownJson = '';


$(function () {
    loadAllGlobalDropdownJson();

    //// Page content initial
    setDatetimeContent($('.index-head-time-value'));
    $('#index_currentAdminName').html($.session.get('currentAdminName'));


    //// Loading exactly page
    loadRightPanelContent('ClueList.html');
    // loadRightPanelContent('NewClue.html');
    loadRightTitleNavPath();


    //// Page style setting
    $('.index-main-title .right a').linkbutton();


    //// Event action setting for left panel item
    $('div.sdmenu a').click(function () {
        $('div.sdmenu a').removeClass('focus');
        $(this).addClass('focus');

        loadRightPanelContent($(this).attr('title'));
        loadRightTitleNavPath();
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

function loadRightTitleNavPath() {
    // Change right main title path
    var activeLeftItem = $('div.sdmenu a.focus');
    var rootName = activeLeftItem.parent().parent().attr('title');
    var subName = activeLeftItem.html();
    var titlePath = '<a href="javascropt:;">' + rootName + '</a>&nbsp;>&nbsp;<a href="javascript:;">' + subName + '</a>';
    $('div.index-main-title .left').html(titlePath);

    // Hide the right new clue button group
    $('.index-main-title .right').hide();
}


function loadAllGlobalDropdownJson() {
    $.ajax({
        type: 'post',
        url: 'LoadDropdown',
        // data: values,
        async: false,
        success: function (result) {
            result = (new Function('return ' + result))();

            if (result.total > 0) {
                window.dropdownJson = result.rows;
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
                window.searchDropdownJson = result.rows;
            }
        }
    });

    $.ajax({
        type: 'post',
        url: 'LoadTreeDropdown',
        // data: values,
        async: false,
        success: function (result) {
            result = (new Function('return ' + result))();

            if (result.total > 0) {
                window.treeDropdownJson = result.rows;
            }
        }
    });
}

function loadDropdown(selectElementId, isAutoHeight) {
    var cb = $('#' + selectElementId);
    var strLength = selectElementId.length;
    var selectElementName = selectElementId.substring(2, strLength);    // "cbJBKJXSLY_LYFS" >> "JBKJXSLY_LYFS"
    var lastChar = selectElementId.substring(strLength - 1, strLength);
    if (isDigital(lastChar)) {
        selectElementName = selectElementId.substring(2, strLength - 1);
        //alert(selectElementName);
    }

    var height = 'auto';
    if (isAutoHeight != null && isAutoHeight == false) {
        height = '340px';
    }
    cb.combobox({
        data: window.dropdownJson[selectElementName],
        valueField: 'id',
        textField: 'text',
        panelHeight: height,
        editable: false,
        onLoadSuccess: function () {
            var data = cb.combobox('getData');
            cb.combobox('select', data[0].id);
        }
    });
}

function loadSearchDropdown(selectElementId, isAutoHeight) {
    var cb = $('#' + selectElementId);
    var selectElementName = selectElementId.substring(8, selectElementId.length);   // "cbSearchJBKJXSLY_LYFS" >> "JBKJXSLY_LYFS"

    var height = 'auto';
    if (isAutoHeight != null && isAutoHeight == false) {
        height = '340px';
    }

    cb.combobox({
        data: window.searchDropdownJson[selectElementName],
        valueField: 'id',
        textField: 'text',
        panelHeight: height,
        editable: false,
        onLoadSuccess: function () {
            var data = cb.combobox('getData');
            cb.combobox('select', data[0].id);
        }
    });
}

function loadTreeDropdownCompany(selectElementId) {
    var cb = $('#' + selectElementId);

    cb.combotree({
        data: window.treeDropdownJson.COMPANY,
        panelHeight: '340px',
        editable: false,
        animate: true,
        lines: true,
        onBeforeExpand: function (node) {
            //cb.combotree('scrollTo', node.target);
        },
        onLoadSuccess: function () {
            cb.combotree('setValue', 0);
        }
    });
}

function loadTreeDropdownZone(selectElementId) {
    var cb = $('#' + selectElementId);

    cb.combotree({
        data: window.treeDropdownJson.ZONE,
        panelHeight: '340',
        editable: false,
        animate: true,
        lines: true,
        onBeforeExpand: function (node) {
            //alert(node.target);
        },
        onLoadSuccess: function () {
            cb.combotree('setValue', 0);
            cb.combotree('tree').tree('collapseAll');
        }
    });
}