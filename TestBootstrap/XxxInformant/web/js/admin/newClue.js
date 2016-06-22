var jbrIndex = 1;
var bjbrIndex = 1;
var clueId = 0;

function initializeNewClue() {
    //// Page style setting
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});
    $('.easyui-combobox.limit-height').combobox({panelHeight: '350px'});
    $('.index-main-title .right').show();

    initJbrTabs();
    initBjbrTabs();


    //// Load "Clue Info" and "Clue Process" dropdown data
    loadDropdown('cbJBKJXSLY_LYFS');
    loadDropdown('cbJBKJXSLY_LYZL');
    loadDropdown('cbJBKJXSLY_BJBRZTLB');
    loadDropdown('cbJBKJXSLY_ZJDW');
    loadDropdown('cbCLFS');
    loadDropdown('cbZWDW');
    loadDropdown('cbCSDW');
    loadDropdown('cbJBKJXSLY_JYLX');


    var url = window.location.href;
    if (url.indexOf('EditClue') >= 0) {
        //// When this is editing clue then load it by id
        clueId = url.substring(url.indexOf('=') + 1, url.length);
        loadClueDataById(clueId)
    } else {
        //// Generate and set "Clue UUID" value
        var clueuuid = uuid();
        $('input[textboxname="JBKJXSLY_XH"]').textbox('setValue', '201606221000000002');
    }
}

function loadClueDataById(clueId) {
    $.post('AdminLoadClue', {clueId: clueId}, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            var pojo = result.rows[0];

            //// Set all "Clue Info" value
            $('#cbJBKJXSLY_LYFS').combobox('select', pojo.JBKJXSLY_LYFSDM);
            $('#cbJBKJXSLY_LYZL').combobox('select', pojo.JBKJXSLY_LYZLDM);
            $('#cbJBKJXSLY_BJBRZTLB').combobox('select', pojo.JBKJXSLY_BJBRZTLBDM);
            $('#cbJBKJXSLY_ZJDW').combobox('select', pojo.JBKJXSLY_ZJDWDM);
            $('input[textboxname="JBKJXSLY_SLRQ"]').textbox('setValue', pojo.JBKJXSLY_SLRQ);
            $('input[textboxname="JBKJXSLY_ZJR"]').textbox('setValue', pojo.JBKJXSLY_ZJR);
            $('input[textboxname="JBKJXSLY_XH"]').textbox('setValue', pojo.JBKJXSLY_XH);

            //// Set all "Clue Process" value
            $('#cbCLFS').combobox('select', pojo.CLFSDM);
            $('#cbZWDW').combobox('select', pojo.ZWDWDM);
            $('#cbCSDW').combobox('select', pojo.CSDWDM);
            $('#cbJBKJXSLY_JYLX').combobox('select', pojo.JBKJXSLY_JYLXDM);
            $('input[textboxname="CBR"]').textbox('setValue', pojo.CBR);
            $('input[textboxname="CSRQ"]').textbox('setValue', pojo.CSRQ);
            $('input[textboxname="CBRCLRQ"]').textbox('setValue', pojo.CBRCLRQ);
            $('input[textboxname="JBZRYJ"]').textbox('setValue', pojo.JBZRYJ);
            $('input[textboxname="CZYJ"]').textbox('setValue', pojo.CZYJ);
            $('input[textboxname="CZSPRQ"]').textbox('setValue', pojo.CZSPRQ);
            $('input[textboxname="JBKJXSLY_XJWH"]').textbox('setValue', pojo.JBKJXSLY_XJWH);
            $('input[textboxname="TZYJ"]').textbox('setValue', pojo.TZYJ);
            $('input[textboxname="TZSPRQ"]').textbox('setValue', pojo.TZSPRQ);
            $('input[textboxname="JCZPS"]').textbox('setValue', pojo.JCZPS);
            $('input[textboxname="JCZPSRQ"]').textbox('setValue', pojo.JCZPSRQ);
            $('input[textboxname="JBKJXSLY_CLQK"]').textbox('setValue', pojo.JBKJXSLY_CLQK);
            $('input[textboxname="JBKJXSLY_JDDD"]').textbox('setValue', pojo.JBKJXSLY_JDDD);
            $('input[textboxname="JBKJXSLY_HFRQ"]').textbox('setValue', pojo.JBKJXSLY_HFRQ);
            $('input[textboxname="JBKJXSLY_BZ"]').textbox('setValue', pojo.JBKJXSLY_BZ);

            //// Set all "Informer" value
            var informerCount = pojo.JBKJXSLY_SFSM.split('@#@').length;
            var i = 1;
            do {
                if (i > 1) {
                    addJbrTab();
                }
                $('input[name="JBKJXSLY_JBRXM' + i + '"]').val(pojo.JBKJXSLY_JBRXM.split('@#@')[i - 1]);
                checkRadioByValue('JBKJXSLY_SFSM' + i, pojo.JBKJXSLY_SFSM.split('@#@')[i - 1]);
                $('input[name="JBKJXSLY_JBRSFZH' + i + '"]').val(pojo.JBKJXSLY_JBRSFZH.split('@#@')[i - 1]);
                $('input[name="JBKJXSLY_JBRDH' + i + '"]').val(pojo.JBKJXSLY_JBRDH.split('@#@')[i - 1]);
                $('#cbJBKJXSLY_LXDQ' + i).combobox('select', pojo.JBKJXSLY_LXDQDM.split('@#@')[i - 1]);
                $('input[name="JBKJXSLY_JBRDWZZ' + i + '"]').val(pojo.JBKJXSLY_JBRDWZZ.split('@#@')[i - 1]);
                i++;
            } while (i <= informerCount);
            $('#newClue_jbrTabs').tabs('select', 0);

            //// Set all "BeInformer" value
            var beInformerCount = pojo.JBKJXSLY_BJBRXM.split('@#@').length;
            var j = 1;
            do {
                if (j > 1) {
                    addBjbrTab();
                }
                $('input[name="JBKJXSLY_BJBRXM' + j + '"]').val(pojo.JBKJXSLY_BJBRXM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_XB' + j).combobox('select', pojo.JBKJXSLY_XB.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_MZ' + j).combobox('select', pojo.JBKJXSLY_MZDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZZMM' + j).combobox('select', pojo.JBKJXSLY_ZZMMDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_AFDQ' + j).combobox('select', pojo.JBKJXSLY_AFDQDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZW' + j).combobox('select', pojo.JBKJXSLY_ZWDM.split('@#@')[j - 1]);
                $('input[name="JBKJXSLY_BJBRDWZZ' + j + '"]').val(pojo.JBKJXSLY_BJBRDWZZ.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_SF' + j).combobox('select', pojo.JBKJXSLY_SFDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_TSSF' + j).combobox('select', pojo.JBKJXSLY_TSSFDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZJ' + j).combobox('select', pojo.JBKJXSLY_ZJDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_QTZJ' + j).combobox('select', pojo.JBKJXSLY_QTZJDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZYSXXZ' + j).combobox('select', pojo.JBKJXSLY_ZYSXXZDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_CYSXXZ' + j).combobox('select', pojo.JBKJXSLY_QTSXXZDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_SALY' + j).combobox('select', pojo.JBKJXSLY_SALYDM.split('@#@')[j - 1]);
                $('input[name="JBKJXSLY_SXJE' + j + '"]').val(pojo.JBKJXSLY_SXJE.split('@#@')[j - 1]);
                checkRadioByValue('JBKJXSLY_NRSFJT' + j, pojo.JBKJXSLY_NRSFJT.split('@#@')[j - 1]);
                checkRadioByValue('JBKJXSLY_SFXKQT' + j, pojo.JBKJXSLY_SFXKQT.split('@#@')[j - 1]);
                j++;
            } while (j <= beInformerCount);
            $('#newClue_bjbrTabs').tabs('select', 0);
        }
    });
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
        content: getJbrTabElement(jbrIndex),
        closable: false
    });
}

