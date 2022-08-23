Vue.component("navbar-guest",{
	data(){
        return{
			showSearchNavbar:false
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
       						<a class="nav-link" href="#">PH1</a>
     					</li>    
      					<li class="nav-item mx-2">
        					<a class="nav-link" href="#">PH2</a>
      					</li>   
      					<li class="nav-item mx-2">
        					<a class="nav-link" href="#">PH3</a>
      					</li> 
      					<li class="nav-item ms-5">
        					<button class="btn btn-outline-success" @click="$router.push('login')">Logovanje</button>
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
