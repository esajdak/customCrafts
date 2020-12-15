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
    dashboardTab.style.display = "none";
    ordersTab.style.display = "none";
    productsTab.style.display = "none";
    tabId.style.display = "block";

}

window.onload = init;