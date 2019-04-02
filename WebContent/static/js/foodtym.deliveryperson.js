/// <reference path="../jquery-3.3.1.js"/>


/** show recently added restaurants */

function load_recently_add_restaurnts() {
    $.ajax({
        url: '/FoodTymAdmin/Admin/Search?type=deliveryperson&by=recentlyadded',
        method: 'GET',
        success: (result) => {
            $('#result-item-container').children().remove().end().append(result);
            $('#display-info').text("Recently Added");
            $('.result-item').on('click', (evt) => {
                var id = $(evt.currentTarget).attr('data-id');
                window.location = '/FoodTymAdmin/Admin/DeliveryPersons?deliveryPersonId=' + id;

            });
        },
        error: () => {
            $('#result-item-container').append(
                `<div class="container">
                <div class="d-flex justify-content-center m-5">
                <p class="display-4">Can't Load...</p>
                </div>
                </div>`
            );
        }
    });
}

function initialize_page() {
    /** setting up search button*/
    $('#search-type-btn').on('click', (evt) => {
        var $btn = $(evt.target);
        if ($btn.attr('data-current') == 'byname') {
            $('#search-by-name-box').hide();
            $('#search-by-locality-box').show();
            $btn.attr('data-current', 'bylocality');
            $btn.text('Search By Name or ID')
            $btn.removeClass('btn-primary').addClass('btn-danger');
        }
        else {
            $('#search-by-name-box').show();
            $('#search-by-locality-box').hide();
            $btn.text('Search By Delivery Area');
            $btn.attr('data-current', 'byname');
            $btn.removeClass('btn-danger').addClass('btn-primary');
        }
    });

    $('#restaurant-search-bar').on('input', (evt) => {
        var text = $(evt.target).val();
        if (!text) {
            load_recently_add_restaurnts();
            return;
        }

        $.ajax({
            url: '/FoodTymAdmin/Admin/Search?type=deliveryperson&by=nameid&nameid=' + text,
            method: 'GET',
            success: (result) => {
                $('#result-item-container').children().remove().end().append(result);
                $('#display-info').text('Search Result for \"' + text + "\"");
                $('.result-item').on('click', (evt) => {
                    var id = $(evt.currentTarget).attr('data-id');
                    window.location = '/FoodTymAdmin/Admin/DeliveryPersons?deliveryPersonId=' + id;

                });
            },
            error: () => {
                $('#result-item-container').append(
                    `<div class="container">
                        <div class="d-flex justify-content-center m-5">
                        <p class="display-4">Can't Load...</p>
                        </div>
                        </div>`
                );
            }
        });
    });

    $(document).ajaxStart(() => {
        $('#loading-animation').show();
    }).ajaxComplete(() => {
        $('#loading-animation').hide();
    });
    load_recently_add_restaurnts();

}


$(document).ready(() => {

    initialize_page();

});
