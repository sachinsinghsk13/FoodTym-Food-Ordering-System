/// <reference path="resources/jquery-3.3.1.js"/>



function autocompleteSetUp() {

    function NcrRegionAutocompletion() {
        var currentFocus;
        $('#restNcrRegion').on('input', () => {
            closeSuggestionList();
            var search = $('#restNcrRegion').val();
            if (!search)
                return false;
            var url = '/FoodTymAdmin/LSAG?search_for=ncr_region&search=' + search;

            $.getJSON(url, (resultArray) => {
                if (resultArray.length == 0)
                    return false;
                currentFocus = -1;
                var autcompleteBox = $('#restNcrRegion').parent('.autocomplete-box');
                var suggestionList = $('<div>').attr('id', 'restNcrRegion-autocomplete-list').addClass('autocomplete-items');
                for (var i = 0; i < resultArray.length && i < 15; i++) { // maximum 15 suggestion only
                    var regionName = resultArray[i].ncrRegionName;
                    var suggestionDiv = $('<div>').text(regionName).append($('<input>').attr('type', 'hidden').val(regionName));
                    suggestionDiv.on('click', (evt) => {
                        var value = $(evt.target).children('input[type=hidden]').val();
                        $('#restNcrRegion').val(value);
                        closeSuggestionList();
                    })
                    suggestionList.append(suggestionDiv);
                }
                autcompleteBox.append(suggestionList);
            });
        });

        $('#restNcrRegion').keydown((evt) => {
            var x = $('#restNcrRegion-autocomplete-list').children('div');

            if (!x)
                return false;
            if (evt.keyCode == 40) {
                currentFocus++;
                addActive(x);
            }

            else if (evt.keyCode == 38) {
                currentFocus--;
                addActive(x);
            }

            else if (evt.keyCode == 13) {
                evt.preventDefault();
                if (currentFocus > -1) {
                    if (x)
                        x.filter(':eq(' + currentFocus + ')').click();
                }
            }
        });

        function addActive(x) {
            if (!x)
                return false;
            x.removeClass('activeSuggestion');

            if (currentFocus >= x.length)
                currentFocus = 0;
            if (currentFocus < 0)
                currentFocus = x.length - 1;

            x.filter(':eq(' + currentFocus + ')').addClass('activeSuggestion');
        }

        function closeSuggestionList() {
            $('#restNcrRegion-autocomplete-list').remove();
        }
        
        $(document).click(closeSuggestionList);

    }

    function LocalityAutocompletion() {
        var currentFocus;
        $('#restLocality').on('input', () => {
            closeSuggestionList();
            var search = $('#restLocality').val();
            if (!search)
                return false;

            var region = $('#restNcrRegion').val();
            var url;
            if (region)
                url = "/FoodTymAdmin/LSAG?search_for=locality&search=" + search + "&ncr_region=" + region;
            else
                url = "/FoodTymAdmin/LSAG?search_for=locality&search=" + search;
            $.getJSON(url, (resultArray) => {
                if (resultArray.length == 0)
                    return false;
                currentFocus = -1;
                var autcompleteBox = $('#restLocality').parent('.autocomplete-box');
                var suggestionList = $('<div>').attr('id', 'restLocality-autocomplete-list').addClass('autocomplete-items');
                for (var i = 0; i < resultArray.length && i < 15; i++) { // maximum 15 suggestion only
                    var localityName = resultArray[i].localityName;
                    var suggestionDiv = $('<div>').text(localityName).append($('<input>').attr('type', 'hidden').val(localityName));
                    suggestionDiv.on('click', (evt) => {
                        var value = $(evt.target).children('input[type=hidden]').val();
                        $('#restLocality').val(value);
                        closeSuggestionList();
                    })
                    suggestionList.append(suggestionDiv);
                }
                autcompleteBox.append(suggestionList);
            });
        });

        $('#restLocality').keydown((evt) => {
            var x = $('#restLocality-autocomplete-list').children('div');

            if (!x)
                return false;
            if (evt.keyCode == 40) {
                currentFocus++;
                addActive(x);
            }

            else if (evt.keyCode == 38) {
                currentFocus--;
                addActive(x);
            }

            else if (evt.keyCode == 13) {
                evt.preventDefault();
                if (currentFocus > -1) {
                    if (x)
                        x.filter(':eq(' + currentFocus + ')').click();
                }
            }
        });

        function addActive(x) {
            if (!x)
                return false;
            x.removeClass('activeSuggestion');

            if (currentFocus >= x.length)
                currentFocus = 0;
            if (currentFocus < 0)
                currentFocus = x.length - 1;

            x.filter(':eq(' + currentFocus + ')').addClass('activeSuggestion');
        }

        function closeSuggestionList() {
            $('#restLocality-autocomplete-list').remove();
        }
        
        $(document).click(closeSuggestionList);
    }

    NcrRegionAutocompletion(); // call the setup funtion
    LocalityAutocompletion();
}

