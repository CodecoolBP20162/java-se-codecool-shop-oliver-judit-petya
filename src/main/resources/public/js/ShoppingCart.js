$(document).ready(function(){
    $('[id="appendedInputButtons"]').on("keyup mouseup", function(){
        var productId = $(this).attr("name");
        var productQuantity = $(this).val();
        var productPriceID = $("#"+productId);
        var productPrice = productPriceID.attr("name");
        var productTotalPrice = Number(productPrice) * Number(productQuantity);
        var link = "/editcart";
        $.ajax({
            url: link,
            type: "POST",
            data: {"cart-id": productId, "cart-quantity": productQuantity},
            datatype: "html",
            success: function() {
                productPriceID.html(String(productTotalPrice)+" USD");
            }
        });
    });
});