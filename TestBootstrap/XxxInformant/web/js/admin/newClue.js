var jbrIndex = 1;
var bjbrIndex = 1;
window.clueId = 0;
window.localFileList = [];
window.serverFileCount = 0;

function initializeNewClue() {
    window.localFileList = [];
    window.serverFileCount = 0;

    //// Page style setting
    $.parser.parse();
    $('.easyui-datebox').datebox({editable: false});
    $('.index-main-title .right').show();


    //// Initialize tabs panel
    initJbrTabs();
    initBjbrTabs();

    //// Load "Clue Info" and "Clue Process" dropdown data
    loadDropdown('cbJBKJXSLY_LYFS');
    loadDropdown('cbJBKJXSLY_LYZL');
    loadDropdown('cbJBKJXSLY_BJBRZTLB');
    loadTreeDropdownCompany('cbJBKJXSLY_ZJDW');
    loadDropdown('cbCLFS');
    loadTreeDropdownCompany('cbZWDW');
    loadTreeDropdownCompany('cbCSDW');
    loadDropdown('cbJBKJXSLY_JYLX');

    //// 判断当前是编辑线索状态还是创建新线索状态
    var url = window.location.href;
    if (url.indexOf('EditClue') >= 0) {
        // When this is editing clue then load it by id
        clueId = url.substring(url.indexOf('=') + 1, url.length);
        loadClueDataById(clueId);
    } else {
        $('input[textboxname="JBKJXSLY_XH"]').textbox('setValue', '数据库自动生成');

    }
}

function loadClueDataFirst() {
    loadClueDataByGetType('First');
}

function loadClueDataPrevious() {
    loadClueDataByGetType('Previous');
}

function loadClueDataNext() {
    loadClueDataByGetType('Next');
}

function loadClueDataLast() {
    loadClueDataByGetType('Last');
}

function loadClueDataByGetType(getType) {
    var values = 'getType=' + getType;
    values += '&clueId=' + window.clueId + '&isALLClue=' + window.isALLClue + '&isXSClue=' + window.isXSClue +
        '&isJCGJClue=' + window.isJCGJClue + '&isTJJClue=' + window.isTJJClue +
        '&isSBJClue=' + window.isSBJClue;

    $.post('AdminGetClueId', values, function (result) {
        result = (new Function('return ' + result))();

        if (result.targetClueId != -1) {
            loadClueDataById(result.targetClueId);
            clueId = result.targetClueId;
            var oldUrl = window.location.href;
            oldUrl = oldUrl.substring(0, oldUrl.indexOf('=') + 1);
            window.location.href = oldUrl + result.targetClueId;
        }
    });
}

function displayFirstAndTailBtnOrNot(clueId) {
    var values = 'clueId=' + clueId + '&isALLClue=' + window.isALLClue + '&isXSClue=' + window.isXSClue +
        '&isJCGJClue=' + window.isJCGJClue + '&isTJJClue=' + window.isTJJClue +
        '&isSBJClue=' + window.isSBJClue;

    $.post('AdminFirstOrLastClue', values, function (result) {
        result = (new Function('return ' + result))();

        var btnList = $('.index-main-title .right span.index-edit-btn-group a');
        if (result.msg.indexOf('FirstClue') >= 0) {
            btnList.eq(0).hide();
            btnList.eq(1).hide();
            btnList.eq(2).show();
            btnList.eq(3).show();
        } else if (result.msg.indexOf('LastClue') >= 0) {
            btnList.eq(0).show();
            btnList.eq(1).show();
            btnList.eq(2).hide();
            btnList.eq(3).hide();
        } else if (result.msg.indexOf('OneClue') >= 0) {
            btnList.hide();
        } else {
            btnList.show();
        }
        $('.index-main-title .right span.index-edit-btn-group').show();
    });
}

