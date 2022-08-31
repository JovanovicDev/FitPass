Vue.component("Profile",{
	data(){
        return{
            loggedUser: {},
            newPassword: null,
            confirmPassword: null,
			passwordError: false,
			submitError:false
        }
    },
	template:
	`	
		<div class="vh-100">
			<navbar></navbar>
			<div id="content" class="d-flex flex-row">
			<profile-sidebar></profile-sidebar>
			
			<div class="container my-5">
		 	<div class="row">
			    <div class="col"></div>
			    <div class="col align-items-center" v-if="loggedUser">
					<form v-on:submit.prevent="checkForm">
						<h1>Lični podaci</h1>
						<hr>
						<label for="username"><b>Korisničko ime</b></label>
						<input type="text" class="form-control" name="username" v-model="loggedUser.username">
						<br>
						<label for="name"><b>Ime</b></label>
						<input type="text" class="form-control" placeholder="Ime" name="name" v-model="loggedUser.ime">
						<br>
						<label for="lastname"><b>Prezime</b></label>
						<input type="text" class="form-control" placeholder="Prezime" name="lastname" v-model="loggedUser.prezime">
						<br>
						<label for="gender"><b>Pol</b>&nbsp;&nbsp;&nbsp;</label>
						<select name="gender" v-model="loggedUser.pol" class="btn btn-primary">
							<option value="MUSKI">Muški</option>
							<option value="ZENSKI">Ženski</option>
						</select>
						<br><br>
						<label for="date"><b>Datum rođenja</b></label>
						<input type="date" class="form-control" placeholder="Datum rođenja" name="date" v-model="loggedUser.datumRodjenja">
						<br>
						<label for="password"><b>Šifra</b></label>
						<input type="password" class="form-control" placeholder="Šifra" name="password" v-model="newPassword">
						<br>
						<label for="confirm"><b>Potvrda šifre</b></label>
						<input type="password" class="form-control" placeholder="Potvrda šifre" name="confirm" v-model="confirmPassword">
						<hr>
						<button type="submit" class="btn-lg btn-primary"><strong>Izmeni podatke</strong></button>
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
		</div>
		
	`
	,
	methods:{
		checkForm : function(){
			if (this.newPassword != this.confirmPassword){
				this.passwordError = true;
			}
			else{
				this.passwordError = false;
				this.loggedUser.sifra = this.newPassword;
				window.localStorage.setItem('loggedUser', JSON.stringify(this.loggedUser));
			 	this.editUser();
			}
		},
		
		editUser : function () {
			event.preventDefault();
			axios.put('rest/users/update', this.loggedUser)
			.then(() => {
				this.$router.push('/home');
			})
		}
	}
	,
	mounted(){
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
	}
})
