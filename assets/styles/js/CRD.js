export default class Crud {

    linkBack

    constructor() {
        this.linkBack = document.querySelector(".lien a").href;
    }

    #toFormdata(formulaire) {
        let fomdata = new FormData(formulaire);
        let parseUrl = new URLSearchParams(fomdata);
        return parseUrl.toString();
    }

    #getAction(formulaire) {
        return formulaire.getAttribute("action");
    }

    #sendAjax(url, formulaire, methode = "POST") {
        let xhr = new XMLHttpRequest();
        xhr.open(methode, url);
        xhr.responseType = "json";
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                console.log(this.#toFormdata(formulaire));
                switch (xhr.status) {
                    case 500:
                        let {err} = xhr.response;
                        document.querySelector(".gnr .err span").innerText = err;
                        break;
                    default:
                        window.location.assign(this.linkBack);
                        return ;
                }
            }
        }
        xhr.send(this.#toFormdata(formulaire));
    }

    doPost(formulaire) {
       return this.#sendAjax(this.#getAction(formulaire), formulaire)
    }

    doPut(formulaire) {
        return this.#sendAjax(this.#getAction(formulaire), formulaire, "PUT");
    }

    doDelete(lien, cb) {
        let xhr = new XMLHttpRequest();
        xhr.open("DELETE", lien.href);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                switch (xhr.status == 200) {
                    case 500:
                        break;
                    default:
                        cb();
                        break;
                }
            }
        }
        xhr.send();
    }
}