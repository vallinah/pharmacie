let list = document.querySelector(".bd");
let tab = document.querySelector('table');

const filtre = document.querySelector(".filtre");
const ttr = document.querySelector(".body .ttr");
ttr.appendChild(filtre);
function ajax(data = "") {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "fn_3");
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

                    
                    if (rep.length > 0) {
                        
                        tab.remove();
                        tab.innerHTML = `<tr>
                                    <th>id_conseil_du_mois</th>
                                    <th>date_debut</th>
                                    <th>date_fin</th>
                                    <th>nom_produit</th>
                                    <th>Action</th>
                            </tr>`;

                        for (let a = 0; a < rep.length; a++) {
                            let tr = document.createElement("tr");
                            let content = "";
                            for (let key in rep[a]) {                                
                                content += `<td>${rep[a][key]}</td>`;
                            }
                            content += `<td>
                                            <div class='action'>
                                                <a href="update.jsp?cls=base.ConseilDuMois&id=${rep[a]['id_conseil_du_mois']}"><i class="bi bi-pencil"></i></a>
                                                <a href="crud?cls=base.ConseilDuMois&id=${rep[a]['id_conseil_du_mois']}"><i class="bi bi-trash"></i></a>
                                            </div>
                                        </td>`
                            tr.innerHTML = content;
                            tab.appendChild(tr);
                        }
                        list.innerHTML = "";
                        list.appendChild(tab);
                    } else {
                        list.innerHTML = "<h1 id='empty'>Aucun element</h1>";
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