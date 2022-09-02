Vue.component("add-sport-facility",{
	data(){
        return{
			loggedUser: {},
            form: {
                naziv: '',
                tip: '',
                sadrzaj: '',
                status: '',
                geografskaSirina: '',
                geografskaDuzina: '',
                adresa: '',
                logo: '',
                prosecnaOcena: '',
                radnoVreme: ''		
            },
			submitError:false
        }
    },
	
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="container my-5">
		 	<div class="row justify-content-center">
			    <div class="col-auto align-items-center">
					<form v-on:submit.prevent="checkForm">
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
						<input type="text" class="form-control" placeholder="Geografska širina" name="geoWidth"  v-model="form.geografskaSirina" required>
						<br>
						<label for="geoLat"><b>Geografska dužina</b></label>
						<input type="text" class="form-control" placeholder="Geografska dužina" name="geoLat" v-model="form.geografskaDuzina" required>
						<br>
						<label for="adress"><b>Adresa</b></label>
						<input type="text" class="form-control" placeholder="Adresa" name="adress" v-model="form.adresa" required>
						<br>
						<label for="logo"><b>Logo</b></label>
						<input type="text" class="form-control" placeholder="Logo PH" name="logo" v-model="form.logo" required>
						
						<br>
						<label for="averageGrade"><b>Prosečna ocena</b></label>
						<input type="text" class="form-control" placeholder="Prosečna ocena" name="averageGrade" v-model="form.prosecnaOcena" required>
						<br>
						<label for="workingHours"><b>Radno vreme</b></label>
						<input type="text" class="form-control" placeholder="Radno vreme" name="workingHours" v-model="form.radnoVreme" required>
						<hr>
						<button type="submit" class="btn btn-outline-success"><strong>Kreiraj</strong></button>
					</form>
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
		checkForm : function(){
				axios.post('rest/sportFacilities/add', this.form)
                 .then(() => {
					this.$router.push('/sport-facilities');
                 })
                 .catch(() => {
                     this.submitError = true;
                 });			
		}
	},	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));	
	}
})
