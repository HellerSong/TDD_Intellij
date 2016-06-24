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
    $.post('AdminLoadAttach', {clueId: clueId}, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            $('#viewAttach_fileDg').datagrid('loadData', result.rows);
        }
    });
    // $('#viewAttach_fileDg').datagrid('acceptChanges');
    //
    // // $('#viewAttach_fileDg').datagrid('reload');
    //
    // var row = '{"fileName":"附件22","status":"已上传","serverPath":"1b5d12a4-a8f2-4ecc-85fd-e298e6c49d48.sql"}';
    //
    // $('#viewAttach_fileDg').datagrid('appendRow', row);

    // load local files
    $.each(serverFileList, function (i, file) {
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