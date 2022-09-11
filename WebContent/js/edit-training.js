Vue.component('edit-training', {
	data() {
		return {
			loggedUser: {},
			selectedTrainingId: 0,
			training: {},
			submitError: false,
			trainers: [],
			isTraining: false,
			noTrainers: false,
			previewImage: null,
			trainerFullName: ''
		}
	},
	template:
	`
		<div class="vh-100">
			<navbar></navbar>
			<div id="content" class="d-flex flex-row">			
			<div class="container my-5">
		 	<div class="row">
			    <div class="col"></div>
			    <div class="col-auto align-items-center">
					<form v-on:submit.prevent="editContent">
						<h1>Izmena sadržaja</h1>
						<hr>
						<label for="name"><b>Naziv</b></label>
						<input type="text" class="form-control" placeholder="Naziv" name="name" v-model="training.naziv">
						<br>
						<label for="type"><b>Tip</b></label>
						<select name="type" v-model="training.tip" class="btn btn-outline-success ms-3" @change="checkType()" required>
							<option value="trening">Trening</option>
							<option value="spa">Spa</option>
							<option value="bazen">Bazen</option>
							<option value="sauna">Sauna</option>
						</select>
						<br><br>
						<label for="duration"><b>Trajanje</b></label>
						<input type="text" class="form-control" placeholder="Trajanje (opciono)" name="duration" v-model="training.trajanje">
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
						<input type="text" class="form-control" placeholder="Opis (opciono)" name="desc"  v-model="training.opis">
						<br>
						
						<label for="slika" class="mb-1"><b>Slika</b></label>
						<div class="row">
							<div class="col">
								<input type="file" accept="image/png" name="slika" id="slika" @change=uploadImage>
							</div>
							<div class="col">
								<img v-bind:src="this.previewImage" class="d-flex" width="120px" height="120px"/>
							</div>
						</div>
												
						<br>
						<label for="price"><b>Doplata</b></label>
						<input type="text" class="form-control" placeholder="Doplata" name="price" v-model="training.doplata">
						<br>
						<hr>
						<button type="submit" class="btn btn-outline-success"><strong>Izmeni sadržaj</strong></button>
					</form>
					<p v-if="submitError">
					    <b>Naziv već postoji!</b>
					</p>
			    </div>
			    <div class="col"></div>
		 	</div>
		</div>
		
			</div>			
		</div>
	`,
	methods: {		
		editContent : function(){				
				let reader = new FileReader();
				reader.onloadend = () => {
					let b64 = reader.result.replace(/^data:.+;base64,/, '');
					this.training.slika = b64;
					if(this.isTraining == false){
						this.training.trenerUsername = '';
						this.training.trener = null;
					}
					axios.put('rest/trainings/update', this.training)
						.then(() => {
							this.$router.push('/sport-facility');	
						});
				}
				if(document.getElementById('slika').files.length != 0) {
					reader.readAsDataURL(document.getElementById('slika').files[0]);
				} else {
					if(this.isTraining == false){
						this.training.trenerUsername = '';
						this.training.trener = null;
					}
					axios.put('rest/trainings/update', this.training)
						.then(() => {
							this.$router.push('/sport-facility');	
						});
				}
		},
		
		loadTraining(){
			axios
				.get('rest/trainings/getById/' + this.selectedTrainingId)
				.then(response => {
					this.training = response.data;
				})
		},
		
		checkType(){
			if(this.training.tip == 'trening') this.isTraining = true;
			else this.isTraining = false;
		},
		
		getTrainers: function(){
			axios.get('rest/users/trainers/')
			.then(response =>{
				this.trainers = response.data;
				if(this.trainers.length == 0) this.noTrainers = true;
				else this.noTrainers = false;
			})
		},
		
		setTrainer: function(){
			let text = this.trainerFullName;
			const Array = text.split(" ");
			this.training.trenerUsername = Array[2];
			axios.get('rest/users/getByUsername/' + Array[2])
			.then((res) => {
				this.training.trener = res.data;
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
	},
	mounted() {
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.selectedTrainingId = window.localStorage.getItem('selectedTrainingId');
		this.loadTraining();
		this.getTrainers();
	}
});