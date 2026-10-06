package cz.roman.spanek.anv.y2026.pr03.creational.builder.after;

public class Main {

    public static void main(String[] args) {

        // 1) Jednoduchá zpráva – zadáme jen to, co je potřeba
        EmailZprava jednoducha = EmailZprava.builder()
                .odesilatel("novak@uni.cz")
                .komu("student@uni.cz")
                .predmet("Zápočet")
                .text("Dobrý den, zápočet bude v pátek.")
                .build();
        System.out.println(jednoducha);
        System.out.println("--------------------------------------------");

        // 2) Složitá zpráva – každá hodnota má jméno, žádné null, žádné "true, false"
        EmailZprava slozita = EmailZprava.builder()
                .odesilatel("novak@uni.cz")
                .komu("student1@uni.cz", "student2@uni.cz")
                .skrytaKopie("vedouci@uni.cz")
                .predmet("Změna termínu zkoušky")
                .text("<h1>Nový termín</h1><p>Zkouška se přesouvá na 20. 1.</p>")
                .html()
                .priorita(Priorita.VYSOKA)
                .priloha("harmonogram.pdf", 340)
                .build();
        System.out.println(slozita);
        System.out.println("--------------------------------------------");

        // 3) Chci jen nastavit prioritu – ostatní volitelné hodnoty prostě vynechám
        EmailZprava jenPriorita = EmailZprava.builder()
                .odesilatel("novak@uni.cz")
                .komu("student@uni.cz")
                .predmet("Upozornění")
                .text("Dnešní cvičení odpadá.")
                .priorita(Priorita.VYSOKA)
                .build();
        System.out.println(jenPriorita);
        System.out.println("--------------------------------------------");

        // 4) Záměna předmětu a textu je nemožná – metody se jmenují .predmet() a .text()
        EmailZprava bezZamen = EmailZprava.builder()
                .odesilatel("novak@uni.cz")
                .komu("student@uni.cz")
                .predmet("Podklady ke cvičení")
                .text("Dobrý den, v příloze posílám podklady ke cvičení.")
                .build();
        System.out.println(bezZamen);
        System.out.println("--------------------------------------------");

        // 5) Nevalidní zpráva (žádný příjemce) – výjimka z build(), nevznikne nekonzistentní objekt
        try {
            EmailZprava.builder()
                    .odesilatel("novak@uni.cz")
                    .predmet("Bez příjemce")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }
}
