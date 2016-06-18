function initializeClueList() {
    //// Main elements setting
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});


    //// Load the data grid data
    $('#clueList_Dg').datagrid({
        fit: true,
        striped: true,
        border: 1,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            {field: 'JBKJXSLY_ID', title: '', checkbox: true, width: 50, align: 'center'},
            {field: 'JBKJXSLY_SLRQ', title: '受理日期', width: 80, align: 'center'},
            {field: 'CBRCLRQ', title: '处理日期', width: 80, align: 'center'},
            {field: 'JBKJXSLY_BJBRXM', title: '被举报人姓名', width: 100, align: 'center'},
            {field: 'JBKJXSLY_BJBRDWZZ', title: '单位住址', width: 100, align: 'center'},
            {field: 'JBKJXSLY_ZJ', title: '职级', width: 100, align: 'center'},
            {field: 'JBKJXSLY_ZYSXXZ', title: '主要涉嫌性质', width: 100, align: 'center'},
            {field: 'JBKJXSLY_LYFS', title: '举报方式', width: 60, align: 'center'},
            {field: 'CBR', title: '承办人', width: 120, align: 'left'},
            {field: 'JBKJXSLY_CLZT', title: '状态', width: 80, align: 'left', formatter: statusFormatter},
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
    loadSearchDropdown('cbJBKJXSLY_LYFS');
}

function actionFormatter(data, row, index) {
    return '<a href="javascript:;" onclick="editCurrentRow()">修改</a>' +
        '<a href="javascript:;" onclick="deleteCurrentRow(' + row.JBKJXSLY_ID + ')">删除</a>'
}

function statusFormatter(value) {
    if (value == 1) {
        return "未处理";
    } else if (value == 2) {
        return "已处理";
    }
}

function submitClueListSearchForm() {
    if (validateClueListSearchForm()) {
        queryClueListDefaultData();
    }
}

function resetClueListSearchForm() {
    $('.easyui-textbox').textbox('clear');
    $('.easyui-datebox').datebox('clear');
    $('.easyui-combobox').combobox('select', 0);
    queryClueListDefaultData();
}


function queryClueListDefaultData() {
    var dg = $('#clueList_Dg');
    queryClueList(1, dg.datagrid('options').pageSize);
    dg.datagrid('resize');
}

function queryClueList(pageNumber, pageSize) {
    var url = 'AdminLoadClueList';
    var values = $('.clueList-search-form').serialize();
    values += '&pageNumber=' + pageNumber + "&pageSize=" + pageSize;
    $.post(url, values, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            var dg = $('#clueList_Dg');

            dg.datagrid('loadData', result.rows);

            dg.datagrid('getPager').pagination({
                total: result.total, pageNumber: pageNumber, pageSize: pageSize
            });
        }
    });
}

//加载成功触发事件的挂载
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
    var titlePathElement = $('div.index-main-title');
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

function editCurrentRow(data, row, index) {

}


function validateClueListSearchForm() {
    // Validate EndTime > StartTime
    var acceptDateStart = $('input[name="JBKJXSLY_CBRCLRQ_Start"]').val();
    var acceptDateEnd = $('input[name="JBKJXSLY_CBRCLRQ_End"]').val();
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