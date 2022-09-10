package beans;

public class Komentar {
	
	private int id;
	private String kupacUsername;
	private int sportskiObjekatId;
	private String tekst;
	private int ocena; //na skali od 1 do 5
	
	public Komentar() {
		this.id = 0;
		this.kupacUsername = "";
		this.sportskiObjekatId = 0;
		this.tekst = "";
		this.ocena = 0;
	}

	public Komentar(int id, String kupacUsername, int sportskiObjekatId, String tekst, int ocena) {
		super();
		this.id = id;
		this.kupacUsername = kupacUsername;
		this.sportskiObjekatId = sportskiObjekatId;
		this.tekst = tekst;
		this.ocena = ocena;
	}
	
	public Komentar(Komentar k) {
		super();
		this.id = k.getId();
		this.kupacUsername = k.getKupacUsername();
		this.sportskiObjekatId = k.getSportskiObjekatId();
		this.tekst = k.getTekst();
		this.ocena = k.getOcena();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getKupacUsername() {
		return kupacUsername;
	}

	public void setKupacUsername(String kupacUsername) {
		this.kupacUsername = kupacUsername;
	}

	public int getSportskiObjekatId() {
		return sportskiObjekatId;
	}

	public void setSportskiObjekatId(int sportskiObjekatId) {
		this.sportskiObjekatId = sportskiObjekatId;
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
