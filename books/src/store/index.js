import Vuex from 'vuex'
import Vue from 'vue'

import base from '@/store/modules/base'
import user from '@/store/modules/user'
import login from '@/store/modules/login'
import online from '@/store/modules/online'
import menu from '@/store/modules/menu'
import role from '@/store/modules/role'
import relations from '@/store/modules/relations'
import hot from '@/store/modules/hot'
import dept from '@/store/modules/dept'

Vue.use(Vuex)
const stroe = new Vuex.Store({
  modules: {
    base,
    login,
    dept,
    user,
    online,
    menu,
    hot,
    role,
    relations
  }
})
export default stroe
