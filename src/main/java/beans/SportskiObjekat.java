package beans;

public class SportskiObjekat {

	private int id;
	private String naziv;
	private TipSportskogObjekta tip; //teretana, bazen, sportski centar, plesni studio, ...
	private String sadrzaj; //grupni, personalni treninzi, sauna, ...
	private String status; //radi ili ne radi
	private Lokacija lokacija;
	private String logo; //slika, stoji String za sada, mozda putanja do slike?
	private double prosecnaOcena;
	private String radnoVreme; //u formatu XX:XX - XX:XX
	private String menadzerUsername;
	
	public SportskiObjekat() {
		this.id = 0;
		this.naziv = "";
		this.tip = TipSportskogObjekta.TERETANA;
		this.sadrzaj = "";
		this.status = "";
		this.lokacija = null;
		this.logo = "";
		this.prosecnaOcena = 0;
		this.radnoVreme = "";
		this.menadzerUsername = "";
	}
	
	public SportskiObjekat(int id, String naziv, TipSportskogObjekta tip, String sadrzaj, String status, Lokacija lokacija, String logo,
			double prosecnaOcena, String radnoVreme, String menadzerUsername) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = TipSportskogObjekta.TERETANA;
		this.sadrzaj = sadrzaj;
		this.status = status;
		this.lokacija = lokacija;
		this.logo = logo;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
		this.menadzerUsername = menadzerUsername;
	}


	public SportskiObjekat(SportskiObjekat o) {
		super();
		this.id = o.getId();
		this.naziv = o.getNaziv();
		this.tip = o.getTip();
		this.sadrzaj = o.getSadrzaj();
		this.status = o.getStatus();
		this.lokacija = o.getLokacija();
		this.logo = o.getLogo();
		this.prosecnaOcena = o.getProsecnaOcena();
		this.radnoVreme = o.getRadnoVreme();
		this.menadzerUsername = o.getMenadzerUsername();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipSportskogObjekta getTip() {
		return tip;
	}

	public void setTip(TipSportskogObjekta tip) {
		this.tip = tip;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public String getRadnoVreme() {
		return radnoVreme;
	}

	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
	}
	
	public String getMenadzerUsername() {
		return menadzerUsername;
	}

	public void setMenadzerUsername(String menadzerUsername) {
		this.menadzerUsername = menadzerUsername;
	}

}
