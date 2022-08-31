Vue.component("users-view",{
	data(){
        return{
			loggedUser: {},
			users: [],
			search: '',	
			filterRole: '',
			filterType: '',
			currentSort: 'username',
			currentSortDir: 'asc'		
        }
    },
	
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="row justify-content-center my-5 g-0">
				<div class="col-auto text-center w-75">
					<h1>Prikaz korisnika</h1>
					<hr>
					<div class="input-group justify-content-between my-2">
						<div class="d-inline-flex">
							<select id="selectRole" class="form-select" v-model="filterRole" @change="doFilterRole()">
								  <option value="KUPAC">Kupac</option>
								  <option value="TRENER">Trener</option>
								  <option value="MENADZER">Menadžer</option>	
								  <option value="ADMIN">Admin</option>		  	
						  	</select>
						  	<select id="selectType" class="form-select ms-2" v-model="filterType" @change="doFilterType()">
							  	  <option>Standard</option>
								  <option>Bronzani</option>
								  <option>Srebrni</option>
								  <option>Zlatni</option>	
						  	</select>						  	
						  	<button type="button" id="resetFiltersButton" class="btn btn-outline-success ms-2 text-nowrap" @click="loadUsers()">Resetuj filtere</button>
						</div>
						<div class="d-inline-flex">
							<div class="form-outline">
						  		<input type="search" id="searchField" class="form-control mx-1" v-model="search"/>			    
						 	</div>
						  	<button type="button" id="searchButton" class="btn btn-outline-success ms-3" @click="doSearch()">Pretraži</button>
						</div>					  
					  
					</div>

	    			<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th @click="sort('username')"><a class="text-reset text-decoration-none" role="button">Korisničko ime</a></th>
			    			<th @click="sort('ime')"><a class="text-reset text-decoration-none" role="button">Ime</a></th>
			    			<th @click="sort('prezime')"><a class="text-reset text-decoration-none" role="button">Prezime</a></th>
			    			<th>Uloga</th>
			    			<th>Tip</th>
			    			<th @click="sort('brojSakupljenihBodova')"><a class="text-reset text-decoration-none" role="button">Broj bodova</a></th>
			    		</tr>
		    			
			    		<tr v-for="u in sortedUsers">
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
		doSearch(){
			if(this.search == ''){
				this.loadUsers();
			} else {
				axios
					.get('rest/users/search/' + this.search)
					.then(response => (this.users = response.data))
			}
		},
		
		doFilterRole(){
			if(!this.filterRole == ''){
				axios
					.get('rest/users/filterRole/' + this.filterRole)
					.then(response => (this.users = response.data))
			}	
		},
		
		doFilterType(){
			if(!this.filterType == ''){
				axios
					.get('rest/users/filterType/' + this.filterType)
					.then(response => (this.users = response.data))
			}		
		},
		
		sort(s){
			if(s === this.currentSort){
				this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
			}
			this.currentSort = s;
		},
		
		loadUsers(){
			axios
          		.get('rest/users/')
          		.then(response => (this.users = response.data))
		}
			
	},
	
	computed:{
		sortedUsers(){
			return this.users.sort((a,b) => {
				let modifier = 1;
				if(this.currentSortDir === 'desc') modifier = -1;
				if(a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
				if(a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
				return 0;
			});
		}
	},
	
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.loadUsers();		
	}
})
