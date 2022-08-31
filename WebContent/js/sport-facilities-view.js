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
			    			<th>Logo</th>
			    			<th>Naziv</th>
			    			<th>Tip</th>
			    			<th>Sadržaj</th>
			    			<th>Status</th>
			    			<th>Lokacija</th>
			    			<th>Radno vreme</th>
			    			<th>Prosečna ocena</th>
			    		</tr>
		    			
			    		<tr v-for="s in sortedSportFacilities">
			    			<td>{{s.logo}}</td>
			    			
			    		</tr>
		    		</table>
				</div>
			</div>
			
		</div>
		
	`
	,
	methods:{
			
	},
	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
	}
})