/*
function autocomplete(inp, suggestionFor, what) {

    var currentFocus;
    $(inp).on('input', () => {
        closeSuggestionList();
        var text = $(inp).val();
        if (!text) return false;

        var arr = $.getJSON('/FoodTym/SAG?suggestion_for=' + suggestionFor + '&what=' + what + '&search_text=' + text,(json)=> {
        	console.log(json);
        	showSuggestions(json);
        });

        function showSuggestions(arr) {

            currentFocus = -1;
            var autocompleteBox = $(inp).parent('.autocomplete-box');
            var suggestionsBox = $('<div>').attr('id', $(inp).attr('id') + 'autocomplete-list').addClass('autocomplete-items');
            autocompleteBox.append(suggestionsBox);

            for (var i = 0; i < arr.length; i++) {
                var suggestionDiv = $('<div>').text(arr[i]['localityName']);
                var valInpBox = $('<input>').attr('type', 'hidden').val(arr[i]['localityName']);
                suggestionDiv.append(valInpBox);
                suggestionDiv.on('click', (evt) => {
                    var val = $(evt.target).find('input[type=hidden]').val();
                    $(inp).val(val);
                    closeSuggestionList();
                });
                suggestionsBox.append(suggestionDiv);
            }

            autocompleteBox.append(suggestionsBox);
        }
    });

    $(inp).keydown((evt) => {
        var x = $('#' + $(inp).attr('id') + 'autocomplete-list').children('div');
        if (!x) { return false }

        if (evt.keyCode == 40) {
            currentFocus++;
            addActive(x);
        }

        else if (evt.keyCode == 38) {
            currentFocus--;
            addActive(x);
        }
        else if (evt.keyCode == 13) {
            evt.preventDefault();
            if (currentFocus > -1) {
                if (x)
                    x.filter(':eq(' + currentFocus + ')').click();
            }
        }

    });

    function addActive(x) {
        if (!x) return false;
        // remove active from all div's
        x.removeClass('activeSuggestion');

        if (currentFocus >= x.length)
            currentFocus = 0;
        if (currentFocus < 0)
            currentFocus = x.length - 1;

        x.filter(':eq(' + currentFocus + ')').addClass('activeSuggestion');
    }

    function closeSuggestionList() {
        $('#' + $(inp).attr('id') + 'autocomplete-list').remove();
    }
}


*/

function setupButtons() {
    /** 
     * Toggle Between New Owner and Existing Owner
    */
    $('#existOwnerBtn').click(()=> {
        $('#ownerIdDiv').css("display","block");
        $('.ownerDetails').attr('disabled','disabled');
        $('#existOwnerBtn').addClass('active');
        $('#newOwnerBtn').removeClass('active');
    });
    $('#newOwnerBtn').click(()=> {
        $('#ownerIdDiv').css("display","none");
        $('.ownerDetails').removeAttr('disabled');
        $('#existOwnerBtn').removeClass('active');
        $('#newOwnerBtn').addClass('active');
    });

    // Show Password Button 
    $('#showPasswordBtn').click(()=> {
        var passwdField = $('#restPassword');
        if (passwdField.is('input[type=password]')) {
            passwdField.attr('type','text');
            $('#showPasswordBtn').text('Hide Password');
        }
        else {
            passwdField.attr('type','password');
            $('#showPasswordBtn').text('Show Password');
        }
    });
}

$(document).ready(()=> {
    // Attach event listeners to buttons
    setupButtons();
    autocompleteSetUp();
});