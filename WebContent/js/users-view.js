Vue.component("users-view",{
	data(){
        return{
			loggedUser: null,
			users: null
        }
    },
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="row justify-content-center my-5">
				<div class="col-auto text-center w-75">
					<h3>Prikaz korisnika</h3>
					
	    			<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey">
			    			<th>Korisničko ime</th>
			    			<th>Ime</th>
			    			<th>Prezime</th>
			    			<th>Uloga</th>
			    			<th>Tip</th>
			    			<th>Broj bodova</th>
			    		</tr>
		    			
			    		<tr v-for="u in users">
			    			<td>{{u.username}}</td>
			    			<td>{{u.ime}}</td>
			    			<td>{{u.prezime}}</td>
			    			<td>{{u.uloga}}</td>
							<td>{{u.tipKupca.ime}}</td>
							<td>{{u.brojSakupljenihBodova}}</td>
			    		</tr>
		    		</table>
				</div>
			</div>		
		</div>
		
	`
	,
	methods:{
			
	}
	,
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		
		axios
          .get('rest/users/')
          .then(response => (this.users = response.data))
	}
})
