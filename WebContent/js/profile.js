Vue.component("Profile",{
	data(){
        return{
            loggedUser: null,
        }
    },
	template:
	`	
		<div class="vh-100">
			<navbar></navbar>
			<div class="d-flex flex-column flex-shrink-0 p-3 bg-light flex-grow-1 overflow-auto" style="width: 280px; height: calc(100vh - 56px);" >
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="#" class="nav-link active">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          Lični podaci
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-dark">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
          Treninzi
        </a>
      </li>
    </ul>
  </div>
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
