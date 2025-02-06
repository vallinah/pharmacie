package base;

import java.sql.ResultSet;

import annotation.AnnotationAttr;
import annotation.AnnotationClass;

@AnnotationClass(nameInBase = "genre", sequence = "genre_id_seq", prefix = "GNR")
public class Genre {
    
    @AnnotationAttr(nameInBase = "id_genre", inc = true)
    private String idGenre;
    @AnnotationAttr(nameInBase = "genre")
    private String genre;

    public Genre(ResultSet set) throws Exception {
        idGenre = set.getString("id_genre");
        genre = set.getString(("genre"));
    }

    public Genre() {}

    public String getIdGenre() {
        return idGenre;
    }
    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
