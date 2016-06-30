function initializeOrgStructure() {
    //// Page style setting
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});

    //// Load the data grid data
    $('#orgStructureList_Dg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
//            {field: 'ID', title: '', checkbox: true, width: 50, align: 'center'},
//            {field: 'ID', title: 'ID', width: 100, align: 'center'},
            {field: 'OwnerID', title: '单位类型', width: 180, align: 'center', formatter: statusFormatterOrgnize},
            {field: 'DisplayName', title: '组织名称', width: 180, align: 'left'},
            {field: 'action', title: '操作', width: 100, align: 'center', formatter: actionFormatterOrgnize}
        ]],
        rownumbers: true,
        singleSelect: false,
        pagination: true,
        pageSize: 20,
        onLoadSuccess: onDataGridLoadSuccessOrgStructure
    });
    queryOrgStructureDefaultData();


    //// Load all dropdown data
//    loadSearchDropdown('cbJBKJXSLY_LYFS');
}

function actionFormatterOrgnize(data, row, index) {
    var sReturn = '';
//	sReturn = '<a href="javascript:;" onclick="editCurrentRow(' + row.JBKJXSLY_ID + ',' + index + ')">启用禁用</a>';
    sReturn += '<a href="javascript:;" onclick="editCurrentRow(' + row.ID + ',' + index + ')">修改</a>';
//	+ 
//		'<a href="javascript:;" onclick="editCurrentRow(' + row.ID + ',' + index + ')">设置权限</a>';
    return sReturn;
}

function statusFormatterOrgnize(value) {
    if (value == "a100000") {
        return "本院其他部门";
    } else if (value == "a200000") {
        return "其它中央国家机关（其它省直机关）";
    } else if (value == "a300000") {
        return "下级其它机关";
    } else if (value == "a400000") {
        return "下级检察院";
    } else {
        return "未设置";
    }
}

function queryOrgStructureDefaultData() {
    var dg = $('#orgStructureList_Dg');
    queryOrgStructure(1, dg.datagrid('options').pageSize);
    dg.datagrid('resize');
}

function queryOrgStructure(pageNumber, pageSize) {
    var url = 'AdminLoadOrgStructureList';
    var values = $('.orgStructureList-search-form').serialize();
    values += '&pageNumber=' + pageNumber + "&pageSize=" + pageSize; //&
    $.post(url, values, function (result) {
        result = (new Function('return ' + result))();

        var dg = $('#orgStructureList_Dg');
        dg.datagrid('loadData', result.rows);
        dg.datagrid('getPager').pagination({
            total: result.total, pageNumber: pageNumber, pageSize: pageSize
        });

        if (result.total <= 0) {
//            alert('未找到任何记录！');
        }
    });
}

function onDataGridLoadSuccessOrgStructure() {
    var page = $(this).datagrid("getPager");
    page.pagination({
        onSelectPage: function (pageNumber, pageSize) {
            queryOrgStructure(pageNumber, pageSize);
        },
        onChangePageSize: function (pageSize) {
            page.pagination({
                pageNumber: 1,
                pageSize: pageSize
            });
            queryOrgStructure(1, pageSize);
        }
    });
}


function submitOrgStructureListSearchForm() {
    if (validateOrgStructureListSearchForm()) {
        queryOrgStructureDefaultData();
    }
}

function resetOrgStructureListSearchForm() {
//    $('.orgStructureList-search-form .easyui-textbox').textbox('clear');
//    $('.orgStructureList-search-form .easyui-datebox').datebox('clear');
    $('.orgStructureList-search-form .easyui-combobox').combobox('select', 0);
    queryOrgStructureDefaultData();
}

function validateOrgStructureListSearchForm() {
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
 var selectedRows = $('#orgStructureList_Dg').datagrid('getSelections');

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
 queryOrgStructureDefaultData();
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
 queryOrgStructureDefaultData();
 } else {
 alert('未找到线索记录，删除失败。');
 }
 });
 }
 }

 function editCurrentRow(id, index) {
 $('#orgStructureList_Dg').datagrid('selectRow', index);
 alert('Edit row id: ' + id);
 }


 */
