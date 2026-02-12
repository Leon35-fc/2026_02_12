package fabiocarlino.u5l9.payload;

import java.util.UUID;

public class BlogPayload {
    private UUID authorId;
    private String categoria;
    private String titolo;
    private String contenuto;
    private int minutiTempoLettura;

    public BlogPayload(String categoria, String titolo, String contenuto, int minutiTempoLettura) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.minutiTempoLettura = minutiTempoLettura;
    }

    public BlogPayload() {
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public int getMinutiTempoLettura() {
        return minutiTempoLettura;
    }

    public UUID getAuthorId() {
        return authorId;
    }

}
