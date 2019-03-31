/// <reference path="../jquery-3.3.1.js"/>
function autocompleteSetUp() {

    function NcrRegionAutocompletion() {
        var currentFocus;
        $('#ncrRegions-input').on('input', () => {
            closeSuggestionList();
            var search = $('#ncrRegions-input').val();
            if (!search)
                return false;
            var url = '/FoodTymAdmin/LSAG?search_for=ncr_region&search=' + search;

            $.getJSON(url, (resultArray) => {
                if (resultArray.length == 0)
                    return false;
                currentFocus = -1;
                var autcompleteBox = $('#ncrRegions-input').parent('.autocomplete-box');
                var suggestionList = $('<div>').attr('id', 'ncrRegions-input-autocomplete-list').addClass('autocomplete-items');
                for (var i = 0; i < resultArray.length && i < 15; i++) { // maximum 15 suggestion only
                    var regionName = resultArray[i].ncrRegionName;
                    var suggestionDiv = $('<div>').text(regionName).append($('<input>').attr('type', 'hidden').val(regionName));
                    suggestionDiv.on('click', (evt) => {
                        var value = $(evt.target).children('input[type=hidden]').val();
                        $('#ncrRegions-input').val(value);
                        closeSuggestionList();
                    })
                    suggestionList.append(suggestionDiv);
                }
                autcompleteBox.append(suggestionList);
            });
        });

        $('#ncrRegions-input').keydown((evt) => {
            var x = $('#ncrRegions-input-autocomplete-list').children('div');

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
            $('#ncrRegions-input-autocomplete-list').remove();
        }

        $(document).click(closeSuggestionList);

    }

    function LocalityAutocompletion() {
        var currentFocus;
        $('#locality-input').on('input', () => {
            closeSuggestionList();
            var search = $('#locality-input').val();
            if (!search)
                return false;

            var region = $('#ncrRegions-input').val();
            var url;
            if (region)
                url = "/FoodTymAdmin/LSAG?search_for=locality&search=" + search + "&ncr_region=" + region;
            else
                url = "/FoodTymAdmin/LSAG?search_for=locality&search=" + search;
            $.getJSON(url, (resultArray) => {
                if (resultArray.length == 0)
                    return false;
                currentFocus = -1;
                var autcompleteBox = $('#locality-input').parent('.autocomplete-box');
                var suggestionList = $('<div>').attr('id', 'locality-input-autocomplete-list').addClass('autocomplete-items');
                for (var i = 0; i < resultArray.length && i < 15; i++) { // maximum 15 suggestion only
                    var localityName = resultArray[i].localityName;
                    var suggestionDiv = $('<div>').text(localityName).append($('<input>').attr('type', 'hidden').val(localityName));
                    suggestionDiv.on('click', (evt) => {
                        var value = $(evt.target).children('input[type=hidden]').val();
                        $('#locality-input').val(value);
                        closeSuggestionList();
                    })
                    suggestionList.append(suggestionDiv);
                }
                autcompleteBox.append(suggestionList);
            });
        });

        $('#locality-input').keydown((evt) => {
            var x = $('#locality-input-autocomplete-list').children('div');

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
            $('#locality-input-autocomplete-list').remove();
        }

        $(document).click(closeSuggestionList);
    }
    NcrRegionAutocompletion(); // call the setup funtion
    LocalityAutocompletion();
}

function setUpSearchBox() {
    $('#search-type-box .custom-control-input').on('change', (evt) => {
        inp = $(evt.target);
        if (inp.is(':checked')) {
            $('.search-type').hide("fast");
            if (inp.attr('id') == 'by-id') {
                $('#search-by-id').show("fast");
            } else if (inp.attr('id') == 'by-locality') {
                $('#search-by-locality').show("fast");
            } else if (inp.attr('id') === 'by-name') {
                $('#search-by-name').show('fast');
            }

        }
    });


    $('.search-button').on('click', (evt) => {
        $('#search-type-box .custom-control-input').each((index, element) => {
            if ($(element).is(':checked')) {
                switch ($(element).attr('id')) {
                    case "by-id":
                }
            }
        });
    });

}

function viewRestaurant() {

}

$(document).ready(() => {
    autocompleteSetUp();
    setUpSearchBox();
});