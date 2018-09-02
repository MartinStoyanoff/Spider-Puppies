$("#create-admin").on("click", function () {
    $("#add-admin-form").toggle();
})
$(document).ready(function () {
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/admin/manage/admins/listAll",
        success: function (data) {
            console.log(data);
            var tbody = $("#bulk-container")
            data.forEach(function (admin) {
                var username = admin["user"]["username"];
                var eMail = admin["eMail"];
                var enabled = admin["user"]["enabled"];
                if(enabled===1){
                    var enabledString = "Yes"
                }
                else{
                    var enabledString = "No"
                }
                var tr = $('<tr>');
                $('<td>').html(username).appendTo(tr);
                $('<td>').html(eMail).appendTo(tr);
                $('<td>').html(enabledString).appendTo(tr);
                tbody.append(tr);
            });
        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
})

