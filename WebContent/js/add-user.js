Vue.component("add-user",{
	data(){
        return{
			loggedUser: {},
            form: {
                username: '',
                name: '',
                lastName: '',
                gender: '',
                dateOfBirth: '',
                role: '',
                password: '',
                confirm: ''		
            },
			passwordError: false,
			submitError:false
        }
    },
	
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="container my-5">
		 	<div class="row">
			    <div class="col"></div>
			    <div class="col align-items-center">
					<form v-on:submit.prevent="checkForm">
						<h1>Registracija novog člana</h1>
						<hr>
						<label for="username"><b>Korisničko ime</b></label>
						<input type="text" class="form-control" placeholder="Korisničko ime" name="username" v-model="form.username" required>
						<br>
						<label for="name"><b>Ime</b></label>
						<input type="text" class="form-control" placeholder="Ime" name="name" v-model="form.name" required>
						<br>
						<label for="lastname"><b>Prezime</b></label>
						<input type="text" class="form-control" placeholder="Prezime" name="lastname" v-model="form.lastName" required>
						<br>
						<label for="gender"><b>Pol</b>&nbsp;&nbsp;&nbsp;</label>
						<select name="gender" v-model="form.gender" class="btn btn-primary" required>
							<option value="MUSKI">Muški</option>
							<option value="ZENSKI">Ženski</option>
						</select>
						<br><br>
						<label for="date"><b>Datum rođenja</b></label>
						<input type="date" class="form-control" placeholder="Datum rođenja" name="date" v-model="form.dateOfBirth" required>
						<br>
						<label for="role"><b>Uloga</b>&nbsp;&nbsp;&nbsp;</label>
						<select name="role" v-model="form.role" class="btn btn-primary" required>
							<option value="MENADZER">Menadžer</option>
							<option value="TRENER">Trener</option>
						</select>
						<br><br>
						<label for="password"><b>Šifra</b></label>
						<input type="password" class="form-control" placeholder="Šifra" name="password"  v-model="form.password" required>
						<br>
						<label for="confirm"><b>Potvrda šifre</b></label>
						<input type="password" class="form-control" placeholder="Potvrda šifre" name="confirm" v-model="form.confirm" required>
						<hr>
						<button type="submit" class="btn-lg btn-primary"><strong>Kreiraj</strong></button>
					</form>
					<p v-if="passwordError">
					    <b>Šifre se ne poklapaju!</b>
				  	</p>
					<p v-if="submitError">
					    <b>Korisničko ime već postoji!</b>
					</p>
			    </div>
			    <div class="col"></div>
		 	</div>
		</div>
		</div>
		
	`
	,
	methods:{
		checkForm : function(){
			if (this.form.password != this.form.confirm){
				this.passwordError = true;
			}
			else{
				axios.post('rest/registration', this.form)
                 .then((res) => {
					window.localStorage.setItem('loggedUser', JSON.stringify(res.data));
					this.$router.push('/');
                 })
                 .catch(() => {
                     this.submitError = true;
                 });
			}
		}
	},	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));	
	}
})
