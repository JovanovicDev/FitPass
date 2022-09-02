package beans;

import java.util.Date;

public class IstorijaTreninga {

	private String vremePrijave;
	private Trening trening;
	private Korisnik kupac;
	private Korisnik trener; //ako trening ima trenera
	
	public IstorijaTreninga() {
		this.vremePrijave = null;
		this.trening = null;
		this.kupac = null;
		this.trener = null;
	}

	public IstorijaTreninga(String vremePrijave, Trening trening, Korisnik kupac, Korisnik trener) {
		super();
		this.vremePrijave = vremePrijave;
		this.trening = trening;
		this.kupac = kupac;
		this.trener = trener;
	}

	public String getVremePrijave() {
		return vremePrijave;
	}

	public void setVremePrijave(String vremePrijave) {
		this.vremePrijave = vremePrijave;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}

	public Korisnik getTrener() {
		return trener;
	}

	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}
	
}
