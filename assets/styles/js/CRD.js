export default class Crud {

    constructor() {}

    #toFormdata(formulaire) {
        let fomdata = new FormData(formulaire);
        let parseUrl = new URLSearchParams(fomdata);
        return parseUrl.toString();
    }

    #getAction(formulaire) {
        return formulaire.getAttribute("action");
    }
    /**
     * @param {string} url 
     * @param {HTMLFormElement} formulaire 
     * @param {Function} cb 
     * @param {string} methode 
     */
    #sendAjax(url, formulaire, cb, methode = "POST") {
        let xhr = new XMLHttpRequest();
        xhr.open(methode, url);
        xhr.responseType = "json";
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                /** @type {HTMLElement} */
                let p = formulaire.nextElementSibling.querySelector("p");

                switch (xhr.status) {
                    case 500:
                        let {err} = xhr.response;
                        p.className = "message error"
                        p.innerText = err;
                        setTimeout(() => {
                            p.className = ""
                            p.innerText = ""
                        }, 2000);
                        break;
                    default:
                        let {rep, obj} = xhr.response;
                        p.className = "message succes"
                        p.innerText = rep;
                        cb(JSON.parse(obj))
                        setTimeout(() => {
                            p.className = ""
                            p.innerText = ""
                        }, 2000);
                        if (methode == 'POST') {
                            formulaire.reset();
                        }
                        return ;
                }
            }
        }
        xhr.send(this.#toFormdata(formulaire));
    }

    /**
     * 
     * @param {HTMLFormElement} formulaire 
     * @param {Function} cb 
     * @returns 
     */
    doPost(formulaire, cb) {
       return this.#sendAjax(this.#getAction(formulaire), formulaire, cb)
    }

    doPut(formulaire, cb) {
        return this.#sendAjax(this.#getAction(formulaire), formulaire, cb, "PUT");
    }

    doDelete(lien, confirm, cb) {

        let tr = lien;
        for (let a = 0; a < 3; a++) {
            tr = tr.parentElement;
        }
        
        /** @type {HTMLElement} */
        let p = document.querySelector(".modal_md.delete .message p");
        
        let xhr = new XMLHttpRequest();
        xhr.open("DELETE", lien.href);
        xhr.responseType = 'json'
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                switch (xhr.status) {
                    case 500:                        
                        let {err} = xhr.response;
                        p.innerText = err;
                        p.className = "message error"

                        setTimeout(() => {
                            p.className = ""
                            p.innerText = ""
                        }, 2000);
                        break;

                    default:                        
                        let {mess} = xhr.response;
                        p.innerText = mess;
                        p.className = "message succes"
                        tr.remove();

                        setTimeout(() => {
                            p.className = ""
                            p.innerText = ""
                        }, 2000);
                        break;
                }
            }
        }
        xhr.send();

        confirm.removeEventListener('click', cb)

    }
}