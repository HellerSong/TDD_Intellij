function loadAttachmentFileDg() {
    $('#viewAttach_fileDg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            {field: 'fileName', title: '文件名称', width: 160, align: 'left'},
            {field: 'status', title: '状态', width: 50, align: 'left'},
            {field: 'serverPath', title: '服务器文件', width: 50, align: 'left', formatter: serverPathFormatter}
        ]],
        rownumbers: true,
        singleSelect: true,
        pagination: false
    });

    // load server files to data grid
    $.post('AdminLoadAttach', {clueId: window.clueId}, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            $('#viewAttach_fileDg').datagrid('loadData', result.rows);

        }
    });

    // load local files to data grid
    $.each(window.localFileList, function (i, file) {
        $('#viewAttach_fileDg').datagrid('appendRow', {
            fileName: file,
            status: '未上传',
            serverPath: 'aa'
        });
    });
}

function serverPathFormatter(value) {
    if (value != null && value.length > 0) {
        return '<a href="..' + value + '" target="_blank">查看</a>';
    }

}