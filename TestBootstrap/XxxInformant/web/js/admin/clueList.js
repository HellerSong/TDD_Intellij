window.isALLClue = 0;
window.isXSClue = 1;
window.isCFClue = 0;
window.isJCGJClue = 0;
window.isTJJClue = 0;
window.isSBJClue = 0;


function initializeClueList() {
    //// Page style setting
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});

    var clueType = $('.sdmenu ul a.focus[title="ClueList.html"]').attr('name');
    if (clueType == 'ALL') {
        window.isALLClue = 1;
        window.isXSClue = 0;
        window.isCFClue = 0;
        window.isJCGJClue = 0;
        window.isTJJClue = 0;
        window.isSBJClue = 0;
    } else if (clueType == 'XS') {
        window.isALLClue = 0;
        window.isXSClue = 1;
        window.isCFClue = 0;
        window.isJCGJClue = 0;
        window.isTJJClue = 0;
        window.isSBJClue = 0;
    } else if (clueType == 'CF') {
        window.isALLClue = 0;
        window.isXSClue = 0;
        window.isCFClue = 1;
        window.isJCGJClue = 0;
        window.isTJJClue = 0;
        window.isSBJClue = 0;
    } else if (clueType == 'JCGJ') {
        window.isALLClue = 0;
        window.isXSClue = 0;
        window.isCFClue = 0;
        window.isJCGJClue = 1;
        window.isTJJClue = 0;
        window.isSBJClue = 0;
    } else if (clueType == 'TJJ') {
        window.isALLClue = 0;
        window.isXSClue = 0;
        window.isCFClue = 0;
        window.isJCGJClue = 0;
        window.isTJJClue = 1;
        window.isSBJClue = 0;
    } else if (clueType == 'SBJ') {
        window.isALLClue = 0;
        window.isXSClue = 0;
        window.isCFClue = 0;
        window.isJCGJClue = 0;
        window.isTJJClue = 0;
        window.isSBJClue = 1;
    }


    //// Load the data grid data
    $('#clueList_Dg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            {field: 'JBKJXSLY_ID', title: '', checkbox: true, width: 50, align: 'center'},
            {field: 'JBKJXSLY_SLRQ', title: '受理日期', width: 80, align: 'center'},
            {field: 'CBRCLRQ', title: '处理日期', width: 80, align: 'center'},
            {field: 'JBKJXSLY_BJBRXM', title: '被举报人姓名', width: 100, align: 'center', formatter: displayStringFormatter},
            {field: 'JBKJXSLY_BJBRDW', title: '单位', width: 140, align: 'center', formatter: displayStringFormatter},
            {field: 'JBKJXSLY_ZJ', title: '职级', width: 120, align: 'center', formatter: displayStringFormatter},
            {field: 'JBKJXSLY_ZYSXXZ', title: '主要涉嫌性质', width: 180, align: 'center', formatter: displayStringFormatter},
            {field: 'JBKJXSLY_LYFS', title: '举报方式', width: 60, align: 'center'},
            {field: 'CBR', title: '承办人', width: 60, align: 'left'},
            {field: 'JBKJXSLY_CLZT', title: '状态', width: 50, align: 'left', formatter: statusFormatter},
            {field: 'action', title: '操作选项', width: 80, align: 'center', formatter: actionFormatter}
        ]],
        rownumbers: false,
        singleSelect: false,
        pagination: true,
        pageSize: 20,
        onLoadSuccess: onDataGridLoadSuccess
    });
    queryClueListDefaultData();


    //// Load all dropdown data
    loadSearchDropdown('cbSearchJBKJXSLY_LYFS');
    loadSearchDropdown('cbSearchJBKJXSLY_AFDQ', false);
}

function actionFormatter(data, row, index) {
    return '<a href="#EditClue?ID=' + row.JBKJXSLY_ID + '" onclick="editCurrentRow(' + row.JBKJXSLY_ID + ',' + index + ')">修改</a>' +
        '<a href="javascript:;" onclick="deleteCurrentRow(' + row.JBKJXSLY_ID + ')">删除</a>'
}

function statusFormatter(value) {
    if (value == 1) {
        return "待处理";
    } else if (value == 2) {
        return "已处理";
    }
}

