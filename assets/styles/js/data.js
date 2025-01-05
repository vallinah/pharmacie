const btn = document.getElementById("btn");

btn.addEventListener('click', () => {
    let data = new URLSearchParams();
    data.append("id_produit", 26);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "data?cls=base.Produit&col=nom_produit");
    xhr.responseType = "json";
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log("vita");
        }
    }
    xhr.send(data.toString());
})