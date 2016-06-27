function loadAttachmentFileDg() {
    $('#viewAttach_fileDg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            // {field: 'JBKJXSLY_ID', title: '', checkbox: true, width: 50, align: 'center'},
            {field: 'fileName', title: '文件名称', width: 160, align: 'left'},
            {field: 'status', title: '状态', width: 50, align: 'left'},
            {field: 'serverPath', title: '服务器文件', width: 50, align: 'left', formatter: serverPathFormatter}
        ]],
        rownumbers: true,
        singleSelect: true,
        pagination: false
    });

    // load server files
    $.post('AdminLoadAttach', {clueId: window.clueId}, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            $('#viewAttach_fileDg').datagrid('loadData', result.rows);
        }
    });

    // load local files
    $.each(window.serverFileList, function (i, file) {
        alert(file);
        $('#viewAttach_fileDg').datagrid('appendRow', {
            fileName: file,
            status: '未上传',
            serverPath: 'ab'
        });
    });
}

function serverPathFormatter(value) {
    if (value != null && value.length > 0) {
        return '<a href="..' + value + '" target="_blank">查看</a>';
    }

}