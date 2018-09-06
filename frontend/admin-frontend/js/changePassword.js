$("#change-password-button").on("click", function (e) {

    e.preventDefault();

    var userName = $("#initial-password").val();
    var password = $("#password").val();
    var adminId = localStorage.getItem("adminId");

    var userDetails = [adminId, userName, password];

    var adminLogin = $.ajax({
        crossOrigin: true,
        type: 'PUT',
        url: "http://localhost:8080/admin/manage/admins/changePassword",
        headers: {
            "Authorization" : "Bearer "+ localStorage.getItem("token")
        },
        contentType: "application/json",
        data: JSON.stringify(userDetails),
        success: function (data) {
            localStorage.clear();
            window.location.href = "/Spider-Puppies/frontend/admin-frontend/log-in.html";

        }
        , error: function () {
            console.log("Unsuccessful request");
        }
    })
});
