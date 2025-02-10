import Crud from "./CRD.js";
import { activeModal } from "./template.js";

let crd = new Crud();
let modal_update = document.querySelector(".modal_md.update");
let form_ajout = document.querySelector("form.ajout");

document.querySelectorAll(".modal_close")?.forEach(el => {
    el.addEventListener('click', () => {
        setTimeout(() => {
            modal_update.innerHTML = "";
        }, 500);
    })
})

/**
 * @param {Event} e 
 * @param {HTMLAnchorElement} el 
 */
function listnerActiveModal(e, el) {
    let tr = el;
    for (let a = 0; a < 3; a++) {
        tr = tr.parentElement;
    }

    e.preventDefault();
    let xhr = new XMLHttpRequest();
    xhr.open("GET", el.href);

    let idParms = new URL(el.href).searchParams.get("id");    

    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            switch (xhr.status) {
                case 500:
                    console.error("Problème de recuperation du contenue de l'hmtlUpdate");
                    break;
                default:
                    modal_update.innerHTML = xhr.responseText;
                    let form_update = modal_update.querySelector("form.update");
                    form_update.action += "&id=" + idParms;
                    form_update.addEventListener('submit', e => {
                        e.preventDefault();
                        crd.doPut(form_update, (arr) => {
                            let obj = [...arr];
                            let td = tr.children;
                            for (let a = 0; a < obj.length; a++) {
                                if (a != obj.length - 1) {
                                    td[a].innerHTML = `<td>${obj[a]}</td>`
                                } else {
                                    let liens = td[a].querySelectorAll("a");
                                    let link = ['update', 'crud'];
                                    for (let a = 0; a < 2; a++) {
                                        liens[a].href = `${link[a]}?cls=${emp.innerText}&id=${obj[a]}`
                                    }
                                }
                            }
                        })
                    })
                    break;
            }
        }
    }
    xhr.send()
}

document.querySelectorAll(`.update.modal_active`).forEach(el => { 
    el.addEventListener('click', e => {
        listnerActiveModal(e, el);
    })
})

const emp = document.getElementById("emplacement");
form_ajout.addEventListener('submit', (e) => {
    e.preventDefault();
    crd.doPost(form_ajout, (arr) => {
        let obj = [...arr];
        let tbody = document.querySelector(".bd table tbody");
        let tr = document.createElement("tr");
        for (let a = 0; a < obj.length; a++) {
            if (a != obj.length - 1) {
                tr.innerHTML += `<td>${obj[a]}</td>`
            } else {
                tr.innerHTML += `<td>
                       <div class='action'>
                           <a href="update?cls=${emp.innerText}&id=${obj[a]}" class="update modal_active" data-active="update"><i class="bi bi-pencil"></i></a>
                           <a href="crud?cls=${emp.innerText}&id=${obj[a]}" class="delete modal_active" data-active="delete"><i class="bi bi-trash"></i></a
                        </div>
                   </td>`

                let lienUpdate = tr.querySelector(".update.modal_active")
                let lienRemove = tr.querySelector(".delete.modal_active")

                lienRemove.addEventListener('click', function (e){
                    activeModal(lienRemove);
                    removeHandler(e, this);
                })
                
                lienUpdate.addEventListener('click', function (e) {
                    activeModal(lienUpdate)
                    listnerActiveModal(e, this)
                })
            }
        }
        tbody.appendChild(tr);
    });
});


/**
 * @param {Event} e 
 * @param {HTMLElement} el
 */
export function removeHandler(e, el) {
    e.preventDefault();
    let confirm = document.querySelector(".modal_md .delete .confirm");
    
    const cb = () => {
        crd.doDelete(el, confirm, cb)
    }
    confirm.addEventListener('click', cb)
}

document.querySelectorAll('.delete.modal_active').forEach(el => {
    el.addEventListener('click', e => {
        removeHandler(e, el);
    })
})