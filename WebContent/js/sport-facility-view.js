Vue.component("sport-facility-view",{
	data(){
        return{
			loggedUser: null,
			sportFacility: {}
        }
    },
	
	template:
	`	
		<div class="vh-100">
			<navbar></navbar>
			<div id="content" class="d-flex flex-row">
			<profile-sidebar></profile-sidebar>
			
			<div class="container my-5">
			    <div class="align-items-center vw-50">
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
			    			<td>{{this.sportFacility.lokacija.adresa}}</td>
			    			<td>{{this.sportFacility.radnoVreme}}</td>
			    			<td>{{this.sportFacility.prosecnaOcena}}</td>			    			
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
				.then(response => (this.sportFacility = response.data))
		}
	},
	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.loadSportFacility();
	}
})
