package beans;

import java.util.Date;

public class Clanarina {

	private char[] id; //10 karaktera
	private String tip; //godisnja, mesecna, polugodisnja, ...
	private Date datumPlacanja;
	private Date datumIsteka;
	private int cena;
	private Korisnik kupac;
	private String status; //aktivna, neaktivna
	private int brojTermina; //dnevni broj ulazaka, moze biti neogranicen
	
	public Clanarina() {
		this.id = new char[10];
		this.tip = "";
		this.datumPlacanja = null;
		this.datumIsteka = null;
		this.cena = 0;
		this.kupac = null;
		this.status = "";
		this.brojTermina = 0;
	}

	public Clanarina(char[] id, String tip, Date datumPlacanja, Date datumIsteka, int cena, Korisnik kupac,
			String status, int brojTermina) {
		super();
		this.id = id;
		this.tip = tip;
		this.datumPlacanja = datumPlacanja;
		this.datumIsteka = datumIsteka;
		this.cena = cena;
		this.kupac = kupac;
		this.status = status;
		this.brojTermina = brojTermina;
	}

	public char[] getId() {
		return id;
	}

	public void setId(char[] id) {
		this.id = id;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Date getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(Date datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public Date getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Korisnik getKupac() {
		return kupac;
	}

	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBrojTermina() {
		return brojTermina;
	}

	public void setBrojTermina(int brojTermina) {
		this.brojTermina = brojTermina;
	}
	
}
