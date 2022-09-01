Vue.component("sport-facilities-view",{
	data(){
        return{
			loggedUser: null,
			sportFacilities: [],
        }
    },
	
	template:
	`	
		<div>
			<navbar v-if="this.loggedUser != null"></navbar>
			<navbar-guest v-if="this.loggedUser == null"></navbar-guest>
			
			<div class="row justify-content-center my-5 g-0">
				<div class="col-auto text-center w-75">
					<h1>Prikaz sportskih objekata</h1>
					<hr>
	    			<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Redni broj</th>
			    			<th>Logo</th>
			    			<th>Naziv</th>
			    			<th>Tip</th>
			    			<th>Sadržaj</th>
			    			<th>Status</th>
			    			<th>Lokacija</th>
			    			<th>Radno vreme</th>
			    			<th>Prosečna ocena</th>
			    		</tr>
		    			
			    		<tr v-for="s in sportFacilities">
			    			<td>{{s.id}}</td>
			    			<td><img height="120px" width="120px" v-bind:src="s.logo"></td>
			    			<td>{{s.naziv}}</td>
			    			<td>{{s.tip}}</td>
			    			<td>{{s.sadrzaj}}</td>
			    			<td>{{s.status}}</td>
			    			<td>{{s.lokacija.adresa}}</td>
			    			<td>{{s.radnoVreme}}</td>
			    			<td>{{s.prosecnaOcena}}</td>			    			
			    		</tr>
		    		</table>
				</div>
			</div>
			
		</div>
		
	`
	,
	methods:{
			loadSportFacilities(){
			axios
          		.get('rest/sportFacilities/')
          		.then(response => (this.sportFacilities = response.data))
		}
	},
	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.loadSportFacilities();
	}
})
