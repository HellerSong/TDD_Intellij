var jbrIndex = 1;
var bjbrIndex = 1;

function initializeNewClue() {
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});
    $('.easyui-combobox.limit-height').combobox({panelHeight: '350px'});
    $('.index-main-title .right').show();

    initJbrTabs();
    initBjbrTabs();

    loadDropdown('cbJBKJXSLY_LYFS');
    loadDropdown('cbJBKJXSLY_LYZL');
    loadDropdown('cbJBKJXSLY_BJBRZTLB');
    loadDropdown('cbJBKJXSLY_ZJDW');
    loadDropdown('cbCLYJ');
    loadDropdown('cbZWDW');
    loadDropdown('cbCSDW');
    loadDropdown('cbJBKJXSLY_JYLX');
}


function initJbrTabs() {
    var jbrTabs = $('#newClue_jbrTabs');

    jbrTabs.tabs();

    jbrTabs.tabs({
        tools: '#newClue_jbrTabTools',
        onClose: function () {
            jbrIndex--;
            jbrTabs.tabs('select', jbrIndex - 1);
        },
        onAdd: function () {
            loadDropdown('cbJBKJXSLY_LXDQ' + jbrIndex);
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
    table += '        <td width="160"><select id="cbJBKJXSLY_LXDQ' + index + '" name="JBKJXSLY_LXDQ' + index + '"></select></td>';
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
        },
        onAdd: function () {
            loadDropdown('cbJBKJXSLY_MZ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZZMM' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_AFDQ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZJ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_SF' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_TSSF' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZW' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_QTZW' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZYSXXZ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_CYSXXZ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_SALY' + bjbrIndex);
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
    table += '        <td width="100"><input type="text" name="JBKJXSLY_BJBRXM' + index + '"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">性别：</td>';
    table += '        <td width="120">';
    table += '            <select name="JBKJXSLY_XB' + index + '" style="width:100%;">';
    table += '                <option value="0" selected>未知</option>';
    table += '                <option value="1">男</option>';
    table += '                <option value="2">女</option>';
    table += '            </select>';
    table += '        </td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">民族：</td>';
    table += '        <td width="160"><select id="cbJBKJXSLY_MZ' + index + '" name="JBKJXSLY_MZ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">政治面貌：</td>';
    table += '        <td width="160"><select id="cbJBKJXSLY_ZZMM' + index + '" name="JBKJXSLY_ZZMM' + index + '" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*案发地区：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_AFDQ' + index + '" name="JBKJXSLY_AFDQ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">*职级：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_ZJ' + index + '" name="JBKJXSLY_ZJ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">单位、住址：</td>';
    table += '        <td width="120" colspan="4"><input type="text" name="JBKJXSLY_BJBRDWZZ' + index + '" style="width:99%;"/></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*身份：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_SF' + index + '" name="JBKJXSLY_SF' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">特殊身份：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_TSSF' + index + '" name="JBKJXSLY_TSSF' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">职务：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_ZW' + index + '" name="JBKJXSLY_ZW' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">其他职务：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_QTZW' + index + '" name="JBKJXSLY_QTZW' + index + '" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '</table>';
    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label">*主要涉嫌性质：</td>';
    table += '        <td width="120"><select id="cbJBKJXSLY_ZYSXXZ' + index + '" name="JBKJXSLY_ZYSXXZ' + index + '" style="width:100%;"></select></td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">其他涉嫌性质:</td>';
    table += '        <td width="140"><select id="cbJBKJXSLY_CYSXXZ' + index + '" name="JBKJXSLY_CYSXXZ' + index + '" style="width:100%;"></select></td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">涉嫌领域：</td>';
    table += '        <td width="140"><select id="cbJBKJXSLY_SALY' + index + '" name="JBKJXSLY_SALY' + index + '" style="width:100%;"></select></td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">涉嫌金额:</td>';
    table += '        <td width="120"><input type="text" name="JBKJXSLY_SXJE' + index + '"/></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*内容是否具体：</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_NRSFJT' + index + '" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_NRSFJT' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">是否携款潜逃:</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_SFXKQT' + index + '" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFXKQT' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*是否属机关检察：</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_SFSBYGX' + index + '" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFSBYGX' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">*是否控告:</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_SFKG' + index + '" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFKG' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">*是否申诉：</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_SFSS' + index + '" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFSS' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">*是否其他:</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_SFQT' + index + '" checked="checked"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFQT' + index + '"/>&nbsp;否';
    table += '        </td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*举报内容：</td>';
    table += '        <td colspan="10"><textarea name="JBKJXSLY_SYZY' + index + '" rows="5" style="width: 99%;"></textarea></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label">*举报内容摘要：</td>';
    table += '        <td colspan="10"><textarea name="JBKJXSLY_NRZY' + index + '" rows="3" style="width: 99%;"></textarea></td>';
    table += '    </tr>';
    table += '</table>';
    table += '<ul class="newClue-attachment">';
    table += '    <input type="file" id="newClue_file" onchange="updateFileSelection()" hidden>';
    table += '    <li class="newClue-attachment-select-file"><a href="javascript:;" onclick="selectFiles()">添加附件</a></li>';
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

function selectFiles() {
    $('#newClue_file').click();
}

function updateFileSelection() {
    alert($('#newClue_file').val());
}

function submitNewClueForm() {
    //alert($('#newClue_formCL').serialize());
    var values = $('#newClue_formXS').serialize() + '&' +
        $('#newClue_formJBR').serialize() + '&' +
        $('#newClue_formBJBR').serialize() + '&' +
        $('#newClue_formCL').serialize();

    //alert($('#newClue_formBJBR').serialize());

    if (validateNewClue()) {
        // $('#clue_fileUpload').upload('AdminSaveClue', values, function (result) {
        $.post('AdminSaveClue', values, function (result) {
            result = (new Function('return ' + result))();

            if (result.status.indexOf('成功') >= 0) {
                alert(result.status);
                //window.close();
            } else {
                alert(result.status);
            }
        });
    }
}

function cancelNewClueForm() {
    $('.index-main-title .right').hide();
    window.location.reload();
}


function validateNewClue() {
    var temp;

    // /** Clue Inform Part Validate **/
    // temp = $.trim($('select[name="informantTypeValue"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请选择举报方式！');
    //     return false;
    // }
    //
    // temp = $.trim($('input[name="informantName"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请输入被举报人姓名！');
    //     return false;
    // }
    //
    // temp = $.trim($('select[name="mainKindValue"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请选择被举报人主要涉嫌性质！');
    //     return false;
    // }
    //
    // temp = $.trim($('select[name="identityValue"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请选择被举报人身份！');
    //     return false;
    // }
    //
    // temp = $.trim($('input[name="clueTitle"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请输入举报内容标题！');
    //     return false;
    // }
    // temp = $.trim($('textarea[name="clueContent"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请输入举报内容！');
    //     return false;
    // }
    //
    // /** Clue Process Part Validate **/
    // temp = $.trim($('select[name="processType"]').val());
    // if (temp == null || temp.length == 0) {
    //     alert('请选择处理方式！');
    //     return false;
    // }

    return true;
}
