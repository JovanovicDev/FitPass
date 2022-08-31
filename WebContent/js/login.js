Vue.component("login",{
	data(){
        return{
            form: {
                username: '',
                password: ''
            },
			loginError:false
        }
    },
	template:
	`	<div class="d-flex flex-column">
			<navbar-guest></navbar-guest>
			<div class="d-flex flex-grow-1 align-items-center justify-content-center my-5">
		 		<div class="row">
			    	<div class="col">
			      	<form v-on:submit.prevent="checkForm" class="form-floating text-center">
							<h1>Logovanje</h1>
							<hr>
							<div class="form-floating mb-3">
								 <input type="text" class="form-control" placeholder="username" v-model="form.username" required>
								 <label for="floatingInput">Korisničko ime</label>
							</div>
							<div class="form-floating">
								 <input type="password" class="form-control" placeholder="Password" v-model="form.password"  required>
								 <label for="floatingPassword">Šifra</label>
								<br>
							</div>
							<button type="submit" class="btn btn-primary align-center"><strong>Uloguj se</strong></button>
							<p class="mt-2">Nemate nalog? <a href="#/registration">Registrujte se</a>.</p>
						</form>
						<p v-if="loginError" class="error" style="color:red">
					  	  <b>Uneli ste netačan podatak!</b>
						</p>
			    	</div>		   
		 		</div>
			</div>
		</div>
		
		
	`
	,
	methods:{
		checkForm : function(){
			
			axios.post('rest/log/in', this.form)
             .then((res) => {
				if(res.status !== 204){
					window.localStorage.setItem('loggedUser', JSON.stringify(res.data));
					this.loginError = false;
					this.$router.push('/home');
					return;
				}
				this.loginError = true;				
             });
		}
	}
	,
	mounted(){
		window.localStorage.clear();
		
	}
})
