let list = document.querySelector(".list");
let tab = document.querySelector('table');

const filtre = document.querySelector(".filtre");
const ttr = document.querySelector(".body .ttr");
ttr.appendChild(filtre);

function ajax(data = "") {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "fn_2");
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.responseType = "json";
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            switch (xhr.status) {
                case 500:
                    
                    break;
                default:
                    let {rep} = xhr.response;
                    rep = JSON.parse(rep);
                    console.log(rep.length);
                    
                    if (rep.length > 0) {
                        
                        tab.remove();
                        tab.innerHTML = `<tr>
                                        <th>id_mouvement</th>
                                        <th>quantite</th>
                                        <th>prix_achat_unitaire</th>
                                        <th>prix_vente_unitaire</th>
                                        <th>date_mouvement</th>
                                        <th>nom_produit</th>
                                    </tr>`;

                        for (let a = 0; a < rep.length; a++) {
                            let tr = document.createElement("tr");
                            let content = "";
                            for (let key in rep[a]) {                                
                                content += `<td>${rep[a][key]}</td>`; 
                            }
                            tr.innerHTML = content;
                            tab.appendChild(tr);
                        }
                        list.innerHTML = "";
                        list.appendChild(tab);
                    } else {
                        list.innerHTML = "<h1>Aucun element</h1>";
                    }
                    break;
            }
        }
    }
    xhr.send(data);
}

let form  = document.forms[0];
form.addEventListener("submit", e => {
    e.preventDefault();
    let params = new URLSearchParams(new FormData(form));
    ajax(params.toString());
})

ajax();