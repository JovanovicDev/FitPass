Vue.component("sport-facility-view",{
	data(){
        return{
			loggedUser: null,
			sportFacility: {},
			adress: '',
			trainers: [],
			visitors: [],
			trainings: []
        }
    },
	
	template:
	`	
		<div class="vh-100">
			<navbar></navbar>
			<div id="content" class="d-flex flex-row">
			<profile-sidebar></profile-sidebar>
			
			<div class="container my-5">
			    <div class="align-items-center text-center vw-50">
			    	<h1>Sportski objekat</h1>
					<hr>
			    	<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Logo</th>
			    			<th>Naziv</th>
			    			<th>Tip</th>
			    			<th>Sadržaj</th>
			    			<th>Status</th>
			    			<th>Lokacija</th>
			    			<th>Radno vreme</th>
			    			<th>Prosečna ocena</th>
			    		</tr>
						<tr>
			    			<td><img height="120px" width="120px" v-bind:src="this.sportFacility.logo"></td>
			    			<td>{{this.sportFacility.naziv}}</td>
			    			<td>{{this.sportFacility.tip}}</td>
			    			<td>{{this.sportFacility.sadrzaj}}</td>
			    			<td>{{this.sportFacility.status}}</td>
			    			<td>{{this.adress}}</td>
			    			<td>{{this.sportFacility.radnoVreme}}</td>
			    			<td>{{this.sportFacility.prosecnaOcena}}</td>			    			
			    		</tr>
			    	</table>
			    	<h1>Sadržaj</h1>
					<hr>
					<div class="d-inline-flex align-items-center">
						<button type="button" id="addButton" class="btn btn-outline-success mb-2" @click="$router.push('/add-training')">Dodaj sadržaj</button>
	    			</div>	
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Slika</th>
			    			<th>Opis</th>
			    			<th>Trener</th>
			    			<th>Doplata</th>
			    		</tr>
						<tr v-for="t in trainings">
			    			<td><img height="120px" width="120px" v-bind:src="t.slika"></td>
			    			<td>{{t.opis}}</td>
			    			<td>{{t.trener.ime}} {{t.trener.prezime}}</td>
			    			<td>{{t.doplata}}</td>
			    		</tr>
			    	</table>
			    	<h1>Treneri</h1>
					<hr>
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Korisničko ime</th>
			    			<th>Ime</th>
			    			<th>Prezime</th>
			    			<th>Pol</th>
			    			<th>Datum rođenja</th>
			    		</tr>
						<tr v-for="t in trainers">
			    			<td>{{t.username}}</td>
			    			<td>{{t.ime}}</td>
			    			<td>{{t.prezime}}</td>
			    			<td>{{t.pol}}</td>
							<td>{{t.datumRodjenja}}</td>
			    		</tr>
			    	</table>
			    	<h1>Posetioci</h1>
					<hr>
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Korisničko ime</th>
			    			<th>Ime</th>
			    			<th>Prezime</th>
			    			<th>Pol</th>
			    			<th>Datum rođenja</th>
			    		</tr>
						<tr v-for="v in visitors">
			    			<td>{{v.username}}</td>
			    			<td>{{v.ime}}</td>
			    			<td>{{v.prezime}}</td>
			    			<td>{{v.pol}}</td>
							<td>{{v.datumRodjenja}}</td>
			    		</tr>
			    	</table>	
			   </div>
		</div>
		
			</div>			
		</div>
		
	`
	,
	methods:{
		loadSportFacility(){
			axios
				.get('rest/sportFacilities/getFacilityByManagerUsername/' + this.loggedUser.username)
					.then(response => {
						this.sportFacility = response.data;
						this.adress = response.data.lokacija.adresa;
						this.getTrainersInFacility(response.data.id);
						this.getVisitorsInFacility(response.data.id);
						this.getTrainingsInFacility(response.data.id);
						window.localStorage.setItem('sportFacilityId', response.data.id);
					})
			
		},
		getTrainersInFacility(id){
			axios
				.get('rest/trainings/getTrainersInFacility/' + id)
				.then(response => {
					this.trainers = response.data;
				})
			
		},
		getVisitorsInFacility(id){
			axios
				.get('rest/users/getVisitorsInFacility/' + id)
				.then(response => (this.visitors = response.data))
		},
		getTrainingsInFacility(id){
			axios
				.get('rest/trainings/getTrainingsInFacility/' + id)
				.then(response => {
					this.trainings = response.data;
				})
		}
	},
	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.loadSportFacility();
	}
})
