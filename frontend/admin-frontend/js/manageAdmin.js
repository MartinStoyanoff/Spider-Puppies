var inputText = [];

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
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
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
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        success: function (data) {
            var tbody = $("#bulk-container")
            data.forEach(function (admin) {

                var userId = admin["id"];
                var username = admin["user"]["username"];
                var eMail = admin["eMail"];
                var tr = $('<tr>');

                $('<td>').html(username).appendTo(tr);
                $('<td>').html(eMail).appendTo(tr);


                var editButton = "<button class='btn btn-outline-success btn-border-green' id='edit-button' value=" + userId + "> Edit </button>";
                var deleteButton = "<button class='btn btn-outline-success btn-border-red'id='delete-button' value=" + userId + "> Delete </button>";
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
    var deleteUser = $.ajax({
        type: 'DELETE',
        url: "http://localhost:8080/admin/manage/admins/delete/" + id,
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
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
    $(this).text("Update");
    this.setAttribute("id", "update-button")
    $(this).parents("tr").find("td:not(:last-child)").each(function () {
        var text = $(this).text();

        if (text.indexOf("@") > 0) {
            $(this).html('<input type="email" class="form-control" value="' + $(this).text() + '">');

        }
        else {
            $(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
        }


    });


});
$("#bulk-container").on("click", "#update-button", function () {
    $(this).parents("tr").find("td:not(:last-child)").each(function () {
        var updatedUsername = $(this.firstChild).val();
        var updateEmail = $(this.nextSibling.firstChild).val();
        inputText.push(updatedUsername);
        inputText.push(updateEmail);

    })
    var id = this.getAttribute("value");
    var username = inputText[0];
    var email = inputText[1];
    var enabled = 1;
    var user = {username: username, password: "null", enabled: enabled}
    var admin = {id: id, user: user, eMail: email, firstLogin: null}

    var updateAdmin = $.ajax({
        type: 'PUT',
        url: "http://localhost:8080/admin/manage/admins/update",
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(admin),
        success: function () {
            inputText = [];
            adminLoad();

        },
        error: function () {
            console.log("Unsuccessful request");
        }
    })
});