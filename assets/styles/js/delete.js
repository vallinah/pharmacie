import Crud from "./CRD.js";

let crd = new Crud();

let bd = document.querySelector(".bd");
let table = bd.querySelector("table");

document.querySelectorAll(".action a:nth-child(2)")
.forEach(el => {
    el.addEventListener('click', e => {
        e.preventDefault();
        crd.doDelete(el, () => {
            let prt = el.parentElement;
            for (let a = 0; a < 2; a++) {
                prt = prt.parentElement;
            }
            prt.remove()
            if (table.querySelectorAll("tr").length <= 1) {
                bd.innerHTML = "<h2>Aucun(s) element(s)</h2>"
            }
        });
    })
})