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
                tbody.append ('<a href="#">'+data[num]+'</a>');
            };

        },
        error: function () {
            console.log("Unsuccessful request");

        }

    })
});

 $('#myDropdown').on('click', 'a', function() {
    var phone = $(this).text();
    console.log(phone);
});