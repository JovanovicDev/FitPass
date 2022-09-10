Vue.component("add-sport-facility",{
	data(){
        return{
			loggedUser: {},
            form: {
                naziv: '',
                tip: '',
                sadrzaj: '',
                status: '',
                lokacija: null,
                logo: '',
                prosecnaOcena: '',
                radnoVreme: '',
                menadzerUsername: ''		
            },
            lokacija: {
				geografskaSirina: '',
                geografskaDuzina: '',
                adresa: '',
			},
            manager: {
				username: '',
                name: '',
                lastName: '',
                gender: '',
                dateOfBirth: '',
                role: 'MENADZER',
                password: '',
                confirm: ''
			},
			submitError:false,
			passwordError: false,
			submitUserError: false,
			previewImage: null,
			newManager: null,
			managers: [],
			noManagers: false,
			managerFullName: ''
        }
    },
	
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="container my-5">
		 	<div class="row justify-content-center">
			    <div class="col-auto align-items-center">
			    	<p v-if="noManagers">Nema dostupnih menadžera! Morate dodati novog kako bi ste mogli kreirati sportski objekat.</p>
						<div v-if="noManagers" class="border border-danger rounded p-3 mb-3">
							<form v-on:submit.prevent="checkForm">
								<h1>Registracija novog menadžera</h1>
								<hr>
								<label for="username"><b>Korisničko ime</b></label>
								<input type="text" class="form-control" placeholder="Korisničko ime" name="username" v-model="manager.username" required>
								<br>
								<label for="name"><b>Ime</b></label>
								<input type="text" class="form-control" placeholder="Ime" name="name" v-model="manager.name" required>
								<br>
								<label for="lastname"><b>Prezime</b></label>
								<input type="text" class="form-control" placeholder="Prezime" name="lastname" v-model="manager.lastName" required>
								<br>
								<label for="gender"><b>Pol</b>&nbsp;&nbsp;&nbsp;</label>
								<select name="gender" v-model="manager.gender" class="btn btn-outline-success" required>
									<option value="MUSKI">Muški</option>
									<option value="ZENSKI">Ženski</option>
								</select>
								<br><br>
								<label for="date"><b>Datum rođenja</b></label>
								<input type="date" class="form-control" placeholder="Datum rođenja" name="date" v-model="manager.dateOfBirth" required>
								<br>
								<label for="password"><b>Šifra</b></label>
								<input type="password" class="form-control" placeholder="Šifra" name="password"  v-model="manager.password" required>
								<br>
								<label for="confirm"><b>Potvrda šifre</b></label>
								<input type="password" class="form-control" placeholder="Potvrda šifre" name="confirm" v-model="manager.confirm" required>
								<hr>
								<button type="submit" class="btn btn-outline-success"><strong>Kreiraj menadžera</strong></button>
							</form>
						</div>
					<form v-on:submit.prevent="addSportFacility" v-if="!noManagers">
						<h1>Registracija novog sportskog objekta</h1>
						<hr>
						<label for="name"><b>Naziv</b></label>
						<input type="text" class="form-control" placeholder="Naziv" name="name" v-model="form.naziv" required>
						<br>
						<label for="type"><b>Tip</b></label>
						<input type="text" class="form-control" placeholder="Tip" name="type" v-model="form.tip" required>
						<br>
						<label for="content"><b>Sadržaj</b></label>
						<input type="text" class="form-control" placeholder="Sadržaj" name="content" v-model="form.sadrzaj" required>
						<br>
						<label for="status"><b>Status</b>&nbsp;&nbsp;&nbsp;</label>
						<select name="status" v-model="form.status" class="btn btn-outline-success" required>
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
		addSportFacility : function(){
				let reader = new FileReader();
				reader.onloadend = () => {
					let b64 = reader.result.replace(/^data:.+;base64,/, '');
					this.form.logo = b64;
					this.form.lokacija = this.lokacija;
					axios.post('rest/sportFacilities/add', this.form)
						.then((res) => {
							this.newManager.sportskiObjekatId = res.data.id;
							this.updateManager();
						});
				}
				reader.readAsDataURL(document.getElementById('logo').files[0]);
		},
		
		checkForm : function(){
			if (this.manager.password != this.manager.confirm){
				this.passwordError = true;
			}
			else{
				axios.post('rest/registration', this.manager)
                 .then(() => {
					this.getAvailableManagers();
					this.noManagers = false;
                 })
                 .catch(() => {
                     this.submitError = true;
                 });
			}
		},
		
		uploadImage(e){
			const image = e.target.files[0];
			const reader = new FileReader();
			reader.readAsDataURL(image);
			reader.onload = e => {
				this.previewImage = e.target.result;
			};
		},
		
		getAvailableManagers: function(){
			axios.get('rest/users/managers/')
			.then(response =>{
				this.managers = response.data
				if(this.managers.length == 0) this.noManagers = true;
				else this.noManagers = false;
			})
		},
		
		setManager: function(){
			let text = this.managerFullName;
			const Array = text.split(" ");
			this.form.menadzerUsername = Array[2];
			axios.get('rest/users/getByUsername/' + Array[2])
			.then((res) => {
				this.newManager = res.data;
			})
		},
		
		updateManager: function(){
			axios.put('rest/users/update', this.newManager)
			.then(() => {
				this.$router.push('/sport-facilities');	
			})
		},
	},	

	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));	
		this.getAvailableManagers();
	}
})
