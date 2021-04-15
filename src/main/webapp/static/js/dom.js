// This adds event handlers to buttons
let dom = {
    init: function () {
        dom.addEventListenersToButtons();
        dataHandler.getShoppingCartInfo();
    },

    addEventListenersToButtons: function () {
        $(".removeProduct").on("click", function () {
            dom.decreaseProductQuantity(this.id);
        });

        $(".addProduct").on("click", function () {
            dom.increaseProductQuantity(this.id);
        });
    },

    decreaseProductQuantity: function (productId) {
        dataHandler.decreaseProductQuantity(productId, dom.displayNewValues);
    },

    increaseProductQuantity: function (productId) {
        dataHandler.increaseProductQuantity(productId, dom.displayNewValues);
    },

    displayNewValues: function (newValues) {

        if (newValues["newQuantity"] !== 0) {
            $("#quantity-" + newValues["productId"]).text(newValues["newQuantity"]);
        } else {
            $("#table-row-" + newValues["productId"]).remove();
        }

        $("#total-items").text("Total items: " + newValues["newTotalItems"]);
        $("#total-price").text("Total price: " + newValues["newTotalPrice"] + " USD");
    },
};

dom.init();