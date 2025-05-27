package Model;

public class Elbil extends Bil{
    private int  batteriKapacitet; // i kwh

    public Elbil(String registreringsnummer, String marke, String modell, double prisPerDag, int batteriKapacitet) {
        super(registreringsnummer, marke, modell, prisPerDag);
        this.batteriKapacitet = batteriKapacitet;
    }

 public int getBatteriKapacitet(){
        return batteriKapacitet;
 }

    @Override
    public String toString() {
        return "Elbil{" + "batteriKapacitet=" + batteriKapacitet + '}';
    }
}