Vue.component("sport-facility-view",{
	data(){
        return{
			loggedUser: null,
			sportFacility: {},
			adress: '',
			trainers: []
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
			    	<h1>Prikaz sportskog objekta</h1>
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
			    	<h1>Prikaz trenera</h1>
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
			   </div>
		</div>
		
			</div>			
		</div>
		
	`
	,
	methods:{
		loadSportFacility(){
			axios
				.get('rest/users/getManagersFacility/' + this.loggedUser.username)
				.then(response => {
					this.sportFacility = response.data;
					this.adress = response.data.lokacija.adresa;
					this.getTrainersInFacility(response.data.id);
				})
		},
		getTrainersInFacility(id){
			axios
				.get('rest/trainings/getTrainersInFacility/' + id)
				.then(response => (this.trainers = response.data))
		}
	},
	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.loadSportFacility();
	}
})
