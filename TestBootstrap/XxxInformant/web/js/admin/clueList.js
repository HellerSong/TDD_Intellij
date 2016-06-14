/**
 * Created by Heller Song on 6/9/2016.
 */
function initializeClueList() {
    $('.easyui-linkbutton').linkbutton({});
    $('.easyui-textbox').textbox({});
    $('.easyui-datebox').datebox({});
    $('.easyui-combobox').combobox({});

    $('#clueListDg').datagrid({
        fit: true,
        striped: true,
        border: 1,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            // { field: 'bookId', title: '', checkbox:true, width: 50, align:'center' },
            {field: 'JBKJXSLY_SLRQ', title: '受理日期', width: 80, align: 'center'},
            {field: 'CBRCLRQ', title: '处理日期', width: 80, align: 'center'},
            {field: 'JBKJXSLY_BJBRXM', title: '被举报人姓名', width: 100, align: 'center'},
            {field: 'JBKJXSLY_BJBRDWZZ', title: '单位住址', width: 100, align: 'center'},
            {field: 'JBKJXSLY_ZJ', title: '职级', width: 100, align: 'center'},
            {field: 'JBKJXSLY_ZYSXXZ', title: '主要涉嫌性质', width: 100, align: 'center'},
            {field: 'JBKJXSLY_LYFS', title: '举报方式', width: 60, align: 'center'},
            {field: 'CBR', title: '承办人', width: 120, align: 'left'},
            {field: 'JBKJXSLY_CLZT', title: '状态', width: 80, align: 'left', formatter: statusFormatter},
            {field: 'action', title: '操作选项', width: 60, align: 'center', formatter: actionFormatter}
        ]],
        rownumbers: false,
        singleSelect: true,
        pagination: true,
        pageSize: 20,
        onLoadSuccess: onDataGridLoadSuccess
    });
    queryClueListDefaultData();

    //var myData = $.parseJSON(jsondata);
    //$('.clueList-datagrid').datagrid("loadData", myData);

    // $('#cbJBKJXSLY_LYFS').combobox({
    //     data: myData,
    //     valueField: 'id',
    //     textField: 'JBKJXSLY_BJBRXM'
    // });
}

function actionFormatter() {
    return '<a href="#">修改</a>&nbsp;&nbsp;<a href="#">删除</a>'
}

function statusFormatter(value) {
    if (value == 1) {
        return "未处理";
    } else if (value == 2) {
        return "已处理";
    }
}

function submitClueListSearchForm() {
    if (validate()) {
        queryClueListDefaultData();
    }
}

function resetClueListSearchForm() {
    $('.easyui-textbox').textbox('clear');
    $('.easyui-datebox').datebox('clear');
    $('.easyui-combobox').combobox('select', 0);
    queryClueListDefaultData();
}


function loadNewCluePage() {
    loadRightPanelContent('NewClue.html');
    var titlePathElement = $('div.index-main-title');
    var titlePath = titlePathElement.html() + '&nbsp;>&nbsp;<a href="#">新建窗口</a>';
    $('div.index-main-title').html(titlePath);
}

function queryClueListDefaultData() {
    queryClueList(1, $('#clueListDg').datagrid('options').pageSize);
}

function queryClueList(pageNumber, pageSize) {
    var url = 'AdminLoadClueList';
    var values = $('.clueList-search-form').serialize();
    values += '&pageNumber=' + pageNumber + "&pageSize=" + pageSize;
    $.post(url, values, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            var dg = $('#clueListDg');

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


function validate() {
    // // Validate EndTime > StartTime
    // var acceptDateStart = $('input[name="acceptDateStart"]').val();
    // var acceptDateEnd = $('input[name="acceptDateEnd"]').val();
    // var start = new Date(acceptDateStart.replace('-', '/').replace('-', '/'));
    // var end = new Date(acceptDateEnd.replace('-', '/').replace('-', '/'));
    //
    // if (acceptDateStart != null && acceptDateStart.length > 0 &&
    //     acceptDateEnd != null && acceptDateEnd.length > 0) {
    //     if (end < start) {
    //         alert('结束时间必须大于起始时间！');
    //         return false;
    //     }
    // }

    return true;
}