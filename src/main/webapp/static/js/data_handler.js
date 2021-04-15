let dataHandler = {

    decreaseProductQuantity: function (productId, callback) {
        $.ajax({
            type: "POST",
            url: "/edit-quantity",
            data: {"quantity": "decrease",
                "id": productId},
            success: function (newValues) {
                callback(newValues);
            }
        })
    },

    increaseProductQuantity: function (productId, callback) {
        $.ajax({
            type: "POST",
            url: "/edit-quantity",
            data: {"quantity": "increase",
                "id": productId},
            success: function (newValues) {
                callback(newValues);
            }
        })
    },

    getShoppingCartInfo: function (callback) {
        $.ajax({
            type: "GET",
            url: "/edit-quantity",
            success: function (shoppingCartInfo) {
                callback(shoppingCartInfo);
            }
        })
    }

};