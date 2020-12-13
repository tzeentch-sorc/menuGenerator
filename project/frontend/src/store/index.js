import Vue from 'vue';
import Vuex from 'vuex';

import { auth } from './auth.module';
import {profileModule} from "@/store/profile.module";
import {currentMenuModule} from "@/store/menu.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    profileModule,
    currentMenuModule
  }
});
