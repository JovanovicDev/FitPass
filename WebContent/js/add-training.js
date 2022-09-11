Vue.component("add-training",{
	data(){
        return{
			loggedUser: {},
			
            form: {
                naziv: '',
                tip: '',
                trajanje: '',
                trenerUsername: '',
                sportskiObjekatId: 0,
                trener: {},
                opis: '',
                slika: '',
                doplata: ''	
            },
			submitError:false,
			previewImage: null,
			trainers: [],
			noTrainers: false,
			isTraining: false,
			trainerFullName: ''
        }
    },
	
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="container my-5">
		 	<div class="row justify-content-center">
			    <div class="col-auto align-items-center">
					<form v-on:submit.prevent="addContent">
						<h1>Dodavanje novog sadržaja</h1>
						<hr>
						<label for="name"><b>Naziv</b></label>
						<input type="text" class="form-control" placeholder="Naziv" name="name" v-model="form.naziv" required>
						<br>
						<label for="type"><b>Tip</b></label>
						<select name="type" v-model="form.tip" class="btn btn-outline-success ms-3" @change="checkType()" required>
							<option value="trening">Trening</option>
							<option value="spa">Spa</option>
							<option value="bazen">Bazen</option>
							<option value="sauna">Sauna</option>
						</select>
						<br><br>
						<label for="duration"><b>Trajanje</b></label>
						<input type="text" class="form-control" placeholder="Trajanje (opciono)" name="duration" v-model="form.trajanje">
						<br>
						
						<div v-if="isTraining">
							<label for="trainers"><b>Trener</b></label>
							<div v-if="noTrainers">
								<br>
								<p>Nema trenera na raspolaganju, mora se uneti novi!</p>
							</div>
							<select v-model="trainerFullName" id="trainers" class="form-control" @change="setTrainer()" required>
								<option v-for="t in trainers">{{t.ime}} {{t.prezime}} {{t.username}}</option>
							</select>
							<br><br>
						</div>						
						
						<label for="desc"><b>Opis</b></label>
						<input type="text" class="form-control" placeholder="Opis (opciono)" name="desc"  v-model="form.opis">
						<br>
						
						<label for="slika" class="mb-1"><b>Slika</b></label>
						<div class="row">
							<div class="col">
								<input type="file" accept="image/png" name="slika" id="slika" @change=uploadImage required>
							</div>
							<div class="col">
								<img v-bind:src="this.previewImage" class="d-flex" width="120px" height="120px"/>
							</div>
						</div>
												
						<br>
						<label for="price"><b>Doplata</b></label>
						<input type="text" class="form-control" placeholder="Doplata" name="price" v-model="form.doplata" required>
						<br>
						<hr>
						<button type="submit" class="btn btn-outline-success"><strong>Dodaj sadržaj</strong></button>
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
		addContent : function(){				
				let reader = new FileReader();
				reader.onloadend = () => {
					let b64 = reader.result.replace(/^data:.+;base64,/, '');
					this.form.slika = b64;
					if(this.isTraining == false){
						this.form.trenerUsername = '';
						this.form.trener = null;
					}
					axios.post('rest/trainings/add', this.form)
						.then(() => {
							this.$router.push('/sport-facility');	
						});
				}
				reader.readAsDataURL(document.getElementById('slika').files[0]);
		},
		
		checkType(){
			if(this.form.tip == 'trening') this.isTraining = true;
			else this.isTraining = false;
		},
		
		setTrainer: function(){
			let text = this.trainerFullName;
			const Array = text.split(" ");
			this.form.trenerUsername = Array[2];
			axios.get('rest/users/getByUsername/' + Array[2])
			.then((res) => {
				this.form.trener = res.data;
			})
		},
		
		uploadImage(e){
			const image = e.target.files[0];
			const reader = new FileReader();
			reader.readAsDataURL(image);
			reader.onload = e => {
				this.previewImage = e.target.result;
			};
		},
		
		getTrainers: function(){
			axios.get('rest/users/trainers/')
			.then(response =>{
				this.trainers = response.data;
				if(this.trainers.length == 0) this.noTrainers = true;
				else this.noTrainers = false;
			})
		},
	},	

	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));	
		this.form.sportskiObjekatId = window.localStorage.getItem('sportFacilityId');
		this.getTrainers();
	}
})
