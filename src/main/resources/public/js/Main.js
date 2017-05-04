$(document).ready(function() {
    alert("JS IN DOM - OK");
    $(".add-to-cart-btn").click(function() {
        console.log($(this).attr("value"));
        var prodID = $(this).attr("value");
        $.ajax({
            url: "/add_to_cart/"+prodID,
            type: "POST",
            async: true,
            data: {, success: "ok", prodId:prodID},
            success:
                function (data) {
                alert("in success")
                    var obj = JSON.parse(JSON.stringify(data));
                    $("#order-quantity").html(obj);
                },
            error:
                function () {
                    alert('Get all boards not OK')
                }
        });
    });
});