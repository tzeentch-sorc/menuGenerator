<template>
  <div style="margin-top: 1%">
    <div v-if="currentUser">
      <md-app>
        <md-app-drawer md-permanent="full">
          <md-toolbar class="md-transparent" md-elevation="0">
            Страницы
          </md-toolbar>

          <md-list>
            <md-list-item @click="setState(0)">
              <md-icon>recent_actors</md-icon>
              <span class="md-list-item-text">Параметры</span>
            </md-list-item>

            <md-list-item @click="setState(1)">
              <md-icon>rule</md-icon>
              <span class="md-list-item-text">Предпочтения</span>
            </md-list-item>

            <md-list-item @click="setState(2)">
              <md-icon>history</md-icon>
              <span class="md-list-item-text">Ваши меню</span>
            </md-list-item>
          </md-list>
        </md-app-drawer>
        <md-app-content>
          <ParamTab v-if="curState === 0"/>
          <Preferences v-if="curState === 1"/>
          <SavedMenuTab v-if="curState === 2"/>
        </md-app-content>
      </md-app>
    </div>
    <back-to-top text="Back to top" visibleoffset="500" bottom="5%" right="15%">
      <md-icon>arrow_upward</md-icon>
    </back-to-top>
  </div>
</template>

<script>
import ParamTab from "@/components/ParamTab";
import SavedMenuTab from "@/components/SavedMenuTab";
import Preferences from "@/components/Prefernces";
export default {
  name: 'Profile',
  components: {Preferences, SavedMenuTab, ParamTab},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile(){
      return this.$store.state.profileModule.profile;
    }
  },
  data(){
    return {
      noDetails: true,
      curState: 0
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
  methods: {
    setState(state){
      this.curState = state;
    },
    getPr(){
      this.$store.dispatch('profileModule/getProfile', this.currentUser.id);
    }
  },
  beforeMount() {
    this.getPr();
  }
};
</script>
<style lang="scss" scoped>

.md-app-drawer{
  max-width: 20%;
}
</style>