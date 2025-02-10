package fn;

import annotation.AnnotationClass;

public class LinkHandler {
    
    private Class<?> cls;
    private String icone;
    private String titre;
    private String page;

    // ? Constructeur

    public LinkHandler(Class<?> cls) {
        setCls(cls);
        setIcone(cls.getAnnotation(AnnotationClass.class).icone());
        setTitre(cls.getAnnotation(AnnotationClass.class).title());
        setPage(cls.getAnnotation(AnnotationClass.class).page());
    }

    // $ Methode Tsotra

    public String giveLink() {
        return "                        <i class='bi " + icone + "'></i>\n" +
                "                        <a href='" + page + "'>" + titre + "</a>";
    }

    // ! Getter and Setter

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
        page = "crud.jsp?cls=" + cls.getName();
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        if (titre.isEmpty()) {
            this.titre = cls.getSimpleName();
        } else {
            this.titre = titre;
        }
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        if (!page.isEmpty()) {
            this.page = page;
        }
    }
}