function getJbrTabElement(index) {
    var table = '';
    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label">姓名：</td>';
    table += '        <td><input type="text" name="JBKJXSLY_JBRXM' + index + '"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label"><span class="span-must-fill">*</span>是否署名：</td>';
    table += '        <td width="160">';
    table += '            <input type="radio" checked="checked" name="JBKJXSLY_SFSM' + index + '" value="1"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFSM' + index + '" value="0"/>&nbsp;否';
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
    table += '        <td width="160"><select id="cbJBKJXSLY_LXDQ' + index + '" name="JBKJXSLY_LXDQ' + index + '" style="width: 100%;"></select></td>';
    table += '        <td width="60">&nbsp;</td>';
    table += '        <td class="label">单位、住址：</td>';
    table += '        <td><input type="text" name="JBKJXSLY_JBRDWZZ' + index + '" style="width: 100%;"/></td>';
    table += '    </tr>';
    table += '</table>';

    return table;
}

function addJbrTab() {
    if (jbrIndex < 3) {
        jbrIndex++;
        $('#newClue_jbrTabs').tabs('add', {
            title: '举报人' + jbrIndex,
            content: getJbrTabElement(jbrIndex),
            closable: false
        });
    } else {
        alert('举报人最大数量为：' + jbrIndex + '个!');
    }

}

