var jbrIndex = 1;
var bjbrIndex = 1;

function initializeNewClue() {
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: '350px'});
    $('.index-main-title .right').show();

    initJbrTabs();
    initBjbrTabs();

    loadDropdown('cbJBKJXSLY_LYFS');
    loadDropdown('cbJBKJXSLY_LYZL');
    loadDropdown('cbJBKJXSLY_BJBRZTLB');
    loadDropdown('cbJBKJXSLY_ZJDW');
}


function initJbrTabs() {
    var jbrTabs = $('#newClue_jbrTabs');

    jbrTabs.tabs();

    jbrTabs.tabs({
        tools: '#newClue_jbrTabTools',
        onClose: function () {
            jbrIndex--;
            jbrTabs.tabs('select', jbrIndex - 1);
        }
    });

    jbrTabs.tabs('add', {
        title: '举报人' + jbrIndex,
        content: getJbrTableElement(jbrIndex),
        closable: false
    });
}

function getJbrTableElement(index) {
    var table = '';
    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label">姓名：</td>';
    table += '        <td><input type="text" name="JBKJXSLY_JBRXM' + index + '"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">*是否署名：</td>';
    table += '        <td width="160">';
    table += '            <input type="radio" checked="checked" name="JBKJXSLY_SFSM' + index + '"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFSM' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '        <td width="60">&nbsp;</td>';
    table += '        <td class="label">身份证号：</td>';
    table += '        <td><input type="text" name="JBKJXSLY_JBRSFZH' + index + '" style="width: 100%;"/></td>';
    table += '    </tr>';
    table += '    <tr><td>&nbsp;</td></tr>';
    table += '    <tr>';
    table += '        <td class="label">联系电话：</td>';
    table += '        <td><input type="text" name="JBKJXSLY_JBRDH' + index + '"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">来信地区：</td>';
    table += '        <td width="160"><select name="JBKJXSLY_LXDQ" id="cbJBKJXSLY_LXDQ' + index + '" style="width: 100%;"></select></td>';
    table += '        <td width="60">&nbsp;</td>';
    table += '        <td class="label">单位、住址：</td>';
    table += '        <td><input type="text" name="JBKJXSLY_JBRDWZZ' + index + '" style="width: 100%;"/></td>';
    table += '    </tr>';
    table += '</table>';

    return table;
}

function addJbrPanel() {
    if (jbrIndex < 3) {
        jbrIndex++;
        $('#newClue_jbrTabs').tabs('add', {
            title: '举报人' + jbrIndex,
            content: getJbrTableElement(jbrIndex),
            closable: false
        });
    } else {
        alert('举报人最大数量为：' + jbrIndex + '个!');
    }

}

function removeJbrPanel() {
    if (jbrIndex > 1) {
        var jbrTabs = $('#newClue_jbrTabs');

        jbrTabs.tabs('select', jbrIndex - 1);
        if (confirm('您确定要删除"举报人' + jbrIndex + '"的信息吗？')) {
            // tab start with index = 0
            jbrTabs.tabs('close', jbrIndex - 1);
        }
    }
}


function initBjbrTabs() {
    var bjbrTabs = $('#newClue_bjbrTabs');

    bjbrTabs.tabs();

    bjbrTabs.tabs({
        tools: '#newClue_bjbrTabTools',
        onClose: function () {
            bjbrIndex--;
            bjbrTabs.tabs('select', bjbrIndex - 1);
        }
    });

    bjbrTabs.tabs('add', {
        title: '被举报人' + bjbrIndex,
        content: getBjbrTableElement(bjbrIndex),
        closable: false
    });
}

