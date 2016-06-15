/**
 * Created by Heller Song on 6/9/2016.
 */
var jbrIndex = 1;
var bjbrIndex = 1;

function initializeNewClue() {
    $.parser.parse();
    $('.easyui-combobox').combobox({panelHeight: 'auto'});

    initJbrTabs();
    $('#newClue_BjbrTabs').tabs({
        onBeforeClose: function () {
            bjbrIndex--;
        }
    });
    // JS修饰Input，Select等防止样式加载两次的BUG
    // $('#newClue_JbrTabs input[type="text"]').textbox();
    // $('#newClue_JbrTabs select').combobox({panelHeight: 'auto'});
    // $('#newClue_BjbrTabs input[type="text"]').textbox();
    // $('#newClue_BjbrTabs select').combobox({panelHeight: 'auto'});
}

function initJbrTabs() {
    var jbrTabs = $('#newClue_JbrTabs');

    jbrTabs.tabs();

    jbrTabs.tabs({
        tools: '#newClue_JbrTabTools',
        onClose: function () {
            jbrIndex--;
            jbrTabs.tabs('select', jbrIndex - 1);
        }
    });

    jbrTabs.tabs('add', {
        title: '举报人' + jbrIndex,
        content: $('#newClue_jbrTabContent').html(),
        closable: true
    });
}

function addJbrPanel() {
    jbrIndex++;
    $('#newClue_JbrTabs').tabs('add', {
        title: '举报人' + jbrIndex,
        content: $('#newClue_jbrTabContent').html(),
        closable: true
    });
}

function removeJbrPanel() {
    if (jbrIndex > 1) {
        // tab start with index = 0
        $('#newClue_JbrTabs').tabs('close', jbrIndex - 1);
    }
}

function addBjbrPanel() {
    bjbrIndex++;
    $('#newClue_BjbrTabs').tabs('add', {
        title: '被举报人' + bjbrIndex,
        content: $('#bjbrTab1').html(),
        closable: true
    });
}

function removeBjbrPanel() {
    var mainTabs = $('#newClue_BjbrTabs');
    var selectedTab = mainTabs.tabs('getSelected');
    if (selectedTab && bjbrIndex > 1) {
        var index = mainTabs.tabs('getTabIndex', selectedTab);
        mainTabs.tabs('close', index);
        bjbrIndex--;
    }
}
