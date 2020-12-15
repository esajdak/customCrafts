const init = () => {
    document.getElementById("dashboard-nav").addEventListener("click", showThisTab);
    document.getElementById("orders-nav").addEventListener("click", showThisTab);
    document.getElementById("products-nav").addEventListener("click", showThisTab);

    console.log("working");
}

const showThisTab = event => {
    let tabId = event.currentTarget.getAttribute("tabToShow");
    let dashboardTab = document.getElementById("dashboard-tab");
    let ordersTab = document.getElementById("orders-tab");
    let productsTab = document.getElementById("products-tab");

    if (tabId == "dashboard-tab") {
        dashboardTab.style.display = "block";
        productsTab.style.display = "none";
        ordersTab.style.display = "none";
    } else if (tabId == "orders-tab") {
        ordersTab.style.display = "block";
        productsTab.style.display = "none";
        dashboardTab.style.display = "none";
    } else if (tabId == "products-tab") {
        productsTab.style.display = "block";
        ordersTab.style.display = "none";
        dashboardTab.style.display = "none";
    }

}

window.onload = init;