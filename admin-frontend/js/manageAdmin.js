$("#create-admin").on("click", function () {
    $("#add-admin-form").toggle();
})
$(document).ready(function () {
    adminLoad();

});
$("#add-admin").on("click", function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var enabled = 1;
    var firstLogin = 1;

    var user = {username: username, password: password, enabled: enabled}
    var admin = {user: user, eMail: email, firstLogin: firstLogin}

    var addAdmin = $.ajax({
        crossOrigin: true,
        type: 'POST',
        url: "http://localhost:8080/admin/manage/admins/add",
        contentType: "application/json",
        data: JSON.stringify(admin),
        success: function () {
            adminLoad();
            $("#username").val('');
            $("#password").val('');
            $("#email").val('');
            $("#add-admin-form").toggle();

        }
    })
});

function adminLoad() {
    $("#bulk-container").empty();
    var invoices = $.ajax({
        type: 'GET',
        url: "http://localhost:8080/admin/manage/admins/listAll",
        success: function (data) {
            console.log(data);
            var tbody = $("#bulk-container")
            data.forEach(function (admin) {

                var userId = admin["id"];
                var username = admin["user"]["username"];
                var eMail = admin["eMail"];
                var enabled = admin["user"]["enabled"];
                var enabledString;
                if (enabled === 1) {
                    enabledString = "Yes"
                }
                else {
                    enabledString = "No"
                }
                var tr = $('<tr>');
                var userNameInput = "<input value=" + username + " type=\"text\" disabled='disabled'>";

                $('<td>').html(userNameInput).appendTo(tr);
                $('<td>').html(eMail).appendTo(tr);
                $('<td>').html(enabledString).appendTo(tr);
                var editButton = "<button id='edit-button' value=" + userId + "> Edit </button>";
                var deleteButton = "<button id='delete-button' value=" + userId + "> Delete </button>";
                $('<td>').html(editButton + deleteButton).appendTo(tr);

                tbody.append(tr);
            });
        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
}

$("#bulk-container").on("click", "#delete-button", function () {
    var id = this.getAttribute("value")
    console.log(id);
    var deleteUser = $.ajax({
        type: 'DELETE',
        url: "http://localhost:8080/admin/manage/admins/delete/" + id,
        success: function () {
            adminLoad();

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
});


$("#bulk-container").on("click", "#edit-button", function () {
    var id = this.getAttribute("value");
    this.text("UPDATE");
    var td = this.parents("tr");

    console.log(td);

});