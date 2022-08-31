Vue.component("navbar-guest",{
	data(){
        return{
			loggedUser: {},
        }
    },
	template:
	`	
		<div>
			<nav class="navbar navbar-expand-lg navbar-light bg-light px-5">
  				<button class="btn btn-outline-success" @click="$router.push('/')">Početna</button>
				<div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
    				<ul class="navbar-nav">
     					<li class="nav-item mx-2">
       						<button class="btn btn-outline-success" @click="$router.push('/sport-facilities')">Sportski objekti</button>
     					</li>    
      					<li class="nav-item mx-2">
        					<button class="btn btn-outline-success" @click="$router.push('/')">Treninzi</button>
      					</li> 
      					<li class="nav-item ms-5">
        					<button class="btn btn-outline-success" @click="$router.push('/login')">Logovanje</button>
      					</li>  
    				</ul>
  				</div>
			</nav>
		</div>
		
	`
	,
	methods:{

	}
	,
	mounted(){		
		
	}
})