function displayStringFormatter(value) {
    if (value != null) {
        if (value.indexOf('@#@') == 0) {
            return '';
        } else {
            return value.replace(/@#@/g, ';');
        }
    } else {
        return '';
    }
}

function queryClueListDefaultData() {
    var dg = $('#clueList_Dg');
    queryClueList(1, dg.datagrid('options').pageSize);
    dg.datagrid('resize');
}

function queryClueList(pageNumber, pageSize) {
    var url = 'AdminLoadClueList';
    var values = $('.clueList-search-form').serialize();
    values += '&pageNumber=' + pageNumber + '&pageSize=' + pageSize;
    values += '&isALLClue=' + window.isALLClue + '&isXSClue=' + window.isXSClue +
        '&isJCGJClue=' + window.isJCGJClue + '&isTJJClue=' + window.isTJJClue +
        '&isSBJClue=' + window.isSBJClue;

    $.post(url, values, function (result) {
        result = (new Function('return ' + result))();

        var dg = $('#clueList_Dg');
        dg.datagrid('loadData', result.rows);
        dg.datagrid('getPager').pagination({
            total: result.total, pageNumber: pageNumber, pageSize: pageSize
        });

        if (result.total <= 0) {
//            alert('未找到任何线索记录！');
        }
    });
}

function onDataGridLoadSuccess() {
    var page = $(this).datagrid("getPager");
    page.pagination({
        onSelectPage: function (pageNumber, pageSize) {
            queryClueList(pageNumber, pageSize);
        },
        onChangePageSize: function (pageSize) {
            page.pagination({
                pageNumber: 1,
                pageSize: pageSize
            });
            queryClueList(1, pageSize);
        }
    });
}


function loadNewCluePage() {
    loadRightPanelContent('NewClue.html');
    loadRightTitleNavPath();
    var titlePathElement = $('div.index-main-title .left');
    var titlePath = titlePathElement.html() + '&nbsp;>&nbsp;<a href="#">新建窗口</a>';
    titlePathElement.html(titlePath);
}

function deleteAllSelectedRows() {
    var selectedRows = $('#clueList_Dg').datagrid('getSelections');

    if (selectedRows.length <= 0) {
        alert('请勾选记录。')
    } else {
        if (confirm('您确定要删除选中的' + selectedRows.length + '条记录么？')) {
            var clueCollection = '';

            for (var i = 0; i < selectedRows.length; i++) {
                clueCollection += selectedRows[i].JBKJXSLY_ID + '@#@';
            }
            clueCollection = clueCollection.substring(0, clueCollection.length - 3);

            $.post('AdminDeleteClue', {clueCollection: clueCollection}, function (result) {
                result = (new Function('return ' + result))();

                if (result.status.indexOf("成功") >= 0) {
                    alert('删除' + selectedRows.length + '条记录成功！');
                    queryClueListDefaultData();
                } else {
                    alert('未找到线索记录，删除失败。');
                }
            });
        }
    }
}

function deleteCurrentRow(id) {
    if (confirm('您确定要删除该条记录么？')) {
        $.post('AdminDeleteClue', {clueCollection: id}, function (result) {
            result = (new Function('return ' + result))();

            if (result.status.indexOf("成功") >= 0) {
                alert('删除记录成功！');
                queryClueListDefaultData();
            } else {
                alert('未找到线索记录，删除失败。');
            }
        });
    }
}

function editCurrentRow(id, index) {
    $('#clueList_Dg').datagrid('selectRow', index);
    loadRightPanelContent('NewClue.html');
    loadRightTitleNavPath();
    var titlePathElement = $('div.index-main-title');
    var titlePath = titlePathElement.html() + '&nbsp;>&nbsp;<a href="#">编辑窗口</a>';
    titlePathElement.html(titlePath);
    //alert('Edit row id: ' + id);
}


function submitClueListSearchForm() {
    if (validateClueListSearchForm()) {
        queryClueListDefaultData();
    }
}

function resetClueListSearchForm() {
    $('.clueList-search-form .easyui-textbox').textbox('clear');
    $('.clueList-search-form .easyui-datebox').datebox('clear');
    $('.clueList-search-form .easyui-combobox').combobox('select', 0);
    queryClueListDefaultData();
}

function validateClueListSearchForm() {
    //// Validate EndTime > StartTime
    var acceptDateStart = $('input[name="JBKJXSLY_SLRQ_Start"]').val();
    var acceptDateEnd = $('input[name="JBKJXSLY_SLRQ_End"]').val();
    var start = new Date(acceptDateStart.replace('-', '/').replace('-', '/'));
    var end = new Date(acceptDateEnd.replace('-', '/').replace('-', '/'));

    if (acceptDateStart != null && acceptDateStart.length > 0 &&
        acceptDateEnd != null && acceptDateEnd.length > 0) {
        if (end < start) {
            alert('结束时间必须大于起始时间！');
            return false;
        }
    }

    return true;
}