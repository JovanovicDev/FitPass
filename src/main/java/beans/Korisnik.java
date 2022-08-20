package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Korisnik {

	private String username;
	private String sifra;
	private String ime;
	private String prezime;
	private String pol;
	private Date datumRodjenja;
	private String uloga; //Administrator, Menadzer, Trener, Kupac
	//Samo za ulogu Trener
	private List<IstorijaTreninga> istorijaTreninga; //lista prethodnih treninga
	//Samo za ulogu Menadzer
	private SportskiObjekat sportskiObjekat;
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
		this.pol = "";
		this.datumRodjenja = null;
		this.uloga = "";
		this.istorijaTreninga = new ArrayList<IstorijaTreninga>();
		this.sportskiObjekat = null;
		this.clanarina = null;
		this.poseceniObjekti = new ArrayList<SportskiObjekat>();
		this.brojSakupljenihBodova = 0;
		this.tipKupca = null;
	}

	public Korisnik(String username, String sifra, String ime, String prezime, String pol, Date datumRodjenja,
			String uloga, List<IstorijaTreninga> istorijaTreninga, SportskiObjekat sportskiObjekat, Clanarina clanarina,
			List<SportskiObjekat> poseceniObjekti, int brojSakupljenihBodova, TipKupca tipKupca) {
		super();
		this.username = username;
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.istorijaTreninga = istorijaTreninga;
		this.sportskiObjekat = sportskiObjekat;
		this.clanarina = clanarina;
		this.poseceniObjekti = poseceniObjekti;
		this.brojSakupljenihBodova = brojSakupljenihBodova;
		this.tipKupca = tipKupca;
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

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public List<IstorijaTreninga> getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(List<IstorijaTreninga> istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}

	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}

	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
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
