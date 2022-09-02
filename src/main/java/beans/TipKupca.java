package beans;

public class TipKupca {

	private String ime; //Zlatni, Srebrni, Bronzani itd.
	private int popust; //izrazava se u procentima
	private int trazeniBrojBodova; //potreban broj bodova kako bi korisnik presao u sledeci tip
	
	public TipKupca() {
		this.ime = "";
		this.popust = 0;
		this.trazeniBrojBodova = 0;
	}
	
	public TipKupca(String ime, int popust, int trazeniBrojBodova) {
		super();
		this.ime = ime;
		this.popust = popust;
		this.trazeniBrojBodova = trazeniBrojBodova;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public int getTrazeniBrojBodova() {
		return trazeniBrojBodova;
	}

	public void setTrazeniBrojBodova(int trazeniBrojBodova) {
		this.trazeniBrojBodova = trazeniBrojBodova;
	}

}
