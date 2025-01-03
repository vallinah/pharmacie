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

    #sendAjax(url, formulaire, methode = "POST") {
        let xhr = new XMLHttpRequest();
        xhr.open(methode, url);
        xhr.responseType = "text";
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {
                console.log(this.#toFormdata(formulaire));
                switch (xhr.status) {
                    case 500:
                        // DANGER : ampina erreur
                        break;
                    default:
                        let res = xhr.responseText;
                        window.location.assign("./crud.jsp?cls=" + res);
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