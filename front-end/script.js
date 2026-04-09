const BASE_URL = "http://192.168.1.16:8080/api/products";

let currentPage = 0;
let totalPages = 0;
const size = 4;

let currentUpdateId = null;

// 🔥 Load Products
function loadProducts(page = 0) {
    fetch(`${BASE_URL}?page=${page}&size=${size}&sortBy=price&direction=asc`)
        .then(handleResponse)
        .then(renderProducts)
        .catch(handleError);
}

// 🔥 Search + Filter
function searchProducts(page = 0) {

    const category = document.getElementById("category").value;
    const min = document.getElementById("minPrice").value;
    const max = document.getElementById("maxPrice").value;
    const keyword = document.getElementById("keyword").value;
    const rating = document.getElementById("rating").value;

    const sortBy = document.getElementById("sortBy").value;
    const direction = document.getElementById("direction").value;

    let url = `${BASE_URL}/search?page=${page}&size=${size}&sortBy=${sortBy}&direction=${direction}`;

    if (category) url += `&category=${category}`;
    if (min) url += `&min=${min}`;
    if (max) url += `&max=${max}`;
    if (keyword) url += `&keywords=${keyword}`;
    if (rating) url += `&rating=${rating}`;

    fetch(url)
        .then(handleResponse)
        .then(renderProducts)
        .catch(handleError);
}

// 🔥 Common Response Handler
function handleResponse(res) {
    if (!res.ok) {
        return res.text().then(text => {
            throw new Error(text || "Request failed");
        });
    }
    return res.json();
}

// 🔥 Error Handler
function handleError(err) {
    console.error("Error:", err);
    showToast(err.message || "Something went wrong ❌");
}

// 🔥 Render Products
function renderProducts(data) {

    const container = document.getElementById("products-container");
    container.innerHTML = "";

    currentPage = data.number;
    totalPages = data.totalPages;

    if (data.content.length === 0) {
        container.innerHTML = "<h3>No products found</h3>";
        return;
    }

    data.content.forEach(product => {

        const card = document.createElement("div");
        card.className = "product-card";

        card.innerHTML = `
            <h3>${product.name}</h3>
            <p><strong>₹${product.price}</strong></p>
            <p>Category: ${product.category}</p>
            <p>⭐ ${product.ratings ?? "N/A"}</p>

            <button onclick="deleteProduct(${product.id})">Delete</button>
            <button onclick="openUpdateModal(${product.id})">Update</button>
        `;

        container.appendChild(card);
    });

    document.getElementById("page-info").innerText =
        `Page ${data.number + 1} of ${data.totalPages}`;
}

// 🔥 Add Product
function addProduct() {

    const product = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        price: parseFloat(document.getElementById("price").value),
        seller: document.getElementById("seller").value,
        stock: parseInt(document.getElementById("stock").value),
        category: document.getElementById("categoryInput").value
    };

    showLoader();

    fetch(`${BASE_URL}/add`, {   // ✅ as you requested
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(product)
    })
    .then(handleResponse)
    .then(() => {
        hideLoader();
        showToast("Product added ✅");
        loadProducts(0);
    })
    .catch(err => {
        hideLoader();
        handleError(err);
    });
}

// 🔥 Delete Product
function deleteProduct(id) {

    if (!confirm("Are you sure you want to delete?")) return;

    showLoader();

    fetch(`${BASE_URL}/${id}`, {
        method: "DELETE"
    })
    .then(res => {
        hideLoader();
        if (!res.ok) throw new Error("Delete failed");
        showToast("Product deleted ✅");
        searchProducts(currentPage);
    })
    .catch(err => {
        hideLoader();
        handleError(err);
    });
}

// 🔥 OPEN MODAL
function openUpdateModal(id) {
    currentUpdateId = id;
    document.getElementById("modal").classList.remove("hidden");
}

// 🔥 CLOSE MODAL
function closeModal() {
    document.getElementById("modal").classList.add("hidden");
}

// 🔥 UPDATE PRODUCT
function submitUpdate() {

    const updated = {
        name: document.getElementById("updateName").value,
        price: parseFloat(document.getElementById("updatePrice").value),
        description: "Updated",
        seller: "Admin",
        stock: 10,
        category: "Electronics"
    };

    showLoader();

    fetch(`${BASE_URL}/${currentUpdateId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updated)
    })
    .then(handleResponse)
    .then(() => {
        hideLoader();
        closeModal();
        showToast("Product updated ✅");
        searchProducts(currentPage);
    })
    .catch(err => {
        hideLoader();
        handleError(err);
    });
}

// 🔥 Pagination
function nextPage() {
    if (currentPage < totalPages - 1) {
        searchProducts(currentPage + 1);
    }
}

function prevPage() {
    if (currentPage > 0) {
        searchProducts(currentPage - 1);
    }
}

// 🔥 Toast
function showToast(message) {
    const toast = document.getElementById("toast");
    toast.innerText = message;
    toast.classList.add("show");

    setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}

// 🔥 Loader
function showLoader() {
    document.getElementById("loader").classList.remove("hidden");
}

function hideLoader() {
    document.getElementById("loader").classList.add("hidden");
}

// 🔥 Init
loadProducts();