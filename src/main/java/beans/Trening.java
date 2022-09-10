package beans;

public class Trening {

	private int id;
	private String naziv;
	private String tip; //grupni, personalni, teretana, ...
	private int sportskiObjekatId;
	private int trajanje; //u minutima
	private Korisnik trener; //ako postoji
	private String opis;
	private String slika; //slika, stoji String za sada, mozda putanja do slike?
	
	public Trening() {
		this.id = 0;
		this.naziv = "";
		this.tip = "";
		this.sportskiObjekatId = 0;
		this.trajanje = 0;
		this.trener = null;
		this.opis = "";
		this.slika = "";
	}

	public Trening(int id, String naziv, String tip, int sportskiObjekatId, int trajanje, Korisnik trener,
			String opis, String slika) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.sportskiObjekatId = sportskiObjekatId;
		this.trajanje = trajanje;
		this.trener = trener;
		this.opis = opis;
		this.slika = slika;
	}
	
	public Trening(Trening t) {
		super();
		this.id = t.getId();
		this.naziv = t.getNaziv();
		this.tip = t.getTip();
		this.sportskiObjekatId = t.getSportskiObjekatId();
		this.trajanje = t.getTrajanje();
		this.trener = t.getTrener();
		this.opis = t.getOpis();
		this.slika = t.getSlika();
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

	public int getSportskiObjekatId() {
		return sportskiObjekatId;
	}

	public void setSportskiObjekatId(int sportskiObjekatId) {
		this.sportskiObjekatId = sportskiObjekatId;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public Korisnik getTrener() {
		return trener;
	}

	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
		
}
