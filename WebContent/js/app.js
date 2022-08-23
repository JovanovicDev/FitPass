const Welcome = {template: '<welcome></welcome>'}
const Login = {template: '<login></login>'}
const Registration = {template: '<registration></registration>'}
const Navbar = {template: '<navbar-guest></navbar-guest>'}

const router = new VueRouter({
	mode:'hash',
	routes:[
		{path:'/', component:Welcome},
		{path:'/login', name:'login', component:Login},
		{path:'/registration', component:Registration}
	]
});

var app = new Vue({
	router,
	el: '#welcome'
});
