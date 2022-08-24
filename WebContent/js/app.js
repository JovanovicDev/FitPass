const Welcome = {template: '<welcome></welcome>'}
const Login = {template: '<login></login>'}
const Registration = {template: '<registration></registration>'}
const NavbarGuest = {template: '<navbar-guest></navbar-guest>'}
const Navbar = {template: '<navbar></navbar>'}
const Home = {template: '<home></home>'}
const Profile = {template: '<profile></profile>'}

const router = new VueRouter({
	mode:'hash',
	routes:[
		{path:'/', component:Welcome},
		{path:'/login', name:'login', component:Login},
		{path:'/registration', component:Registration},
		{path:'/home', component:Home},
		{path:'/profile', component:Profile}
	]
});

var app = new Vue({
	router,
	el: '#welcome'
});
