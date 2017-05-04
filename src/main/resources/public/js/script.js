$(document).ready(function(){
    $(".icon-minus").click(function(){
        var productId = $(this).val();
        var productQuantity = $("#appendedInputButtons").val();
        var link = "/editcart";

        $.ajax({
            url: link,
            type: "POST",
            data: {"id": productId, "quantity": -1},
            datatype: "html",
            success: function() {
                $("#appendedInputButtons").val(productQuantity-1)
            }
        });
    });

    $(".icon-plus").click(function(){
            var productId = $(this).val();
            var productQuantity = $("#appendedInputButtons").val();
            var link = "/editcart";

            $.ajax({
                url: link,
                type: "POST",
                data: {"id": productId, "quantity": +1},
                datatype: "html",
                success: function() {
                    $("#appendedInputButtons").val(productQuantity+1)
                }
            });
        });
});