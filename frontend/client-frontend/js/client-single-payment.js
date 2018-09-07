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
    var clientId = localStorage.getItem("clientId");
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/subscribers/getAllWithPendingInvoice/" + clientId,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (data) {
            console.log(data);
            var tbody = $("#myDropdown");
            for (var num in data) {
                tbody.append('<a href="#" class="phones">' + data[num] + '</a>');
            }
            ;

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    });
});

$('#myDropdown').on('click', 'a', function () {
    var phone = $(this).text();
    myFunction();
    $("#bulk-container").empty();
    $('#personal-info-container').empty();
    $('#services-container').empty();


    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/invoices/findDueInvoice/" + phone,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
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
            var clientId = localStorage.getItem("clientId");
            console.log(clientId);
            console.log(phone);
            var personalDetails = $.ajax({
                type: 'GET',
                url: "http://localhost:8080/api/subscribers/findSubscriberFullInfoByPhone/"+clientId+"/"+phone,
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token"),
                },
                success: function (data) {
                    $('#subscriber-info-container').removeAttr("style");
                    var fullName = data["firstName"] + " " + data["lastName"];
                    var pin = data["personalIdentificationNumber"];
                    var address = data["address"];
                    var firstActivation = data["firstServiceActivationDate"];
                    var nextBillingDate = data["billingDate"];
                    var totalPaidAmount = data["allTimeTurnover"].toFixed(2);

                    var personalDetailsBody = $('#personal-info-container');
                    var detailsHeader = "<h3>Subscriber Personal Details</h3>";
                    personalDetailsBody.append(detailsHeader);

                    var fullNameElement = "<p>Full Name</p>" + "<p>" + fullName + "</p>";
                    var div = "<div class='flex justify-content-between'>"+fullNameElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    var pinElement = "<p>PIN</p>" + "<p>" + pin + "</p>";
                    div = "<div class='flex justify-content-between'>"+pinElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    var addressElement = "<p>Address</p>" + "<p>" + address + "</p>";
                    div = "<div class='flex justify-content-between'>"+addressElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    var firstActivationElement = "<p>First Activation Date</p>" + "<p>" + firstActivation + "</p>";
                    div = "<div class='flex justify-content-between'>"+firstActivationElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');

                    var nextBillingElement = "<p>Next Billing Date</p>" + "<p>" + nextBillingDate + "</p>";
                    div = "<div class='flex justify-content-between'>"+nextBillingElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');

                    var totalPaidElement = "<p>Total Paid Amount (BGN)</p>" + "<p>" + totalPaidAmount + "</p>";
                    div = "<div class='flex justify-content-between'>"+totalPaidElement+"</div>";
                    personalDetailsBody.append(div);
                    div = ('');


                    // ---------------------------------------

                    var telecomServicesList = data["telecomServices"];

                    var serviceProps = ["type", "subscriptionPlan", "price"];

                    var serviceContainer = $('#services-container');
                    $.each(telecomServicesList, function(i, telecomServicesList){
                        var tr = $('<tr>');
                        $.each(serviceProps, function (i, serviceProp) {
                            $('<td>').html(telecomServicesList[serviceProp]).appendTo(tr);
                        });
                        serviceContainer.append(tr);
                    });

                    // ---------------------------------------

                    var invoices = data["invoices"];



                },
                error: function () {
                    console.log("Unsuccessful request");
                }
            });

            // var getPersonalDetails = (function () {
        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
})
;


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
    var clientId = localStorage.getItem("clientId");
    var payment = $.ajax({
        crossOrigin: true,
        type: 'PUT',
        url: "http://localhost:8080/api/invoice/payByIdList"+clientId,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(allVals),
        success: function (data) {
            console.log(data)
            $("#bulk-container").empty();
            var invoices = $.ajax({
                type: 'GET',
                url: "http://localhost:8080/api/invoice/findAllPendingByClientId/"+clientId,
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
});

$("#logout-button").on("click", function () {
    localStorage.clear();
    window.location.href = "../client-frontend/client-log-in.html";
});