function loadClueDataById(clueId) {
    displayFirstAndTailBtnOrNot(clueId);

    $.post('AdminLoadClue', {clueId: clueId}, function (result) {
        result = (new Function('return ' + result))();

        if (result.total > 0) {
            var pojo = result.rows[0];

            //// Set all "Clue Info" value
            $('#cbJBKJXSLY_LYFS').combobox('select', pojo.JBKJXSLY_LYFSDM);
            $('#cbJBKJXSLY_LYZL').combobox('select', pojo.JBKJXSLY_LYZLDM);
            $('#cbJBKJXSLY_BJBRZTLB').combobox('select', pojo.JBKJXSLY_BJBRZTLBDM);
            $('#cbJBKJXSLY_ZJDW').combotree('setValue', pojo.JBKJXSLY_ZJDWDM);
            $('input[textboxname="JBKJXSLY_SLRQ"]').textbox('setValue', pojo.JBKJXSLY_SLRQ);
            $('input[textboxname="JBKJXSLY_ZJR"]').textbox('setValue', pojo.JBKJXSLY_ZJR);
            $('input[textboxname="JBKJXSLY_XH"]').textbox('setValue', pojo.JBKJXSLY_XH);

            //// Set all "Clue Process" value
            $('#cbCLFS').combobox('select', pojo.CLFSDM);
            $('#cbZWDW').combotree('setValue', pojo.ZWDWDM);
            $('#cbCSDW').combotree('setValue', pojo.CSDWDM);
            $('#cbJBKJXSLY_JYLX').combobox('select', pojo.JBKJXSLY_JYLXDM);
            $('input[textboxname="CBR"]').textbox('setValue', pojo.CBR);
            $('input[textboxname="CBRCLRQ"]').textbox('setValue', pojo.CBRCLRQ);
            checkRadioByValue('JBKJXSLY_JBJCGJWFWJ', pojo.JBKJXSLY_JBJCGJWFWJ);
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
                checkRadioByValue('JBKJXSLY_SFSM' + i, pojo.JBKJXSLY_SFSM.split('@#@')[i - 1]);
                if (pojo.JBKJXSLY_SFSM.split('@#@')[i - 1] == 1) {
                    updateSignature(i, true);
                } else {
                    updateSignature(i, false);
                }

                if (pojo.JBKJXSLY_SFSM.split('@#@')[i - 1] == 1) {
                    $('input[name="JBKJXSLY_JBRXM' + i + '"]').val(pojo.JBKJXSLY_JBRXM.split('@#@')[i - 1]);
                    $('input[name="JBKJXSLY_JBRSFZH' + i + '"]').val(pojo.JBKJXSLY_JBRSFZH.split('@#@')[i - 1]);
                    $('input[name="JBKJXSLY_JBRDH' + i + '"]').val(pojo.JBKJXSLY_JBRDH.split('@#@')[i - 1]);
                    $('input[name="JBKJXSLY_JBRDWZZ' + i + '"]').val(pojo.JBKJXSLY_JBRDWZZ.split('@#@')[i - 1]);
                }
                $('#cbJBKJXSLY_LXDQ' + i).combotree('setValue', pojo.JBKJXSLY_LXDQDM.split('@#@')[i - 1]);

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
                $('#cbJBKJXSLY_AFDQ' + j).combotree('setValue', pojo.JBKJXSLY_AFDQDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZW' + j).combobox('select', pojo.JBKJXSLY_ZWDM.split('@#@')[j - 1]);
                $('input[name="JBKJXSLY_BJBRDW' + j + '"]').val(pojo.JBKJXSLY_BJBRDW.split('@#@')[j - 1]);
                $('input[name="JBKJXSLY_BJBRZZ' + j + '"]').val(pojo.JBKJXSLY_BJBRZZ.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_SF' + j).combotree('setValue', pojo.JBKJXSLY_SFDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_TSSF' + j).combotree('setValue', pojo.JBKJXSLY_TSSFDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZJ' + j).combobox('select', pojo.JBKJXSLY_ZJDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_ZYSXXZ' + j).combotree('setValue', pojo.JBKJXSLY_ZYSXXZDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_CYSXXZ' + j).combotree('setValue', pojo.JBKJXSLY_QTSXXZDM.split('@#@')[j - 1]);
                $('#cbJBKJXSLY_SALY' + j).combobox('select', pojo.JBKJXSLY_SALYDM.split('@#@')[j - 1]);
                $('input[name="JBKJXSLY_SXJE' + j + '"]').val(pojo.JBKJXSLY_SXJE.split('@#@')[j - 1]);
                j++;
            } while (j <= beInformerCount);
            $('#newClue_bjbrTabs').tabs('select', 0);
            // checkRadioByValue('JBKJXSLY_SFSBYGX', pojo.JBKJXSLY_SFSBYGX);
            // checkRadioByValue('JBKJXSLY_SFKG', pojo.JBKJXSLY_SFKG);
            // checkRadioByValue('JBKJXSLY_SFSS', pojo.JBKJXSLY_SFSS);
            // checkRadioByValue('JBKJXSLY_SFQT', pojo.JBKJXSLY_SFQT);
            $('input[textboxname="JBKJXSLY_Keywords"]').textbox('setValue', pojo.JBKJXSLY_Keywords);
            $('input[textboxname="JBKJXSLY_SYZY"]').textbox('setValue', pojo.JBKJXSLY_SYZY);
            $('input[textboxname="JBKJXSLY_NRZY"]').textbox('setValue', pojo.JBKJXSLY_NRZY);

            //// Set attachment file value
            $.post('AdminLoadAttach', {clueId: clueId}, function (result) {
                result = (new Function('return ' + result))();

                if (result.total > 0) {
                    window.serverFileCount = result.total;

                    var fileCount = Number(window.localFileList.length) + Number(window.serverFileCount);
                    $('.newClue-attachment-view>a').html('查看附件(' + fileCount + ')');
                }
            });
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
            loadTreeDropdownZone('cbJBKJXSLY_LXDQ' + jbrIndex);
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
    table += '            <input type="radio" checked="checked" name="JBKJXSLY_SFSM' + index + '" id="JBKJXSLY_SFSM' + index + '_YES" value="1" onchange="updateSignature(' + index + ', true)"/>';
    table += '            <label for="JBKJXSLY_SFSM' + index + '_YES">&nbsp;是&nbsp;&nbsp;</label>';
    table += '            <input type="radio" name="JBKJXSLY_SFSM' + index + '" id="JBKJXSLY_SFSM' + index + '_NO" value="0" onchange="updateSignature(' + index + ', false)"/>';
    table += '            <label for="JBKJXSLY_SFSM' + index + '_NO">&nbsp;否</label>';
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
            loadDropdown('cbJBKJXSLY_MZ' + bjbrIndex, false);
            loadDropdown('cbJBKJXSLY_ZZMM' + bjbrIndex);
            loadTreeDropdownZone('cbJBKJXSLY_AFDQ' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZW' + bjbrIndex, false);
            loadTreeDropdownId('cbJBKJXSLY_SF' + bjbrIndex);
            loadTreeDropdownSpecialId('cbJBKJXSLY_TSSF' + bjbrIndex);
            loadDropdown('cbJBKJXSLY_ZJ' + bjbrIndex);
            loadTreeDropdownNature('cbJBKJXSLY_ZYSXXZ' + bjbrIndex, false);
            loadTreeDropdownNature('cbJBKJXSLY_CYSXXZ' + bjbrIndex, false);
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
    table += '        <td class="label"><span class="span-must-fill">*</span>单位：</td>';
    table += '        <td width="120" colspan="4"><input type="text" name="JBKJXSLY_BJBRDW' + index + '" style="width:99%;"/></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">住址：</td>';
    table += '        <td width="120" colspan="4"><input type="text" name="JBKJXSLY_BJBRZZ' + index + '" style="width:99%;"/></td>';
    table += '    </tr>';
    table += '</table>';
    table += '<table class="newClue-table" border="0" width="100%">';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>案发地区：</td>';
    table += '        <td width="200"><select id="cbJBKJXSLY_AFDQ' + index + '" name="JBKJXSLY_AFDQ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">职务：</td>';
    table += '        <td width="200"><select id="cbJBKJXSLY_ZW' + index + '" name="JBKJXSLY_ZW' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label"><span class="span-must-fill">*</span>职级：</td>';
    table += '        <td width="240"><select id="cbJBKJXSLY_ZJ' + index + '" name="JBKJXSLY_ZJ' + index + '" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>身份：</td>';
    table += '        <td width="200"><select id="cbJBKJXSLY_SF' + index + '" name="JBKJXSLY_SF' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">特殊身份：</td>';
    table += '        <td width="200"><select id="cbJBKJXSLY_TSSF' + index + '" name="JBKJXSLY_TSSF' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">涉嫌领域：</td>';
    table += '        <td width="240"><select id="cbJBKJXSLY_SALY' + index + '" name="JBKJXSLY_SALY' + index + '" style="width:100%;"></select></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td class="label"><span class="span-must-fill">*</span>主要涉嫌性质：</td>';
    table += '        <td width="200"><select id="cbJBKJXSLY_ZYSXXZ' + index + '" name="JBKJXSLY_ZYSXXZ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">其他涉嫌性质:</td>';
    table += '        <td width="200"><select id="cbJBKJXSLY_CYSXXZ' + index + '" name="JBKJXSLY_CYSXXZ' + index + '" style="width:100%;"></select></td>';
    table += '        <td width="30">&nbsp;</td>';
    table += '        <td class="label">涉嫌金额:</td>';
    table += '        <td width="240"><input type="text" name="JBKJXSLY_SXJE' + index + '" style="width:99%;"/></td>';
    table += '    </tr>';
    table += '    <tr>';
    table += '        <td>&nbsp;</td>';
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


function updateFileSelection() {
    // alert($('#newClue_fileUpload')[0].files[0].name);
    var fileList = document.getElementById("newClue_fileUpload").files;
    for (var i = 0; i < fileList.length; i++) {
        window.localFileList[i] = fileList[i].name;
    }

    var fileCount = Number(window.localFileList.length) + Number(window.serverFileCount);
    $('.newClue-attachment-view>a').html('查看附件(' + fileCount + ')');
}

function updateSignature(index, isSignature) {
    if (isSignature == true) {
        $('input[name="JBKJXSLY_JBRXM' + index + '"]').removeAttr('disabled');
        $('input[name="JBKJXSLY_JBRSFZH' + index + '"]').removeAttr('disabled');
        $('input[name="JBKJXSLY_JBRDH' + index + '"]').removeAttr('disabled');
        $('input[name="JBKJXSLY_JBRDWZZ' + index + '"]').removeAttr('disabled');
    } else {
        $('input[name="JBKJXSLY_JBRXM' + index + '"]').attr('disabled', true);
        $('input[name="JBKJXSLY_JBRSFZH' + index + '"]').attr('disabled', true);
        $('input[name="JBKJXSLY_JBRDH' + index + '"]').attr('disabled', true);
        $('input[name="JBKJXSLY_JBRDWZZ' + index + '"]').attr('disabled', true);
    }
}

function viewFilesOnServer() {
    OpenNewWindowInCurrentPage({
        id: 'viewAttachmentWindow',
        width: 600,
        height: 400,
        top: 800,
        title: '文件查看窗口',
        url: 'ViewAttach.html',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        resizable: false,
        closable: false,
        param: {},
        onLoadFunctionName: 'loadAttachmentFileDg'
    });
}

function storeNewClueForm() {
    alert('暂存记录');
}

function submitNewClueForm() {
    //// 自动设置是否署名
    for (var i = 1; i <= jbrIndex; i++) {
        var jbrName = $('input[name="JBKJXSLY_JBRXM' + +'"]').val();
        if (jbrName == null || jbrName.length <= 0 || jbrName == '匿名') {
            checkRadioByValue('JBKJXSLY_SFSM' + i, 0);
            updateSignature(i, false);
        }
    }

    if (checkTransferCompanyMatchCaseZone()) {
        if (validateNewClue()) {
            //// Firstly, save clue info
            var values = $('#newClue_formXS').serialize() + '&' +
                $('#newClue_formJBR').serialize() + '&' +
                $('#newClue_formBJBR_Part1').serialize() + '&' +
                $('#newClue_formBJBR_Part2').serialize() + '&' +
                $('#newClue_formCL').serialize() + '&' +
                'clueId=' + clueId;

            $.ajax({
                type: 'post',
                url: 'AdminSaveClue',
                data: values,
                async: false,
                success: function (result) {
                    result = (new Function('return ' + result))();
                    alert(result.status);
                }
            });


            //// Secondly, upload all attachments
            $('#newClue_fileUpload').upload('AdminSaveClueFile', {}, function (result) {
                if (result.status.indexOf('成功') >= -1) {
                    //closeNewClueForm();
                } else {
                    alert('文件上传失败！');
                }
            }, 'json');
        }
    }


}

function closeNewClueForm() {
    $('.index-main-title .right').hide();
    window.location.href = 'Index.html';
}

function checkTransferCompanyMatchCaseZone() {
    if ($('#cbCLFS').combobox('getText').indexOf('交办') >= 0) {
        var companyTree = $('#cbZWDW').combotree('tree');
        var companySelectedNode = companyTree.tree('getSelected');
        var zoneTree = $('#cbJBKJXSLY_AFDQ1').combotree('tree');
        var zoneSelectedNode = zoneTree.tree('getSelected');

        if (companySelectedNode.text.indexOf('请选择') == -1 && zoneSelectedNode.text.indexOf('请选择') == -1) {
            var companyCompareText, zoneCompareText;
            if (companyTree.tree('getParent', companySelectedNode.target) != null) {
                companyCompareText = companyTree.tree('getParent', companySelectedNode.target).text;
            } else {
                companyCompareText = companySelectedNode.text;
            }
            if (zoneTree.tree('getParent', zoneSelectedNode.target) != null) {
                zoneCompareText = zoneTree.tree('getParent', zoneSelectedNode.target).text;
            } else {
                zoneCompareText = zoneSelectedNode.text;
            }

            if (companyCompareText.indexOf('本院其他部门') >= 0 || companyCompareText.indexOf('其他中央国家机关') >= 0) {
                // 本院其他部门，其他中央国家机关，隶属于北京市
                if (zoneCompareText.indexOf('北京') == -1) {
                    if (confirm('转往单位地区与案发地区不对应，请确认是否保存？')) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if (companyCompareText.indexOf('下级') >= 0) {
                // 如：转往单位为：安徽省信访局，案发地区为：北京市，属于不匹配
                if (companySelectedNode.text.indexOf(zoneCompareText) == -1) {
                    if (confirm('转往单位地区与案发地区不对应，请确认是否保存？')) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    return true;
}


function validateNewClue() {
    var temp;

    // //// "BeInformer" Validate
    // temp = $('input[name="JBKJXSLY_BJBRXM1"]').val();
    // temp = $.trim(temp);
    // if (temp == null || temp.length == 0) {
    //     alert('请输入被举报人1 的姓名.');
    //     return false;
    // }
    // if (bjbrIndex == 2) {
    //     temp = $('input[name="JBKJXSLY_BJBRXM2"]').val();
    //     temp = $.trim(temp);
    //     if (temp == null || temp.length == 0) {
    //         alert('请输入被举报人2 的姓名.');
    //         return false;
    //     }
    // }
    // if (bjbrIndex == 3) {
    //     temp = $('input[name="JBKJXSLY_BJBRXM3"]').val();
    //     temp = $.trim(temp);
    //     if (temp == null || temp.length == 0) {
    //         alert('请输入被举报人3 的姓名.');
    //         return false;
    //     }
    // }
    // // var i = 1;
    // // do {
    // //     temp = $('input[name="JBKJXSLY_JBRXM1"]').val();
    // //     temp = $.trim(temp);
    // //     if (temp == null || temp.length == 0) {
    // //         alert('请输入被举报人' + i + ' 的姓名！');
    // //         return false;
    // //     }
    // //     i++;
    // // } while (i <= bjbrIndex);
    //
    // temp = $.trim($('input[textboxname="JBKJXSLY_SYZY"]').textbox('getValue'));
    // if (temp == null || temp.length == 0) {
    //     alert('请输入举报内容！');
    //     return false;
    // }
    //
    // temp = $.trim($('input[textboxname="JBKJXSLY_NRZY"]').textbox('getValue'));
    // if (temp == null || temp.length == 0) {
    //     alert('请输入举报内容摘要！');
    //     return false;
    // }
    //
    //
    // //// "Clue Process" validation
    // temp = $.trim($('input[textboxname="JBZRYJ"]').textbox('getValue'));
    // if (temp == null || temp.length == 0) {
    //     alert('请输入承办人意见！');
    //     return false;
    // }
    //
    // temp = $.trim($('input[textboxname="JBKJXSLY_CLQK"]').textbox('getValue'));
    // if (temp == null || temp.length == 0) {
    //     alert('请输入处理情况！');
    //     return false;
    // }

    return true;
}



