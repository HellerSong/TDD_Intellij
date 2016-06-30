function initializeRoleAccess() {
    //// Page style setting
//    $.parser.parse();

    //// Load the data grid data
    $('#roleList_Dg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
//            {field: 'roleId', title: '', checkbox: true, width: 50, align: 'center'},
            {field: 'RoleName', title: '角色名称', width: 100, align: 'center'},
            {field: 'RMemo', title: '说　　　明', width: 280, align: 'left'}
        ]],
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        pageSize: 20,
        onLoadSuccess: onDataGridLoadSuccessRole
    });
    queryRoleListDefaultData();
}

function queryRoleListDefaultData() {
    var dg = $('#roleList_Dg');
    queryRoleList(1, dg.datagrid('options').pageSize);
    dg.datagrid('resize');
}

function queryRoleList(pageNumber, pageSize) {
    var url = 'AdminLoadRoleList';
    var values = '';
    values += 'pageNumber=' + pageNumber + "&pageSize=" + pageSize;
    $.post(url, values, function (result) {
        result = (new Function('return ' + result))();

        var dg = $('#roleList_Dg');
        dg.datagrid('loadData', result.rows);
        dg.datagrid('getPager').pagination({
            total: result.total, pageNumber: pageNumber, pageSize: pageSize
        });

        if (result.total <= 0) {
//            alert('未找到任何记录！');
        }
    });
}

function onDataGridLoadSuccessRole() {
    var page = $(this).datagrid("getPager");
    page.pagination({
        onSelectPage: function (pageNumber, pageSize) {
            queryRoleList(pageNumber, pageSize);
        },
        onChangePageSize: function (pageSize) {
            page.pagination({
                pageNumber: 1,
                pageSize: pageSize
            });
            queryRoleList(1, pageSize);
        }
    });
}