function getBjbrTableElement(index) {
    var table = '';

    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label">*姓名：</td>';
    table += '        <td width="100"><input type="text" name="JBKJXSLY_JBRXM"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">性别：</td>';
    table += '        <td width="120">';
    table += '            <select name="" style="width:100%;">';
    table += '                <option value="0" selected>未知</option>';
    table += '                <option value="1">男</option>';
    table += '                <option value="2">女</option>';
    table += '            </select>';
    table += '        </td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">民族：</td>';
    table += '        <td width="160"><select name="" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">政治面貌：</td>';
    table += '        <td width="160"><select name="" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*案发地区：</td>';
    table += '        <td width="100"><select name="" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">*职级：</td>';
    table += '        <td width="100"><select name="" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">单位、住址：</td>';
    table += '        <td width="120" colspan="4"><input type="text" name="JBKJXSLY_JBRXM" style="width:99%;"/></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*身份：</td>';
    table += '        <td width="100"><select name="" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">特殊身份：</td>';
    table += '        <td width="100"><select name="" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">职务：</td>';
    table += '        <td width="120"><select name="" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">其他职务：</td>';
    table += '        <td width="120"><select name="" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '</table>';
    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label">*主要涉嫌性质：</td>';
    table += '        <td width="160"><select name="" style="width:100%;"></select></td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">其他涉嫌性质:</td>';
    table += '        <td width="160"><select name="" style="width:100%;"></select></td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">涉嫌领域：</td>';
    table += '        <td width="160"><select name="" style="width:100%;"></select></td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">涉嫌金额:</td>';
    table += '        <td width="80"><input type="text" name="JBKJXSLY_JBRXM"/></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*内容是否具体：</td>';
    table += '        <td>';
    table += '            <input type="radio" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">是否携款潜逃:</td>';
    table += '        <td>';
    table += '            <input type="radio" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio"/>&nbsp;否';
    table += '        </td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*是否属机关检察：</td>';
    table += '        <td>';
    table += '            <input type="radio" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">*是否控告:</td>';
    table += '        <td>';
    table += '            <input type="radio" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">*是否申诉：</td>';
    table += '        <td>';
    table += '            <input type="radio" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">*是否其他:</td>';
    table += '        <td>';
    table += '            <input type="radio" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio"/>&nbsp;否';
    table += '        </td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*举报内容：</td>';
    table += '        <td colspan="10"><textarea name="" cols="" rows="4" class="input6"';
    table += '                                   style="width: 99%;"></textarea></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*举报内容摘要：</td>';
    table += '        <td colspan="10"><textarea name="" cols="" rows="4" class="input6"';
    table += '                                   style="width: 99%;"></textarea></td>';
    table += '    </tr>';
    table += '</table>';
    table += '<ul class="newClue-attachment">';
    table += '    <li class="newClue-attachment-select-file"><a href="#">附件上传</a></li>';
    table += '    <li class="newClue-attachment-count">0个</li>';
    table += '    <li class="newClue-attachment-view"><a href="#">查看附件</a></li>';
    table += '</ul>';

    return table;
}

function addBjbrPanel() {
    if (bjbrIndex < 3) {
        bjbrIndex++;
        $('#newClue_bjbrTabs').tabs('add', {
            title: '被举报人' + bjbrIndex,
            content: getBjbrTableElement(bjbrIndex),
            closable: false
        });
    } else {
        alert('被举报人最大数量为：' + bjbrIndex + '个!');
    }

}

function removeBjbrPanel() {
    if (bjbrIndex > 1) {
        var bjbrTabs = $('#newClue_bjbrTabs');

        bjbrTabs.tabs('select', bjbrIndex - 1);
        if (confirm('您确定要删除"被举报人' + bjbrIndex + '"的信息吗？')) {
            // tab start with index = 0
            bjbrTabs.tabs('close', bjbrIndex - 1);
        }
    }
}


function submitNewClueForm() {
    //$('input[name="JBRXM"]').val('test');
    //alert($('#newClue_XS').serialize());
    //alert($('#newClue_CL').serialize());
    alert($('.newClue-bjbr-table').length);
}

function cancelNewClueForm() {
    $('.index-main-title .right').hide();
    window.location.reload();
}
