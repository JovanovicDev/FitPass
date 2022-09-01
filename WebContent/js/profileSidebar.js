Vue.component("profile-sidebar",{
	data(){
        return{
			loggedUser: {},
        }
    },
	template:
	`	
		<div class="d-flex p-3 bg-light overflow-auto" style="width: 280px; height: calc(100vh - 56px);" >
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item mx-2 my-1">
    		<button class="btn btn-outline-success" @click="$router.push('/profile')">Lični podaci</button>
      </li>
      <li class="nav-item mx-2 my-1" v-if="this.loggedUser.uloga == 'MENADZER'">
       	    <button class="btn btn-outline-success" @click="$router.push('/sport-facility')">Sportski objekat</button>
      </li>
      <li class="nav-item mx-2 my-1" v-if="this.loggedUser.uloga == 'KUPAC'">
       	    <button class="btn btn-outline-success" @click="$router.push('/')">Treninzi</button>
      </li>  
    </ul>
  </div>
		
	`
	,
	methods:{

	}
	,
	mounted(){		
		this.loggedUser = JSON.parse(window.localStorage.getItem('loggedUser'));
	}
})
