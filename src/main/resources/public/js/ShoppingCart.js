$(document).ready(function(){

    $("#appendedInputButtons").bind("keyup mouseup", function(){
        var productId = $("#appendedInputButtons").attr("name");
        var productQuantity = $("#appendedInputButtons").val();
        var link = "/editcart";
        $.ajax({
            url: link,
            type: "POST",
            data: {"cart-id": productId, "cart-quantity": productQuantity},
            datatype: "html",
            success: function() {
                $("#smallQuantity").val(productQuantity);
            }
        });
    });
});