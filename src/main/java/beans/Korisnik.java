package beans;

import java.util.ArrayList;
import java.util.List;

public class Korisnik {

	private String username;
	private String sifra;
	private String ime;
	private String prezime;
	private Pol pol;
	private String datumRodjenja;
	private Uloga uloga; //Administrator, Menadzer, Trener, Kupac
	//Samo za ulogu Trener
	private List<IstorijaTreninga> istorijaTreninga; //lista prethodnih treninga
	//Samo za ulogu Menadzer
	private int sportskiObjekatId;
	//Samo za ulogu Kupac
	private Clanarina clanarina; 
	private List<SportskiObjekat> poseceniObjekti; 
	private int brojSakupljenihBodova;
	private TipKupca tipKupca;
	
	public Korisnik() {
		this.username = "";
		this.sifra = "";
		this.ime = "";
		this.prezime = "";
		this.pol = null;
		this.datumRodjenja = null;
		this.uloga = Uloga.KUPAC;
		this.istorijaTreninga = new ArrayList<IstorijaTreninga>();
		this.sportskiObjekatId = 0;
		this.clanarina = null;
		this.poseceniObjekti = new ArrayList<SportskiObjekat>();
		this.brojSakupljenihBodova = 0;
		this.tipKupca = null;
	}

	public Korisnik(String username, String sifra, String ime, String prezime, Pol pol, String datumRodjenja) {
		super();
		this.username = username;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = Uloga.KUPAC;
		this.istorijaTreninga = new ArrayList<IstorijaTreninga>();
		this.sportskiObjekatId = 0;
		this.clanarina = null;
		this.poseceniObjekti = new ArrayList<SportskiObjekat>();
		this.brojSakupljenihBodova = 0;
		this.tipKupca = null;
	}
	
	public Korisnik(Korisnik k) {
		super();
		this.username = k.getUsername();
		this.sifra = k.getSifra();
		this.ime = k.getIme();
		this.prezime = k.getPrezime();
		this.pol = k.getPol();
		this.datumRodjenja = k.getDatumRodjenja();
		this.uloga = k.getUloga();
		this.istorijaTreninga = k.getIstorijaTreninga();
		this.sportskiObjekatId = k.getSportskiObjekatId();
		this.clanarina = k.getClanarina();
		this.poseceniObjekti = k.getPoseceniObjekti();
		this.brojSakupljenihBodova = k.getBrojSakupljenihBodova();
		this.tipKupca = k.getTipKupca();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<IstorijaTreninga> getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(List<IstorijaTreninga> istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}

	public int getSportskiObjekatId() {
		return sportskiObjekatId;
	}

	public void setSportskiObjekatId(int sportskiObjekatId) {
		this.sportskiObjekatId = sportskiObjekatId;
	}

	public Clanarina getClanarina() {
		return clanarina;
	}

	public void setClanarina(Clanarina clanarina) {
		this.clanarina = clanarina;
	}

	public List<SportskiObjekat> getPoseceniObjekti() {
		return poseceniObjekti;
	}

	public void setPoseceniObjekti(List<SportskiObjekat> poseceniObjekti) {
		this.poseceniObjekti = poseceniObjekti;
	}

	public int getBrojSakupljenihBodova() {
		return brojSakupljenihBodova;
	}

	public void setBrojSakupljenihBodova(int brojSakupljenihBodova) {
		this.brojSakupljenihBodova = brojSakupljenihBodova;
	}

	public TipKupca getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(TipKupca tipKupca) {
		this.tipKupca = tipKupca;
	}
	
}