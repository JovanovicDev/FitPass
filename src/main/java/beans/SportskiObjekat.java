package beans;

public class SportskiObjekat {

	private int id;
	private String naziv;
	private String tip; //teretana, bazen, sportski centar, plesni studio, ...
	private String sadrzaj; //grupni, personalni treninzi, sauna, ...
	private String status; //radi ili ne radi
	private Lokacija lokacija;
	private String logo; //slika, stoji String za sada, mozda putanja do slike?
	private double prosecnaOcena;
	private String radnoVreme; //u formatu XX:XX - XX:XX
	
	public SportskiObjekat() {
		this.id = -1;
		this.naziv = "";
		this.tip = "";
		this.sadrzaj = "";
		this.status = "";
		this.lokacija = null;
		this.logo = "";
		this.prosecnaOcena = 0;
		this.radnoVreme = "";
	}
	
	public SportskiObjekat(int id, String naziv, String tip, String sadrzaj, String status, Lokacija lokacija, String logo,
			double prosecnaOcena, String radnoVreme) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.sadrzaj = sadrzaj;
		this.status = status;
		this.lokacija = lokacija;
		this.logo = logo;
		this.prosecnaOcena = prosecnaOcena;
		this.radnoVreme = radnoVreme;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
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
	
}
