package br.com.marcuzo.quizPaises;


public enum EnumPaises {

    BRASIL(R.drawable.brasil, "B", new String[] {"Iraque", "Brasil", "Coreia do Sul", "Guiana"}),
    BULGARIA(R.drawable.bulgaria, "D", new String[] {"Brasil", "USA", "Canada", "Bulgaria"}),
    CANADA(R.drawable.canada, "A", new String[] {"Canada", "Brasil", "Coreia do Sul", "Guiana"}),
    CHINA(R.drawable.china, "C", new String[] {"Iraque", "Brasil", "China", "Guiana"}),
    COREIA_SUL(R.drawable.coreia_sul, "C", new String[] {"Iraque", "Brasil", "Coreia do Sul", "Guiana"}),
    CUBA(R.drawable.cuba, "B", new String[] {"Iraque", "Cuba", "Granada", "USA"}),
    GUATEMALA(R.drawable.guatemala, "D", new String[] {"Iraque", "Brasil", "Granada", "Guatemala"}),
    HUNGRIA(R.drawable.hungria, "D", new String[] {"Iraque", "Brasil", "Granada", "Hungria"}),
    ITALIA(R.drawable.italia, "A", new String[] {"Italia", "Iraque", "Coreia do Sul", "Guiana"}),
    USA(R.drawable.usa, "A", new String[] {"USA", "Brasil", "Guiana", "Iraque"});

    private int CodImgPais;
    private String respostaCorreta;
    private String alternativas[];

    EnumPaises(int CodImgPais, String respostaCorreta, String alternativas[]) {
        this.CodImgPais = CodImgPais;
        this.respostaCorreta = respostaCorreta;
        this.alternativas = alternativas.clone();
    }

    public int getCodImgPais() {
        return CodImgPais;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public String[] getAlternativas() {
        return alternativas;
    }



}
