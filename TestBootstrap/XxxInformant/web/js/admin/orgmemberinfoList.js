function initializeOrgMemberList() {
    //// Page style setting
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});

    //// Load the data grid data
    $('#orgmemberinfoList_Dg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            {field: 'ID', title: '', checkbox: true, width: 50, align: 'center'},
            {field: 'LoginID', title: '用户名', width: 100, align: 'center'},
            {field: 'DisplayName', title: '真实姓名', width: 80, align: 'center'},
            {field: 'RoleName', title: '角色', width: 80, align: 'center'},
            {field: 'DeptName', title: '部门名称', width: 100, align: 'center'},
            {field: 'OrgName', title: '院市名称', width: 120, align: 'center'},
            {field: 'crTime', title: '创建时间', width: 80, align: 'center'},
//            {field: 'status', title: '状态', width: 60, align: 'center', formatter: statusFormatterOrg},
            {field: 'action', title: '操作', width: 100, align: 'center', formatter: actionFormatterOrg}
        ]],
        rownumbers: true,
        singleSelect: false,
        pagination: true,
        pageSize: 20,
        onLoadSuccess: onDataGridLoadSuccessOrgMember
    });
    queryOrgMemberListDefaultData();


    //// Load all dropdown data
//    loadSearchDropdown('cbJBKJXSLY_LYFS');
}

function actionFormatterOrg(data, row, index) {
    var sReturn = '';
//	sReturn = '<a href="javascript:;" onclick="editCurrentRow(' + row.JBKJXSLY_ID + ',' + index + ')">启用禁用</a>';
    sReturn += '<a href="javascript:;" onclick="editCurrentRow(' + row.ID + ',' + index + ')">修改</a>' +
        '<a href="javascript:;" onclick="editCurrentRow(' + row.ID + ',' + index + ')">设置权限</a>';
    return sReturn;
}

function statusFormatterOrg(value) {
    if (value == 0) {
        return "待处理";
    } else if (value == 1) {
        return "启用";
    } else if (value == -1) {
        return "禁用";
    }
}

function queryOrgMemberListDefaultData() {
    var dg = $('#orgmemberinfoList_Dg');
    queryOrgMemberList(1, dg.datagrid('options').pageSize);
    dg.datagrid('resize');
}

function queryOrgMemberList(pageNumber, pageSize) {
    var url = 'AdminLoadOrgmemberinfoList';
    var values = $('.orgmemberinfoList-search-form').serialize();
    values += '&pageNumber=' + pageNumber + "&pageSize=" + pageSize; //&
    $.post(url, values, function (result) {
        result = (new Function('return ' + result))();

        var dg = $('#orgmemberinfoList_Dg');
        dg.datagrid('loadData', result.rows);
        dg.datagrid('getPager').pagination({
            total: result.total, pageNumber: pageNumber, pageSize: pageSize
        });

        if (result.total <= 0) {
//            alert('未找到任何记录！');
        }
    });
}

function onDataGridLoadSuccessOrgMember() {
    var page = $(this).datagrid("getPager");
    page.pagination({
        onSelectPage: function (pageNumber, pageSize) {
            queryOrgMemberList(pageNumber, pageSize);
        },
        onChangePageSize: function (pageSize) {
            page.pagination({
                pageNumber: 1,
                pageSize: pageSize
            });
            queryOrgMemberList(1, pageSize);
        }
    });
}

function submitOrgmemberinfoListSearchForm() {
    if (validateOrgmemberinfoListSearchForm()) {
        queryOrgMemberListDefaultData();
    }
}

function resetOrgmemberinfoListSearchForm() {
    $('.orgmemberinfoList-search-form .easyui-textbox').textbox('clear');
//    $('.orgmemberinfoList-search-form .easyui-datebox').datebox('clear');
    $('.orgmemberinfoList-search-form .easyui-combobox').combobox('select', 0);
    queryOrgMemberListDefaultData();
}

function validateOrgmemberinfoListSearchForm() {
    //// Validate EndTime > StartTime
//    var acceptDateStart = $('input[name="JBKJXSLY_SLRQ_Start"]').val();
//    var acceptDateEnd = $('input[name="JBKJXSLY_SLRQ_End"]').val();
//    var start = new Date(acceptDateStart.replace('-', '/').replace('-', '/'));
//    var end = new Date(acceptDateEnd.replace('-', '/').replace('-', '/'));
//
//    if (acceptDateStart != null && acceptDateStart.length > 0 &&
//        acceptDateEnd != null && acceptDateEnd.length > 0) {
//        if (end < start) {
//            alert('结束时间必须大于起始时间！');
//            return false;
//        }
//    }

    return true;
}

/*
 function loadNewCluePage() {
 loadRightPanelContent('NewClue.html');
 var titlePathElement = $('div.index-main-title');
 var titlePath = titlePathElement.html() + '&nbsp;>&nbsp;<a href="#">新建窗口</a>';
 titlePathElement.html(titlePath);
 }

 function deleteAllSelectedRows() {
 var selectedRows = $('#orgmemberinfoList_Dg').datagrid('getSelections');

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
 queryOrgMemberListDefaultData();
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
 queryOrgMemberListDefaultData();
 } else {
 alert('未找到线索记录，删除失败。');
 }
 });
 }
 }

 function editCurrentRow(id, index) {
 $('#orgmemberinfoList_Dg').datagrid('selectRow', index);
 alert('Edit row id: ' + id);
 }


 */
