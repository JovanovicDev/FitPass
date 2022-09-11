Vue.component("add-training",{
	data(){
        return{
			loggedUser: {},
			
            form: {
                naziv: '',
                tip: '',
                sportskiObjekatId: 0,
                trajanje: 0,
                trenerId: 0,
                opis: '',
                slika: '',
                doplata: ''	
            },
			submitError:false,
			previewImage: null,
			managers: [],
			noManagers: false
        }
    },
	
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="container my-5">
		 	<div class="row justify-content-center">
			    <div class="col-auto align-items-center">
					<form v-on:submit.prevent="addSportFacility">
						<h1>Dodavanje novog sadržaja</h1>
						<hr>
						<label for="name"><b>Naziv</b></label>
						<input type="text" class="form-control" placeholder="Naziv" name="name" v-model="form.naziv" required>
						<br>
						<label for="type"><b>Tip</b></label>
						<select name="type" v-model="form.tip" class="btn btn-outline-success ms-3" required>
							<option value="trening">Trening</option>
							<option value="spa">Spa</option>
							<option value="bazen">Bazen</option>
							<option value="sauna">Sauna</option>
						</select>
						<br><br>
						<label for="content"><b>Sadržaj</b></label>
						<input type="text" class="form-control" placeholder="Sadržaj" name="content" v-model="form.sadrzaj" required>
						<br>
						<label for="status"><b>Status</b></label>
						<select name="status" v-model="form.status" class="btn btn-outline-success ms-3" required>
							<option>Radi</option>
							<option>Ne radi</option>
						</select>
						<br><br>
						<label for="geoWidth"><b>Geografska širina</b></label>
						<input type="text" class="form-control" placeholder="Geografska širina" name="geoWidth"  v-model="lokacija.geografskaSirina" required>
						<br>
						<label for="geoLat"><b>Geografska dužina</b></label>
						<input type="text" class="form-control" placeholder="Geografska dužina" name="geoLat" v-model="lokacija.geografskaDuzina" required>
						<br>
						<label for="adress"><b>Adresa</b></label>
						<input type="text" class="form-control" placeholder="Adresa" name="adress" v-model="lokacija.adresa" required>
						<br>
						
						<label for="logo" class="mb-1"><b>Logo</b></label>
						<div class="row">
							<div class="col">
								<input type="file" accept="image/png" name="logo" id="logo" @change=uploadImage required>
							</div>
							<div class="col">
								<img v-bind:src="this.previewImage" class="d-flex" width="120px" height="120px"/>
							</div>
						</div>
												
						<br>
						<label for="averageGrade"><b>Prosečna ocena</b></label>
						<input type="text" class="form-control" placeholder="Prosečna ocena" name="averageGrade" v-model="form.prosecnaOcena" required>
						<br>
						<label for="workingHours"><b>Radno vreme</b></label>
						<input type="text" class="form-control" placeholder="Radno vreme" name="workingHours" v-model="form.radnoVreme" required>
						<br>
						<label for="managers"><b>Menadžer</b></label>
						<select v-model="managerFullName" id="managers" class="form-control" @change="setManager()" required>
							<option v-for="m in managers">{{m.ime}} {{m.prezime}} {{m.username}}</option>
						</select>
						<hr>
						<button type="submit" class="btn btn-outline-success"><strong>Kreiraj sportski objekat</strong></button>
					</form>
					<p v-if="passwordError">
					    <b>Šifre se ne poklapaju!</b>
				  	</p>
					<p v-if="submitUserError">
					    <b>Korisničko ime već postoji!</b>
					</p>
					<p v-if="submitError">
					    <b>Naziv već postoji!</b>
					</p>
			    </div>
		 	</div>
		</div>
		</div>
		
	`
	,
	methods:{
		
	},	

	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));	
		this.form.sportFacilityId = window.localStorage.getItem('sportFacilityId');
		this.getAvailableManagers();
	}
})
