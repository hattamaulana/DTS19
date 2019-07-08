package app.example.models;

/**
 * Class ini merupakan Object yang merepresentasikan data dari Sebuah Superhero
 * seperti : Superhero Pasti memiliki :
 * - Nama
 *
 */

public class SuperHero {
    public String name;

    public SuperHero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
