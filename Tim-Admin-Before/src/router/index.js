import Vue from 'vue'
import Router from 'vue-router'
import index from '../components/index'
import login from '../components/login'
import register from '../components/register.vue'
import userList from '../components/userList'
import welcome from '../components/welcome'
import sale from '../components/sale.vue'
import excel from '../components/excel.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/index',
      name: 'index',
      component: index,
      redirect: '/welcome',
      children: [
        {
          path: '/welcome',
          name: 'welcome',
          component: welcome
        },
        {
          path: '/userList',
          name: 'userList',
          component: userList
        },
        {
          path: '/sale',
          name: 'sale',
          component: sale
        },
        {
          path: '/excel',
          name: 'excel',
          component: excel
        }
      ]
    }
  ]
})