function removeJbrTab() {
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
            loadDropdown('cbJBKJXSLY_ZW' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_SF' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_TSSF' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZJ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_QTZJ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZYSXXZ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_CYSXXZ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_SALY' + bjbrIndex);
            $('#cbJBKJXSLY_XB' + bjbrIndex).combobox({panelHeight: 'auto'});
        }
    });

    bjbrTabs.tabs('add', {
        title: '被举报人' + bjbrIndex,
        content: getBjbrTabElement(bjbrIndex),
        closable: false
    });
}

function getBjbrTabElement(index) {
    var table = '';

    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>姓名：</td>';
    table += '        <td width="100"><input type="text" name="JBKJXSLY_BJBRXM' + index + '"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">性别：</td>';
    table += '        <td width="120">';
    table += '            <select id="cbJBKJXSLY_XB' + index + '" name="JBKJXSLY_XB' + index + '" style="width:100%;">';
    table += '                <option value="0" selected>未知</option>';
    table += '                <option value="1">男</option>';
    table += '                <option value="2">女</option>';
    table += '            </select>';
    table += '        </td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">民族：</td>';
    table += '        <td width="120"><select id="cbJBKJXSLY_MZ' + index + '" name="JBKJXSLY_MZ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">政治面貌：</td>';
    table += '        <td width="120"><select id="cbJBKJXSLY_ZZMM' + index + '" name="JBKJXSLY_ZZMM' + index + '" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>案发地区：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_AFDQ' + index + '" name="JBKJXSLY_AFDQ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">职务：</td>';
    table += '        <td width="120"><select id="cbJBKJXSLY_ZW' + index + '" name="JBKJXSLY_ZW' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">单位、住址：</td>';
    table += '        <td width="120" colspan="4"><input type="text" name="JBKJXSLY_BJBRDWZZ' + index + '" style="width:99%;"/></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>身份：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_SF' + index + '" name="JBKJXSLY_SF' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">特殊身份：</td>';
    table += '        <td width="100"><select id="cbJBKJXSLY_TSSF' + index + '" name="JBKJXSLY_TSSF' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label"><span class="span-must-fill">*</span>职级：</td>';
    table += '        <td width="120"><select id="cbJBKJXSLY_ZJ' + index + '" name="JBKJXSLY_ZJ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">其他职级：</td>';
    table += '        <td width="120"><select id="cbJBKJXSLY_QTZJ' + index + '" name="JBKJXSLY_QTZJ' + index + '" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '</table>';
    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>主要涉嫌性质：</td>';
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
    table += '        <td class="label"><span class="span-must-fill">*</span>内容是否具体：</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_NRSFJT' + index + '" checked="checked" value="1"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_NRSFJT' + index + '" value="0"/>&nbsp;否';
    table += '        </td>';
    table += '        <td>&nbsp;</td>';
    table += '        <td class="label">是否携款潜逃:</td>';
    table += '        <td>';
    table += '            <input type="radio" name="JBKJXSLY_SFXKQT' + index + '" checked="checked" value="1"/>&nbsp;是&nbsp;&nbsp;';
    table += '            <input type="radio" name="JBKJXSLY_SFXKQT' + index + '" value="0"/>&nbsp;否';
    table += '        </td>';
    table += '    </tr>';
    table += '</table>';

    return table;
}

function addBjbrTab() {
    if (bjbrIndex < 3) {
        bjbrIndex++;
        $('#newClue_bjbrTabs').tabs('add', {
            title: '被举报人' + bjbrIndex,
            content: getBjbrTabElement(bjbrIndex),
            closable: false
        });
    } else {
        alert('被举报人最大数量为：' + bjbrIndex + '个!');
    }

}

function removeBjbrTab() {
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
    //alert($("#newClue_file")[0].files[1].name);
    var values = document.getElementById("newClue_file").files[0];
    $('#newClue_file').upload('AdminSaveClueFile', {}, function (result) {
        if (result.total > 0) {

        }
    }, 'json');

    //alert(document.getElementById("newClue_file").files[1].name);
    //alert($('#newClue_file').files[0].name);
}


function submitNewClueForm() {
    //alert($('#newClue_formCL').serialize());
    var values = $('#newClue_formXS').serialize() + '&' +
        $('#newClue_formJBR').serialize() + '&' +
        $('#newClue_formBJBR_Part1').serialize() + '&' +
        $('#newClue_formBJBR_Part2').serialize() + '&' +
        $('#newClue_formCL').serialize() + '&' +
        'clueId=' + clueId;

    //alert(values);
    if (validateNewClue()) {
        // $('#clue_fileUpload').upload('AdminSaveClue', values, function (result) {
        $.post('AdminSaveClue', values, function (result) {
            result = (new Function('return ' + result))();

            alert(result.status);
            if (result.status.indexOf('成功') >= 0) {
                closeNewClueForm();
            }
        });
    }
}

function closeNewClueForm() {
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
