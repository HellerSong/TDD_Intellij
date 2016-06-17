var jbrIndex = 1;
var bjbrIndex = 1;

function initializeNewClue() {
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});
    $('#cbJBKJXSLY_ZJDW').combobox({panelHeight: '300px'});

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
        content: $('#newClue_jbrTabContent').html(),
        closable: false
    });
}

function addJbrPanel() {
    jbrIndex++;
    $('#newClue_jbrTabs').tabs('add', {
        title: '举报人' + jbrIndex,
        content: $('#newClue_jbrTabContent').html(),
        closable: true
    });
}

function removeJbrPanel() {
    if (jbrIndex > 1) {
        // tab start with index = 0
        $('#newClue_jbrTabs').tabs('close', jbrIndex - 1);
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
        content: $('#newClue_bjbrTabContent').html(),
        closable: false
    });
}

function addBjbrPanel() {
    bjbrIndex++;
    $('#newClue_bjbrTabs').tabs('add', {
        title: '被举报人' + bjbrIndex,
        content: $('#newClue_jbrTabContent').html(),
        closable: true
    });
}

function removeBjbrPanel() {
    if (bjbrIndex > 1) {
        // tab start with index = 0
        $('#newClue_bjbrTabs').tabs('close', bjbrIndex - 1);
    }
}


function submitNewClueForm() {
    //$('input[name="JBRXM"]').val('test');
    //alert($('#newClue_XS').serialize());
    //alert($('#newClue_CL').serialize());
    alert($('.newClue-bjbr-table').length);
}
