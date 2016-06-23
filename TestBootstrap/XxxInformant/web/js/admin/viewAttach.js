function loadAttachmentFileDg() {
    alert('run load attacgh');
    $('#viewAttach_fileDg').datagrid();

    $('#viewAttach_fileDg').datagrid({
        fit: true,
        striped: true,
        fitColumns: true,
        nowrap: false,
        loadMsg: "正在加载中。。。",
        columns: [[
            // {field: 'JBKJXSLY_ID', title: '', checkbox: true, width: 50, align: 'center'},
            {field: 'fileName', title: '文件名称', width: 80, align: 'center'},
            {field: 'CBRCLRQ', title: '操作', width: 80, align: 'center'},
            {field: 'action', title: '操作选项', width: 70, align: 'center', formatter: fileActionFormatter}
        ]],
        rownumbers: false,
        singleSelect: false,
        pagination: false
    });
}

function fileActionFormatter() {

}