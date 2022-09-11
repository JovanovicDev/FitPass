Vue.component('selected-sport-facility', {
	data() {
		return {
			loggedUser: null,
			selectedSportFacilityId: 0,
			adresa: '',
			selectedSportFacility: {},
			trainings: [],
			comments: []
		}
	},
	template:
	`
		<div>
			<navbar v-if="this.loggedUser != null"></navbar>
			<navbar-guest v-if="this.loggedUser == null"></navbar-guest>
			
			<div class="row justify-content-center my-5 g-0">
				<div class="col-auto text-center w-75">
					<h1>{{this.selectedSportFacility.naziv}}</h1>
					<hr>
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Logo</th>
			    			<th>Tip</th>
			    			<th>Sadržaj</th>
			    			<th>Status</th>
			    			<th>Lokacija</th>
			    			<th>Radno vreme</th>
			    			<th>Prosečna ocena</th>
			    		</tr>
						<tr>
			    			<td><img height="120px" width="120px" v-bind:src="this.selectedSportFacility.logo"></td>
			    			<td>{{this.selectedSportFacility.tip}}</td>
			    			<td>{{this.selectedSportFacility.sadrzaj}}</td>
			    			<td>{{this.selectedSportFacility.status}}</td>
			    			<td>{{this.adresa}}</td>
			    			<td>{{this.selectedSportFacility.radnoVreme}}</td>
			    			<td>{{this.selectedSportFacility.prosecnaOcena}}</td>			    			
			    		</tr>
			    	</table>
			    	
			    	<h1>Treninzi</h1>
					<hr>
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Slika</th>
			    			<th>Naziv</th>
			    			<th>Opis</th>
			    			<th>Doplata</th>
			    			<th>Trener</th>			    			
			    		</tr>
						<tr v-for="t in trainings">
			    			<td><img height="120px" width="120px" v-bind:src="t.slika"></td>
			    			<td>{{t.naziv}}</td>
			    			<td>{{t.opis}}</td>			    			
			    			<td>{{t.doplata}}</td>
			    			<td v-if="t.trener != null">{{t.trener.ime}} {{t.trener.prezime}}</td>
			    		</tr>
			    	</table>	
			    	
			    	<h1>Komentari</h1>
					<hr>
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Kupac</th>
			    			<th>Tekst</th>
			    			<th>Ocena</th>
			    		</tr>
						<tr v-for="k in comments">
			    			<td>{{k.kupacUsername}}</td>
			    			<td>{{k.tekst}}</td>
			    			<td>{{k.ocena}}</td>
			    		</tr>
			    	</table>
					
				</div>
			</div>
			
		</div>
	`,
	methods: {
		loadSelectedSportFacility(){
			axios
				.get('rest/sportFacilities/getById/' + this.selectedSportFacilityId)
				.then(response => {
					this.selectedSportFacility = response.data;
					this.adresa = response.data.lokacija.adresa;
				})
		},
		
		loadTrainingsInFacility(){
			axios
				.get('rest/trainings/getTrainingsInFacility/' + this.selectedSportFacilityId)
				.then(response => {
					this.trainings = response.data;
				})
		},
		
		loadCommentsOnFacility(){
			axios
				.get('rest/comments/getCommentsOnFacility/' + this.selectedSportFacilityId)
				.then(response => {
					this.comments = response.data;
				})
		}
	},
	mounted() {
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.selectedSportFacilityId = window.localStorage.getItem('selectedSportFacilityId');
		this.loadSelectedSportFacility();
		this.loadTrainingsInFacility();
		this.loadCommentsOnFacility();
	}
});