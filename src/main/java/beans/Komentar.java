package beans;

public class Komentar {
	
	private Korisnik kupac;
	private SportskiObjekat sportskiObjekat;
	private String tekst;
	private int ocena; //na skali od 1 do 5
	
	public Komentar() {
		this.kupac = null;
		this.sportskiObjekat = null;
		this.tekst = "";
		this.ocena = 0;
	}

	public Komentar(Korisnik kupac, SportskiObjekat sportskiObjekat, String tekst, int ocena) {
		super();
		this.kupac = kupac;
		this.sportskiObjekat = sportskiObjekat;
		this.tekst = tekst;
		this.ocena = ocena;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}

	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

}
