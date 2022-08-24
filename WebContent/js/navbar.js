Vue.component("navbar",{
	data(){
        return{
			
        }
    },
	template:
	`	
		<div>
			<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
  				<a class="navbar-brand" href="#">FitPass</a>
				<div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
    				<ul class="navbar-nav">
     					<li class="nav-item mx-2">
       						<a class="nav-link" href="#">Sportski objekti</a>
     					</li>    
      					<li class="nav-item mx-2">
        					<a class="nav-link" href="#">Treninzi</a>
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
			console.log("logging out...")
			window.localStorage.clear();
			this.$router.push('/');
		}

	}
	,
	mounted(){		
		
	}
})
