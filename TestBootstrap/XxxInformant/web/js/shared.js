/**
 * Created by Heller Song on 6/13/2016.
 */

function resetSearchForm() {
    $(':input', '[class*="search-form"]').not(
        ':button, :submit, :reset, :hidden').val('').removeAttr('checked');
    $('select').combobox('clear');
    //$('.selectpicker').selectpicker('val', '0');
}
