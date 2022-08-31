Vue.component("users-view",{
	data(){
        return{
			loggedUser: null,
			users: null,
			search: '',
			sortKey: '',
			reverse: false,
			columns: ['Korisničko ime', 'Ime', 'Prezime', 'Uloga', 'Tip', 'Broj bodova'],
        }
    },
	template:
	`	
		<div>
			<navbar></navbar>
			<div class="row justify-content-center my-5">
				<div class="col-auto text-center w-75">
					<h3>Prikaz korisnika</h3>
					<input type="text" v-model="search"/>
					
	    			<table border="1" class="table table-responsive">
			    		<thead>
			    			<tr>
			    				<th v-for:"column in columns">
			    					<a href="#" v-on="click: sortBy(column)" v-class="active: sortKey == column">
			    						{{column}}
			    					</a>
			    				</th>
			    			<tr>
			    		</thead>
		    			
		    			<tbody>
		    				<tr v-for="(u, index) in users | filterBy search | orderBy sortKey reverse">
				    			<td>{{u.username}}</td>
				    			<td>{{u.ime}}</td>
				    			<td>{{u.prezime}}</td>
				    			<td>{{u.uloga}}</td>
								<td>{{u.tipKupca.ime}}</td>
								<td>{{u.brojSakupljenihBodova}}</td>
			    			</tr>
		    			</tbody>			    		
		    		</table>
				</div>
			</div>		
		</div>
		
	`
	,
	methods:{
		sortBy: function(sortKey) {
      		this.reverse = (this.sortKey == sortKey) ? ! this.reverse : false;
      		this.sortKey = sortKey;
    	}	
	}
	,
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		
		axios
          .get('rest/users/')
          .then(response => (this.users = response.data))
	}
})
