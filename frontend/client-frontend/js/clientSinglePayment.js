/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown");
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}

$(document).ready(function () {
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/aclient/subscribers/getAllWithPendingInvoice/9",
        success: function (data) {
            console.log(data);
            var tbody = $("#myDropdown");
            for (var num in data)
            {
                tbody.append ('<a href="#" class="phones">'+data[num]+'</a>');
            };

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    });
});

 $('#myDropdown').on('click', 'a', function() {
    var phone = $(this).text();
     myFunction();
    $("#bulk-container").empty();
    
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/aclient/invoices/findDueInvoice/"+phone,
        success: function (data) {
            console.log(data);
            var tbody = $("#bulk-container"),
                props = ["subscriberPhone", "telecomServiceType", "telecomServiceSubscriptionPlan", "price", "currency"];
            $.each(data, function (i, data) {
                var tr = $('<tr>');
                $('<td><input' + " value=" + data["id"] + ' type="checkbox" class="form-check-input" checked="checked">').appendTo(tr);
                $.each(props, function (i, prop) {
                    $('<td>').html(data[prop]).appendTo(tr);
                });
                tbody.append(tr);
            });

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })
})
    


$("#select-none-button").on("click", function () {
    $("#bulk-container input").removeAttr('checked');
})
$("#select-all-button").on("click", function () {
    $("#bulk-container input").attr("checked", "checked");
})

$("#payment-button").on("click", function payInvoiceByIdList() {
    var allVals = [];
    $('#bulk-container :checked').each(function () {
        allVals.push($(this).val());
    });
    var payment = $.ajax({
        crossOrigin: true,
        type: 'PUT',
        url: "http://localhost:8080/aclient/payInvoiceByIdList",
        contentType: "application/json",
        data: JSON.stringify(allVals),
        success: function (data) {
            console.log(data)
            $("#bulk-container").empty();
            var invoices = $.ajax({
                type: 'GET',
                url: "http://localhost:8080/aclient/invoice/findAllPendingByClientId/9",
                success: function (data) {
                        $('#bulk-container').empty();
                        console.log("Invoices Paid Successfully");

                },
                error: function () {
                    console.log("Unsuccessful request");

                }

            })

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })


})