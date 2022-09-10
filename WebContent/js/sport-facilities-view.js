Vue.component("sport-facilities-view",{
	data(){
        return{
			loggedUser: {},
			sportFacilities: [],
			adminLoggedIn: false,
			filterType: '',
			filterWorking: false,
			searchPressed: false,
			searchName: '',
			searchType: '',
			searchCity: '',
			searchAverageGrade: '',
			currentSort: 'name',
			currentSortDir: 'asc'
        }
    },
	
	template:
	`	
		<div>
			<navbar v-if="this.loggedUser != null"></navbar>
			<navbar-guest v-if="this.loggedUser == null"></navbar-guest>
			
			<div class="row justify-content-center my-5 g-0">
				<div class="col-auto text-center w-75">
					<h1>Sportski objekti</h1>
					<hr>
					
					<div class="input-group justify-content-between my-2 align-items-center">
						<div class="d-inline-flex">
						  	<select id="selectType" class="form-select" v-model="filterType" @change="doFilterType()">
							  	  <option value="TERETANA">Teretana</option>
								  <option value="SPA">Spa centar</option>
								  <option value="BAZEN">Bazen</option>
								  <option value="SPORTSKI">Sportski centar</option>	
						  	</select>	
						  	
						  	<div class="form-check form-switch ms-2 mt-2">
								  <input type="checkbox" id="checkboxWorking" class="form-check-input" v-model="filterWorking" width="10px" @change="doFilterWorking()">					  	
						  		  <label class="form-check-label" for="checkboxWorking">Radi</label>
						  	</div>
						  	
						  	<button type="button" id="resetFiltersButton" class="btn btn-outline-success ms-2 text-nowrap" @click="loadSportFacilities()">Resetuj filtere</button>
						</div>
						<div class="d-inline-flex align-items-center">
							<button type="button" id="addButton" class="btn btn-outline-success me-2" v-if="adminLoggedIn" @click="$router.push('/add-sport-facility')">Dodaj novi</button>
	    					<button type="button" id="searchPressButton" class="btn btn-outline-success ms-3" @click="toggleSearch()">Pretraga</button>				
						</div>									  
					</div>
					<div v-if="searchPressed" class="align-items-center my-2">
	    				<div class="form-outline">
							 <input type="search" id="searchFieldName" class="form-control mx-1" v-model="searchName" placeholder="Naziv"/>
							 <input type="search" id="searchFieldCity" class="form-control mx-1" v-model="searchCity" placeholder="Lokacija"/>
							 <input type="search" id="searchFieldAverageGrade" class="form-control mx-1" v-model="searchAverageGrade" placeholder="Prosečna ocena"/>			    
							 <select class="form-control mx-1" v-model="searchType" id="searchType">
							 	<option disabled selected value>Tip objekta</option>
				       			<option value="TERETANA">Teretana</option>
				       			<option value="SPA">Spa centar</option>
				       			<option value="BAZEN">Bazen</option>
				       			<option value="SPORTSKI">Sportski centar</option>	
				       		</select>
						</div>
						<button type="button" id="searchButton" class="btn btn-outline-success my-2" @click="doSearch()">Pretraži</button>
	    			</div>	
	    			
					<table border="1" class="table table-responsive">
			    		<tr bgcolor="lightgrey" height="2px">
			    			<th>Logo</th>
			    			<th @click="sort('naziv')"><a class="text-reset text-decoration-none" role="button">Naziv</a></th>
			    			<th>Tip</th>
			    			<th>Sadržaj</th>
			    			<th>Status</th>
			    			<th @click="sort('lokacija.adresa')"><a class="text-reset text-decoration-none" role="button">Lokacija</a></th>
			    			<th>Radno vreme</th>
			    			<th @click="sort('prosecnaOcena')"><a class="text-reset text-decoration-none" role="button">Prosečna ocena</a></th>
			    		</tr>
		    			
			    		<tr v-for="s in sortedSportFacilities">
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
				this.filterWorking = false;
				axios
          			.get('rest/sportFacilities/')
          			.then(response => (this.sportFacilities = response.data))
			},
		
			checkIfAdminIsLoggedIn(){
				if(this.loggedUser != null){
					if(this.loggedUser.uloga == 'ADMIN') this.adminLoggedIn = true;
				} else {
					this.adminLoggedIn = false;
				}
			},
			
			doFilterType(){
				if(!this.filterType == ''){
				axios
					.get('rest/sportFacilities/filterType/' + this.filterType)
					.then(response => (this.sportFacilities = response.data))
				}		
			},
			
			doFilterWorking(){			
				if(this.filterWorking == true){
					axios
						.get('rest/sportFacilities/filterWorking/')
						.then(response => (this.sportFacilities = response.data))
				} else {
					this.loadSportFacilities();
				}					
			},
			
			doSearch(){
				var url = 'rest/sportFacilities/search?';
				if(this.searchName != '') url = url + 'name=' + this.searchName + '&'; // MOZDA NIJE NAME NEGO IME AKO PUKNE PROVERI
				if(this.searchType != '') url = url + 'type=' + this.searchType + '&';
				if(this.searchCity != '') url = url + 'city=' + this.searchCity + '&';
				if(this.searchAverageGrade != '') url = url + 'averageGrade=' + this.searchAverageGrade;
				axios
					.get(url)
					.then( res => {
						this.sportFacilities = res.data;
					})
			},
			
			toggleSearch(){
				this.searchPressed = !this.searchPressed;
			},
			
			sort(s){
				if(s === this.currentSort){
					this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
				}
				this.currentSort = s;
			},
	},
	
	computed:{
		sortedSportFacilities(){
			return this.sportFacilities.sort((a,b) => {
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
		this.checkIfAdminIsLoggedIn();
		this.loadSportFacilities();
	}
})
