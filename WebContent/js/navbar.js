Vue.component("navbar",{
	data(){
        return{
			loggedUser: {},
			adminLoggedIn: false,
        }
    },
	template:
	`	
		<div>
			<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
  				<button class="btn btn-outline-success" @click="$router.push('/home')">Početna</button>
				<div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
    				<ul class="navbar-nav">
    					<li class="nav-item mx-2" v-if="adminLoggedIn">
    						<button class="btn btn-outline-success" @click="$router.push('/users')">Korisnici</button>
    					</li>
     					<li class="nav-item mx-2">
       						<button class="btn btn-outline-success" @click="$router.push('/sport-facilities')">Sportski objekti</button>
     					</li>    
      					<li class="nav-item mx-2">
        					<button class="btn btn-outline-success" @click="$router.push('/')">Treninzi</button>
      					</li>   
      					<li class="nav-item mx-2">
        					<button class="btn btn-outline-success" @click="$router.push('/profile')">Profil</button>
      					</li>   
      					<li class="nav-item ms-5">
        					<button class="btn btn-outline-success" v-on:click="logout()">Izlogovanje</button>
      					</li>  
    				</ul>
  				</div>
			</nav>
		</div>
		
	`
	,
	methods:{
			logout:function(){
			window.localStorage.clear();
			this.$router.push('/');
		},
		
		checkIfAdminIsLoggedIn(){
				if(this.loggedUser != null){
					if(this.loggedUser.uloga == 'ADMIN') this.adminLoggedIn = true;
				} else {
					this.adminLoggedIn = false;
				}
			}

	}
	,
	beforeMount(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
		this.checkIfAdminIsLoggedIn();
	}
})
