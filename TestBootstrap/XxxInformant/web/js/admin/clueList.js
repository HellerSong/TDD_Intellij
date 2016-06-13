/**
 * Created by Heller Song on 6/9/2016.
 */
function initializeClueList() {
    $('.easyui-linkbutton').linkbutton();
    $('.easyui-textbox').textbox();
    $('.easyui-datebox').datebox();
    $('.easyui-combobox').combobox();
    $('.clueList-datagrid').datagrid({
        fit: true,
        striped: true,
        border: 1,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            // { field: 'bookId', title: '', checkbox:true, width: 50, align:'center' },
            // { field: 'userName', title: '受理日期', width: 80,align:'center' },
            // { field: 'userTel', title: '处理日期', width: 80,align:'center' },
            {field: 'id', title: 'id', width: 80, align: 'center'},
            {field: 'JBKJXSLY_BJBRXM', title: '被举报人姓名', width: 100, align: 'center'}
            // {field: 'JBKJXSLY_BJBRDWZZ', title: '单位住址', width: 100, align: 'center'},
            // {field: 'JBKJXSLY_ZJ', title: '职级', width: 100, align: 'center'},
            // {field: 'JBKJXSLY_ZYSXXZ', title: '主要涉嫌性质', width: 100, align: 'center'},
            // { field: 'bookStatus', title: '举报方式', width: 60,align:'center',formatter:statusFormatter },
            // { field: 'bookResultText', title: '承办人', width: 120,align:'left' },
            //{field: 'edit', title: '操作选项', width: 60, align: 'center'}
        ]],
        rownumbers: false,
        singleSelect: true,
        pagination: true,
        pageSize: 10,
        onLoadSuccess: onDataGridLoadSuccess('queryClueList')
    });
    var jsondata = '[' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"},' +
        '{"id":"M000005","JBKJXSLY_BJBRXM":"检测设备","sex":1,"birthday":"1991-03-17","addr":"双城市","classid":"03","grade":"78"}]';
    var data = $.parseJSON(jsondata);
    $('.clueList-datagrid').datagrid("loadData", data);
}

function submitClueListSearchForm() {
    if (validate()) {
        var values = $('.clueList-search-form').serialize();
        $.post('AdminLoadClueList', values, function (result) {
            result = (new Function('return ' + result))();

            if (result.total > 0) {
                alert(result.total);
            }
        });
    }
}

function resetClueListSearchForm() {
    $('.clueList-search-form .easyui-textbox').attr('value', '');
    $('.clueList-search-form .easyui-datebox').val('');
    //$('.clueList-search-form .easyui-textbox').val('');

}


function loadNewCluePage() {
    loadRightPanelContent('NewClue.html');
    var titlePathElement = $('div.index-main-title');
    var titlePath = titlePathElement.html() + '&nbsp;>&nbsp;<a href="#">新建窗口</a>';
    $('div.index-main-title').html(titlePath);
}

// 查询申请管理列表
function queryClueList(pageNumber, pageSize) {
    var url = 'AdminLoadClueList';
    var values = $('.clueList-search-form').serialize();
    values += '&pageNumber=' + pageNumber + "&pageSize=" + pageSize;
    $('.easyui-datagrid').datagrid("loadData", data);
    //loadDataGirdJson(url, values, '#applyList');
}


//加载成功触发
function onDataGridLoadSuccess(queryMethodName) {
    var page = $(this).datagrid("getPager");
    page.pagination({
        onSelectPage: function (pageNumber, pageSize) {
            window[queryMethodName](pageNumber, pageSize);
        },
        onChangePageSize: function (pageSize) {
            page.pagination({
                pageNumber: 1,
                pageSize: pageSize
            });
        }
    });
}

//加载数据datagrid数据
function loadDataGirdJson(url, param, target) {
    $.post(url, param, function (text) {
        var data = (new Function("return " + text))();
        $(target).datagrid("loadData", data);
        var pager = $(target).datagrid('getPager');
        pager.pagination({
            total: data.total, pageNumber: param.pageNum, pageSize: param.pageSize
        });
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