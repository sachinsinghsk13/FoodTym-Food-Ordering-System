class FoodPriceType {
    constructor(type, restaurantPrice, foodtymPrice, quantity) {
        this.type = type;
        this.restaurantPrice = restaurantPrice;
        this.foodtymPrice = foodtymPrice;
        this.quantity = quantity;
    }
}

class FoodItem {
    constructor(c, sc, n, d, pt, t, pb,r) {
        this.food_category = c;
        this.food_subcategory = sc;
        this.food_title = n;
        this.food_description = d;
        this.food_prepare_time = pt;
        this.food_type = t;
        this.food_price_basis = pb;
        this.restaurant_id = r;
        this.priceTypes = new Array();
    }
}
function loadCategories() {
    $.getJSON('/FoodTymAdmin/Admin/FoodCategorySuggestionsAjaxGateway?type=category',(arr)=> {
        $('#food_category').children().remove();
        for (var i = 0; i < arr.length;i++) {
            $('#food_category').append('<option>' + arr[i] + '</option>');
        }
    });
}

function SubCategoryAutocompletion() {
    var currentFocus;
    $('#food_subcategory').on('input', () => {
        closeSuggestionList();
        var category = $('#food_category').val();
        var text = $('#food_subcategory').val();
        if (!text)
            return false;
        var url = '/FoodTymAdmin/Admin/FoodCategorySuggestionsAjaxGateway?type=subcategory&category='+category+'&text=' + text;
        $.getJSON(url, (resultArray) => {
            if (resultArray.length == 0)
                return false;
            currentFocus = -1;
            var autcompleteBox = $('#food_subcategory').parent('.autocomplete-box');
            var suggestionList = $('<div>').attr('id', 'food_subcategory-autocomplete-list').addClass('autocomplete-items');
            for (var i = 0; i < resultArray.length && i < 15; i++) { // maximum 15 suggestion only
                var subcategory = resultArray[i];
                var suggestionDiv = $('<div>').text(subcategory).append($('<input>').attr('type', 'hidden').val(subcategory));
                suggestionDiv.on('click', (evt) => {
                    var value = $(evt.target).children('input[type=hidden]').val();
                    $('#food_subcategory').val(value);
                    closeSuggestionList();
                })
                suggestionList.append(suggestionDiv);
            }
            autcompleteBox.append(suggestionList);
        });
    });

    $('#food_subcategory').keydown((evt) => {
        var x = $('#food_subcategory-autocomplete-list').children('div');

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
        $('#food_subcategory-autocomplete-list').remove();
    }
    
    $(document).click(closeSuggestionList);

}

class AddFoodItemForm {
    constructor() {
        this.food_category = $('#food_category');
        this.food_subcategory = $('#food_subcategory');
        this.food_title = $('#food_title');
        this.food_description = $('#food_desc');
        this.food_prepare_time = $('#food_prepare_time');
        this.food_type = $('.food_type');
        this.price_basis = $('#price_basis').val();
        this.restaurantId = $('#restaurantId').attr('data-restaurant-id');
    }

    validateForm() {
        var food_category = this.food_category.val();
        var food_subcategory = this.food_subcategory.val();
        var food_title = this.food_title.val();
        var food_description = this.food_description.val();
        var food_prepare_time = this.food_prepare_time.val();
        var food_type;
        $('.food_type').each((i, e) => {
            if ($(e).is(':checked'))
                food_type = $(e).val();
        });
    }
}

function addFoodItem() {
    var food_category = $('#food_category').val();
    var food_subcategory = $('#food_subcategory').val();
    var food_title = $('#food_title').val();
    var food_description = $('#food_desc').val();
    var food_prepare_time = $('#food_prepare_time').val();
    var food_type;
    $('.food_type').each((i, e) => {
        if ($(e).is(':checked'))
            food_type = $(e).val();
    });
    var price_basis = $('#price_basis').val();
    var restaurantId = $('#restaurantId').attr('data-restaurant-id');
    
    var foodItem = new FoodItem(food_category,
        food_subcategory,
        food_title,
        food_description,
        food_prepare_time,
        food_type,
        price_basis,restaurantId);


    $('#' + price_basis + '_checkbox input[type=checkbox]').each((i, e) => {
        if ($(e).is(':checked')) {
            var id = $(e).attr('id');
            var pricetype = new Object();
            pricetype.type = id;
            pricetype.restaurantPrice = $('#' + id + '_restaurant_price').val();
            pricetype.foodtymPrice = $('#' + id + '_foodtym_price').val();
            if (id === 'pcs')
                pricetype.quantity = $('#' + id + '_pcs').val();
            foodItem.priceTypes.push(pricetype);
        }
    });

    var formdata = new FormData();
    formdata.append("foodItem", JSON.stringify(foodItem));
    formdata.append("img", $('#food_thumb')[0].files[0]);
    $.ajax({
        url:"/FoodTymAdmin/Admin/FoodItems",
        method:'POST',
        data:formdata,
        processData:false,
        contentType:false,
        success: ()=> {
            alert("success");
        },
        error: ()=> {
            alert("Error");
        }
    });
    loadFoodItems();
}
function loadFoodItems() {
    var restaurantId = $('#restaurantId').attr('data-restaurant-id');
    req_url = "/FoodTymAdmin/Admin/FoodItems?restaurantId="+restaurantId;
    $.get({
        url:req_url
    }).done((res)=> {
        $('#food-menu-tab').empty().append(res);
    });
}

function priceInputSetup() {
    $('#price_basis').change(()=> {
        var x = $('#price_basis').val();
        $('.price_type_checkboxes').hide();
        $('#'+x+'_checkbox').show();
        $('.price_div').hide();
        $('#'+x+'_div').show();
    });


    $('.price_type_checkboxes input[type=checkbox]').change((evt)=> {
        var id = $(evt.target).attr('id');
        if ($(evt.target).is(':checked')) {
            $('#price_type_'+id).show();
        }
        else {
            $('#price_type_'+id).hide();
        }
    });
}




$(document).ready(() => {
	priceInputSetup();
    $('#addFoodItemBtn').click(() => {
        addFoodItem();
    });
    loadCategories();
    SubCategoryAutocompletion()
    loadFoodItems();
});